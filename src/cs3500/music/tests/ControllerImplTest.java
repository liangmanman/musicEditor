package cs3500.music.tests;

import cs3500.music.controller.Controller;
import cs3500.music.controller.ControllerImpl;
import cs3500.music.model.Model;
import cs3500.music.model.ModelImpl;
import cs3500.music.model.Note;
import cs3500.music.util.CompositionBuilder;
import cs3500.music.util.MusicReader;
import cs3500.music.view.GuiView;
import cs3500.music.view.ViewFactory;
import org.junit.Test;

import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.*;

import static org.junit.Assert.*;

/**
 * Tests for the ControllerImpl
 */
public class ControllerImplTest {
  private Model model;
  private GuiView view;
  private ControllerImpl controller;

  public void initialize() {
    MusicReader reader = new MusicReader();
    CompositionBuilder<Model> builder = new ModelImpl.Builder();
    File file = new File("mary-little-lamb.txt");
    FileReader fileReader = null;
    try {
      fileReader = new FileReader(file);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    model = reader.parseFile(fileReader, builder);
    view = (GuiView) ViewFactory.create("composite");
    controller = new ControllerImpl(model, view);
  }

//  @Test
//  public void testAdd() {
//    initialize();
//    assertEquals(34, model.getAllNotes().size());
//    controller.getMouse()
//      .mouseClicked(new MouseEvent(view.getDisplayPanel(), 1, 1, 1, 40, 50, 1, true));
//    controller.getKbd()
//      .keyPressed(new KeyEvent(view.getDisplayPanel(), 1, 1, 1, KeyEvent.VK_A, 'a'));
//    assertEquals(35, model.getAllNotes().size());
//  }
//
//  @Test
//  public void testRemove() {
//    initialize();
//    Note n = model.getNotesAtBeat(0).get(1);
//    assertEquals(34, model.getAllNotes().size());
//    controller.getMouse()
//      .mouseClicked(new MouseEvent(view.getDisplayPanel(), 1, 1, 1, 50, 130, 1, true));
//    controller.getKbd()
//      .keyPressed(new KeyEvent(view.getDisplayPanel(), 1, 1, 1, KeyEvent.VK_R, 'r'));
//    assertEquals(34, model.getAllNotes().size());
//
//    assertTrue(model.getAllNotes().contains(n));
//    controller.getMouse()
//      .mouseClicked(new MouseEvent(view.getDisplayPanel(), 1, 1, 1, 50, 90, 1, true));
//    controller.getKbd()
//      .keyPressed(new KeyEvent(view.getDisplayPanel(), 1, 1, 1, KeyEvent.VK_R, 'r'));
//    assertEquals(33, model.getAllNotes().size());
//    assertFalse(model.getAllNotes().contains(n));
//
//    controller.getMouse()
//      .mouseClicked(new MouseEvent(view.getDisplayPanel(), 1, 1, 1, 50, 90, 1, true));
//    controller.getKbd()
//      .keyPressed(new KeyEvent(view.getDisplayPanel(), 1, 1, 1, KeyEvent.VK_R, 'r'));
//    assertEquals(33, model.getAllNotes().size());
//  }
//
//  @Test
//  public void testMove() {
//    initialize();
//    Note n = model.getNotesAtBeat(0).get(1);
//    assertEquals(0, n.getStartFrom());
//    assertEquals(52, n.toInt());
//    controller.getMouse()
//      .mouseClicked(new MouseEvent(view.getDisplayPanel(), 1, 1, 1, 50, 90, 1, true));
//    controller.getMouse()
//      .mousePressed(new MouseEvent(view.getDisplayPanel(), 1, 1, 1, 50, 90, 1, true));
//    controller.getMouse()
//      .mouseReleased(new MouseEvent(view.getDisplayPanel(), 1, 1, 1, 50, 150, 1, true));
//    controller.getKbd()
//      .keyReleased(new KeyEvent(view.getDisplayPanel(), 1, 1, 1, KeyEvent.VK_M, 'm'));
//    assertEquals(0, n.getStartFrom());
//    assertEquals(49, n.toInt());
//
//    controller.getMouse()
//      .mouseClicked(new MouseEvent(view.getDisplayPanel(), 1, 1, 1, 50, 150, 1, true));
//    controller.getMouse()
//      .mousePressed(new MouseEvent(view.getDisplayPanel(), 1, 1, 1, 50, 150, 1, true));
//    controller.getMouse()
//      .mouseReleased(new MouseEvent(view.getDisplayPanel(), 1, 1, 1, 150, 150, 1, true));
//    controller.getKbd()
//      .keyReleased(new KeyEvent(view.getDisplayPanel(), 1, 1, 1, KeyEvent.VK_M, 'm'));
//    assertEquals(5, n.getStartFrom());
//    assertEquals(49, n.toInt());
//
//    controller.getMouse()
//      .mouseClicked(new MouseEvent(view.getDisplayPanel(), 1, 1, 1, 150, 150, 1, true));
//    controller.getMouse()
//      .mousePressed(new MouseEvent(view.getDisplayPanel(), 1, 1, 1, 150, 150, 1, true));
//    controller.getMouse()
//      .mouseReleased(new MouseEvent(view.getDisplayPanel(), 1, 1, 1, 50, 90, 1, true));
//    controller.getKbd()
//      .keyReleased(new KeyEvent(view.getDisplayPanel(), 1, 1, 1, KeyEvent.VK_M, 'm'));
//    assertEquals(0, n.getStartFrom());
//    assertEquals(52, n.toInt());
//  }
//
//  @Test
//  public void testDuration() {
//    initialize();
//    Note n = model.getNotesAtBeat(0).get(1);
//    assertEquals(2, n.getDuration());
//    controller.getMouse()
//      .mouseClicked(new MouseEvent(view.getDisplayPanel(), 1, 1, 1, 50, 90, 1, true));
//    controller.getMouse()
//      .mousePressed(new MouseEvent(view.getDisplayPanel(), 1, 1, 1, 50, 90, 1, true));
//    controller.getMouse()
//      .mouseReleased(new MouseEvent(view.getDisplayPanel(), 1, 1, 1, 150, 90, 1, true));
//    controller.getKbd()
//      .keyReleased(new KeyEvent(view.getDisplayPanel(), 1, 1, 1, KeyEvent.VK_D, 'd'));
//    assertEquals(6, n.getDuration());
//  }

  @Test
  public void testArrowKey() {
    initialize();
    assertEquals(0, view.getScrollPane().getHorizontalScrollBar().getValue());
    assertEquals(0, view.getScrollPane().getVerticalScrollBar().getValue());
    view.controlPanel(model, "D");
    assertEquals(0, view.getScrollPane().getHorizontalScrollBar().getValue());
    assertEquals(20, view.getScrollPane().getVerticalScrollBar().getValue());
    view.controlPanel(model, "U");
    assertEquals(0, view.getScrollPane().getHorizontalScrollBar().getValue());
    assertEquals(0, view.getScrollPane().getVerticalScrollBar().getValue());
    view.controlPanel(model, "R");
    assertEquals(20, view.getScrollPane().getHorizontalScrollBar().getValue());
    assertEquals(0, view.getScrollPane().getVerticalScrollBar().getValue());
    view.controlPanel(model, "L");
    assertEquals(0, view.getScrollPane().getHorizontalScrollBar().getValue());
    assertEquals(0, view.getScrollPane().getVerticalScrollBar().getValue());
  }

  @Test
  public void testHomeEnd() {
    initialize();
    assertEquals(0, view.getScrollPane().getHorizontalScrollBar().getValue());
    view.controlPanel(model, "E");
    assertEquals(419, view.getScrollPane().getHorizontalScrollBar().getValue());
    view.controlPanel(model, "H");
    assertEquals(0, view.getScrollPane().getHorizontalScrollBar().getValue());
  }
}
