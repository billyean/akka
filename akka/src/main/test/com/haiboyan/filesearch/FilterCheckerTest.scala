package com.haiboyan.filesearch

import java.io.File

import org.scalatest.FlatSpec

class FilterCheckerTest extends FlatSpec {
  "FilterChecker passed a list where one file matches the filter " should
    "return a list with that file" in {
    val matchingFile = FileObject(new File("match"))
    val listOfFiles = List(FileObject(new File("random")), matchingFile)
    val matchedFiles = FilterChecker("match").findMatchedFiles(listOfFiles)

    assert(matchedFiles == List(matchingFile))
  }

  "FilterChecker passed a list with a directory that matches the filter " should
    "not return the directory" in {
    val listOfFiles = List(FileObject(new File("random")), DirectoryObject(new File("match")))
    val matchedFiles = FilterChecker("match").findMatchedFiles(listOfFiles)

    assert(matchedFiles.length == 0)
  }
}
