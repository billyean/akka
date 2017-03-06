import java.util.concurrent.TimeUnit

import akka.actor.SupervisorStrategy.{Escalate, Restart, Resume, Stop}
import akka.actor.{Actor, ActorRef, ActorSystem, OneForOneStrategy, Props}

import scala.concurrent.duration.Duration

/**
  * Created by hyan on 12/30/16.
  */
class Aphrodite extends Actor {
  import Aphrodite._

  override def preStart() = {
    println("Aphrodite preStart hook....")
  }

  override  def preRestart(reason: Throwable, message: Option[Any]) = {
    println("Aphrodite preRestart hook...")
    super.preRestart(reason, message)
  }

  override  def postRestart(reason: Throwable) = {
    println("Aphrodite postRestart hook...")
    super.postRestart(reason)
  }
  override def postStop() = {
    println("Aphrodite postStop....")
  }
  override def receive: Receive = {
    case "Resume" =>
      throw ResumeException
    case "Stop" =>
      throw StopException
    case "Restart" =>
      throw RestartException
    case _ =>
      throw new Exception
  }
}

object Aphrodite {
  case object ResumeException extends Exception
  case object StopException extends Exception
  case object RestartException extends Exception
}

class Hera extends Actor {
  import Aphrodite._

  var childRef : ActorRef = _

  override val supervisorStrategy =
    OneForOneStrategy(maxNrOfRetries = 10, withinTimeRange = Duration(1, TimeUnit.SECONDS)) {
      case ResumeException => Resume
      case StopException => Stop
      case RestartException => Restart
      case _:Exception => Escalate
    }

  override def preStart() = {
    childRef = context.actorOf(Props[Aphrodite])
    Thread.sleep(1000)
  }
  override def receive: Receive = {
    case msg =>
      println(s"Hera received ${msg}")
      childRef ! msg
      Thread.sleep(1000)
  }
}

object Supervision extends App {
  val system = ActorSystem("supervision")

  val hera = system.actorOf(Props[Hera])

  hera ! "Restart"
  Thread.sleep(1000)
  println()

  system.terminate()
}
