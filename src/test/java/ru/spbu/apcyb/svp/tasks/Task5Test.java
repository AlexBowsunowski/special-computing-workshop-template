package ru.spbu.apcyb.svp.tasks.task5;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Тесты для задания 5.
 */
class Task5Test {

  @Test
  @DisplayName("readFileTest")
  void readFileTest() throws IOException {
    File testFile = new File("src/test/resources/test5_1.txt");
    PrintWriter testWriter = new PrintWriter(testFile);
    testWriter.write("test1 test2 test1");
    testWriter.close();
    Task5 testClass = new Task5();
    HashMap<String, Integer> exp = new HashMap<>();
    exp.put("test1", 2);
    exp.put("test2", 1);

    HashMap<String, Integer> testResult =
        testClass.readFile("src/test/resources/test5_1.txt");
    Assertions.assertEquals(exp, testResult);
  }

  @Test
  @DisplayName("writeToFileTest")
  void writeToFileTest() throws IOException {

    String[] exp = new String[2];
    exp[0] = "test2 1";
    exp[1] = "test1 2";
    String[] act = new String[2];
    Task5 testClass = new Task5();
    String path = "src/test/resources/task5_test2.txt";
    HashMap<String, Integer> input = new HashMap<>();
    input.put("test1", 2);
    input.put("test2", 1);
    testClass.writeToFile(input, path);
    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
      act[0] = bufferedReader.readLine();
      act[1] = bufferedReader.readLine();
      Assertions.assertArrayEquals(exp, act);
    } catch (IOException e) {
      throw new IOException("write test error");
    }
  }

  @Test
  @DisplayName("mainLogicTest")
  void mainLogicTest() throws IOException {

    String prefix = "src/test/resources";
    Task5 testClass = new Task5();
    testClass.mainLogic(prefix + "/test5_1.txt", prefix + "/test5_2out.txt", prefix);
    try (BufferedReader firstReader =
        new BufferedReader(new FileReader(prefix + "/test1"));
        BufferedReader secondReader =
            new BufferedReader(new FileReader(prefix + "/test2"))) {
      Assertions.assertAll(
          () -> {
            Assertions.assertEquals("test1 test1 ", firstReader.readLine());
            Assertions.assertEquals("test2 ", secondReader.readLine());
          }
      );
    } catch (IOException e) {
      throw new IOException("mainLogicTest error");
    }
  }


}
