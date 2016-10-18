The program was written in mostly scala with a bit of Java included.It uses Java 1.8 and Scala version 2.10.5, Maven 3 and above. Please note the src/main/java folder is not in use. Left because
of some constraints due to eclipse IDE. The actual source code
is in src/main/scala.

Use Maven to import the project into your workspace. 

1) ZipMain: Ingestion of data to my hard drive. Execute the ZipMain scala 
   class. This can be done by either packaging it into a jar file or execute
   from within your IDE say, eclipse. Right click on the project and click 
   on Run as Scala Application.
   I have included two zip files in the root folder that were downloaded from Amazon which can be used for testing.
   Note: The output folder in step 1 is used as input for 2 and 3.

2) WordCountMain: This is the driver class for counting the number of average
   words in the emails. Uses very simple implementation of map reduce that is
   built into scala. The reduce phase is almost non existent. This program relies 
   on the txt files in the subfolder of the parent to compute the number of words
   in each email.

3) RecipientsCountMain: This is the driver class for finding the top 100 recipients
   of emails. Requires that you pass the location of the output directories where
   you have extracted the content of the Zip files in step 1. The program uses the
   XML file in the main directory to extracts the users that were sent emails. Please
   note that the program does NOT validate the email address extracted.
   
   
 Resource test files are located under src/test/resources. These are small XML and TXT 
 files.  
 For Logging I have implemented it using the Logger object.