package view;

import controller.ViewListener;

/**
 * Allow listeners to take in user inputs and emit it to GUI.
 */
public interface ViewEmitter {

  /**
   * Add a Listener to the list of Listeners.
   *
   * @param l represent a ViewListener.
   */
  void addListener(ViewListener l);
}
