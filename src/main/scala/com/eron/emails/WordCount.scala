package com.eron.emails

import java.io.FileInputStream
import javax.mail.internet.MimeMessage
import java.util.Arrays
import java.util.Optional
import javax.mail.Address
import javax.mail.Session
import java.io.File
import javax.mail.Message

class WordCount {
  // Function returning tuple representation of total for body and
  // total for subjects.
  def counter(emlFile: File): (Long, Long) = {
    	    var props = System.getProperties();
	        props.put("mail.host", "smtp.dummydomain.com");
	        props.put("mail.transport.protocol", "smtp");
     
	        val mailSession = Session.getDefaultInstance(props, null);
	        val source = new FileInputStream(emlFile);
	        val message = new MimeMessage(mailSession, source);
	        
	        val words = message.getContent().toString.toLowerCase.split("\\W+");
	        
	        var subject = Some(message.getSubject)
	        
	        val counter: Long = words.length
	        
	        if(subject.get == null)
	          (counter, 0)
	        else
	        {
	          val subject2 = subject.get.split("\\W+")
	          (counter, subject2.length)
	        }
	          
  }
}



