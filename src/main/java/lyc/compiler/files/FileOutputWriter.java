package lyc.compiler.files;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class FileOutputWriter {

    private static final String OUTPUT_DIRECTORY  = "target/output";

    private FileOutputWriter(){}

    public static void writeOutput(String fileName, FileGenerator fileGenerator) {
        createOutputDirectory();
      try(FileWriter fileWriter = new FileWriter("%s/%s".formatted(OUTPUT_DIRECTORY, fileName))) {
          fileGenerator.generate(fileWriter);
          fileWriter.flush();
      } catch (IOException e) {
          System.err.println("Error trying to create file " + e.getMessage());
      }
    }

    private static void createOutputDirectory() {
        Path path = Paths.get(OUTPUT_DIRECTORY);
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            System.err.println("Error trying to create directory " + e.getMessage());
        }
    }

}
