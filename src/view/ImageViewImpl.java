package view;

import java.io.IOException;

import model.image.ImageModel;
import model.pixel.Pixel;
import model.storage.ImageLibrary;

/**
 * This is the viewer class that implements the ImageView interface. It will process different
 * kinds of image model and make it writable or visible and with errors outputting if there is any.
 */
public class ImageViewImpl implements ImageView {
  private final ImageLibrary library;
  private final Appendable appendable;

  /**
   * This is the constructor for the view class. It allows the transformation between a model
   * contains inside a library and a writable string output that represents the model.
   *
   * @param library    The storage of images.
   * @param appendable The output information.
   */
  public ImageViewImpl(ImageLibrary library, Appendable appendable) {
    if (library == null || appendable == null) {
      throw new IllegalArgumentException("The provide model is null.");
    }
    this.library = library;
    this.appendable = appendable;
  }

  /**
   * This is the constructor for the view class. It allows the transformation between a model
   * contains inside a library and a default output to the system.
   *
   * @param library The storage of images.
   */
  public ImageViewImpl(ImageLibrary library) {
    this(library, System.out);
  }

  /**
   * Turns the information of an image to String.
   * @param fileName represents the file name
   * @return the content of the file in String
   */
  @Override
  public String toString(String fileName) {
    ImageModel model = library.contain(fileName);

    if (model == null) {
      throw new IllegalStateException();
    }
    Pixel[][] copys = model.getPixels();

    StringBuilder outModel = new StringBuilder();


    outModel = new StringBuilder("P3\n" + model.getWidth() + " " + model.getHeight() + " " +
            "\n" + "255\n");


    for (int i = 0; i < copys.length; i++) {
      StringBuilder oneLine = new StringBuilder("");
      for (int k = 0; k < copys[i].length; k++) {
        for (int j = 0; j < 3; j++) {
          if (k == copys[i].length - 1 && j == 3 - 1) {
            oneLine.append(copys[i][k].getColor().get(j));
          } else {
            oneLine.append(copys[i][k].getColor().get(j) + " ");
          }
        }
      }
      outModel.append(oneLine + System.lineSeparator());
    }
    return outModel.toString();
  }

  /**
   * Render a message to the output.
   * @param message The message that needs to be transmitted.
   * @throws IOException if it's unable to transmit the message.
   */
  @Override
  public void renderMessage(String message) throws IOException {
    this.appendable.append(message);
  }
}
