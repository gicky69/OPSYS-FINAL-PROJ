import java.util.*;

public class PPS {

    public static List<Processes> SPI = new ArrayList<>();
    public static List<GanttChart> GDC = new ArrayList<>();


    public PPS(int p[][]) {
        // Preemptive Priority Scheduling
        int n = p.length;
        int[] at = new int[n];
        int[] bt = new int[n];
        int[] prio = new int[n];

        for (int i = 0; i < n; i++) {
            at[i] = p[i][0];
            bt[i] = p[i][1];
            prio[i] = p[i][2];
        }

        Map<String, Object> result = PPS(at, bt, prio);

        System.out.println("Job\tAT\tBT\tFT\tTAT\tWT");
        List<Processes> solvedPI = (List<Processes>) result.get("solvedPI");
        List<GanttChart> ganttChartI = (List<GanttChart>) result.get("ganttChartI");

        SPI.clear();
        GDC.clear();
        SPI.addAll(solvedPI);
        GDC.addAll(ganttChartI);

        for (Processes p1 : solvedPI) {
            System.out.println(p1.job + "\t" + p1.at + "\t" + p1.bt  + "\t" + p1.ft + "\t" + p1.tat + "\t" + p1.wat);
        }

        System.out.println("Gantt Chart");
        for (GanttChart g : ganttChartI) {
            System.out.println(g.job + "\t" + g.start + "\t" + g.end);
        }
    }

    public static List<Processes> getSPI() {
        return SPI;
    }

    public static List<GanttChart> getGDC() {
        return GDC;
    }

    public static Map<String, Object> PPS(int at[], int bt[], int prio[]){
        Processes[] processes = new Processes[at.length];
        for (int i = 0; i < at.length; i++) {
            processes[i] = new Processes(i, at[i], bt[i], prio[i], bt[i]);
        }

        Arrays.sort(processes, Comparator.comparingInt((Processes p1) -> p1.at).thenComparingInt(p1 -> p1.priority));

        List<GanttChart> ganttChart = new ArrayList<>();
        List<Processes> solvedP = new ArrayList<>();
        PriorityQueue<Processes> rqueue = new PriorityQueue<>(
            Comparator.comparingInt((Processes p) -> p.priority));
        int currentTime = 0;
        int i = 0;
        Integer lastJob = null;


        while (i < processes.length || !rqueue.isEmpty()) {
            while (i < processes.length && processes[i].at <= currentTime) {
                rqueue.add(processes[i]);
                i++;
            }

            if (rqueue.isEmpty()) {
                currentTime = processes[i].at;
                continue;
            }

            Processes currentProcess = rqueue.poll();

            int timeSlice = 1;
            if (currentProcess.remainingBt <= timeSlice) {
                currentTime += currentProcess.remainingBt;
                if (lastJob == null || !lastJob.equals(currentProcess.job)) {
                    ganttChart.add(new GanttChart(currentProcess.job, currentTime - currentProcess.remainingBt, currentTime));
                } else {
                    GanttChart lastEntry = ganttChart.get(ganttChart.size() - 1);
                    lastEntry.end = currentTime;
                }
                currentProcess.ft = currentTime;
                currentProcess.tat = currentProcess.ft - currentProcess.at;
                currentProcess.wat = currentProcess.tat - currentProcess.bt;
                currentProcess.remainingBt = 0;
                solvedP.add(currentProcess);
            } else {
                currentTime += timeSlice;
                if (lastJob == null || !lastJob.equals(currentProcess.job)) {
                    ganttChart.add(new GanttChart(currentProcess.job, currentTime - timeSlice, currentTime));
                } else {
                    GanttChart lastEntry = ganttChart.get(ganttChart.size() - 1);
                    lastEntry.end = currentTime;
                }
                currentProcess.remainingBt -= timeSlice;
                rqueue.add(currentProcess);
            }
            lastJob = currentProcess.job;
        }

        Map<String, Object> result = new HashMap<>();
        result.put("solvedPI", solvedP);
        result.put("ganttChartI", ganttChart);
        return result;
    }

}