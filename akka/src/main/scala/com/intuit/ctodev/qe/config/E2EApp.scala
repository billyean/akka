package com.intuit.ctodev.qe.config

import akka.actor.{ActorSystem, Props}
import py4j.GatewayServer

import scala.collection.mutable.ListBuffer

/**
  * Created by hyan on 3/9/17.
  */
object E2EApp extends App{
  val gatewayServer = new GatewayServer(this)

  val system = ActorSystem("E2EApp")

  val printMessageActor = system.actorOf(Props[PrintMessage])

  gatewayServer.start()
}

class E2EApp {
  var listeners = Set[MessageListener]()

  def addListener(listener: MessageListener): Unit = {
    listeners = listeners + listener
  }

  def removeListener (listener: MessageListener): Unit = {
    listeners = listeners - listener
  }

  def notifyAllListeners[T <: Any](message: T) = {
    listeners.foreach(e => e.notify(message))
  }
}
