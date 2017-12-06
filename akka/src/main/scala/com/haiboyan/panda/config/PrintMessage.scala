package com.haiboyan.panda.config

import akka.actor.Actor
import akka.actor.Actor.Receive

/**
  * Created by hyan on 3/9/17.
  */
class PrintMessage extends Actor with MessageListener {
  override def receive: Receive = {
    case message:String => println(message)
    case _ => println("Unknown type")
  }

  override def notify(source: Any): Unit = {
    self ! source
  }
}
