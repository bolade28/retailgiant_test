package com.eron.emails

import java.io.File
import java.util.logging.Level
import java.util.logging.Logger

object WordCountMain {
  val LOGGER: Logger = Logger.getLogger("WordCountMain Testing")
  def main(args: Array[String]) {
    Logger.getLogger("Word Counts Solution").setLevel(Level.INFO)   

    val wc = new WordCount
    var count = 0
    var total_body = 0L
    var total_subject = 0L
    var average = 0L
    
    val files = ListFIlesInDirectoryAndSubdirectory.recursivelyListFilesInDir(new File(args(0))).filter { x => x.getName.endsWith("txt") }
    
    for (file <- files) {
      val result = wc.counter(file);
      count += 1
      total_body += result._1
      total_subject += result._2
      
      LOGGER.info(s"** FIleNumber: $count File  $file.getAbsolutePath *** Words =  $result ** TotalBody = $total_body **total subject $total_subject")         
    }
    val total = total_body + total_subject
    average = total/count
    LOGGER.info(s"**** Number of Emails: $count ***** Total = $total Average : $average")
  }
  
}