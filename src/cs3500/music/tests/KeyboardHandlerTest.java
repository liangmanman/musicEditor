package cs3500.music.tests;

import cs3500.music.controller.KeyboardHandler;
import org.junit.Test;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Tests for the KeyboardHandler
 */
public class KeyboardHandlerTest {
  private Map<Integer, Runnable> keysTyped;
  private Map<Integer, Runnable> keysPressed;
  private Map<Integer, Runnable> keysReleased;
  private KeyboardHandler kbd;
  private JButton jb;
  private StringBuilder sb;

  public void initialize() {
    keysTyped = new HashMap<>();
    keysPressed = new HashMap<>();
    keysReleased = new HashMap<>();
    kbd = new KeyboardHandler();
    jb = new JButton();
    sb = new StringBuilder();

    keysPressed.put(KeyEvent.VK_A, () -> {
      sb.append("Add the note");
    });
    keysPressed.put(KeyEvent.VK_R, () -> {
      sb.append("Remove the note");
    });
    keysReleased.put(KeyEvent.VK_M, () -> {
      sb.append("Move the note");
    });
    keysReleased.put(KeyEvent.VK_D, () -> {
      sb.append("Edit the duration of the note");
    });
    keysPressed.put(KeyEvent.VK_I, () -> {
      sb.append("Edit the instrument of the note");
    });
    keysPressed.put(KeyEvent.VK_V, () -> {
      sb.append("Edit the volume of the note");
    });
    keysPressed.put(KeyEvent.VK_UP, () -> {
      sb.append("Arrow key: UP\n");
    });
    keysPressed.put(KeyEvent.VK_DOWN, () -> {
      sb.append("Arrow key: DOWN\n");
    });
    keysPressed.put(KeyEvent.VK_LEFT, () -> {
      sb.append("Arrow key: LEFT\n");
    });
    keysPressed.put(KeyEvent.VK_RIGHT, () -> {
      sb.append("Arrow key: RIGHT");
    });
    keysPressed.put(KeyEvent.VK_HOME, () -> {
      sb.append("To the beginning\n");
    });
    keysPressed.put(KeyEvent.VK_END, () -> {
      sb.append("To the end");
    });
    keysPressed.put(KeyEvent.VK_SPACE, () -> {
      sb.append("Pause or Resume\n");
    });
    keysPressed.put(KeyEvent.VK_P, () -> {
      sb.append("Start the music");
    });

    kbd.setKeysTyped(keysTyped);
    kbd.setKeysPressed(keysPressed);
    kbd.setKeysReleased(keysReleased);
  }

  @Test
  public void testAdd() {
    initialize();
    kbd.keyTyped(new KeyEvent(jb, 1, 1, 1, KeyEvent.VK_A, 'a'));
    assertEquals("", sb.toString());
    kbd.keyPressed(new KeyEvent(jb, 1, 1, 1, KeyEvent.VK_A, 'a'));
    assertEquals("Add the note", sb.toString());

  }

  @Test
  public void testRemove() {
    initialize();
    kbd.keyPressed(new KeyEvent(jb, 1, 1, 1, KeyEvent.VK_R, 'r'));
    assertEquals("Remove the note", sb.toString());
  }

  @Test
  public void testMove() {
    initialize();
    kbd.keyPressed(new KeyEvent(jb, 1, 1, 1, KeyEvent.VK_M, 'm'));
    assertEquals("", sb.toString());
    kbd.keyReleased(new KeyEvent(jb, 1, 1, 1, KeyEvent.VK_M, 'm'));
    assertEquals("Move the note", sb.toString());
  }

  @Test
  public void testDuration() {
    initialize();
    kbd.keyReleased(new KeyEvent(jb, 1, 1, 1, KeyEvent.VK_D, 'd'));
    assertEquals("Edit the duration of the note", sb.toString());
  }

  @Test
  public void testInstrument() {
    initialize();
    kbd.keyPressed(new KeyEvent(jb, 1, 1, 1, KeyEvent.VK_I, 'i'));
    assertEquals("Edit the instrument of the note", sb.toString());
  }

  @Test
  public void testVolume() {
    initialize();
    kbd.keyPressed(new KeyEvent(jb, 1, 1, 1, KeyEvent.VK_V, 'v'));
    assertEquals("Edit the volume of the note", sb.toString());
  }

  @Test
  public void testArrow() {
    initialize();
    assertEquals("", sb.toString());
    kbd.keyPressed(new KeyEvent(jb, 1, 1, 1, KeyEvent.VK_UP, '1'));
    kbd.keyPressed(new KeyEvent(jb, 1, 1, 1, KeyEvent.VK_DOWN, '2'));
    assertEquals("Arrow key: UP\n" + "Arrow key: DOWN\n", sb.toString());
    kbd.keyPressed(new KeyEvent(jb, 1, 1, 1, KeyEvent.VK_LEFT, '3'));
    kbd.keyPressed(new KeyEvent(jb, 1, 1, 1, KeyEvent.VK_RIGHT, '4'));
    assertEquals("Arrow key: UP\n" + "Arrow key: DOWN\n" + "Arrow key: LEFT\n"
      + "Arrow key: RIGHT",
      sb.toString());
  }

  @Test
  public void testHomeEnd() {
    initialize();
    kbd.keyPressed(new KeyEvent(jb, 1, 1, 1, KeyEvent.VK_HOME, '1'));
    kbd.keyPressed(new KeyEvent(jb, 1, 1, 1, KeyEvent.VK_END, '2'));
    assertEquals("To the beginning\n" + "To the end", sb.toString());
  }

  @Test
  public void testP() {
    initialize();
    kbd.keyPressed(new KeyEvent(jb, 1, 1, 1, KeyEvent.VK_SPACE, '1'));
    kbd.keyPressed(new KeyEvent(jb, 1, 1, 1, KeyEvent.VK_P, '2'));
    assertEquals("Pause or Resume\n" + "Start the music", sb.toString());
  }
}
