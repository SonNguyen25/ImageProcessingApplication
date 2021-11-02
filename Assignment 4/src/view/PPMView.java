package view;

import java.awt.*;
import java.io.IOException;

import command.ImageCommand;
import command.PPMLoad;
import model.ImageModel;
import model.storage.ImageLibrary;

public class PPMView extends AbstractViewer {
  private final ImageModel model = null;
  Appendable appendable;
  public PPMView(ImageLibrary library, Appendable appendable) {
    super(library, appendable);
  }

  public PPMView(ImageLibrary library) {
    super(library);
  }

}
