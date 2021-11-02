package command;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import view.PPMView;

public class Save implements ImageCommand{
  String savingPath;
  String fileNameIn;
  public Save(String savingPath, String fileName) {
    this.savingPath = savingPath;
    this.fileNameIn = fileName;
  }

  @Override
  public void go(HashMap<String, ImageModel> library) {
    ImageModel model = library.getOrDefault(fileNameIn, null);
    if (model == null) {
      throw new IllegalStateException("Cannot find immage");
    }
    try {
      File out = new File(this.savingPath);
      FileOutputStream imageOut = new FileOutputStream(out);
      imageOut.write(new PPMView(model).toString().getBytes());
      imageOut.flush();
      imageOut.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
