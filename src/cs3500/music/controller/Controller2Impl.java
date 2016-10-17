package cs3500.music.controller;

import java.util.*;
import java.awt.event.*;

import cs3500.music.model.*;
import cs3500.music.view.*;

import javax.swing.*;

/**
 * Represent an implementation of the controller
 */
public class Controller2Impl implements Controller {
  private Model model;
  private GuiView view;
  private KeyListener kbd;
  private MouseListener mouse;

  /**
   * Default Constructor
   *
   * @param model the model of the music editor
   * @param view  the view of the music editor
   */
  public Controller2Impl(Model model, GuiView view) {
    this.model = model;
    this.view = view;
    this.view.display(this.model);
    configure();
    this.view.resetFocus();
  }

  /**
   * Creates and sets a keyboard listener for the view. In effect it creates snippets of code as
   * Runnable object, one for each time a key is typed, pressed and released, only for those that
   * the program needs
   */
  private void configure() {
    Map<Integer, Runnable> keysTyped = new HashMap<>();
    Map<Integer, Runnable> keysPressed = new HashMap<>();
    Map<Integer, Runnable> keysReleased = new HashMap<>();
    MouseHandler mouse = new MouseHandler();

    // Add a note with default instrument(1) and volume(0)
    keysPressed.put(KeyEvent.VK_A, () -> {
      String input = "";
      int i;
      Note n;
      while (true) {
        try {
          int beat = mouse.getP().x;
          int pitch = model.getHighestPitch() - mouse.getP().y;
          n = Note.convertToNote(pitch);
          input =
            JOptionPane.showInputDialog(null, "Length：\n", "Select a positive integer").trim();
          i = Integer.parseInt(input) - 1;
          n.edit(0, i, beat);
          model.add(n);
          view.repaint();
          break;
        } catch (Exception e) {
          if (e.getMessage() == null) {
            break;
          }
        }
      }
    });

    // Remove an existing note
    keysPressed.put(KeyEvent.VK_R, () -> {
      Note n = view.getNote(mouse.getP());
      if (n != null) {
        model.remove(n);
        view.repaint();
      }
    });

    // Move an existing note
    keysReleased.put(KeyEvent.VK_M, () -> {
      Note n = view.getDisplayPanel().getNote(mouse.getP());
      if (n != null) {
        int x = mouse.getEndP().x - mouse.getStartP().x;
        int y = mouse.getStartP().y - mouse.getEndP().y;
        model.edit(n, y, 0, x);
        view.repaint();
      }
    });

    // Edit the duration of the note
    keysReleased.put(KeyEvent.VK_D, () -> {
      Note n = view.getDisplayPanel().getNote(mouse.getP());
      if (n != null) {
        int x = mouse.getEndP().x - mouse.getStartP().x - n.getDuration() + 1;
        try {
          model.edit(n, 0, x, 0);
          view.repaint();
        } catch (Exception ignored) {
        }
      }
    });

    // Edit the instrument of the note
    keysPressed.put(KeyEvent.VK_I, () -> {
      Note n = view.getDisplayPanel().getNote(mouse.getP());
      if (n != null) {
        String s = "";
        int i;
        while (true) {
          try {
            s = JOptionPane.showInputDialog(view.getDisplayPanel(), "Instrument: \n",
              "Select a number from 1 to 128").trim();
            i = Integer.parseInt(s);
            n.editVolInst(i, n.getVolume());
            view.repaint();
            break;
          } catch (Exception e) {
            if (e.getMessage() == null) {
              break;
            }
          }
        }
      }
    });

    // Edit the volume of the note
    keysPressed.put(KeyEvent.VK_V, () -> {
      Note n = view.getDisplayPanel().getNote(mouse.getP());
      if (n != null) {
        String s = "";
        int i;
        while (true) {
          try {
            s = JOptionPane.showInputDialog(view.getDisplayPanel(), "Volume: \n",
              "Select a number from 0 to 100").trim();
            i = Integer.parseInt(s);
            n.editVolInst(n.getInstrument(), i);
            view.repaint();
            break;
          } catch (Exception e) {
            if (e.getMessage() == null) {
              break;
            }
          }
        }
      }
    });

    // Scroll through a composition with the arrow keys
    keysPressed.put(KeyEvent.VK_UP, () -> {
      view.controlPanel(model, "U");
    });

    keysPressed.put(KeyEvent.VK_DOWN, () -> {
      view.controlPanel(model, "D");
    });

    keysPressed.put(KeyEvent.VK_LEFT, () -> {
      view.controlPanel(model, "L");
    });

    keysPressed.put(KeyEvent.VK_RIGHT, () -> {
      view.controlPanel(model, "R");
    });

    // Jump to the beginning or end of the composition, via the Home or End keys
    keysPressed.put(KeyEvent.VK_HOME, () -> {
      view.controlPanel(model, "H");
    });

    keysPressed.put(KeyEvent.VK_END, () -> {
      view.controlPanel(model, "E");
    });

    // Pause or Resume
    keysPressed.put(KeyEvent.VK_SPACE, () -> {
      view.pause();
    });

    // Start the music
    keysPressed.put(KeyEvent.VK_P, () -> {
      view.play(this.model);
    });

    KeyboardHandler kbd = new KeyboardHandler();
    kbd.setKeysTyped(keysTyped);
    kbd.setKeysPressed(keysPressed);
    kbd.setKeysReleased(keysReleased);
    this.kbd = kbd;
    this.mouse = mouse;
    view.addKeyListener(kbd);
    view.addMouseListener(mouse);
  }

  public KeyListener getKbd() {
    return kbd;
  }

  public MouseListener getMouse() {
    return mouse;
  }

  @Override
  public void display() {
    if (view instanceof GuiView) {
      GuiView view1 = (GuiView) view;
      view1.display(model);
    }
    else {
      view.display(model);
    }
  }
}
