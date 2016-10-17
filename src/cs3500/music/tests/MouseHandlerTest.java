package cs3500.music.tests;

import cs3500.music.controller.MouseHandler;
import org.junit.Test;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseEvent;

import static org.junit.Assert.*;

/**
 * Tests for the MouseHandler
 */
public class MouseHandlerTest {
  private MouseHandler m = new MouseHandler();
  private JButton jb = new JButton();

  @Test
  public void testSetPoint() {
    Point p = new Point(40, 40);
    assertEquals(new Point(0, 1), m.setPoint(p));
  }

  @Test
  public void testMouseClicked() {
    assertEquals(new Point(0, 0), m.getP());
    m.mouseClicked(new MouseEvent(jb, 1, 1, 1, 80, 40, 1, true));
    assertEquals(new Point(2, 1), m.getP());
  }

  @Test
  public void testMousePressed() {
    assertEquals(new Point(0, 0), m.getStartP());
    m.mousePressed(new MouseEvent(jb, 1, 1, 1, 80, 40, 1, true));
    assertEquals(new Point(2, 1), m.getStartP());
  }

  @Test
  public void testMouseReleased() {
    assertEquals(new Point(0, 0), m.getEndP());
    m.mouseReleased(new MouseEvent(jb, 1, 1, 1, 80, 40, 1, true));
    assertEquals(new Point(2, 1), m.getEndP());
  }
}
