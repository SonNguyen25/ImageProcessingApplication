package command;

import model.ImageModel;
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
        System.out.println(library.contain("koala-red").getCopy()[0][0].getColor().get(0));
        System.out.println(library.contain("koala").getCopy()[0][0].getColor().get(0));
    }
}

