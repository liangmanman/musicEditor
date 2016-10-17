package cs3500.music.controller;

import java.util.*;
import java.awt.event.*;

import cs3500.music.model.*;
import cs3500.music.view.*;

import javax.swing.*;

/**
 * Represent an implementation of the controller
 */
public class ControllerImpl implements Controller {
  private Model model;
  private View view;

  /**
   * Default Constructor
   *
   * @param model the model of the music editor
   * @param view  the view of the music editor
   */
  public ControllerImpl(Model model, View view) {
    this.model = model;
    this.view = view;

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
