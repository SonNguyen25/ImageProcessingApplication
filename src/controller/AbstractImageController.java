package controller;


import java.io.IOException;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import java.util.function.Function;

import command.Brighten;
import command.FlipHorizontal;
import command.FlipVertical;
import command.ImageCommand;
import command.PPMLoad;
import command.Save;
import command.ValueComponent;
import model.storage.ImageLibrary;

import view.ImageView;

public abstract class AbstractImageController implements ImageController {
  private ImageLibrary library;
  private ImageView view;
  private Readable in = null;

  public AbstractImageController(ImageLibrary library, ImageView view, Readable in) {
    if (library == null || view == null || in == null) {
      throw new IllegalArgumentException("Null arguments!");
    }
    this.library = library;
    this.view = view;
    this.in = in;
  }

  protected abstract ImageCommand returnLoad(String path, String filename);

  @Override
  public void process() throws IllegalStateException {
    try {
      Scanner scan = new Scanner(this.in);
      Stack<ImageCommand> commands = new Stack<>();

      Map<String, Function<Scanner, ImageCommand>> knownCommands = new HashMap<>();
      knownCommands.put("load", (Scanner s) -> returnLoad(s.next(), s.next()));
      knownCommands.put("brighten", (Scanner s) -> new Brighten(s.nextInt(),
              s.next(), s.next()));
      knownCommands.put("save", (Scanner s) -> new Save(s.next(), s.next()));
      knownCommands.put("horizontal-flip", (Scanner s) -> new FlipHorizontal(s.next(), s.next()));
      knownCommands.put("vertical-flip", (Scanner s) -> new FlipVertical(s.next(), s.next()));
      knownCommands.put("red", (Scanner s) -> new ValueComponent(s.next(), s.next(),
              "red"));
      knownCommands.put("green", (Scanner s) -> new ValueComponent(s.next(), s.next(),
              "green"));
      knownCommands.put("blue", (Scanner s) -> new ValueComponent(s.next(), s.next(),
              "blue"));
      knownCommands.put("luma", (Scanner s) -> new ValueComponent(s.next(), s.next(),
              "luma"));
      knownCommands.put("intensity", (Scanner s) -> new ValueComponent(s.next(), s.next(),
              "intensity"));
      knownCommands.put("max", (Scanner s) -> new ValueComponent(s.next(), s.next(),
              "max"));

      while (scan.hasNext()) {
        ImageCommand c;
        String in = scan.next();
        if (in.equalsIgnoreCase("q") || in.equalsIgnoreCase("quit")) {
          return;
        }
        try {
          Function<Scanner, ImageCommand> cmd = knownCommands.getOrDefault(in, null);
          if (cmd == null) {
            throw new IllegalStateException();
          } else {
            c = cmd.apply(scan);
            commands.add(c);
            c.go(library);
            this.view.renderMessage("Action Completed!");

          }
        } catch (IllegalStateException e) {
          this.view.renderMessage("Action failed");
        }


      }
    } catch (IOException e) {
      throw new IllegalStateException("The appendable writes fail");
    }
  }
}
