package cs3500.music.model;

import java.util.*;

/**
 * Represent a generic model for the music editor
 */
public interface Model {
  /**
   * Edit the existing element
   *
   * @param n the note we edit
   * @param p the pitch we edit
   * @param d the duration we edit
   * @param s the startFrom we edit
   */
  void edit(Note n, int p, int d, int s);

  /**
   * Add a new note
   *
   * @param n the note we add
   */
  void add(Note n);

  /**
   * Remove the existing element
   *
   * @param n the note we remove
   */
  void remove(Note n);

  /**
   * Combine two pieces of music such that they play simultaneously
   *
   * @param music the piece of music we combine with
   */
  void combineS(List<Note> music);

  /**
   * Combine two pieces of music such that they play consecutively
   *
   * @param music the piece of music we combine with
   */
  void combineC(List<Note> music);

  /**
   * display the view of the music editor
   *
   * @return a string contains the view of the editor
   */
  String display();

  /**
   * Get the highest pitch of the model as an integer
   *
   * @return a int represent the highest pitch in a music
   */
  int getHighestPitch();

  /**
   * Get the lowest pitch of the model as an integer
   *
   * @return a int represent the lowest pitch in a music
   */
  int getLowestPitch();

  /**
   * Get the maximum beat of the model
   *
   * @return a int represent the last beat in a music
   */
  int getMaxBeats();

  /**
   * Get all the notes in the model
   *
   * @return List<Note> </Note> contains all notes
   */
  List<Note> getAllNotes();

  /**
   * Determine if the model has notes on this beat
   *
   * @param Beat
   * @return boolean to check if the model contains the key
   */
  boolean containsKey(int Beat);

  /**
   * Get all the notes at this beat
   *
   * @param beat
   * @return List<Note></Note> representing all notes in a single beat
   * @throws IllegalArgumentException
   */
  List<Note> getNotesAtBeat(int beat);

  /**
   * Set a tempo to the model
   *
   * @param tempo
   */
  void setTempo(int tempo);

  /**
   * Get the tempo of the model
   *
   * @return int representing tempo
   */
  int getTempo();
}
