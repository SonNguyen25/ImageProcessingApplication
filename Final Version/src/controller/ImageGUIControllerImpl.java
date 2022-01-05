package controller;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import command.Blur;
import command.Brighten;
import command.Downscale;
import command.FlipHorizontal;
import command.FlipVertical;
import command.GreyScale;
import command.Load;
import command.LumaGreyScale;
import command.Save;
import command.Sepia;
import command.Sharpen;
import model.ImageUtil;
import model.image.ImageModel;
import model.storage.ImageLibrary;
import view.ViewGUI;

/**
 * This is the implementation class of the controller that will give information to the model to
 * make changes to the image and render message to the view to display the program in a GUI.
 */
public class ImageGUIControllerImpl implements ImageController, ViewListener {
  private final List<String> stringCommands;
  private final ViewGUI view;
  private final ImageLibrary library;

  /**
   * Constructor for GUI for the controller that takes in the library that stores the images
   * and also a view to display the program.
   * @param library represents the images' storage.
   * @param view represents the view of the program.
   */
  public ImageGUIControllerImpl(ImageLibrary library, ViewGUI view) {
    if (library == null || view == null) {
      throw new IllegalArgumentException("Null input.");
    }
    this.library = library;
    this.view = view;
    this.stringCommands = new ArrayList<>(Arrays.asList("load", "brighten", "save",
            "horizontal-flip", "vertical-flip", "red-component", "green-component",
            "blue-component", "luma-component", "intensity-component", "value-component", "blur",
            "sharpen", "greyscale", "sepia", "downscale"));
    this.view.addListener(this);
  }

  /**
   * This is the execution method of the whole controller.
   *
   * @throws IllegalStateException When the input causes error.
   */
  @Override
  public void process() throws IllegalStateException {
    this.view.showView(true);
  }

  /**
   * Load the file into the library, and updates the corresponding view.
   * @param filepath represents the filepath.
   * @param currentName represents the current name of the file being load.
   */
  @Override
  public void loadEvent(String filepath, String currentName) {
    new Load(filepath, currentName).process(this.library, this.stringCommands);
    this.updateView(currentName);
  }

  /**
   * Save the file into the user's chosen directory.
   * @param filepath represents the filepath.
   * @param currentName represents the current name of the file being save.
   */
  @Override
  public void saveEvent(String filepath, String currentName) {
    new Save(filepath, currentName).process(this.library, this.stringCommands);
  }

  /**
   * Blurring the image, and updates the view.
   */
  @Override
  public void blurEvent(String model, String newName) {
    new Blur(model, newName).process(this.library, this.stringCommands);
    this.updateView(newName);
  }

  /**
   * Sharpening the image, and updates the view.
   */
  @Override
  public void sharpenEvent(String model, String newName) {
    new Sharpen(model, newName).process(this.library, this.stringCommands);
    this.updateView(newName);
  }

  /**
   * Brighten the image or darken it with the given level of increment and update the view.
   */
  @Override
  public void brightenEvent(int level, String model, String newName) {
    new Brighten(level, model, newName).process(this.library, this.stringCommands);
    this.updateView(newName);
  }

  /**
   * Apply sepia operation on the image and update the view.
   */
  @Override
  public void sepiaEvent(String model, String newName) {
    new Sepia(model, newName).process(this.library, this.stringCommands);
    this.updateView(newName);
  }

  /**
   * Apply the greyscale operation on the image and update the view.
   */
  @Override
  public void greyscaleEvent(String model, String newName) {
    new LumaGreyScale(model, newName).process(this.library, this.stringCommands);
    this.updateView(newName);
  }

  /**
   * Apply the greyscale operation by using components stated by the user and update the view.
   */
  @Override
  public void componentEvent(String model, String newName, String way) {
    new GreyScale(model, newName, way).process(this.library, this.stringCommands);
    this.updateView(newName);
  }

  /**
   * Flip the image according to the way stated by the user and update the view.
   */
  @Override
  public void flipEvent(String model, String newName, String way) {
    if (way.equals("horizontal")) {
      new FlipHorizontal(model, newName).process(this.library, this.stringCommands);
    }
    else if (way.equals("vertical")) {
      new FlipVertical(model, newName).process(this.library, this.stringCommands);
    }
    this.updateView(newName);
  }

  /**
   * Downsize the image according to the new dimensions stated by the user and update the view.
   */
  @Override
  public void downsizeEvent(String model, String newName, int height, int width) {
    new Downscale(height, width, model, newName).process(this.library, this.stringCommands);
    this.updateView(newName);
  }



  /**
   * Convert the image into BufferedImage and update it in the view.
   */
  protected void updateView(String name) {
    ImageModel image = this.library.contain(name);
    int end = name.lastIndexOf(".");
    String fileType = name.substring(end + 1);
    BufferedImage current = ImageUtil.toBufferedImage(fileType, image);
    this.view.updateNameLabel(name);
    this.view.updateDisplay(current);
  }


}
