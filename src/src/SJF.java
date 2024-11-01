import java.util.*;

public class SJF {
    public static void sortbyColumn(int a[][], int c){
        Arrays.sort(a, (x, y) -> Integer.compare(x[c],y[c]));
    }
    
    Processes processes = new Processes(0, 0, 0, 0, 0);
    GanttChart ganttChart = new GanttChart(0, 0, 0);

    public static List<Processes> SPI = new ArrayList<>();
    public static List<GanttChart> GDC = new ArrayList<>();
    
    public SJF(int[][] p) {
        int n = p.length;
        int[] at = new int[n];
        int[] bt = new int[n];

        for (int i = 0; i < n; i++) {
            at[i] = p[i][0];
            bt[i] = p[i][1];
        }

        Map<String, Object> result = sjf(at, bt);

        System.out.println("Job\tAT\tBT\tFT\tTAT\tWT");
        List<Processes> solvedPI = (List<Processes>) result.get("solvedPI");
        List<GanttChart> ganttChartI = (List<GanttChart>) result.get("ganttChartI");
        SPI.clear();
        GDC.clear();
        SPI.addAll(solvedPI);
        GDC.addAll(ganttChartI);

        for (Processes p1 : solvedPI) {
            System.out.println(p1.job + "\t" + p1.at + "\t" + p1.bt + "\t" + p1.ft + "\t" + p1.tat + "\t" + p1.wat);
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

    public static Map<String, Object> sjf(int[] at, int[] bt) {
        List<Processes> processesList = new ArrayList<>();

        for (int i = 0; i < at.length; i++) {
            processesList.add(new Processes(i, at[i], bt[i], 0, bt[i]));
        }

        processesList.sort(Comparator.comparingInt(p -> p.at));

        List<Processes> solvedPI = new ArrayList<>();
        List<GanttChart> ganttChart = new ArrayList<>();
        
        PriorityQueue<Processes> readyQueue = new PriorityQueue<>(
                Comparator.comparingInt((Processes p) -> p.bt).thenComparingInt(p -> p.at)
        );

        int currentTime = 0;
        int index = 0;

        while (!readyQueue.isEmpty() || index < processesList.size()) {
            while (index < processesList.size() && processesList.get(index).at <= currentTime) {
                readyQueue.add(processesList.get(index));
                index++;
            }

            if (readyQueue.isEmpty()) {
                currentTime = processesList.get(index).at;
                continue;
            }

            Processes currentProcess = readyQueue.poll();

            ganttChart.add(new GanttChart(currentProcess.job, currentTime, currentTime + currentProcess.bt));
            currentProcess.ft = currentTime + currentProcess.bt;
            currentProcess.tat = currentProcess.ft - currentProcess.at;
            currentProcess.wat = currentProcess.tat - currentProcess.bt;

            solvedPI.add(currentProcess);
            currentTime += currentProcess.bt;
        }

        Map<String, Object> result = new HashMap<>();
        result.put("solvedPI", solvedPI);
        result.put("ganttChartI", ganttChart);
        return result;
    }
}