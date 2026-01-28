public class Employee extends Component {
    private int salary;

    public Employee(String name, int salary) {
        super(name);
        this.salary = salary;
    }

    @Override
    public void display() {
        System.out.println("<employee>");
        super.display();
        System.out.println("<salary>" + salary+"</salary>");
        System.out.println("</employee>");
    }

    @Override
    public void add(Component component) {
        throw new UnsupportedOperationException("Not supported in leaf.");    }

    @Override
    public void remove(Component component) {
        throw new UnsupportedOperationException("Not supported in leaf.");    }

    @Override
    public Component getChild(int index) {
        throw new UnsupportedOperationException("Not supported in leaf.");    }

    public double getSalary() {
        return salary;
    }
}
