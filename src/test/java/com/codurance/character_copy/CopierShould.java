package com.codurance.character_copy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class CopierShould {

  private Source source;
  private Destination destination;
  private Copier copier;

  @BeforeEach
  void setUp() {
    source = mock(Source.class);
    destination = mock(Destination.class);
    copier = new Copier(source, destination);
  }

  @Test
  void destination_should_receive_characters() {
    copier.copy();
    verify(source).getChar();
  }

  @Test
  void forward_character_to_the_destination() {
    char character = 'H';
    when(source.getChar()).thenReturn(character);
    copier.copy();
    verify(destination).setChar(character);
  }

  @Test
  void forward_characters_to_destination_until_it_receives_a_new_line() {
    char character1 = 'H';
    char character2 = 'I';
    char character3 = '\n';
    char character4 = 'K';
    when(source.getChar()).thenReturn(character1)
            .thenReturn(character2)
            .thenReturn(character3)
            .thenReturn(character4);
    for (int i = 0; i < 4; i++) {
      copier.copy();
    }

    verify(destination).setChar(character1);
    verify(destination).setChar(character2);
    verify(destination, never()).setChar(character3);
    verify(destination, never()).setChar(character4);
  }
}
