
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static java.util.Objects.isNull;

public class Horse {

    private final String name;
    private final double speed;
    private double distance;

    static final Logger logger = LogManager.getLogger(Horse.class);

    public Horse(String name, double speed, double distance) {

        if (isNull(name)) {
           logger.log(Level.ERROR,"Name cannot be null");
            throw new IllegalArgumentException("Name cannot be null.");
        } else if (name.isBlank()) {
            logger.log(Level.ERROR,"Name cannot be blank");
            throw new IllegalArgumentException("Name cannot be blank.");
        }
        if (speed < 0) {
            logger.log(Level.ERROR,"Speed cannot be negative");
            throw new IllegalArgumentException("Speed cannot be negative.");
        }
        if (distance < 0) {
            logger.log(Level.ERROR,"Distance cannot be negative");
            throw new IllegalArgumentException("Distance cannot be negative.");
        }

        this.name = name;
        this.speed = speed;
        this.distance = distance;
       logger.log(Level.DEBUG," Создание Horse, имя ["+name+"], скорость ["+speed+"]");
    }

    public Horse(String name, double speed) {
        this(name, speed, 0);
    }



    public String getName() {
        return name;
    }

    public double getSpeed() {
        return speed;
    }

    public double getDistance() {
        return distance;
    }

    public void move() {
        distance += speed * getRandomDouble(0.2, 0.9);
    }

    public static double getRandomDouble(double min, double max) {
        return (Math.random() * (max - min)) + min;
    }


}
