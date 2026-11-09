import java.util.Scanner;
import java.util.Arrays;

public class ArrayStatistics {

    static int getSum(int arr[]) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return sum;
    }

    static double getAverage(int arr[]) {
        if (arr.length == 0) {
            return 0;
        }
        int sum = getSum(arr);
        return (double) sum / arr.length;
    }

    static int getLargest(int arr[]) {
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    static int getSmallest(int arr[]) {
        int min = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        return min;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter size of array: ");
        int n = sc.nextInt();

        int arr[] = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Enter number " + (i + 1) + ": ");
            arr[i] = sc.nextInt();
        }

        System.out.println("----- Result -----");

        if (n == 0) {
            System.out.println("Array is empty");
            System.out.printf("Sum : %d\n", 0);
            System.out.printf("Average : %.2f\n", 0.0);
            System.out.println("Largest : NA");
            System.out.println("Smallest : NA");
        } else {
            System.out.printf("Sum : %d\n", getSum(arr));
            System.out.printf("Average : %.2f\n", getAverage(arr));
            System.out.printf("Largest : %d\n", getLargest(arr));
            System.out.printf("Smallest : %d\n", getSmallest(arr));

            Arrays.sort(arr);
            System.out.printf("Sorted Array : %s\n", Arrays.toString(arr));
        }

        sc.close();
    }
  }
