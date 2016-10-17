package cs3500.music.util;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Created by liangmanman1 on 4/25/16.
 */
public class MusicReaderWithRepeat {
  public static <T> T parseFile(Readable readable, CompositionBuilderWithRepeat<T> piece) {
    Scanner scanner = new Scanner(readable);
    while (scanner.hasNext()) {
      String lineType = scanner.next();
      switch (lineType) {
        case "tempo":
          try {
            piece.setTempo(scanner.nextInt());
          } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("Malformed tempo line: " + scanner.nextLine());
          }
          break;
        case "inverted-repeat":
        case "repeat":
        case "ending":
          String str = "";
          try {
            str = scanner.nextLine().substring(1);
            String[] split = str.split(" ");
            int[] beats = new int[split.length];
            int i = 0;
            while ( i < beats.length) {
              beats[i] = Integer.parseInt(split[i]);
              i++;
            }
            piece.setBeats(beats, lineType);
          } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("Malformed repeat symbol line: " + lineType
              + str);
          }
          break;
        case "note":
          try {
            int startBeat = scanner.nextInt();
            int endBeat = scanner.nextInt();
            int instrument = scanner.nextInt();
            int pitch = scanner.nextInt();
            int volume = scanner.nextInt();
            piece.addNote(startBeat, endBeat, instrument, pitch, volume);
          } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("Malformed note line: " + scanner.nextLine());
          }
          break;
        default:
          throw new IllegalArgumentException("Bad line type: " + lineType);
      }
    }
    return piece.build();
  }
}
