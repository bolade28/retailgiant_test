package com.eron.emails

import java.io.File

object ListFIlesInDirectoryAndSubdirectory {
    def recursivelyListFilesInDir(dir: File): Array[File] = {
      val these = dir.listFiles
      these ++ these.filter(_.isDirectory).flatMap(recursivelyListFilesInDir)
  } 
}