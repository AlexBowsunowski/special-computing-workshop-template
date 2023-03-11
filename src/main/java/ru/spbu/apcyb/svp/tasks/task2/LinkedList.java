package ru.spbu.apcyb.svp.tasks.task2;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Реализация двусвязного списка с имплементацией интерфейса java.util.List.
 */
public class LinkedList implements java.util.List<Object> {


  private Node head = null;

  /**
   * Элемент двусвязного списка. data - объект, хранящийся в элементе. next - следубщий элемент в
   * списке. prev - предыдущий элемент в списке.
   */
  public static class Node {

    private Object data;
    private Node next;
    private Node prev;

    Node(Object data, Node prev, Node next) {
      this.data = data;
      this.next = next;
      this.prev = prev;
    }
  }


  @Override
  public int size() {
    int it = 0;
    Node element = this.head;
    while (element != null) {
      it++;
      element = element.next;
    }
    return it;
  }

  @Override
  public boolean isEmpty() {
    return this.head == null;
  }

  @Override
  public boolean contains(Object o) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public Iterator<Object> iterator() throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public Object[] toArray() {
    Object[] output = new Object[this.size()];
    Node it = this.head;
    for (int i = 0; i < this.size(); i++) {
      output[i] = it.data;
      it = it.next;
    }
    return output;
  }

  @Override
  public <T> T[] toArray(T[] a) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean add(Object o) {
    Node it = this.head;
    if (it == null) {
      this.head = new Node(o, null, null);


    } else {
      while (it.next != null) {
        it = it.next;
      }
      it.next = new Node(o, it, null);
    }
    return true;
  }

  @Override
  public void add(int index, Object element) throws IndexOutOfBoundsException {
    Node it = this.head;
    if (index < 0 || index > this.size()) {
      throw new IndexOutOfBoundsException();
    }

    int curIndex = 0;
    while (curIndex != index) {
      it = it.next;
      curIndex++;
    }

    if (it == null) {
      this.head = new Node(element, null, null);
    } else if (it.prev == null) {
      Node newNode = new Node(element, null, it);
      this.head.prev = newNode;
      this.head = newNode;
    } else {
      Node newNode = new Node(element, it.prev, it);
      newNode.prev.next = newNode;
      it.prev = newNode;
    }
  }

  @Override
  public boolean remove(Object o) {
    Node it = this.head;
    while (it != null && it.data != o) {
      it = it.next;
    }
    if (it != null) {

      if (it.prev == null) {
        this.head = it.next;
      } else {
        it.prev.next = it.next;
      }

      if (it.next != null) {
        it.next.prev = it.prev;
      }
    }
    return true;
  }

  @Override
  public Object remove(int index) throws IndexOutOfBoundsException {
    Node it = this.head;
    if (index < 0 || index >= this.size()) {
      throw new IndexOutOfBoundsException();
    }
    int curIndex = 0;
    while (curIndex != index) {
      it = it.next;
      curIndex++;
    }
    if (it.prev == null) {
      this.head = it.next;
    } else {
      it.prev.next = it.next;
    }

    if (it.next != null) {
      it.next.prev = it.prev;
    }
    return it.data;
  }

  @Override
  public boolean containsAll(Collection<?> c) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean addAll(Collection<?> c) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean addAll(int index, Collection<?> c) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean removeAll(Collection<?> c) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean retainAll(Collection<?> c) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public void clear() {
    this.head = null;
  }

  @Override
  public Object get(int index) throws IndexOutOfBoundsException {
    Node it = this.head;
    if (index >= this.size() || index < 0) {
      throw new IndexOutOfBoundsException();
    }
    int curIndex = 0;
    while (curIndex != index) {
      it = it.next;
      curIndex++;
    }
    return it.data;
  }

  @Override
  public Object set(int index, Object element) throws IndexOutOfBoundsException {
    if (index >= this.size()) {
      throw new IndexOutOfBoundsException();
    }
    int curIndex = 0;
    Node it = this.head;
    while (curIndex != index) {
      it = it.next;
      curIndex++;
    }
    Object prev = it.data;
    it.data = element;
    return prev;
  }


  @Override
  public int indexOf(Object o) {

    Node it = this.head;
    int index = 0;
    while (it != null && it.data != o) {
      it = it.next;
      index++;
    }
    if (it != null) {
      return index;
    }
    return -1;
  }

  @Override
  public int lastIndexOf(Object o) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public ListIterator<Object> listIterator() throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public ListIterator<Object> listIterator(int index) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public List<Object> subList(int fromIndex, int toIndex) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }
}