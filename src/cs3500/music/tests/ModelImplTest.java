package cs3500.music.tests;

import cs3500.music.model.ModelImpl;
import cs3500.music.model.Note;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests for the class ModelImpl
 */
public class ModelImplTest {
  Note n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, n16, n17, n18, n19, n20,
    n21, n22, n23, n24, n25, n26, n27, n28, n29, n30, n31, n32, n33, n34, n35;
  List<Note> notes;
  ModelImpl model;
  ModelImpl model2;

  public void initialCondition() {
    n1 = new Note(Note.Pitch.Gn, 3, 7, 0, 1, 71);
    n2 = new Note(Note.Pitch.En, 4, 2, 0, 1, 71);
    n3 = new Note(Note.Pitch.Dn, 4, 2, 2, 1, 71);
    n4 = new Note(Note.Pitch.Cn, 4, 2, 4, 1, 71);
    n5 = new Note(Note.Pitch.Dn, 4, 2, 6, 1, 71);
    n6 = new Note(Note.Pitch.Gn, 3, 7, 8, 1, 71);
    n7 = new Note(Note.Pitch.En, 4, 7, 8, 1, 71);
    n8 = new Note(Note.Pitch.En, 4, 5, 10, 1, 71);
    n9 = new Note(Note.Pitch.En, 4, 3, 12, 1, 71);
    n10 = new Note(Note.Pitch.Gn, 3, 8, 16, 1, 71);
    n11 = new Note(Note.Pitch.Dn, 4, 2, 16, 1, 71);
    n12 = new Note(Note.Pitch.Dn, 4, 2, 18, 1, 71);
    n13 = new Note(Note.Pitch.Dn, 4, 4, 20, 1, 71);
    n14 = new Note(Note.Pitch.Gn, 3, 2, 24, 1, 71);
    n15 = new Note(Note.Pitch.En, 4, 2, 24, 1, 71);
    n16 = new Note(Note.Pitch.Gn, 4, 2, 26, 1, 71);
    n17 = new Note(Note.Pitch.Gn, 4, 4, 28, 1, 71);
    n18 = new Note(Note.Pitch.Gn, 3, 8, 32, 1, 71);
    n19 = new Note(Note.Pitch.En, 4, 2, 32, 1, 71);
    n20 = new Note(Note.Pitch.Dn, 4, 2, 34, 1, 71);
    n21 = new Note(Note.Pitch.Cn, 4, 2, 36, 1, 71);
    n22 = new Note(Note.Pitch.Dn, 4, 2, 38, 1, 71);
    n23 = new Note(Note.Pitch.Gn, 3, 8, 40, 1, 71);
    n24 = new Note(Note.Pitch.En, 4, 2, 40, 1, 71);
    n25 = new Note(Note.Pitch.En, 4, 2, 42, 1, 71);
    n26 = new Note(Note.Pitch.En, 4, 2, 44, 1, 71);
    n27 = new Note(Note.Pitch.En, 4, 2, 46, 1, 71);
    n28 = new Note(Note.Pitch.Gn, 3, 8, 48, 1, 71);
    n29 = new Note(Note.Pitch.Dn, 4, 2, 48, 1, 71);
    n30 = new Note(Note.Pitch.Dn, 4, 2, 50, 1, 71);
    n31 = new Note(Note.Pitch.En, 4, 2, 52, 1, 71);
    n32 = new Note(Note.Pitch.Dn, 4, 2, 54, 1, 71);
    n33 = new Note(Note.Pitch.Gn, 3, 8, 40, 1, 71);
    n34 = new Note(Note.Pitch.En, 3, 8, 56, 1, 71);
    n35 = new Note(Note.Pitch.Cn, 4, 8, 56, 1, 71);
    notes = Arrays
      .asList(n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, n16, n17, n18, n19,
        n20, n21, n22, n23, n24, n25, n26, n27, n28, n29, n30, n31, n32, n33, n34, n35);
    model = new ModelImpl(notes);
    model2 = new ModelImpl(new ArrayList<>());
  }

//  @Test
//  public void testEdit() {
//    initialCondition();
//    Note n = model.getNoteMap().get(0).get(1);
//    assertEquals(n2, n);
//    model.edit(n, -2, 0, 0);
//    assertEquals(new Note(Note.Pitch.Dn, 4, 2, 0, 1, 71), n);
//    model.edit(n, 0, 0, 2);
//    assertEquals(n3, n);
//    Note nn = model.getNoteMap().get(8).get(1);
//    model.edit(nn, 0, -4, 4);
//    assertEquals(n9, nn);
//  }

//  @Test
//  public void testEdit2() {
//    initialCondition();
//    assertEquals(55, model.getHighestPitch());
//    assertEquals(40, model.getLowestPitch());
//    assertEquals(63, model.getMaxBeats());
//    model.edit(model.getNoteMap().get(56).get(0), 20, 0, 0);
//    assertEquals(55, model.getHighestPitch());
//    assertEquals(43, model.getLowestPitch());
//    model.edit(model.getNoteMap().get(56).get(0), 0, 10, 0);
//    assertEquals(73, model.getMaxBeats());
//  }

//  @Test
//  public void testAdd() {
//    initialCondition();
//    assertTrue(model2.getNoteMap().isEmpty());
//    model2.add(n1);
//    model2.add(n2);
//    model2.add(n3);
//    assertEquals(Arrays.asList(n1, n2), model2.getNoteMap().get(0));
//    assertEquals(null, model2.getNoteMap().get(1));
//    assertEquals(Arrays.asList(n3), model2.getNoteMap().get(2));
//    model2.add(n1);
//    assertEquals(Arrays.asList(n1, n2), model2.getNoteMap().get(0));
//    Note n11 = new Note(Note.Pitch.Gn, 3, 3, 0, 1, 71);
//    model2.add(n11);
//    assertEquals(Arrays.asList(n1, n2, n11), model2.getNoteMap().get(0));
//  }

  @Test
  public void testAdd2() {
    initialCondition();
    assertEquals(55, model.getHighestPitch());
    assertEquals(40, model.getLowestPitch());
    assertEquals(63, model.getMaxBeats());
    Note na = new Note(Note.Pitch.Cn, 3, 2, 0, 1, 71);
    Note nb = new Note(Note.Pitch.Gn, 5, 2, 63, 1, 71);
    model.add(na);
    model.add(nb);
    assertEquals(67, model.getHighestPitch());
    assertEquals(36, model.getLowestPitch());
    assertEquals(64, model.getMaxBeats());
  }

  @Test(expected = IllegalArgumentException.class)
  public void removeInvalid1() {
    initialCondition();
    model2.remove(n1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void removeInvalid2() {
    initialCondition();
    Note n = new Note(Note.Pitch.Gn, 3, 3, 0, 1, 71);
    model2.remove(n);
  }

//  @Test
//  public void testRemove() {
//    initialCondition();
//    assertEquals(Arrays.asList(n1, n2), model.getNoteMap().get(0));
//    model.remove(n1);
//    assertEquals(Arrays.asList(n2), model.getNoteMap().get(0));
//    model.remove(n2);
//    assertTrue(model.getNoteMap().get(0).isEmpty());
//  }

//  @Test
//  public void testRemove2() {
//    initialCondition();
//    assertEquals(55, model.getHighestPitch());
//    assertEquals(40, model.getLowestPitch());
//    assertEquals(63, model.getMaxBeats());
//    model.remove(model.getNoteMap().get(26).get(0));
//    model.remove(model.getNoteMap().get(28).get(0));
//    model.remove(model.getNoteMap().get(56).get(0));
//    model.remove(model.getNoteMap().get(56).get(0));
//    assertEquals(52, model.getHighestPitch());
//    assertEquals(43, model.getLowestPitch());
//    assertEquals(55, model.getMaxBeats());
//  }

//  @Test
//  public void testCombineS() {
//    initialCondition();
//    Note na = new Note(Note.Pitch.An, 3, 7, 0, 1, 71);
//    Note nb = new Note(Note.Pitch.Gn, 4, 2, 0, 1, 71);
//    Note nc = new Note(Note.Pitch.Fn, 4, 2, 2, 1, 71);
//    assertEquals(Arrays.asList(n1, n2), model.getNoteMap().get(0));
//    assertEquals(Arrays.asList(n3), model.getNoteMap().get(2));
//    model.combineS(Arrays.asList(na, nb, nc));
//    assertEquals(Arrays.asList(n1, n2, na, nb), model.getNoteMap().get(0));
//    assertEquals(Arrays.asList(n3, nc), model.getNoteMap().get(2));
//  }

  @Test
  public void testCombineS2() {
    initialCondition();
    assertEquals(55, model.getHighestPitch());
    assertEquals(40, model.getLowestPitch());
    assertEquals(63, model.getMaxBeats());
    Note na = new Note(Note.Pitch.Cn, 3, 2, 0, 1, 71);
    Note nb = new Note(Note.Pitch.Gn, 5, 2, 63, 1, 71);
    model.combineS(Arrays.asList(na, nb));
    assertEquals(67, model.getHighestPitch());
    assertEquals(36, model.getLowestPitch());
    assertEquals(64, model.getMaxBeats());
  }

//  @Test
//  public void testCombineC() {
//    initialCondition();
//    Note na = new Note(Note.Pitch.An, 3, 7, 0, 1, 71);
//    Note nb = new Note(Note.Pitch.Gn, 4, 2, 0, 1, 71);
//    Note nc = new Note(Note.Pitch.Fn, 4, 2, 2, 1, 71);
//    assertEquals(Arrays.asList(n1, n2), model.getNoteMap().get(0));
//    assertEquals(Arrays.asList(n3), model.getNoteMap().get(2));
//    model.combineC(Arrays.asList(na, nb, nc));
//    assertEquals(Arrays.asList(n1, n2), model.getNoteMap().get(0));
//    assertEquals(Arrays.asList(n3), model.getNoteMap().get(2));
//    assertEquals(Arrays.asList(na, nb), model.getNoteMap().get(64));
//    assertEquals(Arrays.asList(nc), model.getNoteMap().get(66));
//  }

  @Test
  public void testCombineC2() {
    initialCondition();
    assertEquals(55, model.getHighestPitch());
    assertEquals(40, model.getLowestPitch());
    assertEquals(63, model.getMaxBeats());
    Note na = new Note(Note.Pitch.Cn, 3, 2, 0, 1, 71);
    Note nb = new Note(Note.Pitch.Gn, 5, 2, 0, 1, 71);
    Note nc = new Note(Note.Pitch.Fn, 4, 2, 2, 1, 71);
    model.combineC(Arrays.asList(na, nb, nc));
    assertEquals(67, model.getHighestPitch());
    assertEquals(36, model.getLowestPitch());
    assertEquals(67, model.getMaxBeats());
  }

  @Test
  public void testDisplay() {
    initialCondition();
    assertEquals("", model2.display());
    assertEquals(
      "    E3   F3  F#3   G3  G#3   A3  A#3   B3   C4  C#4   D4  D#4   E4   F4  F#4   G4 \n"
        + " 0                 X                                            X                 \n"
        + " 1                 |                                            |                 \n"
        + " 2                 |                                  X                           \n"
        + " 3                 |                                  |                           \n"
        + " 4                 |                        X                                     \n"
        + " 5                 |                        |                                     \n"
        + " 6                 |                                  X                           \n"
        + " 7                                                    |                           \n"
        + " 8                 X                                            X                 \n"
        + " 9                 |                                            |                 \n"
        + "10                 |                                            X                 \n"
        + "11                 |                                            |                 \n"
        + "12                 |                                            X                 \n"
        + "13                 |                                            |                 \n"
        + "14                 |                                            |                 \n"
        + "15                                                                                \n"
        + "16                 X                                  X                           \n"
        + "17                 |                                  |                           \n"
        + "18                 |                                  X                           \n"
        + "19                 |                                  |                           \n"
        + "20                 |                                  X                           \n"
        + "21                 |                                  |                           \n"
        + "22                 |                                  |                           \n"
        + "23                 |                                  |                           \n"
        + "24                 X                                            X                 \n"
        + "25                 |                                            |                 \n"
        + "26                                                                             X  \n"
        + "27                                                                             |  \n"
        + "28                                                                             X  \n"
        + "29                                                                             |  \n"
        + "30                                                                             |  \n"
        + "31                                                                             |  \n"
        + "32                 X                                            X                 \n"
        + "33                 |                                            |                 \n"
        + "34                 |                                  X                           \n"
        + "35                 |                                  |                           \n"
        + "36                 |                        X                                     \n"
        + "37                 |                        |                                     \n"
        + "38                 |                                  X                           \n"
        + "39                 |                                  |                           \n"
        + "40                 X                                            X                 \n"
        + "41                 |                                            |                 \n"
        + "42                 |                                            X                 \n"
        + "43                 |                                            |                 \n"
        + "44                 |                                            X                 \n"
        + "45                 |                                            |                 \n"
        + "46                 |                                            X                 \n"
        + "47                 |                                            |                 \n"
        + "48                 X                                  X                           \n"
        + "49                 |                                  |                           \n"
        + "50                 |                                  X                           \n"
        + "51                 |                                  |                           \n"
        + "52                 |                                            X                 \n"
        + "53                 |                                            |                 \n"
        + "54                 |                                  X                           \n"
        + "55                 |                                  |                           \n"
        + "56  X                                       X                                     \n"
        + "57  |                                       |                                     \n"
        + "58  |                                       |                                     \n"
        + "59  |                                       |                                     \n"
        + "60  |                                       |                                     \n"
        + "61  |                                       |                                     \n"
        + "62  |                                       |                                     \n"
        + "63  |                                       |                                     \n",
      model.display());
  }
}
