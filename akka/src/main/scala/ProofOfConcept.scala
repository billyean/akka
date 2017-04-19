import ProofOfConcept.{Poll, Sleep}
import akka.actor.{Actor, ActorSystem, Props}
import java.util.Random
import java.util.concurrent.{CountDownLatch, Executor}

import scala.concurrent.{ExecutionContext, ExecutionContextExecutor}
import scala.concurrent.duration._
import scala.language.implicitConversions
import scala.concurrent.ExecutionContext.Implicits.global


/**
  * Created by hyan on 4/13/17.
  */

object ProofOfConcept {
  val countDown  = new CountDownLatch(1000000)

  case class Poll(message: String, loop: Int, system: ActorSystem)

  case class Timeout()

  case class Sleep(message: String, loop: Int)
}


class ProofOfConcept extends Actor {
  override implicit def receive: Receive = {
    case  Poll(message, loop, system) => {
      if (new Random().nextBoolean || loop == 0) {
        ProofOfConcept.countDown.countDown()
      } else {
        system.scheduler.schedule(
          1000 milliseconds,
          3000 milliseconds,
          self,
          Poll(message, loop - 1, system))
      }
    }
  }
}

object Main extends App {
  val system = ActorSystem("proof-of-concept")

  var v = 0

  val start = System.currentTimeMillis()
  for (v <- 1 to 1000000)  {
    val proof = system.actorOf(Props[ProofOfConcept])

    val random = new Random()

    val message = String.valueOf(random.nextInt())

    proof ! Poll(message, 1, system)
  }


  ProofOfConcept.countDown.await()

  println(System.currentTimeMillis() - start)
  system.terminate()
}
