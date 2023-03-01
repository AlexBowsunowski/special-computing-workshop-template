package ru.spbu.apcyb.svp.tasks.task4;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Тесты для задания 4.
 */
class Task4Test {

  @Test
  @DisplayName("readToBuffer")
  void readToBuffer() throws IOException {

    Task4 testClass = new Task4(10);

    try (BufferedReader testReader =
        new BufferedReader(new FileReader("src/test/resources/test1.txt"))) {
      testClass.readToBuffer(testReader);

      int[] exp = new int[]{1, 2, 3, 0, 0, 0, 0, 0, 0, 0};
      int[] act = testClass.getBuffer();
      Assertions.assertArrayEquals(exp, act);
    } catch (IOException e) {
      throw new IOException("test error");
    }
  }

  void writeForTest() throws IOException {
    Task4 testclass = new Task4(1);
    ExecutorService testExecutor = Executors.newSingleThreadExecutor();
    Future<Double>[] testInput = new Future[1];
    testInput[0] = testExecutor.submit(new MyTask(0));
    try (FileWriter testWriter = new FileWriter("src/test/resources/test2.txt")) {
      testclass.writeToFile(testWriter, testInput);
    } catch (IOException | ExecutionException | InterruptedException e) {
      throw new IOException("writeForTest error");
    }

  }

  @Test
  @DisplayName("writeToFileTest")
  void writeToFileTest() throws IOException {
    writeForTest();
    try (BufferedReader expReader
        = new BufferedReader(new FileReader("src/test/resources/test3.txt"));
        BufferedReader actReader
            = new BufferedReader(new FileReader("src/test/resources/test2.txt"))
    ) {

      Assertions.assertEquals(Double.parseDouble(expReader.readLine()),
          Double.parseDouble(actReader.readLine()));
    } catch (IOException e) {
      throw new IOException("writeTest error");
    }
  }

  @Test
  @DisplayName("mainLogicTest")
  void mainLogicTest() throws IOException {
    Task4 testclass = new Task4(10);
    testclass.mainLogic("src/test/resources/maintestinput.txt",
        "src/test/resources/maintestoutput.txt");

    try (
        BufferedReader expReader =
            new BufferedReader(new FileReader("src/test/resources/test5.txt"));
        BufferedReader actReader =
            new BufferedReader(new FileReader("src/test/resources/maintestoutput.txt"))) {
      String[] exp = new String[2];
      exp[0] = expReader.readLine();
      exp[1] = expReader.readLine();
      String[] act = new String[2];
      act[0] = actReader.readLine();
      act[1] = actReader.readLine();
      Assertions.assertArrayEquals(exp, act);
    } catch (IOException e) {
      throw new IOException("maintest error");
    }
  }

  @Test
  @DisplayName("oneThreadtest")
  void oneThreadtest() throws IOException {

    Task4 testclass = new Task4(10);
    testclass.oneThread("src/test/resources/maintestinput.txt",
        "src/test/resources/maintestoutput.txt");

    try (
        BufferedReader expReader =
            new BufferedReader(new FileReader("src/test/resources/test5.txt"));
        BufferedReader actReader =
            new BufferedReader(new FileReader("src/test/resources/maintestoutput.txt"))) {
      String[] exp = new String[2];
      exp[0] = expReader.readLine();
      exp[1] = expReader.readLine();
      String[] act = new String[2];
      act[0] = actReader.readLine();
      act[1] = actReader.readLine();
      Assertions.assertArrayEquals(exp, act);
    } catch (IOException e) {
      throw new IOException("oneThread error");
    }


  }
}