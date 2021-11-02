package view;

import java.awt.*;
import java.io.IOException;

import command.ImageCommand;
import command.PPMLoad;

public class PPMView extends AbstractViewer {
  private final ImageModel model;
  Appendable appendable;
  public PPMView(ImageModel model, Appendable appendable) {
    super(model, appendable);
  }

}
