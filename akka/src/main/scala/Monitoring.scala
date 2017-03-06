import akka.actor.Actor.Receive
import akka.actor.{Actor, ActorRef, ActorSystem, Props, Terminated}

/**
  * Created by hyan on 12/30/16.
  */
class Ares(athena: ActorRef) extends Actor {
  override def preStart() = {
    context.watch(athena)
  }

  override def postStop() = {
    println("Ares postStop....")
  }

  override def receive: Receive = {
    case Terminated =>
      context.stop(self)
  }
}

class Athena extends Actor {
  override def receive: Receive = {
    case msg =>
      println(s"Athena received ${msg}")
      context.stop(self)
  }
}

object Monitoring extends App {
  val system = ActorSystem("monitoring")

  val athena = system.actorOf(Props[Athena])

  val ares = system.actorOf(Props(new Ares(athena)))

  athena ! "Hi"
  Thread.sleep(1000)
  athena ! Terminated
  system.terminate()
}
