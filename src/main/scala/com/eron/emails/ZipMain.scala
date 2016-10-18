package com.eron.emails

import java.io.File
import java.util.logging.Logger

object ZipMain {
  val LOGGER: Logger = Logger.getLogger("ZipMain Testing")
  def main(args: Array[String]) {
    
    if(args.length != 2) {
       println("ZipMain: source_directory_containing_zip_files_from_eron output_directory")
       System.exit(-1)     
    }
    val utils = new ZipUtils
    
    LOGGER.info("------- Started --------")
    utils.zipFileDir(new File(args(0)), new File(args(1)))
    LOGGER.info(" --- Finished unzipping files. Please Checkout the destination folder " + args(1) + " for files generated -------")
    
  }
}