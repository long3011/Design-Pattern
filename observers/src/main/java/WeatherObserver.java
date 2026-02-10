public class WeatherObserver implements Observer{
    private String name;

    public WeatherObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(String event) {
        System.out.println("[" + name + "] Weather update received: " + event);
    }
}
