package view;

import java.awt.*;
import java.io.IOException;

public abstract class AbstractViewer implements ImageView{
  private final ImageModel model;
  Appendable appendable;
  public AbstractViewer(ImageModel model, Appendable appendable) {
    if (model == null || appendable == null) {
      throw new IllegalStateException("The provide model is null.");
    }
    this.model = model;
    this.appendable = appendable;
  }

  public AbstractViewer(ImageModel model) {
    this(model, System.out);
  }

  @Override
  public String toString() {
    String outModel = "P3\n" + model.getWidth() + " " + model.getHeight() + " " +
            "\n" + "255\n";
    Pixel[][] copy = model.getCopy();
    for (int i=0; i< copy.length;i++) {
      for (int k=0; k< copy[i].length;k++) {
        if (k == copy[i].length - 1) {
          outModel += copy[i][k][0] + " " + copy[i][k][1] + " " + copy[i][k][2];
        } else {
          outModel += copy[i][k][0] + " " + copy[i][k][1] + " " + copy[i][k][2]
                  + " ";
        }
      }
      outModel += System.lineSeparator();
    }
    return outModel;
  }

  @Override
  public void renderMessage(String message) throws IOException {
    this.appendable.append(message);
  }
}
