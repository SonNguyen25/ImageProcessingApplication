package view;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.LayoutStyle;

import controller.ViewListener;
import model.ImageUtil;
import model.image.ImageModel;
import model.pixel.Pixel;
import model.storage.ImageLibrary;

/**
 * This is the viewer class that implements the ViewGUI interface.
 * It will provide a GUI for user to interact with the images and to display images and
 * its histogram along with its changed versions to the user. Contains operations to display the
 * image library, buttons, errors and text boxes in option panes to allow user to make changes
 * to the image.
 */
public class ImageViewGUIImpl extends JFrame implements ViewGUI {

  private final ImageLibrary library;
  private final List<String> stringCommands = new ArrayList<>(Arrays.asList("load", "brighten",
          "save",
          "horizontal-flip", "vertical-flip", "red-component", "green-component",
          "blue-component", "luma-component", "intensity-component", "value-component", "blur",
          "sharpen", "greyscale", "sepia", "downscale"));
  private String currentName;
  private final List<ViewListener> listenerList;

  //GUI variables
  private JButton libraryButton;
  private JButton loadButton;
  private JButton saveButton;
  private JLabel histogram;
  private final JScrollPane imageScroll;
  private final GroupLayout layout;
  private final JSeparator separator;
  private final JLabel guideLabel;
  private final JLabel imageDisplay;
  private final JScrollPane hisPanel;
  private final JLabel imageName;

  /**
   * This is the constructor for the view class for a GUI.
   * It allows the display and operations between a model contains inside a library.
   *
   * @param library The storage of images.
   */
  public ImageViewGUIImpl(ImageLibrary library) {
    super();
    if (library == null) {
      throw new IllegalArgumentException("The provide model/library is null.");
    }
    this.library = library;
    this.setTitle("Son Nguyen & Weixin Kong's Image Processing Application");
    this.listenerList = new ArrayList<>();

    guideLabel = new JLabel("Choose what to do to the image from the Menu bar above!");

    separator = new JSeparator();
    separator.setOrientation(SwingConstants.VERTICAL);
    imageDisplay = new JLabel();
    imageScroll = new JScrollPane();
    imageScroll.setViewportView(imageDisplay);
    layout = new GroupLayout(getContentPane());


    hisPanel = new JScrollPane();
    histogram = new JLabel();


    currentName = "";
    imageName = new JLabel();
    imageName.setText(currentName);
    this.getContentPane().setLayout(layout);
    this.setUpMenuBar();
    this.setUpButtons();
    this.setUpHorizontalLayout();
    this.setUpVerticalLayout();

    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.pack();
  }

  /**
   * Display the whole GUI view to the user.
   *
   * @param show represents the boolean to verify if it is to be displayed or not.
   */
  @Override
  public void showView(boolean show) {
    this.setVisible(show);
  }

  /**
   * Set up the horizontal layout of the view.
   */
  protected void setUpHorizontalLayout() {
    layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                            .addComponent(loadButton)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement
                                                    .UNRELATED)
                                            .addComponent(libraryButton)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement
                                                    .UNRELATED)
                                            .addComponent(saveButton))
                                    .addComponent(guideLabel)
                                    .addComponent(hisPanel, GroupLayout.PREFERRED_SIZE, 257,
                                            GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(separator, GroupLayout.DEFAULT_SIZE, 13,
                                    Short.MAX_VALUE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(imageScroll, GroupLayout.PREFERRED_SIZE, 720,
                                            GroupLayout.PREFERRED_SIZE)
                                    .addComponent(imageName))
                            .addContainerGap())
    );
  }

  /**
   * Set up the vertical layout of the view.
   */
  protected void setUpVerticalLayout() {
    layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(separator)
                                    .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(GroupLayout
                                                            .Alignment.BASELINE)
                                                    .addComponent(loadButton)
                                                    .addComponent(libraryButton)
                                                    .addComponent(saveButton))
                                            .addGap(23, 23, 23)
                                            .addComponent(guideLabel)
                                            .addGap(18, 18, 18)
                                            .addComponent(hisPanel, GroupLayout.PREFERRED_SIZE,
                                                    257, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(GroupLayout.Alignment.TRAILING,
                                            layout.createSequentialGroup()
                                                    .addComponent(imageName,
                                                            GroupLayout.DEFAULT_SIZE,
                                                            19, Short.MAX_VALUE)
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement
                                                            .RELATED)
                                                    .addComponent(imageScroll,
                                                            GroupLayout.PREFERRED_SIZE,
                                                            546, GroupLayout.PREFERRED_SIZE))))
    );
  }

  /**
   * Set up the necessary buttons to load, save, and check the library to operate on different
   * images.
   */
  @Override
  public void setUpButtons() {
    loadButton = new JButton("Load");
    libraryButton = new JButton("Library");
    saveButton = new JButton("Save");
    loadButton.addActionListener(e -> displayImageLoad());
    libraryButton.addActionListener(e -> displayLibrary());
    saveButton.addActionListener(e -> operationSave());
  }

  /**
   * Let user choose directory and file extension to save the image as in order to save the file.
   */
  protected void operationSave() {
    if (currentName == null) {
      this.showErrorMessage("There are not any images loaded!");
    } else {
      JFileChooser directory = new JFileChooser();
      directory.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
      int result;
      try {
        File location;
        result = directory.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
          String[] types = {"ppm", "png", "bmp", "jpg", "jpeg"};
          String input = (String) JOptionPane.showInputDialog(this,
                  "Choose a file extension to export: ", "Save as...",
                  JOptionPane.QUESTION_MESSAGE, null, types, null);
          location = directory.getSelectedFile();
          for (ViewListener listener : listenerList) {
            listener.saveEvent(location.getAbsolutePath() + "\\" + currentName + "." + input,
                    currentName);
          }
          JOptionPane.showMessageDialog(this, "Save successful!", ":)",
                  JOptionPane.INFORMATION_MESSAGE);
        } else if (result == JFileChooser.ERROR_OPTION) {
          this.showErrorMessage("Invalid file path!");
        }
      } catch (IllegalStateException e) {
        this.showErrorMessage("Invalid file path!");
      }
    }
  }

  /**
   * Display the image based on the name given by the user to the GUI when it's loaded from the
   * user's directory.
   */
  protected void displayImageLoad() {
    JFileChooser loader = new JFileChooser();
    loader.setFileFilter(new FileNameExtensionFilter("Image Files",
            "jpg", "png", "jpeg", "bmp", "ppm"));
    int result;
    try {
      result = loader.showOpenDialog(this);
      File image;
      if (result == JFileChooser.APPROVE_OPTION) {
        currentName = JOptionPane.showInputDialog(this,
                "What name do you want to load this image as?", null);
        if (currentName == null || currentName.equals("")
                || this.stringCommands.contains(currentName)) {
          this.showErrorMessage("Can't load image with an empty name " +
                  "or with a name similar to a command!");
        } else {
          image = loader.getSelectedFile();
          for (ViewListener listener : listenerList) {
            listener.loadEvent(image.getAbsolutePath(), currentName);
          }
        }
      }
    } catch (IllegalStateException e) {
      this.showErrorMessage("Invalid file path or file type!");
    }
  }

  /**
   * Update the image and histogram display.
   */
  @Override
  public void updateDisplay(BufferedImage image) {
    imageDisplay.setIcon(new ImageIcon(image));
    imageDisplay.setHorizontalAlignment(SwingConstants.CENTER);
    imageName.setText(currentName + "       Height: " + image.getHeight() + "  Width: "
            + image.getWidth());

    Pixel[][] pixels = this.library.contain(currentName).getPixels();
    histogram = new ImageHistogram(pixels);
    histogram.setPreferredSize(new Dimension(1000, 1000));
    hisPanel.setViewportView(histogram);
  }

  /**
   * Update name of the image displayed.
   */
  public void updateNameLabel(String newName) {
    this.currentName = newName;
  }

  /**
   * Display the image on the GUI based on its given name.
   *
   * @param name represents the name of the image.
   */
  protected void displayImage(String name) {
    this.currentName = name;
    ImageModel model = this.library.contain(name);
    int end = name.lastIndexOf(".");
    String fileType = name.substring(end + 1);
    BufferedImage current = ImageUtil.toBufferedImage(fileType, model);
    this.updateDisplay(current);
  }

  /**
   * Display the image library on the GUI for the user to choose which images they want to work on.
   */
  protected void displayLibrary() {
    JFrame storage = new JFrame();
    storage.setTitle("Images Library");
    JPanel panel = new JPanel();
    storage.add(panel);
    storage.setSize(600, 600);
    storage.setVisible(true);

    Map<String, ImageModel> lib = this.library.returnStorage();
    for (Map.Entry<String, ImageModel> entry : lib.entrySet()) {
      JButton item = new JButton(entry.getKey());
      item.addActionListener(e -> displayImage(entry.getKey()));
      panel.add(item);
    }

  }

  /**
   * Set up the Menu Bar for the GUI and all of its related contents.
   */
  @Override
  public void setUpMenuBar() {
    JMenuBar menuBar = new JMenuBar();

    //Setup File Submenu
    JMenu fileOption = new JMenu("File");
    JMenuItem loadOption = new JMenuItem("Load");
    loadOption.addActionListener(e -> displayImageLoad());
    JMenuItem libraryOption = new JMenuItem("Library");
    libraryOption.addActionListener(e -> displayLibrary());
    JMenuItem saveOption = new JMenuItem("Save");
    saveOption.addActionListener(e -> operationSave());
    fileOption.add(loadOption);
    fileOption.add(libraryOption);
    fileOption.add(saveOption);
    menuBar.add(fileOption);

    //Setup Filter Submenu
    JMenu filterOption = new JMenu("Filter");
    JMenuItem blurOption = new JMenuItem("Blur");
    blurOption.addActionListener(e -> operationProcess("blur", ""));
    JMenuItem sharpenOption = new JMenuItem("Sharpen");
    sharpenOption.addActionListener(e -> operationProcess("sharpen", ""));
    JMenuItem brightenOption = new JMenuItem("Brighten/Darken");
    brightenOption.addActionListener(e -> operationProcess("brighten", ""));
    JMenuItem sepiaOption = new JMenuItem("Sepia");
    sepiaOption.addActionListener(e -> operationProcess("sepia", ""));
    JMenuItem greyscaleOption = new JMenuItem("Greyscale");
    greyscaleOption.addActionListener(e -> operationProcess("greyscale", ""));
    JMenu componentMenu = new JMenu("Component Visualization");
    JMenuItem valueOption = new JMenuItem("Value");
    valueOption.addActionListener(e -> operationProcess("component", "max"));
    JMenuItem intensityOption = new JMenuItem("Intensity");
    intensityOption.addActionListener(e -> operationProcess("component", "intensity"));
    JMenuItem lumaOption = new JMenuItem("Luma");
    lumaOption.addActionListener(e -> operationProcess("component", "luma"));
    JMenuItem redOption = new JMenuItem("Red");
    redOption.addActionListener(e -> operationProcess("component", "red"));
    JMenuItem greenOption = new JMenuItem("Green");
    greenOption.addActionListener(e -> operationProcess("component", "green"));
    JMenuItem blueOption = new JMenuItem("Blue");
    blueOption.addActionListener(e -> operationProcess("component", "blue"));

    componentMenu.add(valueOption);
    componentMenu.add(intensityOption);
    componentMenu.add(lumaOption);
    componentMenu.add(redOption);
    componentMenu.add(greenOption);
    componentMenu.add(blueOption);
    filterOption.add(blurOption);
    filterOption.add(sharpenOption);
    filterOption.add(brightenOption);
    filterOption.add(sepiaOption);
    filterOption.add(greyscaleOption);
    filterOption.add(componentMenu);
    menuBar.add(filterOption);

    //Setup Action Submenu
    JMenu actionOption = new JMenu("Action");
    JMenuItem flipHorizontal = new JMenuItem("Flip Horizontal");
    flipHorizontal.addActionListener(e -> operationProcess("flip", "horizontal"));
    JMenuItem flipVertical = new JMenuItem("Flip Vertical");
    flipVertical.addActionListener(e -> operationProcess("flip", "vertical"));
    JMenuItem downsizeOption = new JMenuItem("Downscale");
    downsizeOption.addActionListener(e -> operationProcess("downscale", ""));
    actionOption.add(flipHorizontal);
    actionOption.add(flipVertical);
    actionOption.add(downsizeOption);
    menuBar.add(actionOption);
    setJMenuBar(menuBar);
  }

  /**
   * Given a new name to store the image as, apply the operations selected on the image.
   *
   * @param op  represent the operation.
   * @param way represent the way to do the operation.
   */
  protected void operationProcess(String op, String way) {
    String newName = JOptionPane.showInputDialog(this,
            "What name do you want to load this image as?", null);
    if (newName == null || newName.equals("")
            || this.stringCommands.contains(newName)) {
      this.showErrorMessage("Can't save image with an empty name " +
              "or with a name similar to a command!");
    } else {
      try {
        switch (op) {
          case "blur":
            for (ViewListener listener : listenerList) {
              listener.blurEvent(currentName, newName);
              this.currentName = newName;
            }
            break;
          case "sharpen":
            for (ViewListener listener : listenerList) {
              listener.sharpenEvent(currentName, newName);
              this.currentName = newName;
            }
            break;
          case "brighten":
            String level = JOptionPane.showInputDialog(this,
                    "Enter level of increments: Positive integers for brightening, " +
                            "negative integers for darkening", null);
            if (level == null || level.equals("")) {
              this.showErrorMessage("Increments level can't be empty!");
            } else {
              try {
                int increment = Integer.parseInt(level);
                for (ViewListener listener : listenerList) {
                  listener.brightenEvent(increment, currentName, newName);
                  this.currentName = newName;
                }
              } catch (NumberFormatException e) {
                this.showErrorMessage("PLease enter a proper integer!");
              }
            }
            break;
          case "sepia":
            for (ViewListener listener : listenerList) {
              listener.sepiaEvent(currentName, newName);
              this.currentName = newName;
            }
            break;
          case "greyscale":
            for (ViewListener listener : listenerList) {
              listener.greyscaleEvent(currentName, newName);
              this.currentName = newName;
            }
            break;
          case "component":
            for (ViewListener listener : listenerList) {
              listener.componentEvent(currentName, newName, way);
              this.currentName = newName;
            }
            break;
          case "flip":
            for (ViewListener listener : listenerList) {
              listener.flipEvent(currentName, newName, way);
              this.currentName = newName;
            }
            break;
          case "downscale":
            String height = JOptionPane.showInputDialog(this,
                    "Enter new height of image", null);
            String width = JOptionPane.showInputDialog(this,
                    "Enter new width of image", null);
            if (height == null || height.equals("") || width == null || width.equals("")) {
              this.showErrorMessage("Invalid dimensions!");
            } else {
              try {
                int heightNew = Integer.parseInt(height);
                int widthNew = Integer.parseInt(width);
                if (heightNew >= this.library.contain(currentName).getHeight()
                        || widthNew >= this.library.contain(currentName).getWidth()) {
                  this.showErrorMessage("Dimensions can't be bigger " +
                          "or equal to current image");
                } else {
                  for (ViewListener listener : listenerList) {
                    listener.downsizeEvent(currentName, newName, heightNew, widthNew);
                    this.currentName = newName;
                  }
                }
              } catch (NumberFormatException e) {
                this.showErrorMessage("PLease enter a proper integer!");
              }
            }
            break;
          default:
            //there are no cases left, if there's new operations then it can be added more here.
            break;
        }
      } catch (IllegalArgumentException e) {
        this.showErrorMessage("Cannot find image/inappropriate name!");
      }
    }
  }

  /**
   * Show error message to the GUI.
   */
  @Override
  public void showErrorMessage(String msg) {
    JOptionPane.showMessageDialog(this, msg, "Error Spotted!",
            JOptionPane.ERROR_MESSAGE);
  }

  /**
   * Add a Listener to the list of Listeners.
   *
   * @param l represent a ViewListener.
   */
  @Override
  public void addListener(ViewListener l) {
    try {
      this.listenerList.add(Objects.requireNonNull(l));
    } catch (NullPointerException e) {
      throw new IllegalArgumentException("Null listener");
    }
  }

}
