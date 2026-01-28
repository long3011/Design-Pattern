public class TextFieldB extends  TextField {
    String placeholderText;
    public TextFieldB(String placeholderText) {
        this.placeholderText = placeholderText;
    }
    public void setText(String text) {
        this.placeholderText = text;
    }
    @Override
    public void display() {
        System.out.println("-".repeat(placeholderText.length() + 4));
        System.out.println("- " + placeholderText + " -");
        System.out.println("-".repeat(placeholderText.length() + 4));
    }
}
