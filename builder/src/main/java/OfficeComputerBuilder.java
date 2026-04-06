public class OfficeComputerBuilder implements ComputerBuilder {
    private final Computer computer = new Computer();

    @Override
    public void buildProcessor() {
        computer.setProcessor("Intel Core i5-13400");
    }

    @Override
    public void buildRAM() {
        computer.setRamGb(16);
    }

    @Override
    public void buildHardDrive() {
        computer.setHardDrive("512 GB SSD");
    }

    @Override
    public void buildGraphicsCard() {
        computer.setGraphicsCard("Integrated Graphics");
    }

    @Override
    public void buildOperatingSystem() {
        computer.setOperatingSystem("Windows 11 Home");
    }

    @Override
    public Computer getComputer() {
        return computer;
    }
}
