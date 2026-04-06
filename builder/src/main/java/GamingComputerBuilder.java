public class GamingComputerBuilder implements ComputerBuilder {
    private final Computer computer = new Computer();

    @Override
    public void buildProcessor() {
        computer.setProcessor("AMD Ryzen 9 7950X3D");
    }

    @Override
    public void buildRAM() {
        computer.setRamGb(32);
    }

    @Override
    public void buildHardDrive() {
        computer.setHardDrive("2 TB NVMe SSD");
    }

    @Override
    public void buildGraphicsCard() {
        computer.setGraphicsCard("NVIDIA GeForce RTX 4090");
    }

    @Override
    public void buildOperatingSystem() {
        computer.setOperatingSystem("Windows 11 Pro");
    }

    @Override
    public Computer getComputer() {
        return computer;
    }
}
