package cs3500.music.view;


import cs3500.music.model.Model;

/**
 * Console view
 */
public class ConsoleViewImpl implements View {
  private String console = "";

  @Override
  public void display(Model model) {
    console = model.display();
    System.out.println(console);
  }

  public String getConsole() {
    return console;
  }
}
