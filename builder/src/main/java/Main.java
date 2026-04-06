public class Main {
    public static void main(String[] args) {
        ComputerDirector computerDirector = new ComputerDirector();
        Computer officeComputer = computerDirector.constructOfficeComputer();
        Computer gamingComputer = computerDirector.constructGamingComputer();

        System.out.println("=== Gaming Computer ===");
        System.out.println(gamingComputer);
        System.out.println();
        System.out.println("=== Office Computer ===");
        System.out.println(officeComputer);
    }
}
