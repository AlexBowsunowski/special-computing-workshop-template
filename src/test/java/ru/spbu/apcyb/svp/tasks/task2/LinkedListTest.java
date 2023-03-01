package ru.spbu.apcyb.svp.tasks.task2;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class LinkedListTest {

  @Test
  @DisplayName("addTest")
  void addTest() {
    LinkedList list = new LinkedList();
    list.add(10);
    Assertions.assertArrayEquals(new Integer[]{10}, list.toArray());
  }

  @Test
  @DisplayName("addTest2")
  void addTest2() {

    LinkedList list = new LinkedList();
    list.add(0, 10);
    Assertions.assertEquals(10, list.get(0));
  }

  @Test
  @DisplayName("addTest3")
  void addTest3() {
    LinkedList list = new LinkedList();
    list.add(0, 10);
    list.add(0, "str");
    Assertions.assertArrayEquals(new Object[]{"str", 10}, list.toArray());
  }

  @Test
  @DisplayName("addTest4")
  void addTest4() {
    LinkedList list = new LinkedList();
    list.add(0, 10);
    list.add(0, "str");
    list.add(1, 5);
    Assertions.assertArrayEquals(new Object[]{"str", 5, 10}, list.toArray());
  }

  @Test
  @DisplayName("addExceptionTest")
  void addExceptionTest() {

    LinkedList list = new LinkedList();
    list.add("a");
    assertThrows(IndexOutOfBoundsException.class,
        () -> list.add(3, 6));
  }


  @Test
  @DisplayName("sizeTest")
  void sizeTest() {
    LinkedList list = new LinkedList();
    Assertions.assertEquals(0, list.size());
  }

  @Test
  @DisplayName("sizeTest2")
  void sizeTest2() {
    LinkedList list = new LinkedList();
    list.add("a");
    Assertions.assertEquals(1, list.size());
  }

  @Test
  @DisplayName("isEmptyTest")
  void isEmptyTest() {
    LinkedList list = new LinkedList();
    Assertions.assertTrue(list.isEmpty());
  }

  @Test
  @DisplayName("isEmptyTest2")
  void isEmptyTest2() {
    LinkedList list = new LinkedList();
    list.add(new int[]{1});
    Assertions.assertFalse(list.isEmpty());
  }

  @Test
  @DisplayName("removeTest")
  void removeTest() {

    LinkedList list = new LinkedList();
    list.add(10);
    list.add(5);
    list.add(1);
    list.remove(Integer.valueOf(5));
    Assertions.assertArrayEquals(new Object[]{10, 1}, list.toArray());
  }

  @Test
  @DisplayName("removeTest2")
  void removeTest2() {
    LinkedList list = new LinkedList();
    list.add("str");
    list.remove("str");
    Assertions.assertTrue(list.isEmpty());
  }


  @Test
  @DisplayName("removeTest3")
  void removeTest3() {

    LinkedList list = new LinkedList();
    list.add(10);
    Object element = list.remove(0);

    assertAll(
        () -> Assertions.assertTrue(list.isEmpty()),
        () -> Assertions.assertEquals("10", element.toString())
    );
  }


  @Test
  @DisplayName("removeTest4")
  void removeTest4() {
    LinkedList list = new LinkedList();
    list.add(10);
    list.add("str");
    list.remove(0);
    Assertions.assertEquals("str", list.get(0));

  }

  @Test
  @DisplayName("removeTest5")
  void removeTest5() {
    LinkedList list = new LinkedList();
    list.add(1);
    list.add(2);
    list.add(3);
    list.remove(2);
    Assertions.assertArrayEquals(new Object[]{1, 2}, list.toArray());

  }

  @Test
  @DisplayName("removeExceptionTest")
  void removeExceptionTest() {
    LinkedList list = new LinkedList();
    assertThrows(IndexOutOfBoundsException.class,
        () -> list.remove(0));
  }


  @Test
  @DisplayName("getTest")
  void getTest() {

    LinkedList list = new LinkedList();
    list.add(10);
    list.add("str");
    list.add(new int[]{3});
    Assertions.assertEquals("str", list.get(1));


  }

  @Test
  @DisplayName("getExceptionTest")
  void getExceptionTest() {
    LinkedList list = new LinkedList();
    list.add(10);
    assertThrows(IndexOutOfBoundsException.class,
        () -> list.get(2));
  }


  @Test
  @DisplayName("indexOfTest")
  void indexOfTest() {

    LinkedList list = new LinkedList();
    list.add("str");
    Assertions.assertEquals(0, list.indexOf("str"));
    Assertions.assertEquals(-1, list.indexOf(10));
  }

  @Test
  @DisplayName("indeOfTest2")
  void indeOfTest2() {
    LinkedList list = new LinkedList();
    list.add("str");
    Assertions.assertEquals(-1, list.indexOf(10));
  }

  @Test
  @DisplayName("setTest")
  void setTest() {

    LinkedList list = new LinkedList();
    list.add("str");
    list.add(1);
    list.set(1, "string");
    assertEquals("string", list.get(1));
  }

  @Test
  @DisplayName("setExceptionTest")
  void setExceptionTest() {

    LinkedList list = new LinkedList();
    list.add("str");
    assertThrows(IndexOutOfBoundsException.class, () -> list.set(1, "string"));
  }
}