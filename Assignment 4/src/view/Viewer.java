package view;

import java.io.IOException;

import model.ImageModel;
import model.Pixel.Pixel;
import model.storage.ImageLibrary;

public class Viewer implements ImageView{
  private ImageLibrary library;
  Appendable appendable;
  public Viewer(ImageLibrary library, Appendable appendable) {
    if (library == null || appendable == null) {
      throw new IllegalStateException("The provide model is null.");
    }
    this.library = library;
    this.appendable = appendable;
  }

  public Viewer(ImageLibrary library) {
    this(library, System.out);
  }

  @Override
  public String toString(String fileName) {
    ImageModel model = library.contain(fileName);

    if (model == null) {
      throw new IllegalStateException();
    }
    StringBuilder outModel = new StringBuilder("P3\n" + model.getWidth() + " " + model.getHeight() + " " +
            "\n" + "255\n");

    Pixel[][] copys = model.getCopy();


    for (int i=0; i< copys.length;i++) {
      StringBuilder oneLine = new StringBuilder("");
      for (int k=0; k< copys[i].length;k++) {
          for (int j = 0; j < copys[i][k].getColor().size(); j++ ) {
            if (k == copys[i].length - 1 && j == copys[i][k].getColor().size() - 1) {
              oneLine.append(copys[i][k].getColor().get(j));
            }
            oneLine.append(copys[i][k].getColor().get(j) + " ");
          }
      }
      outModel.append(oneLine + System.lineSeparator());
    }
    return outModel.toString();
  }

  @Override
  public void renderMessage(String message) throws IOException {
    this.appendable.append(message);
  }
}
