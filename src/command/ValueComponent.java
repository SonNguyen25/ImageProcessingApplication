package command;


import model.image.ImageModel;
import model.storage.ImageLibrary;

public class ValueComponent implements ImageCommand{
    String name;
    String nameOutput;
    String way;
    public ValueComponent(String name, String nameOut, String way) {
        this.name = name;
        this.nameOutput = nameOut;
        this.way = way;
    }

    @Override
    public void go(ImageLibrary library) throws IllegalStateException {
        ImageModel model = library.contain(name);
        if (model == null) {
            throw new IllegalStateException("Cannot find immage");
        }
        model.greyScale(way);
        library.put(this.nameOutput, model);
    }
}

