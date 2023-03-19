package ru.spbu.apcyb.svp.tasks.task4;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * класс задания для CompletableFuture. На вход получает число, возвращает тангенс числа.
 */
public class MyTask implements Callable<Double> {

  private final int data;

  MyTask(int data) {
    this.data = data;
  }

  @Override
  public Double call() throws Exception {
    return Math.tan(this.data);
  }
}