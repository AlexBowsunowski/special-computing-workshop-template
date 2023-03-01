package ru.spbu.apcyb.svp.tasks;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


/**
 * Задание 3.
 */
public class Task3 {

  protected void printFiles(File curRoot, FileWriter fileWriter) throws IOException {
    for (File curFile : curRoot.listFiles()) {
      if (curFile.isDirectory()) {
        printFiles(curFile, fileWriter);
      } else {
        fileWriter.write(curFile.getPath().concat("\n"));
      }
    }
  }

  private void mainLogic(String root, String output) throws IOException {
    File rootDir = new File(root);
    if (!rootDir.exists() || !rootDir.isDirectory()) {
      throw new IOException("directory doesn't exist ");
    }
    if (!rootDir.isDirectory()) {
      throw new IOException("input path isn't a directory");
    }
    File outputFile = new File(output);
    if (outputFile.isDirectory()) {
      throw new IOException("output file can't be a directory");
    }
    try (FileWriter fileWriter = new FileWriter(outputFile)) {
      this.printFiles(rootDir, fileWriter);
    } catch (IOException e) {
      throw new IOException("cant write into file");
    }
  }

  /**
   * Программа выводит содержимое дериктории в отдельный файл.
   *
   * @param args - путь до директории, содержимое которой необходимо вывести и путь до файла, в
   *             который осуществляется вывод.
   * @throws IOException ошибка в задании пути до дериктории.
   */

  public static void main(String[] args) throws IOException {
    Task3 task3 = new Task3();
    if (args.length != 2) {
      throw new IOException("need 2 parameters in command line");
    }
    task3.mainLogic(args[0], args[1]);

  }
}