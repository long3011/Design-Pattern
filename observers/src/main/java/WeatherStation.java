public class WeatherStation extends Observable implements Runnable {

    public void run() {
        try {
            int temperature = (int) (Math.random() * 30); // Initial temperature between 0 and 30
            while (true) {
                // Simulate temperature change
                temperature += (Math.random()>0.5 ? 1:-1 );// Change temperature by -1 or +1
                String event = "Temperature changed to: " + temperature + "°C";
                notifyObservers(event);
                Thread.sleep((int) (Math.random()*4000)+1000); // Wait for 1-5 seconds before next update
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
