package cs3500.music.util;

/**
 * Created by liangmanman1 on 4/25/16.
 */
public interface CompositionBuilderWithRepeat<T> extends CompositionBuilder<T> {
  /**
   * Set the repeat symbols
   */
  void setBeats(int[] beats, String str);
}
