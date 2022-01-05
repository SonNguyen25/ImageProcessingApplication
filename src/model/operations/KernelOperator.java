package model.operations;

import java.util.Arrays;

import model.pixel.EightBitPixelModel;
import model.pixel.Pixel;

/**
 * This is a class that uses for getting a kernel for blur or sharpen and apply it to a
 * 2-D array of pixels.
 */
public class KernelOperator {
  private final double[][] kernel;
  private final int size;
  private Pixel[][] pixels;

  /**
   * This is the constructor for KernelOperator that takes in a kernel that changes the
   * color of the image.
   *
   * @param kernel The 2-D kernel for changing the image.
   * @param size   The size of the kernel.
   * @param pixels The 2-D array of pixels.
   */
  public KernelOperator(double[][] kernel, Pixel[][] pixels, int size) {
    if (kernel == null || pixels == null) {
      throw new IllegalArgumentException();
    }
    this.kernel = kernel;
    this.size = size;
    this.pixels = pixels;
  }

  /**
   * This a method that uses for applying the 2-D kernel to the given input 2-D pixels with or
   * without masked image's pixels.
   *
   * @param masked The masked image's pixels.
   * @return Update 2-D pixels by applying 2-D kernel.
   */
  public Pixel[][] processKernel(Pixel[][] masked) {
    int moveIndex = (size - 1) / 2;
    if (masked == null) {
      for (int i = 0; i < pixels.length; i++) {
        for (int k = 0; k < pixels[i].length; k++) {
          this.processKernelHelper(i, k, moveIndex);
        }
      }
    } else {
      for (int i = 0; i < pixels.length; i++) {
        for (int k = 0; k < pixels[i].length; k++) {
          if (masked[i][k].getColor().get(0) == 0 && masked[i][k].getColor().get(1) == 0
                  && masked[i][k].getColor().get(2) == 0) {
            this.processKernelHelper(i, k, moveIndex);
          }
        }
      }
    }

    return pixels;
  }

  /**
   * This helper method help to apply the 2-D kernel to the given input 2-D pixels.
   *
   * @param i         represent the height position of pixel.
   * @param k         represent the width position of pixel.
   * @param moveIndex represents the move index of the operation.
   */
  private void processKernelHelper(int i, int k, int moveIndex) {
    int newR = 0;
    int newG = 0;
    int newB = 0;

    int[][] sizeCutR = new int[size][size];
    int[][] sizeCutG = new int[size][size];
    int[][] sizeCutB = new int[size][size];
    for (int j = i - moveIndex, a = 0; j < i + moveIndex + 1; j++, a++) {

      for (int h = k - moveIndex, b = 0; h < k + moveIndex + 1; h++, b++) {
        if (j < 0 || h < 0 || j >= pixels.length || h >= pixels[i].length) {
          sizeCutR[a][b] = 0;
          sizeCutG[a][b] = 0;
          sizeCutB[a][b] = 0;
        } else {
          sizeCutR[a][b] = pixels[j][h].getColor().get(0);
          sizeCutG[a][b] = pixels[j][h].getColor().get(1);
          sizeCutB[a][b] = pixels[j][h].getColor().get(2);

        }
      }

    }

    for (int z = 0; z < sizeCutR.length; z++) {
      for (int y = 0; y < sizeCutR[z].length; y++) {
        newR += (int) (sizeCutR[z][y] * kernel[z][y]);
        newG += (int) (sizeCutG[z][y] * kernel[z][y]);
        newB += (int) (sizeCutB[z][y] * kernel[z][y]);
      }
    }
    if (pixels[i][k].getColor().size() > 3) {
      pixels[i][k] = new EightBitPixelModel(Arrays.asList(newR, newG, newB,
              pixels[i][k].getColor().get(3)));
    } else {
      pixels[i][k] = new EightBitPixelModel(Arrays.asList(newR, newG, newB));
    }
  }

}
