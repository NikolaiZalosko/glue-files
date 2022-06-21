package by.nickz.gluefiles.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static by.nickz.gluefiles.util.FileUtils.isTextFilePredicate;

public class TextFileReaderWriterImpl implements TextFileReaderWriter {

  private static final String DATE_FORMAT = "yyyymmddHHmmss";

  @Override
  public String glueFiles(String sourceFolderPath, String outputFolderPath) {
    checkFolderValidity(sourceFolderPath);
    checkFolderValidity(outputFolderPath);

    Path resultFile = null;
    try {
      resultFile = createResultFile(outputFolderPath);

      List<Path> files = getTextFilesRecursively(sourceFolderPath);

      List<String> lines = new ArrayList<>();
      for (Path file : files) {
        lines.addAll(Files.readAllLines(file));
      }

      Files.write(resultFile, lines);
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }

    return resultFile.toString();
  }

  private Path createResultFile(String outputFolderPath) throws IOException {
    String timestamp = new SimpleDateFormat(DATE_FORMAT).format(new Date());
    String resultFileName = outputFolderPath + File.separator + "glued-file-" + timestamp + ".txt";
    Path resultFile = Paths.get(resultFileName);

    Files.createFile(resultFile);

    return resultFile;
  }

  private void checkFolderValidity(String folderPath) {
    File sourceFolder = new File(folderPath);
    if (!sourceFolder.exists()) {
      throw new IllegalArgumentException(folderPath + " doesn't exist.");
    }
    if (!sourceFolder.isDirectory()) {
      throw new IllegalArgumentException(folderPath + " is not a directory.");
    }
  }

  private List<Path> getTextFilesRecursively(String rootFolderPath) throws IOException {
    List<Path> files;
    try (Stream<Path> pathsStream = Files.find(Paths.get(rootFolderPath),
            Integer.MAX_VALUE,
            isTextFilePredicate())
        .sorted(Comparator.comparing(path -> path.getFileName().toString()))) {
      files = pathsStream.collect(Collectors.toList());
    }
    return files;
  }
}
