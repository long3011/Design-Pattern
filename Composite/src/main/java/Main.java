public class Main {
    public static void main(String[] args) {
        Organization org = new Organization("Tech Corp");

        Department devDept = new Department("Development");
        Employee dev1 = new Employee("Alice", 3000);
        Employee dev2 = new Employee("Bob", 2000);
        devDept.add(dev1);
        devDept.add(dev2);

        Department hrDept = new Department("Human Resources");
        Employee hr1 = new Employee("Charlie", 4000);
        hrDept.add(hr1);

        org.add(devDept);
        org.add(hrDept);

        org.display();
        System.out.println("Total Salary: " + org.getTotalSalary());
    }
}
