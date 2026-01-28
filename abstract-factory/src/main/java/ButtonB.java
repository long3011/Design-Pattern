public class ButtonB extends Button {
    String buttonText;
    public ButtonB(String buttonText) {
        this.buttonText = buttonText;
    }
    public void setText(String text) {
        this.buttonText = text;
    }
    @Override
    public void display() {
        System.out.println("-".repeat(buttonText.length() + 4));
        System.out.println("- "+buttonText+" -");
        System.out.println("-".repeat(buttonText.length() + 4));
    }
}
