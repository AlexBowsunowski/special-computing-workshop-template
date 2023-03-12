package ru.spbu.apcyb.svp.tasks;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
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
    Assertions.assertThrows(IOException.class, () -> testclass.main(new String[]{rootTest, "src"}));


  }

  @Test
  @DisplayName("mainTest")
  void mainTest() throws IOException {
    String rootTest = "src/test";
    String outputTest = "output.txt";
    List<String> actual = new ArrayList<>();
    Task3 testclass = new Task3();
    String test_path = "src/test/";
    List<String> expected = Files.walk(Paths.get(test_path)).filter(Files::isRegularFile)
        .map(Path::toString).collect(Collectors.toList());

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