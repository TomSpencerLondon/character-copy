package com.codurance.character_copy;

public class Copier {
  private Destination destination;
  private Source source;

  public Copier(Source source, Destination destination) {
    this.source = source;
    this.destination = destination;
  }

  public void copy() {
    while (true) {
      char character = source.getChar();
      if (character == '\n') {
        break;
      }
      destination.setChar(character);
    }
  }
}
