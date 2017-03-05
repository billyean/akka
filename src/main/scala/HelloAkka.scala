import akka.actor.{Actor, ActorSystem, Props}

/**
  * Created by hyan on 12/29/16.
  */
case class WhoToGreeter(who: String)

class Greeter extends Actor {
  def receive = {
    case WhoToGreeter(who) => println(s"Hello $who")
  }
}

object HelloAkka extends App {
  val system = ActorSystem("Hello-Akka")

  val greeter = system.actorOf(Props[Greeter])

  greeter ! WhoToGreeter("Akka")
}
