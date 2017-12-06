package com.haiboyan.filesearch

import java.io.File
import java.nio.file.Files.list
import java.nio.file.Paths.get

class Matcher(filter: String, root: String) {
  val rootIOObject = FileConverter.convertToIOObject(new File(root))

  def execute() = {
    val matchedFiles = rootIOObject match  {
      case file : FileObject if FilterChecker(filter).matches(file) => List(file)
      case directory: DirectoryObject => ???
      case _ => List()
    }
  }
}
