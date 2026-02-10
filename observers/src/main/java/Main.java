public class Main {
    public static void main(String[] args) throws InterruptedException {
        WeatherStation weatherStation = new WeatherStation();

        Observer observer1 = new WeatherObserver("Observer 1");
        Observer observer2 = new WeatherObserver("Observer 2");
        Observer observer3 = new WeatherObserver("Observer 3");

        weatherStation.addObserver(observer1);
        weatherStation.addObserver(observer2);
        weatherStation.addObserver(observer3);

        Thread weatherThread = new Thread(weatherStation);
        weatherThread.start();
        try{
            Thread.sleep(10000); // Let it run for 10 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        weatherStation.removeObserver(observer1);
        weatherStation.removeObserver(observer3);
    }
}
