import java.util.Scanner;
import javax.swing.SwingUtilities;

public class Main {
    public static void algorithm(){ 
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[][]  p = new int[n][3];
        for (int i=0;i<n;i++) {
            System.out.println("P" + i + "\nAT BT PRIORITY: ");
            p[i][0] = sc.nextInt();
            p[i][1] = sc.nextInt();
            p[i][2] = sc.nextInt();
        }

        System.out.println("UNSORTED TABLE");
        for (int i=0;i<n;i++) {
            System.out.println("P" + i + " " + p[i][0] + " " + p[i][1] + " " + p[i][2]);
        }

        // SJF sjf = new SJF(p);
        // PS nps = new PS(p);
        PPS pps = new PPS(p);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MFrame frame = new MFrame();
            frame.setVisible(true);
        });
    }
}