interface Animal {
    void makeSound();
}

class Dog implements Animal {
    public void makeSound() {
        System.out.println("Dog barks.");
    }
}

class Cat implements Animal {
    public void makeSound() {
        System.out.println("Cat meows.");
    }
}

public class InterfaceExample {
    public static void main(String[] args) {
        Animal dog = new Dog();
        dog.makeSound();

        Animal cat = new Cat();
        cat.makeSound();
    }
}

/*
*Key Points on Interface

*An interface is a reference type that defines a contract of methods that a class must implement.
*An interface cannot be instantiated, meaning you cannot create objects directly from it.
*Interfaces provide a way to achieve multiple inheritance in Java, as a class can implement multiple interfaces.
*All methods declared in an interface are abstract and do not have an implementation.
*Interfaces can also define constants, which are implicitly public, static, and final.
*Classes that implement an interface must provide implementations for all the methods defined in the interface.
*Interfaces cannot have instance variables, constructors, or instance methods with implementations.
*/