package com.haiboyan.panda

/**
  * Created by hyan on 3/9/17.
  */
object Test extends App{
  var s = Set(1,2,3,4)

  s = s + 5

  //s.foreach(println)

  s = s - 3

  s = s - 6

  s.foreach(println)

}
