package ru.spbu.apcyb.svp.tasks;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Тесты для задания 3.
 */
class Task3Test {

  @Test
  @DisplayName("outFileErrorTest")
  void outFileErrorTest() {
    String rootTest = "src/test";
    Task3 testclass = new Task3();
    Assertions.assertThrows(IOException.class,
        () -> testclass.main(new String[]{rootTest, "src"}));


  }

  @Test
  @DisplayName("mainTest")
  void mainTest() throws IOException {
    String rootTest = "src/test";
    String outputTest = "output.txt";
    List<String> actual = new ArrayList<>();
    Task3 testclass = new Task3();

    List<String> expected = new ArrayList(Arrays.asList(new String[]{
        "src/test/java/ru/spbu/apcyb/svp/tasks/Task1Test.java",
        "src/test/java/ru/spbu/apcyb/svp/tasks/Task5Test.java",
        "src/test/java/ru/spbu/apcyb/svp/tasks/LinkedListTest.java",
        "src/test/java/ru/spbu/apcyb/svp/tasks/ArrayUtilsTest.java",
        "src/test/java/ru/spbu/apcyb/svp/tasks/QueueJrTest.java",
        "src/test/java/ru/spbu/apcyb/svp/tasks/Task3Test.java",
        "src/test/java/ru/spbu/apcyb/svp/tasks/Task2Test.java",
        "src/test/resources/.stub",
    }));

    testclass.main(new String[]{rootTest, outputTest});

    try (Scanner scanner = new Scanner(new File(outputTest))) {
      while (scanner.hasNextLine()) {
        actual.add(scanner.nextLine());
      }
    } catch (IOException e) {
      throw new IOException("test error");
    }
    Assertions.assertEquals(expected, actual);
  }

  @Test
  @DisplayName("dirErrorTest")
  void dirErrorTest() {
    String rootTest = "src/error";
    Task3 testclass = new Task3();
    Assertions.assertThrows(IOException.class,
        () -> testclass.main(new String[]{rootTest, "output.txt"}));

  }


}