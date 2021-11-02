package controller;


import command.ImageCommand;
import command.PPMLoad;
import model.storage.ImageLibrary;
import view.ImageView;

public class PPMController extends AbstractImageController {

  public PPMController(ImageLibrary library, ImageView view, Readable in) {
    super(library, view, in);
  }

  protected ImageCommand returnLoad(String path, String filename) {
    return new PPMLoad(path, filename);
  }
}
