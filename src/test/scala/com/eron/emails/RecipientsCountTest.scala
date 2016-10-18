package com.eron.emails

import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.File
import org.junit.Assert._
import scala.collection.immutable.ListMap

class RecipientsCountTest {
  var recipientsCount: RecipientsCount = null
  
  @Before
  def setUp() {
    recipientsCount = new RecipientsCount()
  }

  @Test
  def testRecipientsEmailsCount() {
    var mapResult = Map.empty[String, Float] 
    val testFile = getClass.getResource("/").getFile()
    
    val files = ListFIlesInDirectoryAndSubdirectory.recursivelyListFilesInDir(new File(testFile)).filter { x => x.getName.endsWith("xml") }
    
    for (file <- files) {
      if(!file.isDirectory() && file.exists())
        mapResult = recipientsCount.recipientsEmails(file, mapResult);
      
      println(s"------------- Finished processung file: $file --------------")
    }
    val sortedListMap = ListMap(mapResult.toSeq.sortWith(_._2 > _._2):_*).take(20)

    for(key <- sortedListMap.keys) {
      val count = sortedListMap.get(key).get
      println(s"key = $key,  counts = $count")
    }
    assertTrue(sortedListMap.take(1).get("harry.arora@enron.com").get == 2.0)
  }  
 
}