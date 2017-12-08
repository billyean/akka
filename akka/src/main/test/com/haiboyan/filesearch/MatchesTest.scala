package com.haiboyan.filesearch

import java.io.File

import org.scalatest.FlatSpec

class MatchesTest extends FlatSpec {
  "Matcher that is passed a file matching the filter " should
    "return a list with that file name" in {
    val matcher = new Matcher("fake", "fakePath")
    val results = matcher.execute()

    assert(results == List("fakePath"))
  }

  "Matcher using a directory containing one file matching the filter " should
    "return a list with that file name" in {
    val matcher = new Matcher("txt", new File(".//testfiles//").getCanonicalPath)
    val results = matcher.execute()
    assert(results == List("readme.txt"))
  }


  "Matcher with search sub folder using a directory containing one file matching the filter and one file in subfolder matching the filter " should
    "return a list with that two files name" in {
    val matcher = new Matcher("txt", new File(".//testfiles//").getCanonicalPath, true)
    val results = matcher.execute()
    assert(results == List("notes.txt", "readme.txt"))
  }

  "Matcher given a path that one file matches file filter and content filter " should
    "return a list with that file name" in {
    val matcher = new Matcher("data", new File(".//testfiles//").getCanonicalPath, true, Some("Tristan"))
    val results = matcher.execute()
    assert(results == List("names.data"))
  }


  "Matcher given a path that has no file matches file filter and content filter " should
    "return a list with that file name" in {
    val matcher = new Matcher("txt", new File(".//testfiles//").getCanonicalPath, true, Some("Tristan"))
    val results = matcher.execute()
    assert(results == List())
  }
}
