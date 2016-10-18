package com.eron.emails

import java.util.logging.Level
import java.util.logging.Logger
import scala.collection.immutable.ListMap
import java.io.File


class RecipientsCount {
  
    def recipientsEmails(inputFile: File, mapRes: Map[String, Float]) = {
        Logger.getLogger("Recipient Counts Solution").setLevel(Level.INFO)   
        var mapResult = mapRes
        try {
        println("inputFile: " + inputFile.getAbsolutePath() + " ---" + inputFile.getName)
        val loadnode = xml.XML.loadFile(inputFile)
    
        // Performing intial XPath
        val  r1 = loadnode \\ "Tags" \ "Tag"
    
        val myresult = for(i <- 0 to r1.length-1) {
             
        if((r1(i) \\ "Tag" \ "@TagName").text == "#To") {
           val toTag = (r1(i) \\ "Tag" \ "@TagValue").text
           // println("I="  + i + " XML = " + toTag)
           val array = toTag.toLowerCase.split(",").toList               
          
           val res = array.groupBy((word: String) => word).mapValues(_.length) 
           
           for(key <- res.keys) {
             if(mapResult.contains(key)) {
               val x = mapResult.get(key).get + res.get(key).get
               mapResult = mapResult + (key -> x)
             } else {
               mapResult = mapResult + (key -> res.get(key).get)
             }
           }
         } 
         
         if((r1(i) \\ "Tag" \ "@TagName").text == "#CC") {
           val toTag = (r1(i) \\ "Tag" \ "@TagValue").text
           // println("I="  + i + " XML = " + toTag)
           val array = toTag.toLowerCase.split(",").toList               
          
           val res = array.groupBy((word: String) => word).mapValues(_.length)
           
           for(key <- res.keys) {
             if(mapResult.contains(key)) {
               val x = mapResult.get(key).get + (res.get(key).get)/2F
               mapResult = mapResult + (key -> x)
             } else {
               mapResult = mapResult + (key -> (res.get(key).get)/2F)
             }
           }
         }            
      } 
    }catch {
         case ex: Exception => println("SAXParser Error while processing " + inputFile.getAbsolutePath())
      }
      
      
      mapResult

  }
    
}