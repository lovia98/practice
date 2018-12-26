package com.example.algorithm.adaptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import org.junit.Test;

public class AdapterSample {

  public class EnumerationAdapter implements Iterator {

    private Enumeration enumeration;

    public EnumerationAdapter(Enumeration enumeration) {
      this.enumeration = enumeration;
    }

    @Override
    public boolean hasNext() {
      return enumeration.hasMoreElements();
    }

    @Override
    public Object next() {
      return enumeration.nextElement();
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }

  public class IteratorAdaptor implements Enumeration {

    Iterator iterator;

    public IteratorAdaptor(Iterator iterator) {
      this.iterator = iterator;
    }

    @Override
    public boolean hasMoreElements() {
      return iterator.hasNext();
    }

    @Override
    public Object nextElement() {
      return iterator.next();
    }
  }

  @Test
  public void test() {

    List<Integer> list = Arrays.asList(1,2,3,8,4,5);
    Iterator<Integer> itr = list.iterator();

    Enumeration<Integer> adapter =  new IteratorAdaptor(itr);
    printList(adapter);
  }

  private void printList(Enumeration enumer){

    //enumeration 어댑터를 이용하여 리스트 출력
    while(enumer.hasMoreElements()) {
      System.out.println(enumer.nextElement().toString());
    }

  }
}
