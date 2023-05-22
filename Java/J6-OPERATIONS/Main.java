import java.util.ArrayList;
import java.util.List;

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

public class Main {
    public static void main(String[] args) {
   
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Alice", 25));
        persons.add(new Person("Bob", 30));
        persons.add(new Person("Charlie", 20));
        persons.add(new Person("Dave", 35));
        persons.add(new Person("Eve", 28));

        
        List<Person> filteredPersons = persons.stream()
                .filter(person -> person.getAge() > 25)
                .toList();

        
        List<Person> sortedPersons = persons.stream()
                .sorted((p1, p2) -> Integer.compare(p1.getAge(), p2.getAge()))
                .toList();

        
        System.out.println("Filtered Persons:");
        filteredPersons.forEach(person -> System.out.println(person.getName() + " - " + person.getAge()));

        
        System.out.println("\nSorted Persons:");
        sortedPersons.forEach(person -> System.out.println(person.getName() + " - " + person.getAge()));
    }
}
