abstract class Vehicle {
    protected String brand;

    public Vehicle(String brand) {
        this.brand = brand;
    }

    public abstract void start();

    public void stop() {
        System.out.println("Vehicle stopped.");
    }
}

class Car extends Vehicle {
    public Car(String brand) {
        super(brand);
    }

    public void start() {
        System.out.println("Car started. Brand: " + brand);
    }
}

public class AbstractExample {
    public static void main(String[] args) {
        Vehicle car = new Car("Toyota");
        car.start();
        car.stop();
    }
}



/*
Key Points about Abstract Classes:

*An abstract class is a class that cannot be instantiated, meaning you cannot create objects directly from it.
*Abstract classes are used as base classes for other classes to inherit from.
*An abstract class can have abstract methods, which are declared without an implementation.
*Abstract classes can also have non-abstract methods with implementations.
*Subclasses that inherit from an abstract class must provide implementations for all the abstract methods defined in the abstract class.
*Abstract classes can have instance variables and constructors.
*A class can only extend one abstract class.
*/