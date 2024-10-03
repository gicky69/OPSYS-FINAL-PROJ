public class SJF {
    public SJF(int p[][]) {
        // SJF Non Preemptive
        int n = p.length;
        float avg_wt, avg_tat;
        int total = 0; // tmep

        // Sort P by BT
        for (int i = 0;i < n;i++) {
            int ix = i;

            for (int j = i + 1;j < n;j++) {
                if (p[j][1] < p[ix][1]) {
                    ix = j;
                }
            }

            int temp = p[i][1];
            p[i][1] = p[ix][1];
            p[ix][1] = temp;

            temp = p[i][0];
            p[i][0] = p[ix][0];
            p[ix][0] = temp;
        }

        // select Minimum BT
        p[0][3] = 0;
        // Calculate WT
        for (int i=0;i<n;i++) {
            p[i][3] = 0;
            for (int j = 0;j < i;j++) {
                p[i][3] += p[j][1];
            }
            total += p[i][3];
        }
        avg_wt = (float)total / n;
        total = 0;

        // Calculate TAT
        for (int i = 0;i < n;i++) {
            p[i][4] = p[i][1] + p[i][3];
            total += p[i][4];
        }
        avg_tat = (float)total / n;


        // clear screen
        System.out.print("\033[H\033[2J");
        System.out.flush();

        // Print Unsorted Table
        System.out.println("P\tBT\tAT\tWT\tTAT");
        for (int i = 0;i < n;i++) {
            System.out.println("P" + p[i][0] + "\t" + p[i][1] + "\t" + p[i][2] + "\t" + p[i][3] + "\t" + p[i][4]);
        }

        System.out.println("Average Waiting Time: " + avg_wt);
        System.out.println("Average Turnaround Time: " + avg_tat);

        System.out.println("Gantt Chart");
        for (int i = 0; i < n; i++) {
            System.out.print("P" + p[i][0] + " ");
        }
        System.out.println();

    }

}
