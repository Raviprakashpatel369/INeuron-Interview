import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class SecondLargestSmallest {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of integers: ");
        int count = scanner.nextInt();

        System.out.println("Enter the integers:");
        for (int i = 0; i < count; i++) {
            numbers.add(scanner.nextInt());
        }

        if (count < 2) {
            System.out.println("Insufficient number of integers entered.");
        } else {
            int secondLargest = findSecondLargest(numbers);
            int secondSmallest = findSecondSmallest(numbers);

            System.out.println("Second Largest: " + secondLargest);
            System.out.println("Second Smallest: " + secondSmallest);
        }
    }

    private static int findSecondLargest(List<Integer> numbers) {
        int max = Integer.MIN_VALUE;
        int secondMax = Integer.MIN_VALUE;

        for (int num : numbers) {
            if (num > max) {
                secondMax = max;
                max = num;
            } else if (num > secondMax && num < max) {
                secondMax = num;
            }
        }

        return secondMax;
    }

    private static int findSecondSmallest(List<Integer> numbers) {
        int min = Integer.MAX_VALUE;
        int secondMin = Integer.MAX_VALUE;

        for (int num : numbers) {
            if (num < min) {
                secondMin = min;
                min = num;
            } else if (num < secondMin && num > min) {
                secondMin = num;
            }
        }

        return secondMin;
    }
}
