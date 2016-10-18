package com.eron.emails

import java.io.File

import org.junit.Assert._
import org.junit.Before
import org.junit.Test

class WordCountTest {
  var wordCount: WordCount = null
  
  @Before
  def setUp() {
    wordCount = new WordCount()
  }
  
  @Test
  def testCounter() {
    var count = 0
    var total_body = 0L
    var total_subject = 0L
    var average = 0L
    val testFile = getClass.getResource("/").getFile()
    
    val files = ListFIlesInDirectoryAndSubdirectory.recursivelyListFilesInDir(new File(testFile)).filter { x => x.getName.endsWith("txt") }

    for (file <- files) {
      val result = wordCount.counter(file);
      count += 1
      total_body += result._1
      total_subject += result._2
      
      println(s"** FIleNumber: $count File  $file.getAbsolutePath *** Words =  $result ** TotalBody = $total_body **total subject $total_subject")         
    }
    val total = total_body + total_subject
    average = total/count
    println(s"**** Number of Emails: $count ***** Total = $total Average : $average")    
    
    
 
    assertEquals(186, average)
  }   
}