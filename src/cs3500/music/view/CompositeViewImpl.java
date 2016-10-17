package cs3500.music.view;

import cs3500.music.model.Model;
import cs3500.music.model.ModelWithRepeat;
import cs3500.music.model.Note;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

/**
 * Represent a composite view, which includes both gui view and midi vies
 */
public class CompositeViewImpl implements GuiView {
  private GuiViewImpl guiView;
  private MidiViewImpl midiView;
  private Timer timer;
  private int count;
  private int i;
  private int lastRepeat;

  public CompositeViewImpl() {
    this.guiView = new GuiViewImpl();
    this.midiView = new MidiViewImpl();
    this.count = 0;
    this.i = 0;
    lastRepeat = 0;
  }

  @Override
  public void display(Model model) {
    guiView.display(model);
  }

  @Override
  public void play(Model model) {
    guiView.set(0);
    midiView.setCurrentBeat(0);
    if (timer == null) {
      this.timer = new Timer(model.getTempo() / 20000, new ActionHandler(model));
    }
    timer.restart();
  }

  @Override
  public void pause() {
    if (timer != null)
      if (timer.isRunning()) {
        timer.stop();
      }
      else {
        timer.start();
      }
  }

  @Override
  public void set(int n) {
    guiView.set(n);
  }

  @Override
  public ConcreteGuiViewPanel getDisplayPanel() {
    return guiView.getDisplayPanel();
  }

  @Override
  public void repaint() {
    guiView.repaint();
  }

  @Override
  public void addKeyListener(KeyListener listener) {
    guiView.addKeyListener(listener);
  }

  @Override
  public void addMouseListener(MouseListener listener) {
    guiView.addMouseListener(listener);
  }

  @Override
  public void resetFocus() {
    guiView.resetFocus();
  }

  @Override
  public void controlPanel(Model model, String s) {
    guiView.controlPanel(model, s);
  }

  @Override
  public JScrollPane getScrollPane() {
    return guiView.getScrollPane();
  }

  @Override
  public Note getNote(Point p) {
    return guiView.getNote(p);
  }

  private class ActionHandler implements ActionListener {
    private Model model;

    public ActionHandler(Model model) {
      this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      count++;

      if (timer.isRunning() && count >= 40) {
        // midi part
        if (count % 20 == 0) {
          midiView.displayOneBeat(model);
        }

        // gui part
        guiView.getDisplayPanel().setLineX(guiView.getDisplayPanel().getLineX() + 1);
        guiView.repaint();
      }

      // repeat part
      if (model instanceof ModelWithRepeat) {
        ModelWithRepeat repeat = (ModelWithRepeat) model;

        if (repeat.repeatBeats.size() == i ) {

        }
        else if (midiView.getCurrentBeat() == repeat.repeatBeats.get(i)) {

          if (repeat.invertedRepeatBeats.size() <= i ) {
            midiView.setCurrentBeat(0);
            guiView.set(0);
          }
          else {
            midiView.setCurrentBeat(repeat.invertedRepeatBeats.get(i));
            guiView.getDisplayPanel().setLineX(20 * midiView.getCurrentBeat() + 21);
          }
          i++;
        }
      }

    //  System.out.println("beat" + midiView.getCurrentBeat());
      if (midiView.getCurrentBeat() == model.getMaxBeats() + 2) {
        timer.stop();
      }
    }
  }
}
