package com.haiboyan.filesearch

import java.io.File
import java.nio.file.Files.readAllLines

class FilterChecker(filter: String) {
  val filterAsRegex = filter.r

  def matches(ioObject : IOObject) = {
    ioObject match {
      case f: FileObject => f.name contains filter
      case _ => false
    }
  }

  def findMatchedFiles(fileObjects: List[IOObject]) = fileObjects.filter(f => matches(f))
    //    for (fileObject <- fileObjects if (matches(fileObject.name)))
    //        yield fileObject

  def matchesFileContent(file: File) = {
    import scala.collection.JavaConverters._
    readAllLines(file.toPath).asScala exists(p => p.contains(filter))
  }

  def findMatchedContentCount(file: File): Int = {
    def getFilterMatchCount(content: String) =
      (filterAsRegex findAllIn content).length

    import scala.collection.JavaConverters._
    readAllLines(file.toPath).asScala.foldLeft(0) ((accumulator, line) => accumulator + getFilterMatchCount(line))
  }
}


object FilterChecker {
  def apply(filter: String) = new FilterChecker(filter)
}