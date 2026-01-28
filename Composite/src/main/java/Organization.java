import java.util.ArrayList;
import java.util.List;

public class Organization extends Component {

    private List<Component> departments = new ArrayList<>();

    public Organization(String name) {
        super(name);
    }

    @Override
    public void display() {
        System.out.println("<organization>");
        super.display();
        for (Component department : departments) {
            department.display();
        }
        System.out.println("</organization>");
    }

    @Override
    public void add(Component component) {
        this.departments.add(component);
    }

    @Override
    public void remove(Component component) {
        this.departments.remove(component);
    }

    @Override
    public Component getChild(int index) {
        return this.departments.get(index);
    }

    public double getTotalSalary() {
        double totalSalary = 0;
        for (Component department : departments) {
            totalSalary += ((Department) department).getTotalSalary();
        }
        return totalSalary;
    }
}
