public class AFactory extends  UIFactory {
    @Override
    public Button createButton(String buttonText) {
        return new ButtonA(buttonText);
    }

    @Override
    public TextField createTextField(String placeholderText) {
        return new TextFieldA(placeholderText);
    }

    @Override
    public Checkbox createCheckbox(String label) {
        return new CheckboxA(label);
    }
}
