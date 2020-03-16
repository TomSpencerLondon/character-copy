package com.codurance.character_copy;

import java.util.List;

class SourceStub implements Source {
  private List<Character> input;
  private int timesCalled = 0;

  public char getChar() {
    char next = input.get(timesCalled);
    timesCalled++;
    return next;
  }

  public void setInput(List<Character> input) {
    this.input = input;
  }

  public int timesCalled() {
    return timesCalled;
  }
}
