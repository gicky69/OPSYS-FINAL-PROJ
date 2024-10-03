import java.util.Scanner;
import java.util.HashMap;

public class Main {
    public Main(int p[][]) {

    }



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("N: ");
        int n = sc.nextInt();

        int[][] p = new int[n][5];
        System.out.println("Enter AT and BT");
        for (int i =0;i<n;i++) {
            System.out.println("P" + (i+1) + ": ");
            p[i][0] = i + 1;
            p[i][1] = sc.nextInt();
            p[i][2] = sc.nextInt();
        }

        System.out.println("UNSORTED TABLE" );
        System.out.println("P\tAT\tBT");
        for (int i = 0;i<n;i++) {
            System.out.println("P" + p[i][0] + "\t" + p[i][1] + "\t" + p[i][2]);
        }
        System.out.println();

        SJF sJf = new SJF(p);


    }
}