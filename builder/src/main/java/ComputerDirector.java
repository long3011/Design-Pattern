public class ComputerDirector {
    private ComputerBuilder builder;

    public Computer constructGamingComputer() {
        this.builder = new GamingComputerBuilder();
        builder.buildProcessor();
        builder.buildRAM();
        builder.buildHardDrive();
        builder.buildGraphicsCard();
        builder.buildOperatingSystem();
        return builder.getComputer();
    }

    public Computer constructOfficeComputer() {
        this.builder = new OfficeComputerBuilder();
        builder.buildProcessor();
        builder.buildRAM();
        builder.buildHardDrive();
        builder.buildGraphicsCard();
        builder.buildOperatingSystem();
        return builder.getComputer();
    }
}
