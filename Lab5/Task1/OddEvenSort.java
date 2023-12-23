//Given a sequence of N natural numbers. It is necessary to sort the sequence so that in the first part of the sequence the odd numbers from it will be sorted in ascending order, and in the second part the even numbers will be sorted in descending order.
//
//        In the first line of the input, the number of elements in the array N is given, and in the second line, the numbers are given. The output should print the sorted array.
//
//        Class Name: OddEvenSort

package Lab5.Task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OddEvenSort {

    static void oddEvenSort(int a[], int n) {
        int parCount;
        int nepCount = parCount = 0;

        for (int i = 0; i < n; i++) {
            if (a[i] % 2 == 0) {
                parCount++;
            } else {
                nepCount++;
            }
        }

        int[] nepNiza = new int[nepCount];
        int[] parNiza = new int[parCount];

        int parIndex;
        int nepIndex =parIndex = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] % 2 == 0) {
                parNiza[parIndex] = a[i];
                parIndex++;
            } else {
                nepNiza[nepIndex] = a[i];
                nepIndex++;
            }
        }

        for (int i = 0; i < nepCount - 1; i++) {
            for (int j = 0; j < nepCount - i - 1; j++) {
                if (nepNiza[j] > nepNiza[j + 1]) {
                    int temp = nepNiza[j];
                    nepNiza[j] = nepNiza[j + 1];
                    nepNiza[j + 1] = temp;
                }
            }
        }

        for (int i = 0; i < parCount - 1; i++) {
            for (int j = 0; j < parCount - i - 1; j++) {
                if (parNiza[j] < parNiza[j + 1]) {
                    int temp = parNiza[j];
                    parNiza[j] = parNiza[j + 1];
                    parNiza[j + 1] = temp;
                }
            }
        }

        int index = 0;
        for (int i = 0; i < nepCount; i++) {
            a[index++] = nepNiza[i];
        }

        for (int i = 0; i < parCount; i++) {
            a[index++] = parNiza[i];
        }
    }

    public static void main(String[] args) throws IOException {
        int i;
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String s = stdin.readLine();
        int n = Integer.parseInt(s);

        s = stdin.readLine();
        String[] pom = s.split(" ");
        int[] a = new int[n];
        for (i = 0; i < n; i++)
            a[i] = Integer.parseInt(pom[i]);
        oddEvenSort(a, n);
        for (i = 0; i < n - 1; i++)
            System.out.print(a[i] + " ");
        System.out.print(a[i]);
    }
}