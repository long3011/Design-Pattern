public class main {
    public static void main(String[] args) {
        UIFactory factory = new BFactory();
        Button button = factory.createButton("Submit");
        TextField textField = factory.createTextField("Enter Name");
        Checkbox checkbox = factory.createCheckbox("I agree to the terms");
        button.display();
        textField.display();
        checkbox.display();
        button.setText("Send");
        textField.setText("Enter Email");
        checkbox.setText("I accept the conditions");
        button.display();
        textField.display();
        checkbox.display();
    }
}
