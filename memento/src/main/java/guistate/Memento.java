package guistate;

import java.util.Arrays;

public class Memento implements IMemento {
    private int[] options;
    private boolean isSelected;

    public Memento(int[] options, boolean isSelected) {
        this.options = options.clone(); // Copy options array
        this.isSelected = isSelected;
        System.out.println("Memento created");
    }

    public int[] getOptions() {
        return options;
    }

    public boolean isSelected() {
        return isSelected;
    }

    @Override
    public String describe() {
        return " | options=" + Arrays.toString(options)
                + " | selected=" + isSelected;
    }

    @Override
    public String toString() {
        return describe();
    }
}
