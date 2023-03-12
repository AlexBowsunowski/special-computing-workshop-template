package ru.spbu.apcyb.svp.tasks.task1;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Scanner;
import java.util.logging.Handler;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;


/**
 * Тесты для задания 1.
 */
class Task1Test {

  @Test
  @DisplayName("parseStringTest")
  void parseStringTest() {
    Task1 testClass = new Task1();
    String input = "5 3 2";
    testClass.parseString(input);
    long expectedSum = 5;
    long[] expectedValues = new long[]{2, 3};
    assertAll(
        () -> Assertions.assertEquals(expectedSum, testClass.getSum()),
        () -> Assertions.assertArrayEquals(expectedValues, testClass.getValues())
    );


  }

  @ParameterizedTest
  @ValueSource(strings = {"1 a 3", "c 7 8"})
  @DisplayName("parseStringTest2")
  void parseStringTest2(String input) {
    Task1 testClass = new Task1();
    assertThrows(NumberFormatException.class,
        () -> testClass.parseString(
            input));

  }

  @ParameterizedTest
  @ValueSource(strings = {"-1 2 3", "1 2 -3", "1   "})
  @DisplayName("parseStringTest3")
  void parseStringTest3(String input) {
    Task1 testClass = new Task1();
    assertThrows(ArithmeticException.class, () ->
        testClass.parseString(input));
  }

  @Test
  @DisplayName("getNumsOfCombinationTest")
  void getNumsOfCombinationTest() {
    long[] testValues = new long[]{1, 2};
    final long expectedAns = 3;
    final long testSum = 4;

    Task1 testClass = new Task1();
    testClass.setSum(testSum);
    testClass.setValues(testValues);
    testClass.setNumOfCombinations(testClass.getSum(), testClass.getValues().length - 1,
        testClass.getValues());
    Assertions.assertEquals(expectedAns, testClass.getNumOfCombinations());
  }


  private static Stream<Arguments> getCombinationsTestArguments() throws FileNotFoundException {

    Task1 testClass = new Task1();
    long inputSum = 2;
    long[] inputValues = new long[]{1, 2};
    testClass.setSum(inputSum);
    testClass.setValues(inputValues);
    testClass.loggerConfiguration();
    testClass.getCombinations(inputSum, inputValues.length - 1, inputValues);
    testClass.getLogger().getHandlers()[0].close();

    File file = new File("output.log");
    Scanner reader = new Scanner(file);

    Arguments[] args = new Arguments[2];
    args[0] = Arguments.of(reader.nextLine(), "[2]");
    args[1] = Arguments.of(reader.nextLine(), "[1, 1]");

    reader.close();

    return Stream.of(args);
  }

  @ParameterizedTest
  @MethodSource("getCombinationsTestArguments")
  @DisplayName("getCombinationsTest")
  void getCombinationsTest(String input, String expected) {

    assertEquals(expected, input);
  }


  private static InputStream prepareFileForTest() throws IOException {
    File file = new File("input.in");
    Files.writeString(file.toPath(), "4 2 1");
    return new FileInputStream(file);
  }


  private static Stream<Arguments> prepareValuesForTest()
      throws IOException {
    InputStream stream = prepareFileForTest();
    Task1 testClass = new Task1();
    testClass.loggerConfiguration();
    testClass.mainLogic(stream);
    for (Handler i : testClass.getLogger().getHandlers()) {
      i.close();
    }

    File file = new File("output.log");
    Scanner reader = new Scanner(file);

    Arguments[] args = new Arguments[4];
    args[0] = Arguments.of(reader.nextLine(), "3");
    args[1] = Arguments.of(reader.nextLine(), "[2, 2]");
    args[2] = Arguments.of(reader.nextLine(), "[2, 1, 1]");
    args[3] = Arguments.of(reader.nextLine(), "[1, 1, 1, 1]");

    return Stream.of(args);
  }


  @ParameterizedTest
  @MethodSource("prepareValuesForTest")
  @DisplayName("mainLogicTest")
  void mainLogicTest(String input, String expected) {

    assertEquals(expected, input);
  }

}