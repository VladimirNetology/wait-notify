import java.util.ArrayList;
import java.util.List;

public class CarDealer {
    public static final int MILLIS_DELIVERY = 5000;
    public static final int MILLIS_SELL = 2000;
    public List<Car> cars = new ArrayList<>();

    public synchronized void deliverCar() {
        try {
            System.out.println("START: Car Delivery " + Thread.currentThread().getName());
            Thread.sleep(MILLIS_DELIVERY);
            cars.add(new Car());
            notify();
            System.out.println("FINISH: Car Delivery " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized void sellCar() {
        try {
            System.out.println("START: Car Sell " + Thread.currentThread().getName());
            while (cars.size() == 0) {
                System.out.println(" - WAIT: Car Sell " + Thread.currentThread().getName());
                wait();
            }
            Thread.sleep(MILLIS_SELL);
            cars.remove(0);
            System.out.println("FINISH: Car Sell " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
