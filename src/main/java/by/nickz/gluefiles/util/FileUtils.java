package by.nickz.gluefiles.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.function.BiPredicate;

public final class FileUtils {

  private FileUtils() {}

  public static BiPredicate<Path, BasicFileAttributes> isTextFilePredicate() {
    return (path, attributes) -> {
      try {
        if (attributes.isRegularFile() && isTextFile(path.toFile())) {
          return true;
        }
      } catch (IOException e) {
        e.printStackTrace();
      }

      return false;
    };
  }

  private static boolean isTextFile(File f) throws IOException {
    String type = Files.probeContentType(f.toPath());
    //type isn't text
    if (type == null) {
      //type couldn't be determined, assume binary
      return false;
    } else {
      return type.startsWith("text");
    }
  }
}
