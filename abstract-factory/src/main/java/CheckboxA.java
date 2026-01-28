public class CheckboxA extends  Checkbox {
    String label;
    public CheckboxA(String label) {
        this.label = label;
    }
    public void setText(String text) {
        this.label = text;
    }
    @Override
    public void display() {
        System.out.println("= = " + label);
    }
}
