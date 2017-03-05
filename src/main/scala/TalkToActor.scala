import java.util.concurrent.TimeUnit

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.actor.Actor.Receive
import akka.pattern.ask
import akka.util.Timeout

/**
  * Created by hyan on 12/29/16.
  */

case class User(username: String, email: String)

object Recorder {
  sealed trait RecorderMsg
  case class NewUser(user: User) extends RecorderMsg

  def props(checker: ActorRef, storage: ActorRef) = Props(new Recorder(checker, storage))
}

object Checker {
  sealed trait CheckerMsg
  case class CheckUser(user: User) extends CheckerMsg

  sealed trait CheckerResponse
  case class BlackUser(user: User) extends CheckerResponse
  case class WhiteUser(user: User) extends CheckerResponse

}

object Storage {
  sealed trait StorageMsg

  case class AddUser(user: User) extends  StorageMsg
}

class Storage extends Actor {
  var users = List.empty[User]

  override def receive = {
    case Storage.AddUser(user) =>
      println(s"Storage: $user added")
      users = user :: users
  }
}

class Checker extends Actor {
  val blackList = List(
    User("Tristan", "tristan.yim@gmail.com")
  )
  override def receive: Receive = {
    case Checker.CheckUser(user) if blackList.contains(user) =>
        println(s"Checker: $user is in the blacklist")
        sender() ! Checker.BlackUser(user)
    case Checker.CheckUser(user) =>
      println(s"Checker: $user is not in the blacklist")
        sender() ! Checker.WhiteUser(user)
  }
}

class Recorder(checker: ActorRef, storage: ActorRef) extends Actor {
  import scala.concurrent.ExecutionContext.Implicits.global
  implicit val timeout = Timeout(5, TimeUnit.SECONDS)

  override def receive: Receive = {
    case Recorder.NewUser(user) =>
      ask(checker, Checker.CheckUser(user)).foreach(
        response => response match {
          case Checker.WhiteUser(user) =>
            storage ! Storage.AddUser(user)
          case Checker.BlackUser(user) =>
            println(s"Recorder: $user in the blacklist")
        }
      )
  }
}

object TalkToActor extends App {
  val system = ActorSystem("talk-to-actor")

  val checker = system.actorOf(Props[Checker])

  val storage = system.actorOf(Props[Storage])

  val recorder = system.actorOf(Recorder.props(checker, storage))

  recorder ! Recorder.NewUser(User("Zachary", "zachary.yan@gmail.com"))

  Thread.sleep(1000)

  system.terminate()
}

