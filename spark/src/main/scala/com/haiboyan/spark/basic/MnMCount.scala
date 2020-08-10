package com.haiboyan.spark.basic

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object MnMCount extends App {
  if (args.length != 1) {
    println("Usage: MMCount <file>")
    System.exit(-1)
  }
  val spark = SparkSession.builder().appName("CountMM").getOrCreate()

  val mm_file = args(0)
  val mnm_df = spark.read.format("csv")
    .option("header", true)
    .option("inferSchema", true)
    .load(mm_file)

  val count_mnm_df = mnm_df.select("State", "Color", "Count")
    .groupBy("State", "Color")
    .agg(count("Count").alias("Total"))
    .orderBy(col("Total").desc)

  count_mnm_df.show(60, false)
  println()

  val ca_count_mnm_df = mnm_df.select("State", "Color", "Count")
    .where("State = 'CA'")
    .groupBy("State", "Color")
    .agg(count("Count").alias("Total"))
    .orderBy(col("Total").desc)

  count_mnm_df.show(10, false)
  spark.stop()
}
