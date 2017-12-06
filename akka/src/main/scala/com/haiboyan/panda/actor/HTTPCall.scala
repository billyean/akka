package com.haiboyan.panda.actor

import akka.actor.Actor

/**
  * Created by hyan on 3/22/17.
  */
object HTTPCall {
  trait HTTPCallMessage;

  case class SendHTTPPost(url:String, message: String, heads: Option[String]) extends HTTPCallMessage;

  case class SendHTTPDelete(url:String, key: String, heads: Option[String]) extends HTTPCallMessage;

  case class SendHTTPGet(url:String, key: String, heads: Option[String]) extends HTTPCallMessage;
}

class HTTPCall extends Actor {
  override def receive: Receive = {
    case receive => {

    }
    case _ =>{

    }
  }
}
