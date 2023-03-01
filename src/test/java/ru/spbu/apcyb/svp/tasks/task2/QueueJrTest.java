package ru.spbu.apcyb.svp.tasks.task2;


import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QueueJrTest {


  @Test
  @DisplayName("addTest")
  void addTest() {
    QueueJr queue = new QueueJr();
    queue.add("str");
    Assertions.assertEquals("str", queue.peek());
  }

  @Test
  @DisplayName("sizeTest")
  void sizeTest() {
    QueueJr queue = new QueueJr();
    Assertions.assertEquals(0, queue.size());
    queue.add(5);
    Assertions.assertEquals(1, queue.size());
  }

  @Test
  @DisplayName("sizeTest2")
  void sizeTest2() {
    QueueJr queue = new QueueJr();
    queue.add(5);
    Assertions.assertEquals(1, queue.size());
  }

  @Test
  @DisplayName("pollTest")
  void pollTest() {
    QueueJr queue = new QueueJr();
    queue.add("str");
    queue.add(5);
    Object element = queue.poll();
    assertAll(
        () -> Assertions.assertEquals("str", element),
        () -> Assertions.assertArrayEquals(new Object[]{5}, queue.toArray())
    );
  }

  @Test
  @DisplayName("pollTest2")
  void pollTest2() {
    QueueJr queue = new QueueJr();
    Assertions.assertNull(queue.poll());
  }

  @Test
  @DisplayName("elementTest")
  void elementTest() {

    QueueJr queue = new QueueJr();
    queue.add("str");
    queue.add(5);
    Object element = queue.element();
    assertAll(
        () -> Assertions.assertEquals("str", element),
        () -> Assertions.assertArrayEquals(new Object[]{"str", 5}, queue.toArray())
    );
  }

  @Test
  @DisplayName("elementExceptionTest")
  void elementExceptionTest() {
    QueueJr queue = new QueueJr();
    Assertions.assertThrows(NoSuchElementException.class, () -> queue.element());
  }

  @Test
  @DisplayName("removeTest")
  void removeTest() {

    QueueJr queueJr = new QueueJr();
    queueJr.add("str");
    queueJr.add(5);
    Object element = queueJr.remove();
    assertAll(
        () -> Assertions.assertEquals("str", element),
        () -> Assertions.assertArrayEquals(new Object[]{5}, queueJr.toArray())
    );
  }

  @Test
  @DisplayName("removeExceptionTest")
  void removeExceptionTest() {
    QueueJr queueJr = new QueueJr();
    Assertions.assertThrows(NoSuchElementException.class, () -> queueJr.remove());
  }

  @Test
  @DisplayName("removeTest2")
  void removeTest2() {
    QueueJr queue = new QueueJr();
    queue.add("a");
    queue.add("g");
    queue.remove("g");
    assertArrayEquals(new Object[]{"a"}, queue.toArray());
  }

  @Test
  @DisplayName("peekTest")
  void peekTest() {
    QueueJr queueJr = new QueueJr();
    queueJr.add("str");
    queueJr.add(5);
    Object element = queueJr.peek();
    assertAll(
        () -> Assertions.assertEquals("str", element),
        () -> Assertions.assertArrayEquals(new Object[]{"str", 5}, queueJr.toArray())
    );
  }

  @Test
  @DisplayName("peekTest2")
  void peekTest2() {
    QueueJr queueJr = new QueueJr();
    Assertions.assertNull(queueJr.peek());
  }

  @Test
  @DisplayName("isEmptyTest")
  void isEmptyTest() {
    QueueJr blankQueue = new QueueJr();

    assertTrue(blankQueue.isEmpty());

  }

  @Test
  @DisplayName("isEmptyTest2")
  void isEmptyTest2() {
    QueueJr queue = new QueueJr();
    queue.add(1);
    assertFalse(queue.isEmpty());
  }

  @Test
  @DisplayName("clearTest")
  void clearTest() {

    QueueJr queue = new QueueJr();
    queue.add(new Integer[]{3, 6, 5});
    queue.clear();
    assertTrue(queue.isEmpty());
  }

}