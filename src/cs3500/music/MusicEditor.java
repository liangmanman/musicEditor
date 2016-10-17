package cs3500.music;

import cs3500.music.controller.Controller;
import cs3500.music.controller.Controller2Impl;
import cs3500.music.controller.ControllerImpl;
import cs3500.music.model.*;
import cs3500.music.util.*;
import cs3500.music.view.*;

import java.io.*;
import javax.sound.midi.InvalidMidiDataException;

public class MusicEditor {

  public static void main(String[] args)
    throws IOException, InvalidMidiDataException, InterruptedException {

    MusicReaderWithRepeat reader = new MusicReaderWithRepeat();
    CompositionBuilderWithRepeat<Model> builder = new ModelWithRepeat.Builder();

    String fileName = args[0];
    File file = new File(fileName);
    FileReader fileReader = new FileReader(file);
    Model model = reader.parseFile(fileReader, builder);

    ModelWithRepeat re = (ModelWithRepeat)model;



    String viewType = args[1];
    View view = ViewFactory.create(viewType);

    if (view instanceof GuiView) {
      GuiView view1 = (GuiView) view;
      Controller controller = new Controller2Impl(model, view1);
    }
    else {
      view.display(model);
    }
  }
}
