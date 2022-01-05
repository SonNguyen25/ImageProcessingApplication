
import org.junit.Test;

import controller.ImageGUIControllerImpl;

import model.storage.ImageLibrary;
import model.storage.ImageStorage;
import view.ImageViewGUIImpl;
import view.ViewGUI;

/**
 * Test classes for GUI Controller.
 */
public class ImageGUIControllerImplTest {

  /**
   * Test for null library in constructor.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNullLibrary() {
    ImageLibrary lib = new ImageStorage();
    ViewGUI view = new ImageViewGUIImpl(lib);
    new ImageGUIControllerImpl(null, view);
  }

  /**
   * Test for null view in constructor.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNullView() {

    ImageLibrary lib = new ImageStorage();
    ViewGUI view = new ImageViewGUIImpl(lib);
    new ImageGUIControllerImpl(lib, null);
  }

}