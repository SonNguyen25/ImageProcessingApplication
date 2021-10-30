package image;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public interface Image {

  public void readImage(String filename) throws FileNotFoundException;

  int width();

  int height();


}
