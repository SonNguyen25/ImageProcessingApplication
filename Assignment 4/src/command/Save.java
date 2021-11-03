package command;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import model.image.ImageModel;
import model.storage.ImageLibrary;
import view.ImageViewImpl;

public class Save implements ImageCommand{
  String savingPath;
  String fileNameIn;
  public Save(String savingPath, String fileNameIn) {
    this.savingPath = savingPath;
    this.fileNameIn = fileNameIn;
  }

  /**
   * This method is used to execute the save command in the model to save the given image in the
   * library to a system output path.
   * @param library The library that stores all models.
   * @param stringCommands The list of command's names.
   * @throws IllegalArgumentException When the command fails, it will occur.
   */
  @Override
  public void go(ImageLibrary library, List<String> stringCommands) throws IllegalArgumentException{
    ImageModel model = library.contain(fileNameIn);
    if (model == null) {
      System.out.println("Cannot find immage");
      throw new IllegalArgumentException("Cannot find immage");
    }
    try {
      File out = new File(this.savingPath);
      FileOutputStream imageOut = new FileOutputStream(out);
      imageOut.write(new ImageViewImpl(library).toString(this.fileNameIn).getBytes());
      imageOut.flush();
      imageOut.close();
      System.out.println(fileNameIn + " has been saved successfully");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
