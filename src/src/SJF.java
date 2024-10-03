public class SJF {
    public SJF(int p[][]) {
        // SJF Non Preemptive
        int n = p.length;
        float avgwt, avgtat;
        int total = 0; // tmep

        // Sort P by BT
        for (int i = 0;i < n;i++) {
            int ix = i;

            for (int j = i + 1;j < n;j++) {
                if (p[j][2] < p[ix][2]) {
                    ix = j;
                }
            }

            // Sort BT
            int temp = p[i][2];
            p[i][2] = p[ix][2];
            p[ix][2] = temp;

            // Sort PROCESS NUMBER
            temp = p[i][0];
            p[i][0] = p[ix][0];
            p[ix][0] = temp;

            // Sort AT
            temp = p[i][1];
            p[i][1] = p[ix][1];
            p[ix][1] = temp;

            // Sort PRIORITY
            temp = p[i][3];
            p[i][3] = p[ix][3];
            p[ix][3] = temp;

        }

        // Set WT 0 sa nauuna sa table
        // compute na yung WT
        // then get average
        p[0][4] = 0;
        // Calculate WT
        for (int i=0;i<n;i++) {
            p[i][4] = 0;
            for (int j = 0;j < i;j++) {
                p[i][4] += p[j][2];
            }
            total += p[i][4];
        }
        avgwt = (float)total / n;
        total = 0;

        // Calculate TAT
        for (int i = 0;i < n;i++) {
            p[i][5] = p[i][2] + p[i][4];
            total += p[i][5];
        }
        avgtat = (float)total / n;


        // clear screen
        System.out.print("\033[H\033[2J");
        System.out.flush();

        // Print Unsorted Table
        System.out.println("SJF Non Preemptive");
        System.out.println("P\tAT\tBT\tPT\tWT\tTAT");
        for (int i = 0;i < n;i++) {
            System.out.println("P" + p[i][0] + "\t" + p[i][1] + "\t" + p[i][2] + "\t" + p[i][3] + "\t" + p[i][4]+ "\t" + p[i][5]);
        }

        System.out.println("Average Waiting Time: " + avgwt);
        System.out.println("Average Turnaround Time: " + avgtat);

        System.out.println("Gantt Chart");
        for (int i = 0; i < n; i++) {
            System.out.print("P" + p[i][0] + " ");
        }
        System.out.println();

    }

}
