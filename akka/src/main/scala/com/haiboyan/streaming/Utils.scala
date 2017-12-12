package com.haiboyan.streaming

import org.apache.spark.rdd.RDD

object Utils {
  def drop[A](rdd: RDD[A], n: Int) = {
    rdd.mapPartitionsWithIndex((index, iter) => if (index == 0) iter.drop(1) else iter)
  }
}
