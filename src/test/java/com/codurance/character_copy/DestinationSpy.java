package com.codurance.character_copy;

import java.util.ArrayList;
import java.util.List;

class DestinationSpy implements Destination {
  private List<Character> charsCalled;

  public DestinationSpy() {
    this.charsCalled = new ArrayList<>();
  }

  public void setChar(char character) {
    charsCalled.add(character);
  }

  public List<Character> getCharsCalled() {
    return charsCalled;
  }
}
