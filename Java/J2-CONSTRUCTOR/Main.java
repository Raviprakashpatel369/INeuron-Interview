class Parent {
    public Parent() {
        System.out.println("Parent constructor invoked.");
    }
}

class Child extends Parent {
    public Child() {
        super(); 
        System.out.println("Child constructor invoked.");
    }
}

public class Main {
    public static void main(String[] args) {
        Child child = new Child();
    }
}
