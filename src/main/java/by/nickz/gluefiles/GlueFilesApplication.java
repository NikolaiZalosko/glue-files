package by.nickz.gluefiles;

import by.nickz.gluefiles.service.TextFileReaderWriter;
import by.nickz.gluefiles.service.TextFileReaderWriterImpl;

public class GlueFilesApplication {

  private static final TextFileReaderWriter readerWriter = new TextFileReaderWriterImpl();

  public static void main(String[] args) {
    String sourceFolderPath = args[0];
    String outputFolderPath = args[1];

    String resultFileName = readerWriter.glueFiles(sourceFolderPath, outputFolderPath);

    System.out.println("Result file: " + resultFileName);
  }
}
