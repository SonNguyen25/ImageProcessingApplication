package view;

import java.awt.image.BufferedImage;

/**
 * Represents an interface for operations related to the GUI of the image processing app, contains
 * operations to show and display the images and its changes and buttons and functions for user
 * interactions.
 */
public interface ViewGUI extends ViewEmitter {

  /**
   * Display the whole GUI view to the user.
   *
   * @param show represents the boolean to verify if it is to be displayed or not.
   */
  void showView(boolean show);

  /**
   * Show error message to the GUI.
   */
  void showErrorMessage(String msg);

  /**
   * Set up the Menu Bar for the GUI and all of its related contents.
   */
  void setUpMenuBar();

  /**
   * Set up the necessary buttons to load, save, and check the library to operate on different
   * images.
   */
  void setUpButtons();

  /**
   * Update the image and histogram display.
   */
  void updateDisplay(BufferedImage image);

  /**
   * Update name of the image displayed.
   */
  void updateNameLabel(String newName);
}
