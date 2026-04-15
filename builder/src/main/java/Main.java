public class Main {
    public static void main(String[] args) {
        ComputerDirector gamingComputerDirector = new ComputerDirector(new GamingComputerBuilder());
        ComputerDirector officeComputerDirector = new ComputerDirector(new OfficeComputerBuilder());
        Computer officeComputer = gamingComputerDirector.constructComputer();
        Computer gamingComputer = officeComputerDirector.constructComputer();

        System.out.println("=== Gaming Computer ===");
        System.out.println(gamingComputer);
        System.out.println();
        System.out.println("=== Office Computer ===");
        System.out.println(officeComputer);
    }
}
