package com.codurance.character_copy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CopierShould {

  @Mock Source source;
  private DestinationSpy destinationSpy;
  private Copier copier;

  @BeforeEach
  void setUp() {
    destinationSpy = new DestinationSpy();
    copier = new Copier(source, destinationSpy);
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
    assertEquals(1, destinationSpy.getCharsCalled().size());
    assertTrue(destinationSpy.getCharsCalled().contains(character));
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

    assertEquals(2, destinationSpy.getCharsCalled().size());
    assertTrue(destinationSpy.getCharsCalled().contains(character1));
    assertTrue(destinationSpy.getCharsCalled().contains(character2));
    assertFalse(destinationSpy.getCharsCalled().contains(character3));
    assertFalse(destinationSpy.getCharsCalled().contains(character4));
  }

  private class DestinationSpy implements Destination {
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
}
