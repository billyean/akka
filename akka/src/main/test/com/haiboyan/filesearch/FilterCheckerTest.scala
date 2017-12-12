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

  "FilterChecker passed a file with content that matches the filter 3 times " should
    "return a 3" in {
    val matched = new FilterChecker("Tristan").findMatchedContentCount(new File("./testfiles/names.data"))
    assert(matched == 3)
  }

    "FilterChecker passed a file with content that does not match the filter " should
      "return a 0" in {
      val matched = new FilterChecker("tristan").findMatchedContentCount(new File("./testfiles/readme.txt"))
      assert(matched == 0)
    }
}
