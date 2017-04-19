package com.intuit.ctodev.qe.actor

import akka.actor.Actor
import com.intuit.ctodev.qe.actor.HDFSPollingReader.{Poll, conf}
import com.intuit.ctodev.qe.polling.SleepActor.Sleep
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import java.io.{BufferedReader, InputStreamReader}

/**
  * This is a simple polling reader that poll all contents from HDFS Client with given path. After read
  * the content, HDFSPollingReader will check if given value is in HDFS file, it returns ValueFound message
  * to sender if the value found, otherwise it will sleep sometimes and keep polling again. This check will
  * be timed-out once the given time is reached. HDFSPollingReader will send a Timeout message to sender if
  * timeout.
  *
  * This code has to be run under the same HDFS client installed
  *
  * Created by Haibo Yan on 3/22/17.
  */
object HDFSPollingReader  {
  case class Poll(path: String, value: String, timeout: Long, timeStart: Long)

  case class ValueFound()

  case class Timeout()

  val conf = new Configuration()
  private val hdfsCoreSitePath = new Path("core-site.xml")
  private val hdfsHDFSSitePath = new Path("hdfs-site.xml")

  conf.addResource(hdfsCoreSitePath)
  conf.addResource(hdfsHDFSSitePath)
}

class HDFSPollingReader extends Actor {
  var path: String = ""

  var value: String = ""

  var timeout: Long = 0

  var timeStart: Long = 0

  override def receive: Receive = {
    case Sleep(time) => {
      Thread.sleep(1000L)
      self ! ""
    }
    case  Poll(path, value, timeout, timeStart) => {
      this.path = path
      this.value = value
      this.timeout = timeout
      this.timeStart = timeStart

      val fileSystem = FileSystem.get(conf)
      val input = new BufferedReader(new InputStreamReader(fileSystem.open(new Path(path))))
      if (input.lines().anyMatch(_.contains(value))) {
        println("Find the vale")
        sender() ! HDFSPollingReader.ValueFound
      }

      if (timeStart + timeout > System.currentTimeMillis) {
        sender() ! HDFSPollingReader.Timeout
      } else {
        self ! Sleep
      }
    }
  }
}
