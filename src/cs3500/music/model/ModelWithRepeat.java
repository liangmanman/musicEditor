package cs3500.music.model;

import java.util.ArrayList;
import java.util.List;

import cs3500.music.util.CompositionBuilder;
import cs3500.music.util.CompositionBuilderWithRepeat;

/**
 * Created by liangmanman1 on 4/25/16.
 */
public class ModelWithRepeat extends ModelImpl {
  public List<Integer> invertedRepeatBeats;
  public List<Integer> repeatBeats;
  public List<Integer> endingBeats;

  public ModelWithRepeat() {
    super();
    this.invertedRepeatBeats = new ArrayList<>();
    this.repeatBeats = new ArrayList<>();
    this.endingBeats = new ArrayList<>();
  }

  public void setBeats(int[] beats, String str) {
    switch (str) {
      case "inverted-repeat":
        for (int n : beats) {
          invertedRepeatBeats.add(n);
        }
        break;
      case "repeat":
        for (int n : beats) {
          repeatBeats.add(n);
        }
        break;
      case "ending":
        for (int n : beats) {
          endingBeats.add(n);
        }
        break;
      default:
        throw new IllegalArgumentException("Malformed repeat symbol");
    }
  }

  public static final class Builder implements CompositionBuilderWithRepeat<Model> {
    private Model model;

    /**
     * Constructor for Builder
     */
    public Builder() {
      this.model = new ModelWithRepeat();
    }


    @Override
    public void setBeats(int[] beats, String str) {
      if (!(model instanceof ModelWithRepeat)) {
        throw new IllegalArgumentException("Malformed model");
      }
      ModelWithRepeat modelWithRepeat = (ModelWithRepeat) model;
      modelWithRepeat.setBeats(beats, str);
    }

    @Override
    public Model build() {
      return model;
    }

    @Override
    public CompositionBuilder<Model> setTempo(int tempo) {
      model.setTempo(tempo);
      return this;
    }

    @Override
    public CompositionBuilder<Model> addNote(int start, int end, int instrument, int pitch,
      int volume) {
      Note n = Note.convertToNote(pitch - 12);
      Note.Pitch newPitch = n.getPitch();
      int newOctave = n.getOctave();
      int duration = end - start;
      Note newNote = new Note(newPitch, newOctave, duration, start, instrument, volume);
      this.model.add(newNote);
      return this;
    }
  }
}
