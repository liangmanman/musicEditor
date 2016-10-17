package cs3500.music.tests;

import cs3500.music.model.Note;
import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by lgd on 2016/3/1.
 */
public class NoteTest {
  Note n1 = new Note(Note.Pitch.Cn, 3, 2, 0, 1, 72);
  Note n2 = new Note(Note.Pitch.Cn, 4, 8, 1, 1, 72);
  Note n3 = new Note(Note.Pitch.Cn, 4, 6, 2, 1, 71);
  Note n4 = new Note(Note.Pitch.Ds, 4, 4, 0, 1, 72);
  Note n5 = new Note(Note.Pitch.Bn, 3, 2, 1, 1, 72);
  Note n6 = new Note(Note.Pitch.Bn, 10, 2, 2, 1, 72);
  Note n7 = new Note(Note.Pitch.Ds, 10, 2, 2, 1, 72);

  @Test(expected = RuntimeException.class)
  public void constructorInvalid1() {
    Note n0 = new Note(Note.Pitch.Cn, -1, 2, 0, 1, 72);
  }

  @Test(expected = RuntimeException.class)
  public void constructorInvalid2() {
    Note n0 = new Note(Note.Pitch.Cn, 1, -2, 0, 1, 72);
  }

  @Test(expected = RuntimeException.class)
  public void constructorInvalid3() {
    Note n0 = new Note(Note.Pitch.Cn, 1, 2, -2, 1, 72);
  }

  @Test
  public void testGetPitch() {
    assertEquals(Note.Pitch.Cn, n1.getPitch());
    assertEquals(Note.Pitch.Ds, n4.getPitch());
    assertEquals(Note.Pitch.Bn, n6.getPitch());
    assertEquals(Note.Pitch.Ds, n7.getPitch());
  }

  @Test
  public void testGetOctave() {
    assertEquals(3, n1.getOctave());
    assertEquals(4, n2.getOctave());
    assertEquals(3, n5.getOctave());
    assertEquals(10, n7.getOctave());
  }

  @Test
  public void testGetDuration() {
    assertEquals(8, n2.getDuration());
    assertEquals(6, n3.getDuration());
    assertEquals(4, n4.getDuration());
    assertEquals(2, n5.getDuration());
  }

  @Test
  public void testGetStartFrom() {
    assertEquals(0, n1.getStartFrom());
    assertEquals(1, n2.getStartFrom());
    assertEquals(2, n3.getStartFrom());
    assertEquals(2, n6.getStartFrom());
  }

  @Test
  public void testCompareTo() {
    assertTrue(n1.compareTo(n2) < 0);
    assertTrue(n2.compareTo(n4) < 0);
    assertTrue(n2.compareTo(n3) == 0);
    assertTrue(n4.compareTo(n3) > 0);
    assertTrue(n5.compareTo(n1) > 0);
  }

  @Test
  public void testToInt() {
    assertEquals(36, n1.toInt());
    assertEquals(48, n2.toInt());
    assertEquals(51, n4.toInt());
    assertEquals(131, n6.toInt());
  }

  @Test
  public void testToString() {
    assertEquals("  C3 ", n1.toString());
    assertEquals(" D#4 ", n4.toString());
    assertEquals(" B10 ", n6.toString());
    assertEquals(" D#10", n7.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void editInvalid() {
    Note n = new Note(Note.Pitch.Cn, 3, 2, 0, 1, 71);
    n.edit(-1, -1, -1);
  }

  @Test
  public void testEdit() {
    Note n = new Note(Note.Pitch.Cn, 3, 2, 0, 1, 71);
    assertEquals(Note.Pitch.Cn, n.getPitch());
    n.edit(1, 0, 0);
    assertEquals(Note.Pitch.Cs, n.getPitch());
    assertEquals(3, n.getOctave());
    n.edit(12, 0, 0);
    assertEquals(4, n.getOctave());
    assertEquals(2, n.getDuration());
    n.edit(0, 1, 0);
    assertEquals(3, n.getDuration());
    assertEquals(0, n.getStartFrom());
    n.edit(0, 0, 10);
    assertEquals(10, n.getStartFrom());
  }

  @Test
  public void testEqual() {
    Note n33 = new Note(Note.Pitch.Cn, 4, 6, 2, 1, 71);
    Note n44 = new Note(Note.Pitch.Ds, 4, 4, 0, 1, 72);
    Note n55 = new Note(Note.Pitch.Bn, 3, 2, 1, 1, 72);
    assertTrue(n3.equals(n33));
    assertTrue(n4.equals(n44));
    assertTrue(n5.equals(n55));
  }

  @Test
  public void testHashCode() {
    assertEquals(Objects.hashCode(n1), n1.hashCode());
    assertEquals(Objects.hashCode(n4), n4.hashCode());
    assertEquals(Objects.hashCode(n7), n7.hashCode());
  }
}
