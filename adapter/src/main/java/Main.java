public class Main {
    public static void main(String[] args) {
        NewDateInterface date = new CalendarToNewDateAdapter();

        date.setYear(2026);
        date.setMonth(4);
        date.setDay(6);

        System.out.printf("Initial date: %02d/%02d/%04d%n", date.getDay(), date.getMonth(), date.getYear());

        date.advanceDays(1);
        System.out.printf("After advancing 1 day: %02d/%02d/%04d%n", date.getDay(), date.getMonth(), date.getYear());

        date.advanceDays(5);
        System.out.printf("After advancing 5 more days: %02d/%02d/%04d%n", date.getDay(), date.getMonth(), date.getYear());
    }
}

