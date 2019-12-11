package util;

public abstract  class DatabaseCache {

  abstract public void put(Object o);
  abstract public Object get(Object o);
  abstract public void clear();
  abstract public void remove(Object o);


  }


