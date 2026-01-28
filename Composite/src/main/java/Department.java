import java.util.ArrayList;
import java.util.List;

public class Department extends Component {

    private List<Component> employees = new ArrayList<>();

    public Department(String name) {
        super(name);
    }

    @Override
    public void display() {
        System.out.println("<department>");
        super.display();
        for (Component employee : employees) {
            employee.display();
        }
        System.out.println("</department>");
    }

    @Override
    public void add(Component component) {
        this.employees.add(component);
    }

    @Override
    public void remove(Component component) {
        this.employees.remove(component);
    }

    @Override
    public Component getChild(int index) {
        return this.employees.get(index);
    }

    public double getTotalSalary() {
        double totalSalary = 0;
        for (Component employee : employees) {
            if (employee instanceof Employee) {
                totalSalary += ((Employee) employee).getSalary();
            }
        }
        return totalSalary;
    }
}
