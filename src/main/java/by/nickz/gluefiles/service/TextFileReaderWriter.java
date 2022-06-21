package by.nickz.gluefiles.service;

public interface TextFileReaderWriter {

  /**
   *
   * @param sourceFolderPath root folder containing text files and folders
   * @param outputFolderPath folder where result file will be placed
   * @return result file path
   */
  String glueFiles(String sourceFolderPath, String outputFolderPath);
}
