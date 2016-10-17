package cs3500.music.model;

import java.util.Objects;

/**
 * Represent a music note
 */
public class Note {
  private Pitch pitch;
  private int octave;
  private int duration;
  private int startFrom;
  private int instrument;
  private int volume;

  /**
   * Constructs a Note
   *
   * @param pitch      the pitch of the note
   * @param octave     the octave of the note
   * @param duration   the duration of the note with unit beat
   * @param startFrom  the startFrom of the note with unit beat
   * @param instrument the instrument of the beat
   * @param volume     the volume of the beat
   */
  public Note(Pitch pitch, int octave, int duration, int startFrom, int instrument, int volume) {
    if (octave < 0 || duration <= 0 || startFrom < 0 || instrument < 1 || instrument > 128 ||
      volume < 0 || volume > 128) {
      throw new RuntimeException("Note cannot have negative elements");
    }
    this.pitch = pitch;
    this.octave = octave;
    this.duration = duration;
    this.startFrom = startFrom;
    this.instrument = instrument;
    this.volume = volume;
  }

  public enum Pitch {
    Cn, Cs, Dn, Ds, En, Fn, Fs, Gn, Gs, An, As, Bn;

    @Override
    public String toString() {
      switch (this) {
        case Cn:
          return "C";
        case Cs:
          return "C#";
        case Dn:
          return "D";
        case Ds:
          return "D#";
        case En:
          return "E";
        case Fn:
          return "F";
        case Fs:
          return "F#";
        case Gn:
          return "G";
        case Gs:
          return "G#";
        case An:
          return "A";
        case As:
          return "A#";
        case Bn:
          return "B";
      }
      return "";
    }
  }

  /**
   * @return Pitch of this note in Pitch
   */
  public Pitch getPitch() {
    return pitch;
  }

  /**
   * @return octave of this note in int
   */
  public int getOctave() {
    return octave;
  }

  /**
   * @return duration of the note in beats
   */
  public int getDuration() {
    return duration;
  }

  /**
   * @return startFrom of the note in beats
   */
  public int getStartFrom() {
    return startFrom;
  }

  /**
   * @return instrument of the note in int
   */
  public int getInstrument() {
    return instrument;
  }

  /**
   * @return volume of the note in int
   */
  public int getVolume() {
    return volume;
  }

  /**
   * Compare two notes, determining which one comes before
   *
   * @param n the note to compare with
   */
  public int compareTo(Note n) {
    if (octave - n.octave < 0) {
      return -1;
    }
    else if (octave - n.octave > 0) {
      return 1;
    }
    else {
      return pitch.compareTo(n.pitch);
    }
  }

  /**
   * convert the note to an integer
   *
   * @return a converted integer
   */
  public int toInt() {
    int n = octave * 12;
    switch (pitch) {
      case Cn:
        return n;
      case Cs:
        return n + 1;
      case Dn:
        return n + 2;
      case Ds:
        return n + 3;
      case En:
        return n + 4;
      case Fn:
        return n + 5;
      case Fs:
        return n + 6;
      case Gn:
        return n + 7;
      case Gs:
        return n + 8;
      case An:
        return n + 9;
      case As:
        return n + 10;
      case Bn:
        return n + 11;
    }
    return -1;
  }

  /**
   * @return Pitch in String
   */
  public String toString() {
    String pitch = this.pitch.toString();
    String str = " " + pitch + octave;
    if (pitch.length() == 1 && octave < 10) {
      return " " + str + " ";
    }
    else if (pitch.length() == 1 || octave < 10) {
      return str + " ";
    }
    else {
      return str;
    }
  }

  /**
   * Convert a number to an empty note
   *
   * @param value the value we want to convert
   * @return a new Note with specific pitch and octave value
   */
  public static Note convertToNote(int value) {
    if (value < 0) {
      throw new IllegalArgumentException("Value cannot be negative");
    }
    Pitch[] pitches = Pitch.values();
    return new Note(pitches[value % 12], value / 12, 1, 0, 1, 0);
  }

  /**
   * Edit the note's duration and startFrom
   *
   * @param pitch the pitch we want to change
   * @param dur   the duration we want to change
   * @param from  the startFrom we want to change
   */
  public void edit(int pitch, int dur, int from) {
    Pitch[] pitches = Pitch.values();
    int newP = toInt() + pitch;
    int newD = duration + dur;
    int newS = startFrom + from;
    if (newP < 0 || newD <= 0 || newS < 0) {
      throw new IllegalArgumentException("The input is invalid");
    }
    this.pitch = pitches[newP % 12];
    this.octave = newP / 12;
    this.duration = newD;
    this.startFrom = newS;
  }

  /**
   * Edit the note's instrument and volume
   *
   * @param instrument the instrument we want to change
   * @param volume the volume we want to change
   */
  public void editVolInst(int instrument, int volume) {
    if (instrument < 1 || instrument > 128 || volume < 0 || volume > 100) {
      throw new IllegalArgumentException("The input is invalid");
    }
    this.instrument = instrument;
    this.volume = volume;

  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Note)) {
      return false;
    }
    Note note = (Note) o;
    return this.pitch == note.pitch && this.octave == note.octave &&
      this.duration == note.duration && this.startFrom == note.startFrom &&
      this.instrument == note.instrument && this.volume == note.volume;
  }

  @Override
  public int hashCode() {
    return Objects.hash(pitch, octave, duration, startFrom, instrument, volume);
  }

  /**
   * @return int representing pitch & octave in Int, in modified version
   */
  public int pitchInInt() {
    return 12 * (octave + 1) + pitch.ordinal();
  }
}
