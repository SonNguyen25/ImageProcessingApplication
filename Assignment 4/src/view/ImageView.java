package view;

import java.io.IOException;

public interface ImageView {
String toString();

void renderMessage(String message) throws IOException;
}
