package com.haiboyan.panda.polling

import akka.actor.Actor
import com.haiboyan.panda.polling.SleepActor.SleepDone
import com.haiboyan.panda.polling.SleepActor.{Sleep, SleepDone}

/**
  * The purpose for this Actor is just sleeping and doing nothing, purpose for this class is prevent sleeping affect
  * main actor process then main thread can handle other message.
  * Sleep actor can be created in-flight and should be associate with a main actor. Which after sleep
  *
  * Created by hyan on 3/23/17.
  */
object SleepActor {
  case class Sleep(time: Long)
  case class SleepDone()
}

class SleepActor extends Actor {
  override def receive: Receive = {
    case Sleep(time) =>
      Thread.sleep(time)
      sender() ! SleepDone
    case _ => //Throw
  }
}
