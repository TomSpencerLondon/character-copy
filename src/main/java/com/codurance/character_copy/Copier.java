package com.codurance.character_copy;

public class Copier {
  private Destination destination;
  private Source source;
  private boolean newLineCalled;

  public Copier(Source source, Destination destination) {
    this.source = source;
    this.destination = destination;
  }

  public void copy() {
    char character = source.getChar();
    if (character == '\n'){
      newLineCalled = true;
    }
    if (newLineCalled){
      return;
    }
    destination.setChar(character);
  }
}
