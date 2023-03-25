package ru.spbu.apcyb.svp.tasks.task4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Задание 4.
 */
public class Task4 {

  private AtomicInteger numOfThreads;
  private int[] buffer;

  Task4(AtomicInteger numOfThreads) {
    this.numOfThreads = numOfThreads;
    this.buffer = new int[numOfThreads.get()];
  }

  public int[] getBuffer() {
    return buffer;
  }

  protected void readToBuffer(BufferedReader bufferedReader) throws IOException {
    int count = 0;
    String line;
    while (count != 10 && (line = bufferedReader.readLine()) != null) {
      this.buffer[count] = Integer.parseInt(line);
      count++;
    }
    if (count != 10) {
      this.numOfThreads.set(count);
    }
  }

  protected void writeToFile(FileWriter fileWriter, Future<Double>[] outBuffer)
      throws IOException, ExecutionException, InterruptedException {

    for (int i = 0; i < this.numOfThreads.get(); i++) {
      fileWriter.write(outBuffer[i].get().toString().concat("\n"));
    }
  }

  protected void submitTasks(File outFile, File file, Logger logger)
      throws IOException {
    List<MyTask> tasks = new ArrayList<>();
    ExecutorService executorService = Executors.newFixedThreadPool(this.numOfThreads.get());
    try (FileWriter fileWriter = new FileWriter(outFile);
        BufferedReader inputReader = new BufferedReader(new FileReader(file))) {
      readToBuffer(inputReader);
      while (this.numOfThreads.get() != 0) {

        for (int i = 0; i < this.numOfThreads.get(); i++) {
          tasks.add(new MyTask(this.buffer[i]));
        }

        Future<Double>[] outBuffer = executorService.invokeAll(tasks).toArray(new Future[0]);
        writeToFile(fileWriter, outBuffer);
        readToBuffer(inputReader);
      }
    } catch (ExecutionException | InterruptedException e) {
      Thread.currentThread().interrupt();
      logger.log(Level.SEVERE, "Thread Error");
    } catch (IOException e) {
      executorService.shutdown();
      throw new IOException("problem with input or output file");
    }
    executorService.shutdown();
  }


  protected void oneThread(String inDir, String outDir) throws IOException {
    File file = new File(inDir);
    File outFile = new File(outDir);
    Logger logger = Logger.getLogger(Task4.class.getName());

    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        FileWriter fileWriter = new FileWriter(outFile)
    ) {
      long start = System.currentTimeMillis();
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        int data = Integer.parseInt(line);
        fileWriter.write(Double.toString(Math.tan(data)).concat("\n"));
      }
      long end = System.currentTimeMillis();
      long threadOne = end - start;
      logger.log(Level.INFO, "One thread: {0}", threadOne);

    } catch (IOException e) {
      throw new IOException("problem with input or output file");

    }
  }


  protected void mainLogic(String inDir, String outDir) throws IOException {

    Logger logger = Logger.getLogger(Task4.class.getName());
    File file = new File(inDir);
    File outFile = new File(outDir);


    long start = System.currentTimeMillis();
    submitTasks(outFile, file, logger);
    long end = System.currentTimeMillis();
    long multiThread = end - start;
    logger.log(Level.INFO, "Multi thread: {0}", multiThread);

  }

  /**
   * Принимает на вход путь до входного файла и путь до файла записи результатов. Вычисляет тангенс
   * чисел, записанных во входном файле.
   *
   * @param args .
   * @throws IOException - ошибка в ходе выполнения программы.
   */

  public static void main(String[] args)
      throws IOException {
    Task4 task = new Task4(new AtomicInteger(10));
    task.mainLogic("src/main/resources/numbers.txt",
        "src/main/resources/numbers_out.txt");
    task.oneThread("src/main/resources/numbers.txt",
        "src/main/resources/numbersone_out.txt");
  }

}