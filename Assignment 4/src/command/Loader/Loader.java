package command.Loader;

public interface Loader {
  <T> T load() throws IllegalStateException;
}
