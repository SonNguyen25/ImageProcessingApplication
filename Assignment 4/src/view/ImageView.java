package view;

import java.io.IOException;

public interface ImageView {
  String toString(String fileName);

  void renderMessage(String message) throws IOException;
}
