package com.eron.emails

import scala.collection.immutable.ListMap
import java.io.File
import java.util.logging.Level
import java.util.logging.Logger


object RecipientsCountMain {
  val LOGGER: Logger = Logger.getLogger("RecipientsCountMain Testing")
  def main(args: Array[String]) {
    LOGGER.setLevel(Level.INFO)
    val wc = new RecipientsCount

    var mapResult = Map.empty[String, Float] 
    
    if(args.length != 1) {
       LOGGER.info("Error: Usage RecipientsCountMain: full_path_output_dir")
       System.exit(-1)
    }
    val files = ListFIlesInDirectoryAndSubdirectory.recursivelyListFilesInDir(new File(args(0))).filter { x => x.getName.endsWith("xml") }
    
    for (file <- files) {
      if(!file.isDirectory() && file.exists())
        mapResult = wc.recipientsEmails(file, mapResult);
      
      LOGGER.info(s"----- Finished processung file: $file --------")
    }
    val sortedListMap = ListMap(mapResult.toSeq.sortWith(_._2 > _._2):_*).take(100)

    for(key <- sortedListMap.keys) {
      val count = sortedListMap.get(key).get
      println(s"key = $key,  counts = $count")
    }
  }    
    
}
