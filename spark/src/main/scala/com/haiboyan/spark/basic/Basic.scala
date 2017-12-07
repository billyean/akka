package com.haiboyan.spark.basic

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf

object Basic {
  def main(args: Array[String]) =  {
    val conf = new SparkConf().setAppName("Basic Demo Application")
    val sc = new SparkContext(conf)
    val sparkHome = sys.env("SPARK_HOME")
    val employees = sc.textFile(sparkHome + "/examples/src/main/resources/people.txt")
    employees.map(e => e.split(",")).foreach(r => println(r(0)))
    sc.stop()
  }
}
