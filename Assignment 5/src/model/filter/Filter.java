package model.filter;

import model.pixel.Pixel;

public interface Filter {

  Pixel[][] applyFilter();
}
