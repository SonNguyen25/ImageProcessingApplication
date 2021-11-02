package controller;


import java.lang.invoke.StringConcatException;
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
import view.ImageView;

public class ImageControllerImpl implements ImageController{
  private ImageMode model;
  private ImageView view;
  private Readable in =null;

  public ImageControllerImpl(ImageMode model, ImageView view, Readable in) {
    try {
      if (model == null || view == null || in == null) {
        throw new IllegalStateException("Null input.");
      }
      this.model = model;
      this.view = view;
      this.in = in;
    } catch (IllegalStateException e) {
      e.printStackTrace();
    }

  }

  @Override
  public void process() throws IllegalStateException {
    Scanner scan = new Scanner(this.in);
    Stack<ImageCommand> commands = new Stack<>();

    Map<String, Function<Scanner, ImageCommand>> knownCommands = new HashMap<>();
    knownCommands.put("load", (Scanner s) -> {return new PPMLoad(s.next(), s.next());});
    knownCommands.put("brighten", (Scanner s) -> {return new Brighten(s.nextInt(),
            s.next(), s.next());});
    knownCommands.put("save", (Scanner s) -> {return new Save(s.next(), s.next());});
    knownCommands.put("horizontal-flip ", (Scanner s) -> {return
            new FlipHorizontal(s.next(), s.next());});
    knownCommands.put("vertical-flip ", (Scanner s) -> {return
            new FlipVertical(s.next(), s.next());});

    while(scan.hasNext()) {
      ImageCommand c;
      String in = scan.next();
      if (in.equalsIgnoreCase("q") || in.equalsIgnoreCase("quit")) {
        return;
      }

      Function<Scanner, ImageCommand> cmd = knownCommands.getOrDefault(in, null);
      if (cmd == null) {
        throw new IllegalStateException();
      } else {
        c = cmd.apply(scan);
        commands.add(c);
        c.go(model.getLibrary());
      }
    }
    }
  }
