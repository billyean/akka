package com.haiboyan.filesearch

import java.io.File
import java.nio.file.Files

object FileConverter {
  def convertToIOObject(file: File) =
    file.isDirectory match {
      case true => DirectoryObject(file)
      case _ => FileObject(file)
    }
}
