package view;

import java.awt.*;
import java.io.IOException;

import model.ImageModel;
import model.Pixel.Pixel;
import model.storage.ImageLibrary;

public abstract class AbstractViewer implements ImageView{
  private ImageLibrary library;
  Appendable appendable;
  public AbstractViewer(ImageLibrary library, Appendable appendable) {
    if (library == null || appendable == null) {
      throw new IllegalStateException("The provide model is null.");
    }
    this.library = library;
    this.appendable = appendable;
  }

  public AbstractViewer(ImageLibrary library) {
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
        if (k == copys[i].length - 1) {
          oneLine.append(copys[i][k].getColor().get(0) + " "
                  + copys[i][k].getColor().get(1) + " "
                  + copys[i][k].getColor().get(2)) ;
        } else {
          oneLine.append(copys[i][k].getColor().get(0) + " " + copys[i][k].getColor().get(1)+ " "
                  + copys[i][k].getColor().get(2) + " ");
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
