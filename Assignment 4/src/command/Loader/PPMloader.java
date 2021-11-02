package command.Loader;

import java.awt.*;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class PPMloader extends AbstractRGBLoader{
  public PPMloader(String in) {
    super(in);
  }
}
