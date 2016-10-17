package cs3500.music.model;

import java.util.*;
import cs3500.music.util.CompositionBuilder;


/**
 * Represent a music editor
 */
public class ModelImpl implements Model {
  private Map<Integer, List<Note>> noteMap;
  private int highestPitch;
  private int lowestPitch;
  private int maxBeats;
  private int tempo;

  /**
   * initialize constructor
   */
  public ModelImpl() {

    this.noteMap = new TreeMap<>();
    this.tempo = 0;
  }

  /**
   * Construct a constructor
   */
  public ModelImpl(List<Note> notes) {
    this.noteMap = new TreeMap<>();
    notes.forEach(this::add);
    highestPitch();
    lowestPitch();
    maxBeats();
  }

  private void highestPitch() {
    this.highestPitch = 0;
    noteMap.values().forEach((notes) -> notes.forEach((n) -> {
      if (n.toInt() > highestPitch) {
        highestPitch = n.toInt();
      }
    }));
  }

  private void lowestPitch() {
    this.lowestPitch = 100000;
    noteMap.values().forEach((notes) -> notes.forEach((n) -> {
      if (n.toInt() < lowestPitch) {
        lowestPitch = n.toInt();
      }
    }));
  }

  private void maxBeats() {
    this.maxBeats = 0;
    noteMap.values().forEach((notes) -> notes.forEach((n) -> {
      if (n.getDuration() + n.getStartFrom() - 1 > maxBeats) {
        maxBeats = n.getDuration() + n.getStartFrom() - 1;
      }
    }));
  }

  @Override
  /**
   * Edit the existing element
   *
   * @param n the note we edit
   * @param p the pitch we edit
   * @param d the duration we edit
   * @param s the startFrom we edit
   */
  public void edit(Note n, int p, int d, int s) {
    remove(n);
    n.edit(p, d, s);
    add(n);
    highestPitch();
    lowestPitch();
    maxBeats();
  }

  @Override
  public void add(Note n) {
    List<Note> notes;
    if (!this.noteMap.containsKey(n.getStartFrom())) {
      noteMap.put(n.getStartFrom(), new ArrayList<>(Arrays.asList(n)));
    }
    else {
      notes = noteMap.get(n.getStartFrom());
      if (!notes.contains(n)) {
        notes.add(n);
      }
    }
    highestPitch();
    lowestPitch();
    maxBeats();
  }

  @Override
  public void remove(Note n) {
    int idx = n.getStartFrom();
    if (!noteMap.containsKey(idx) || !noteMap.get(idx).contains(n)) {
      throw new IllegalArgumentException("The music don't contain this note");
    }
    noteMap.get(idx).remove(n);
    highestPitch();
    lowestPitch();
    maxBeats();
  }

  @Override
  public void combineS(List<Note> music) {
    music.forEach((n) -> {
      int temp1 = highestPitch;
      int temp2 = lowestPitch;
      int temp3 = maxBeats;
      add(n);
      highestPitch = temp1;
      lowestPitch = temp2;
      maxBeats = temp3;
    });
    highestPitch();
    lowestPitch();
    maxBeats();
  }

  @Override
  public void combineC(List<Note> music) {
    music.forEach((n) -> {
      int temp1 = highestPitch;
      int temp2 = lowestPitch;
      int temp3 = maxBeats;
      n.edit(0, 0, maxBeats + 1);
      add(n);
      highestPitch = temp1;
      lowestPitch = temp2;
      maxBeats = temp3;
    });
    highestPitch();
    lowestPitch();
    maxBeats();
  }

  private StringBuilder addSpace(StringBuilder str, int digit) {
    if (digit < 0) {
      throw new IllegalArgumentException("Digit cannot be negative");
    }
    int i = 0;
    while (i < digit) {
      str.insert(0, " ");
      i++;
    }
    return str;
  }

  @Override
  public String display() {
    if (this.noteMap.isEmpty()) {
      return "";
    }
    StringBuilder sb = new StringBuilder();
    int digit = String.valueOf(maxBeats).length();
    addSpace(sb, digit);
    for (int temp = lowestPitch; temp <= highestPitch; temp++) {
      sb.append(Note.convertToNote(temp).toString());
    }
    sb.append("\n");
    for (int i = 0; i <= maxBeats; i++) {
      StringBuilder beats = new StringBuilder().append(i);
      addSpace(beats, digit - String.valueOf(i).length());
      sb.append(beats);
      for (int j = lowestPitch; j < highestPitch + 1; j++) {
        sb.append("     ");
      }
      sb.append("\n");
    }
    for (int i = 0; i <= maxBeats; i++) {
      for (int j = lowestPitch; j < highestPitch + 1; j++) {
        if (noteMap.containsKey(i)) {
          int line = (digit + (highestPitch - lowestPitch + 1) * 5 + 1);
          for (Note n : noteMap.get(i)) {
            int noteHead = line * (i + 1) + digit + (n.toInt() - lowestPitch) * 5;
            sb.replace(noteHead, noteHead + 5, "  X  ");
            for (int k = 1; k < n.getDuration(); k++) {
              int noteSustain = noteHead + line * k;
              sb.replace(noteSustain, noteSustain + 5, "  |  ");
            }
          }
        }
      }
    }
    return sb.toString();
  }

  public Map<Integer, List<Note>> getNoteMap() {
    return noteMap;
  }

  @Override
  public int getHighestPitch() {
    return highestPitch;
  }

  @Override
  public int getLowestPitch() {
    return lowestPitch;
  }

  @Override
  public int getMaxBeats() {
    return maxBeats;
  }

  @Override
  public List<Note> getAllNotes() {
    List<Note> result = new ArrayList<>(Arrays.asList());
    for (int i = 0; i < this.maxBeats; i++) {
      if (this.noteMap.containsKey(i)) {
        for(Note n : this.getNotesAtBeat(i)) {
          result.add(n);
        }
      }
    }
    return result;
  }

  @Override
  public boolean containsKey(int Beat) {
    return this.noteMap.containsKey(Beat);
  }

  @Override
  public List<Note> getNotesAtBeat(int beat) {
    if (this.containsKey(beat)) {
      return this.noteMap.get(beat);
    }
    else {
      throw new IllegalArgumentException("This beat doesn't exist in the model");
    }
  }

  @Override
  public void setTempo(int tempo) {
    this.tempo = tempo;
  }

  @Override
  public int getTempo() { return this.tempo;}


  public static final class Builder implements CompositionBuilder<Model> {
    private Model model;

    /**
     * Constructor for Builder
     */
    public Builder() {
      this.model = new ModelImpl();
    }

    @Override
    public Model build() {
      return this.model;
    }

    @Override
    public CompositionBuilder<Model> setTempo(int tempo) {
      this.model.setTempo(tempo);

      return this;
    }

    @Override
    public CompositionBuilder<Model> addNote
      (int start, int end, int instrument, int pitch, int volume) {
      Note n = Note.convertToNote(pitch - 12);
      Note.Pitch newPitch = n.getPitch();
      int newOctave =  n.getOctave();
      int duration = end - start;
      Note newNote = new Note(newPitch, newOctave, duration, start, instrument, volume);
      this.model.add(newNote);
      return this;
    }
  }
}
