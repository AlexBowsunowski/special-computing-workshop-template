package ru.spbu.apcyb.svp.tasks.task1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;
import java.util.logging.StreamHandler;


/**
 * Задание 1.
 */


public class Task1 {


  private long sum;
  private long[] values;
  private final Logger logger = Logger.getLogger(Task1.class.getName());


  private long numOfCombinations = 0;


  public long getSum() {
    return this.sum;
  }

  public long[] getValues() {
    return this.values;
  }

  public Logger getLogger() {
    return this.logger;
  }

  public void setSum(long sum) {
    this.sum = sum;
  }

  public long getNumOfCombinations() {
    return this.numOfCombinations;
  }

  public void setValues(long[] values) {
    this.values = values;
  }


  /**
   * Перенаправление логирования в файл для тестирования.
   */
  protected void loggerConfiguration() throws FileNotFoundException {
    File logging = new File("logging.properties");
    if (logging.exists() && !System.getProperties().hasKey("java.util.logging.config.file")) {
      System.setProperty("java.util.logging.config.file", path);
    }
  }


  protected String readString() throws IOException {
    InputStream inputStream = System.in;
    Reader inputStreamReader = new InputStreamReader(inputStream);
    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
    String data;

    try {
      data = bufferedReader.readLine();
    } catch (java.io.IOException e) {
      throw new java.io.IOException("input error, cant read");
    }
    return data;
  }


  protected void parseString(String input) throws NumberFormatException, ArithmeticException {
    String[] inputFormat = input.trim().replaceAll("\\s{2,}", " ").split(" ");
    long num;
    Set<Long> tmpValues = new TreeSet<>();

    try {
      num = Long.parseLong(inputFormat[0]);
      if (num <= 0) {
        throw new ArithmeticException("invalid sum");
      }

      this.sum = num;

    } catch (NumberFormatException e) {
      throw new NumberFormatException("its not a number");
    }

    for (int i = 1; i < inputFormat.length; i++) {
      try {
        long value = Long.parseLong(inputFormat[i]);
        if (value <= 0) {
          throw new ArithmeticException("invalid value");
        }
        tmpValues.add(value);


      } catch (NumberFormatException e) {
        throw new NumberFormatException("its not a number");
      }

    }

    Long[] tmpArray = tmpValues.toArray(Long[]::new);
    this.values = new long[tmpArray.length];
    for (int j = 0; j < tmpArray.length; j++) {
      this.values[j] = tmpArray[j];

      if (this.values.length == 0) {
        throw new ArithmeticException("invalid values");
      }
    }

    protected void printCombination (Logger logger, List < Long > combination){

      String out = combination.toString();
      logger.log(java.util.logging.Level.INFO, out);
    }

    protected void printNumber (Logger logger,long ans){
      String out = Long.toString(ans);
      logger.log(java.util.logging.Level.INFO, out);
    }

    protected List<List<Long>> setNumOfCombinations ( long currentSum, int maxIndex, long[] values){
      List<List<Long>> result = new ArrayList<>();
      if (currentSum == 0) {
        result.add(new ArrayList<>());
      } else {
        for (int i = maxIndex; i >= 0; i--) {
          long currentValue = values[i];
          if (currentValue > currentSum) {
            continue;
          }

          for (List<Long> remain : setNumOfCombinations(currentSum - currentValue, i, values)) {
            List<Long> currentCombination = new ArrayList<>();
            currentCombination.add(currentValue);
            currentCombination.addAll(remain);

            if (currentSum == this.sum) {
              this.numOfCombinations += 1;
            } else {
              result.add(currentCombination);
            }
          }
        }
      }
      return result;
    }

    protected List<List<Long>> getCombinations ( long currentSum, int maxIndex, long[] values){
      List<List<Long>> result = new ArrayList<>();
      if (currentSum == 0) {
        result.add(new ArrayList<>());
      } else {
        for (int i = maxIndex; i >= 0; i--) {
          long currentValue = values[i];
          if (currentValue > currentSum) {
            continue;
          }

          for (List<Long> remain : getCombinations(currentSum - currentValue, i, values)) {
            List<Long> currentCombination = new ArrayList<>();
            currentCombination.add(currentValue);
            currentCombination.addAll(remain);

            if (currentSum == this.sum) {
              printCombination(this.logger, currentCombination);

            } else {
              result.add(currentCombination);
            }
          }
        }
      }
      return result;
    }

    protected void mainLogic (InputStream stream) throws IOException {
      System.setIn(stream);
      String input;

      input = readString();
      parseString(input);

      setNumOfCombinations(sum, values.length - 1, values);
      printNumber(logger, numOfCombinations);
      getCombinations(sum, values.length - 1, values);
    }

    /**
     * На вход принимается строка, состоящая из целых чисел, все числа разделены пробелом. Первое
     * число трактуется как сумма, все последующие - как существующие номиналы. Программа выводит
     * количество комбинаций и сами комбинации.
     *
     * @param args .
     */

    public static void main (String[]args) throws IOException {
      loggerConfiguration();
      Task1 task = new Task1();
      task.mainLogic(System.in);
    }
  }
