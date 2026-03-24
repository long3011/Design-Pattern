package guistate;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Controller {
    private Model model;
    private Gui gui;

    // Undo/redo stacks
    private final Deque<IMemento> undoHistory;
    private final Deque<IMemento> redoHistory;

    // Full timeline for history window
    private final List<IMemento> timeline;

    public Controller(Gui gui) {
        this.model = new Model();
        this.gui = gui;
        this.undoHistory = new ArrayDeque<>();
        this.redoHistory = new ArrayDeque<>();
        this.timeline = new ArrayList<>();

        // Save initial state to timeline
        timeline.add(model.createMemento());
    }

    public void setOption(int optionNumber, int choice) {
        beforeNewChange();
        model.setOption(optionNumber, choice);
        afterStateChanged();
    }

    public int getOption(int optionNumber) {
        return model.getOption(optionNumber);
    }

    public void setIsSelected(boolean isSelected) {
        beforeNewChange();
        model.setIsSelected(isSelected);
        afterStateChanged();
    }

    public boolean getIsSelected() {
        return model.getIsSelected();
    }

    public void undo() {
        if (undoHistory.isEmpty()) {
            return;
        }

        // Guideline: current -> redo, then restore popped undo
        redoHistory.push(model.createMemento());
        IMemento previousState = undoHistory.pop();
        model.restoreState(previousState);

        gui.updateGui();
        timeline.add(model.createMemento());
        gui.updateHistoryView(getTimeline());
    }

    public void redo() {
        if (redoHistory.isEmpty()) {
            return;
        }

        // Guideline: current -> undo, then restore popped redo
        undoHistory.push(model.createMemento());
        IMemento nextState = redoHistory.pop();
        model.restoreState(nextState);

        gui.updateGui();
        timeline.add(model.createMemento());
        gui.updateHistoryView(getTimeline());
    }

    public List<IMemento> getTimeline() {
        return List.copyOf(timeline);
    }

    public void restoreFromHistoryIndex(int index) {
        if (index < 0 || index >= timeline.size()) {
            return;
        }

        // Restoring from history is a new branch of state changes
        undoHistory.push(model.createMemento());
        redoHistory.clear();

        IMemento selected = timeline.get(index);
        model.restoreState(selected);
        gui.updateGui();

        timeline.add(model.createMemento());
        gui.updateHistoryView(getTimeline());
    }

    private void beforeNewChange() {
        undoHistory.push(model.createMemento());
        redoHistory.clear(); // required by assignment
    }

    private void afterStateChanged() {
        timeline.add(model.createMemento());
        gui.updateHistoryView(getTimeline());
    }
}
