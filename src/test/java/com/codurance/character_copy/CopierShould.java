package com.codurance.character_copy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CopierShould {

  private SourceStub sourceStub;
  private DestinationSpy destinationSpy;
  private Copier copier;

  @BeforeEach
  void setUp() {
    sourceStub = new SourceStub();
    destinationSpy = new DestinationSpy();
    copier = new Copier(sourceStub, destinationSpy);
  }

  @Test
  void destination_should_receive_characters() {
    sourceStub.setInput(List.of('\n'));
    copier.copy();
    assertEquals(1, sourceStub.timesCalled());
  }

  @Test
  void forward_character_to_the_destination() {
    char character = 'H';
    sourceStub.setInput(List.of(character, '\n'));
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
    sourceStub.setInput(List.of(character1, character2, character3, character4));

    copier.copy();

    assertEquals(2, destinationSpy.getCharsCalled().size());
    assertTrue(destinationSpy.getCharsCalled().contains(character1));
    assertTrue(destinationSpy.getCharsCalled().contains(character2));
    assertFalse(destinationSpy.getCharsCalled().contains(character3));
    assertFalse(destinationSpy.getCharsCalled().contains(character4));
  }

}
