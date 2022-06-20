import java.util.ArrayList;
import java.util.List;

public class CarDealer {
    private final int MILLIS_DELIVERY;
    private final int MILLIS_SELL;
    private final List<Car> cars = new ArrayList<>();

    public CarDealer(int MILLIS_DELIVERY, int MILLIS_SELL) {
        this.MILLIS_DELIVERY = MILLIS_DELIVERY;
        this.MILLIS_SELL = MILLIS_SELL;
    }

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
