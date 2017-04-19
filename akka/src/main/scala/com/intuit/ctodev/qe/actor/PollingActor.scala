package com.intuit.ctodev.qe.actor

/**
  * Created by hyan on 3/23/17.
  */
class PollingActor {
  case class Sleep(time: Long)

  case class SleepDone()
}
