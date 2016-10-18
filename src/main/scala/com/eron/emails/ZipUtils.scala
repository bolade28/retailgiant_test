package com.eron.emails

import java.io.InputStream
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.zip.ZipInputStream
import java.util.zip.ZipEntry
import java.io.File
import java.util.Date

class ZipUtils {
    val ARCHIVE_EXTENSION = "zip"
    
    def zipFileDir(sourceDir: File, outputDir: File) {
      if(sourceDir.exists() && sourceDir.isDirectory()) {
			  val files = sourceDir.listFiles.filter( x => x.getName.endsWith(ARCHIVE_EXTENSION))
			  
			  for(file <- files) {
			    var fullPathOutputDir = new File(outputDir + "/" + file.getName.substring(0, file.getName.indexOf(".")))
			    
			    if(!fullPathOutputDir.exists())
			      fullPathOutputDir.mkdirs()
			      			    
			    zipDecompressor(file, fullPathOutputDir)
			  }        
      }      
    }
    
    def zipDecompressor(zipFile: File, outputDir: File) 
    {
        // create a buffer to improve copy performance later.
        val buffer = new Array[Byte](1024)
        
        // open the zip file stream
        val theFile = new FileInputStream(zipFile);
        val zipInputStreamstream = new ZipInputStream(theFile);

        try
        {

            // now iterate through each item in the stream. The get next
            // entry call will return a ZipEntry for each file in the
            // stream
            var entry = zipInputStreamstream.getNextEntry()
            while(entry != null )
            {
                  
                // Once we get the entry from the stream, the stream is positioned to read the raw data, 
                // and it will keep reading until the end.
                var outpath = outputDir + "/" + entry.getName();
                
                var entryFile = new File(outputDir, entry.getName());
                
                if (entryFile.getParentFile() != null && !entryFile.getParentFile().exists()) {
                    entryFile.getParentFile().mkdirs();
                }
                 
                var output: FileOutputStream = null;
                try
                {
                    output = new FileOutputStream(outpath);
                    var len = zipInputStreamstream.read(buffer)
                    while (len > 0)
                    {
                        output.write(buffer, 0, len)
                        len = zipInputStreamstream.read(buffer)
                    }
                }
                finally
                {
                    // Close the output file
                    if(output!=null) 
                      output.close();
                }
                entry = zipInputStreamstream.getNextEntry()
            }
        }
        finally
        {
            //Close the archive file.
            zipInputStreamstream.close();
        }
    }
}