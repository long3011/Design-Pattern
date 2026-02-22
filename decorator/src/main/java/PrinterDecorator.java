public class PrinterDecorator implements Printer {
    private Printer printer;

    public PrinterDecorator(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void print(String message) {
        // You can add additional behavior here before or after printing
        printer.print(message);
    }
}
