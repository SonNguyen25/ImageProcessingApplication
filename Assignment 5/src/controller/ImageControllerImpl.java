package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import java.util.function.Function;

import command.Brighten;
import command.FlipHorizontal;
import command.FlipVertical;
import command.ImageCommand;
import command.Load;
import command.Save;
import command.GreyScale;
import model.storage.ImageLibrary;
import view.ImageView;

/**
 * This is the implementation class of the controller that will give information to the model to
 * make changes to the image and render message to the view to display the program.
 */

public class ImageControllerImpl implements ImageController {
  private ImageLibrary library;
  private ImageView view;
  private Readable in = null;

  /**
   * This is the constructor for the controller that takes in the library that stores the images
   * and also a view to display and users' inputs.
   *
   * @param library The images' storage.
   * @param view    The display of message.
   * @param in      The users' inputs.
   */
  public ImageControllerImpl(ImageLibrary library, ImageView view, Readable in) {
    if (library == null || view == null || in == null) {
      throw new IllegalArgumentException("Null input.");
    }
    this.library = library;
    this.view = view;
    this.in = in;
  }

  /**
   * This is the execution method of the whole controller.
   *
   * @throws IllegalStateException When the input causes error.
   */
  @Override
  public void process() throws IllegalStateException {
    List<String> stringCommands = new ArrayList<>(Arrays.asList("load", "brighten", "save",
            "horizontal-flip", "vertical-flip", "red-component", "green-component",
            "blue-component", "luma-component", "intensity-component", "value-component"));
    try {
      Scanner scan = new Scanner(this.in);
      Stack<ImageCommand> commands = new Stack<>();
      Map<String, Function<Scanner, ImageCommand>>
              knownCommands = new HashMap<>();
      knownCommands.put("load", (Scanner s) -> new Load(s.next(), s.next()));
      knownCommands.put("brighten", (Scanner s) -> new Brighten(s.nextInt(), s.next(), s.next()));
      knownCommands.put("save", (Scanner s) -> new Save(s.next(), s.next()));
      knownCommands.put("horizontal-flip", (Scanner s) -> new FlipHorizontal(s.next(), s.next()));
      knownCommands.put("vertical-flip", (Scanner s) -> new FlipVertical(s.next(), s.next()));
      knownCommands.put("red-component", (Scanner s) -> new GreyScale(s.next(), s.next(),
              "red"));
      knownCommands.put("green-component", (Scanner s) -> new GreyScale(s.next(), s.next(),
              "green"));
      knownCommands.put("blue-component", (Scanner s) -> new GreyScale(s.next(), s.next(),
              "blue"));
      knownCommands.put("luma-component", (Scanner s) -> new GreyScale(s.next(), s.next(),
              "luma"));
      knownCommands.put("intensity-component", (Scanner s) -> new GreyScale(s.next(),
              s.next(), "intensity"));
      knownCommands.put("value-component", (Scanner s) -> new GreyScale(s.next(), s.next(), "max"));

      while (scan.hasNext()) {
        ImageCommand c;
        String in = scan.next();
        if (in.equalsIgnoreCase("q") || in.equalsIgnoreCase("quit")) {
          this.view.renderMessage("Application closed");
          return;
        }
        try {
          Function<Scanner, ImageCommand> cmd = knownCommands.getOrDefault(in, null);
          if (cmd == null) {
            throw new IllegalArgumentException();
          } else {
            c = cmd.apply(scan);
            commands.add(c);
            c.process(library, stringCommands);
            this.view.renderMessage("Action Completed! \n");
          }
        } catch (Exception e) {
          this.view.renderMessage("Action failed \n");
        }
      }

    } catch (IOException e) {
      throw new IllegalStateException("The appendable writes fail");
    }
  }
}
