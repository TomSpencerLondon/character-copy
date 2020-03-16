package com.codurance.character_copy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CopierShould {

  @Mock Source source;
  @Mock Destination destination;
  private Copier copier;

  @BeforeEach
  void setUp() {
    copier = new Copier(source, destination);
  }

  @Test
  void destination_should_receive_characters() {
    when(source.getChar()).thenReturn('\n');
    copier.copy();
    verify(source).getChar();
  }

  @Test
  void forward_character_to_the_destination() {
    char character = 'H';
    when(source.getChar()).thenReturn(character)
            .thenReturn('\n');
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

    copier.copy();

    verify(destination).setChar(character1);
    verify(destination).setChar(character2);
    verify(destination, never()).setChar(character3);
    verify(destination, never()).setChar(character4);
  }

//  private class destinationSpy implements Destination {
//  }
}
