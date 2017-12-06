package com.haiboyan.panda.config

/**
  * Created by hyan on 3/9/17.
  */
trait MessageListener {
  def notify(source: Any)
}
