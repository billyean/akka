package com.haiboyan.filesearch

class FilterChecker(filter: String) {
  def matches(ioObject : IOObject) = {
    ioObject match {
      case f: FileObject => f.name matches filter
      case _ => false
    }
  }

  def findMatchedFiles(fileObjects: List[IOObject]) = fileObjects.filter(f => matches(f))
    //    for (fileObject <- fileObjects if (matches(fileObject.name)))
    //        yield fileObject
}


object FilterChecker {
  def apply(filter: String) = new FilterChecker(filter)
}