package command;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import model.image.ImageModel;
import model.storage.ImageLibrary;
import view.PPMView;

public class Save implements ImageCommand{
  String savingPath;
  String fileNameIn;
  public Save(String savingPath, String fileNameIn) {
    this.savingPath = savingPath;
    this.fileNameIn = fileNameIn;
  }

  @Override
  public void go(ImageLibrary library) throws IllegalStateException{
    ImageModel model = library.contain(fileNameIn);
    if (model == null) {
      throw new IllegalStateException("Cannot find immage");
    }
    try {
      File out = new File(this.savingPath);
      FileOutputStream imageOut = new FileOutputStream(out);
      imageOut.write(new PPMView(library).toString(this.fileNameIn).getBytes());
      imageOut.flush();
      imageOut.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
