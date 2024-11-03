import java.util.*;

public class PS {

    public static void sortbyColumn(int a[][], int c){
        Arrays.sort(a, (x, y) -> Integer.compare(x[c],y[c]));
    }

    public static List<Processes> SPI = new ArrayList<>();
    public static List<GanttChart> GDC = new ArrayList<>();

    public PS(int p[][]) {
        int n = p.length;
        int[] at = new int[n];
        int[] bt = new int[n];

        for (int i = 0; i < n; i++) {
            at[i] = p[i][0];
            bt[i] = p[i][1];
        }

        Map<String, Object> result = NPP(p);

        // System.out.println("Job\tAT\tBT\tFT\tTAT\tWT");
        List<Processes> solvedPI = (List<Processes>) result.get("solvedPI");
        List<GanttChart> ganttChartI = (List<GanttChart>) result.get("ganttChartI");

        SPI.clear();
        GDC.clear();
        SPI.addAll(solvedPI);
        GDC.addAll(ganttChartI);

        // for (Processes p1 : solvedPI) {
        //     System.out.println(p1.job + "\t" + p1.at + "\t" + p1.bt + "\t" + p1.priority + "\t" + p1.ft + "\t" + p1.tat + "\t" + p1.wat);
        // }

        // System.out.println("Gantt Chart");
        // for (GanttChart g : ganttChartI) {
        //     System.out.println(g.job + "\t" + g.start + "\t" + g.end);
        // }
    }

    public static List<Processes> getSPI() {
        return SPI;
    }

    public static List<GanttChart> getGDC() {
        return GDC;
    }

    public static Map<String, Object> NPP(int p[][]) {
        // Non Preemptive Priority Scheduling
        int n = p.length;
        Processes[] processes = new Processes[n];
        for (int i = 0; i < p.length; i++) {
            processes[i] = new Processes(i, p[i][0], p[i][1], p[i][2], 0);
        }
    
        Arrays.sort(processes, Comparator.comparingInt((Processes p1) -> p1.at).thenComparing(p1 -> p1.priority));
    
        // System.out.println("Sorted Table:");
        // for (int i = 0; i < n; i++) {
        //     System.out.println("P" + processes[i].job + " AT: " + processes[i].at + " BT: " + processes[i].bt + " Priority: " + processes[i].priority);
        // }
    
        List<GanttChart> ganttChart = new ArrayList<>();
        List<Processes> solvedPI = new ArrayList<>();
        PriorityQueue<Processes> readyQueue = new PriorityQueue<>(Comparator.comparingInt(proc -> proc.priority));
    
        int currentTime = 0;
        int index = 0; 
    
        while (index < processes.length || !readyQueue.isEmpty()) {
            while (index < processes.length && processes[index].at <= currentTime) {
                readyQueue.add(processes[index]);
                // System.out.println("test" + processes[index].job + " test " + currentTime);
                index++;
            }
    
            if (readyQueue.isEmpty()) {
                currentTime = processes[index].at;
                // System.out.println("nex ttime " + currentTime);
                continue;
            }

            Processes currentProcess = readyQueue.poll();
    
            ganttChart.add(new GanttChart(currentProcess.job, currentTime, currentTime + currentProcess.bt));
            currentProcess.ft = currentTime + currentProcess.bt;
            currentProcess.tat = currentProcess.ft - currentProcess.at;
            currentProcess.wat = currentProcess.tat - currentProcess.bt;
    
            // System.out.println("Processing P" + currentProcess.job + " at time " + currentTime);
            // System.out.println("Finish Time (FT): " + currentProcess.ft);
            // System.out.println("Turnaround Time (TAT): " + currentProcess.tat);
            // System.out.println("Waiting Time (WT): " + currentProcess.wat);
    
            solvedPI.add(currentProcess);
            currentTime += currentProcess.bt;
        }
    
        Map<String, Object> result = new HashMap<>();
        result.put("solvedPI", solvedPI);
        result.put("ganttChartI", ganttChart);
        return result;
    }
    
}