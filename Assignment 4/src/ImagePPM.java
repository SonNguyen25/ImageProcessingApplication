import java.util.ArrayList;
import java.util.List;

public class ImagePPM implements Image {
  private int width;
  private int height;
  private List<int[][]> rgb;

  public ImagePPM(String filename) {
    this.rgb = new ArrayList<int[][]>();
  }

  @Override
  public int readImage() {
    return 0;
  }

  @Override
  public int width() {
    return this.width;
  }

  @Override
  public int height() {
    return this.height;
  }
}
