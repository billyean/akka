package com.intuit.ctodev.qe.config

import java.io.{BufferedWriter, File, FileWriter}

import scala.io.Source
import java.nio.file._
import java.nio.file.Files._
import java.nio.file.Paths._
import java.nio.file.StandardOpenOption._

import scala.collection.mutable

/**
  * Created by hyan on 3/7/17.
  */
object FilterIds {
  def filter(inputFile:String, outputFile:String, badIdFilePath:String) = {
    val badLines = Source.fromFile(badIdFilePath).getLines()
    val lines = Source.fromFile(inputFile).getLines().filter(!badLines.contains(_)).map(_ + File.separator)
                  .flatMap(_.getBytes("utf8")).toArray
    Files.write(Paths.get(outputFile), lines)
  }

  def main(args: Array[String]): Unit = {
    val ipFile = "/Jmeter/12212016/ip"
    val smartIdFile = "/Jmeter/12212016/smartid"
    val ividFile = "/Jmeter/12212016/ivid"

    //bad id file path
    val bad_ids_filePath = "/Jmeter/12212016/filter_ids"

    val filtered_ip_file = "/Jmeter/12212016/ip_ftr"
    val filtered_smartid_file = "/Jmeter/12212016/smartid_ftr"
    val filtered_ivid_file = "/Jmeter/12212016/ivid_ftr"



    filter(ipFile,filtered_ip_file,bad_ids_filePath)
    filter(smartIdFile,filtered_smartid_file,bad_ids_filePath)
    filter(ividFile,filtered_ivid_file,bad_ids_filePath)

    var capital = Map("US" -> "Washington", "France" -> "Paris")
    capital += ("China" -> "Beijing")
    val newMap = capital.map{case (k, v) => (k -> List())}
  }
}
