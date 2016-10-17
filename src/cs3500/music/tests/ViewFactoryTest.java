package cs3500.music.tests;

import cs3500.music.view.*;
import org.junit.Test;

import cs3500.music.view.GuiViewImpl;

import static org.junit.Assert.*;

/**
 * Created by liangmanman1 on 3/22/16.
 */
public class ViewFactoryTest {
  ViewFactory factory = new ViewFactory();

  @Test(expected = IllegalArgumentException.class)
  public void inputInvalid() {
    factory.create("abc");
  }

  @Test
  public void testCreate1() {
    assertTrue(factory.create("console") instanceof View);
    assertTrue(factory.create("console") instanceof ConsoleViewImpl);
    assertFalse(factory.create("console") instanceof GuiViewImpl);
    assertFalse(factory.create("console") instanceof MidiViewImpl);
  }

  @Test
  public void testCreate2() {
    assertTrue(factory.create("visual") instanceof View);
    assertFalse(factory.create("visual") instanceof ConsoleViewImpl);
    assertTrue(factory.create("visual") instanceof GuiViewImpl);
    assertFalse(factory.create("visual") instanceof MidiViewImpl);
  }

  @Test
  public void testCreate3() {
    assertTrue(factory.create("midi") instanceof View);
    assertFalse(factory.create("midi") instanceof ConsoleViewImpl);
    assertFalse(factory.create("midi") instanceof GuiViewImpl);
    assertTrue(factory.create("midi") instanceof MidiViewImpl);
  }
}
