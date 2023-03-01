package ru.spbu.apcyb.svp.tasks.task5;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

/**
 * Задание 5.
 */
public class Task5 {


  protected HashMap<String, Integer> readFile(String fileName) throws IOException {
    HashMap<String, Integer> hashMap = new HashMap<>();
    try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

      stream.flatMap(line ->
              Stream.of(line.toLowerCase().replaceAll("\\p{Punct}", "")
                  .split("\\s+")))
          .forEach(word -> {
            if (!word.isEmpty()) {
              hashMap.merge(word, 1, (oldValue, newValue) -> oldValue + 1);
            }
          });
      return hashMap;

    } catch (IOException e) {
      throw new IOException("cant read file");
    }

  }


  protected void writeToFile(HashMap<String, Integer> input, String outDir) throws IOException {

    File outFile = new File(outDir);
    try (PrintWriter printWriter = new PrintWriter(outFile)) {
      input.forEach((key, value) -> printWriter.write(key + " " + value.toString() + "\n"));
    } catch (IOException e) {
      throw new IOException("cant write into file");
    }
  }


  protected void mainLogic(String inDir, String outDir, String prefix) throws IOException {
    HashMap<String, Integer> hashMap = readFile(inDir);
    writeToFile(hashMap, outDir);
    hashMap.forEach((key, value) -> CompletableFuture.runAsync(new MyTask(value, key, prefix))
    );
  }


  public static void main(String[] args) throws IOException {
    Task5 task5 = new Task5();
    task5.mainLogic("text.txt", "outfile.txt", "src/main/resources/task5_files");
  }

}
