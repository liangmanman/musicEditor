package cs3500.music.tests;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

import cs3500.music.model.*;
import cs3500.music.util.CompositionBuilder;
import cs3500.music.util.MusicReader;
import cs3500.music.view.*;

import static org.junit.Assert.*;

/**
 * Created by liangmanman1 on 3/22/16.
 */
public class ConsoleViewImplTest {
  ConsoleViewImpl view = new ConsoleViewImpl();
  Note n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, n16, n17, n18, n19, n20,
    n21, n22, n23, n24, n25, n26, n27, n28, n29, n30, n31, n32, n33, n34, n35;
  List<Note> notes;
  Model model;

  public void initialCondition() {
    n1 = new Note(Note.Pitch.Gn, 3, 7, 0, 0, 72);
    n2 = new Note(Note.Pitch.En, 4, 2, 0, 0, 72);
    n3 = new Note(Note.Pitch.Dn, 4, 2, 2, 0, 72);
    n4 = new Note(Note.Pitch.Cn, 4, 2, 4, 0, 72);
    n5 = new Note(Note.Pitch.Dn, 4, 2, 6, 0, 72);
    n6 = new Note(Note.Pitch.Gn, 3, 7, 8, 0, 72);
    n7 = new Note(Note.Pitch.En, 4, 7, 8, 0, 72);
    n8 = new Note(Note.Pitch.En, 4, 5, 10, 0, 72);
    n9 = new Note(Note.Pitch.En, 4, 3, 12, 0, 72);
    n10 = new Note(Note.Pitch.Gn, 3, 8, 16, 0, 72);
    n11 = new Note(Note.Pitch.Dn, 4, 2, 16, 0, 72);
    n12 = new Note(Note.Pitch.Dn, 4, 2, 18, 0, 72);
    n13 = new Note(Note.Pitch.Dn, 4, 4, 20, 0, 72);
    n14 = new Note(Note.Pitch.Gn, 3, 2, 24, 0, 72);
    n15 = new Note(Note.Pitch.En, 4, 2, 24, 0, 72);
    n16 = new Note(Note.Pitch.Gn, 4, 2, 26, 0, 72);
    n17 = new Note(Note.Pitch.Gn, 4, 4, 28, 0, 72);
    n18 = new Note(Note.Pitch.Gn, 3, 8, 32, 0, 72);
    n19 = new Note(Note.Pitch.En, 4, 2, 32, 0, 72);
    n20 = new Note(Note.Pitch.Dn, 4, 2, 34, 0, 72);
    n21 = new Note(Note.Pitch.Cn, 4, 2, 36, 0, 72);
    n22 = new Note(Note.Pitch.Dn, 4, 2, 38, 0, 72);
    n23 = new Note(Note.Pitch.Gn, 3, 8, 40, 0, 72);
    n24 = new Note(Note.Pitch.En, 4, 2, 40, 0, 72);
    n25 = new Note(Note.Pitch.En, 4, 2, 42, 0, 72);
    n26 = new Note(Note.Pitch.En, 4, 2, 44, 0, 72);
    n27 = new Note(Note.Pitch.En, 4, 2, 46, 0, 72);
    n28 = new Note(Note.Pitch.Gn, 3, 8, 48, 0, 72);
    n29 = new Note(Note.Pitch.Dn, 4, 2, 48, 0, 72);
    n30 = new Note(Note.Pitch.Dn, 4, 2, 50, 0, 72);
    n31 = new Note(Note.Pitch.En, 4, 2, 52, 0, 72);
    n32 = new Note(Note.Pitch.Dn, 4, 2, 54, 0, 72);
    n33 = new Note(Note.Pitch.Gn, 3, 8, 40, 0, 72);
    n34 = new Note(Note.Pitch.En, 3, 8, 56, 0, 72);
    n35 = new Note(Note.Pitch.Cn, 4, 8, 56, 0, 72);
    notes = Arrays
      .asList(n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, n16, n17, n18, n19,
        n20, n21, n22, n23, n24, n25, n26, n27, n28, n29, n30, n31, n32, n33, n34, n35);
    model = new ModelImpl(notes);
  }

  @Test
  public void testDisplayEmpty() throws Exception {
    assertEquals("", view.getConsole());
    view.display(new ModelImpl());
    assertEquals("", view.getConsole());
  }

  @Test
  public void testDisplayLamb() throws Exception {
    initialCondition();
    assertEquals("", view.getConsole());
    view.display(model);
    assertEquals(
      "    E3   F3  F#3   G3  G#3   A3  A#3   B3   C4  C#4   D4  D#4   E4   F4  F#4   G4 \n" +
        " 0                 X                                            X                 \n" +
        " 1                 |                                            |                 \n" +
        " 2                 |                                  X                           \n" +
        " 3                 |                                  |                           \n" +
        " 4                 |                        X                                     \n" +
        " 5                 |                        |                                     \n" +
        " 6                 |                                  X                           \n" +
        " 7                                                    |                           \n" +
        " 8                 X                                            X                 \n" +
        " 9                 |                                            |                 \n" +
        "10                 |                                            X                 \n" +
        "11                 |                                            |                 \n" +
        "12                 |                                            X                 \n" +
        "13                 |                                            |                 \n" +
        "14                 |                                            |                 \n" +
        "15                                                                                \n" +
        "16                 X                                  X                           \n" +
        "17                 |                                  |                           \n" +
        "18                 |                                  X                           \n" +
        "19                 |                                  |                           \n" +
        "20                 |                                  X                           \n" +
        "21                 |                                  |                           \n" +
        "22                 |                                  |                           \n" +
        "23                 |                                  |                           \n" +
        "24                 X                                            X                 \n" +
        "25                 |                                            |                 \n" +
        "26                                                                             X  \n" +
        "27                                                                             |  \n" +
        "28                                                                             X  \n" +
        "29                                                                             |  \n" +
        "30                                                                             |  \n" +
        "31                                                                             |  \n" +
        "32                 X                                            X                 \n" +
        "33                 |                                            |                 \n" +
        "34                 |                                  X                           \n" +
        "35                 |                                  |                           \n" +
        "36                 |                        X                                     \n" +
        "37                 |                        |                                     \n" +
        "38                 |                                  X                           \n" +
        "39                 |                                  |                           \n" +
        "40                 X                                            X                 \n" +
        "41                 |                                            |                 \n" +
        "42                 |                                            X                 \n" +
        "43                 |                                            |                 \n" +
        "44                 |                                            X                 \n" +
        "45                 |                                            |                 \n" +
        "46                 |                                            X                 \n" +
        "47                 |                                            |                 \n" +
        "48                 X                                  X                           \n" +
        "49                 |                                  |                           \n" +
        "50                 |                                  X                           \n" +
        "51                 |                                  |                           \n" +
        "52                 |                                            X                 \n" +
        "53                 |                                            |                 \n" +
        "54                 |                                  X                           \n" +
        "55                 |                                  |                           \n" +
        "56  X                                       X                                     \n" +
        "57  |                                       |                                     \n" +
        "58  |                                       |                                     \n" +
        "59  |                                       |                                     \n" +
        "60  |                                       |                                     \n" +
        "61  |                                       |                                     \n" +
        "62  |                                       |                                     \n" +
        "63  |                                       |                                     \n",
      view.getConsole());
  }

  @Test
  public void testMeryLittleLamb() throws FileNotFoundException {
    MusicReader reader = new MusicReader();
    CompositionBuilder<Model> builder = new ModelImpl.Builder();

    File file = new File("mary-little-lamb.txt");
    FileReader fileReader = new FileReader(file);
    Model model = reader.parseFile(fileReader, builder);
    view.display(model);
    assertEquals("    E3   F3  F#3   G3  G#3   A3  A#3   B3   C4  C#4   D4  D#4   E4   F4  F#4   "
      +
      "G4 \n" +
        " 0                 X                                            X                 \n" +
        " 1                 |                                            |                 \n" +
        " 2                 |                                  X                           \n" +
        " 3                 |                                  |                           \n" +
        " 4                 |                        X                                     \n" +
        " 5                 |                        |                                     \n" +
        " 6                 |                                  X                           \n" +
        " 7                                                    |                           \n" +
        " 8                 X                                            X                 \n" +
        " 9                 |                                            |                 \n" +
        "10                 |                                            X                 \n" +
        "11                 |                                            |                 \n" +
        "12                 |                                            X                 \n" +
        "13                 |                                            |                 \n" +
        "14                 |                                            |                 \n" +
        "15                                                                                \n" +
        "16                 X                                  X                           \n" +
        "17                 |                                  |                           \n" +
        "18                 |                                  X                           \n" +
        "19                 |                                  |                           \n" +
        "20                 |                                  X                           \n" +
        "21                 |                                  |                           \n" +
        "22                 |                                  |                           \n" +
        "23                 |                                  |                           \n" +
        "24                 X                                            X                 \n" +
        "25                 |                                            |                 \n" +
        "26                                                                             X  \n" +
        "27                                                                             |  \n" +
        "28                                                                             X  \n" +
        "29                                                                             |  \n" +
        "30                                                                             |  \n" +
        "31                                                                             |  \n" +
        "32                 X                                            X                 \n" +
        "33                 |                                            |                 \n" +
        "34                 |                                  X                           \n" +
        "35                 |                                  |                           \n" +
        "36                 |                        X                                     \n" +
        "37                 |                        |                                     \n" +
        "38                 |                                  X                           \n" +
        "39                 |                                  |                           \n" +
        "40                 X                                            X                 \n" +
        "41                 |                                            |                 \n" +
        "42                 |                                            X                 \n" +
        "43                 |                                            |                 \n" +
        "44                 |                                            X                 \n" +
        "45                 |                                            |                 \n" +
        "46                 |                                            X                 \n" +
        "47                 |                                            |                 \n" +
        "48                 X                                  X                           \n" +
        "49                 |                                  |                           \n" +
        "50                 |                                  X                           \n" +
        "51                 |                                  |                           \n" +
        "52                 |                                            X                 \n" +
        "53                 |                                            |                 \n" +
        "54                 |                                  X                           \n" +
        "55                 |                                  |                           \n" +
        "56  X                                       X                                     \n" +
        "57  |                                       |                                     \n" +
        "58  |                                       |                                     \n" +
        "59  |                                       |                                     \n" +
        "60  |                                       |                                     \n" +
        "61  |                                       |                                     \n" +
        "62  |                                       |                                     \n" +
        "63  |                                       |                                     \n",
      view.getConsole());
  }
}
