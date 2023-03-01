package ru.spbu.apcyb.svp.tasks.task5;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * класс для создания и записи в CompletableFuture.
 */
public class MyTask implements Runnable {

  private final String word;
  private final Integer num;
  private final String prefix;

  /**
   * конструктор класса.
   *
   * @param num    количество слов, которое надо записать в файл.
   * @param word   слово, для которого создается файл.
   * @param prefix путь до дериктории в которой будут создаваться файлы со словами.
   */
  public MyTask(Integer num, String word, String prefix) {
    this.num = num;
    this.word = word;
    this.prefix = prefix + "/";
  }


  @Override
  public void run() {
    File out = new File(prefix + this.word);
    Logger logger = Logger.getLogger(MyTask.class.getName());

    if (!word.isEmpty() && !word.equals(" ")) {
      try (FileWriter fileWriter = new FileWriter(out)) {
        for (int i = 0; i < this.num; i++) {
          fileWriter.write(word + " ");
        }
      } catch (IOException e) {
        logger.log(Level.SEVERE, "thread error, cant create file");
      }

    }
  }
}
