public class GanttChart {
    int job;
    int start;
    int end;

    public GanttChart(int job, int start, int end) {
        this.job = job;
        this.start = start;
        this.end = end;
    }

    public int getJob() {
        return job;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public void setEnd(int end) {
        this.end = end;
    }

}