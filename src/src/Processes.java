public class Processes {
    int job;
    int at;
    int bt; 
    int ft; 
    int tat; 
    int wat; 
    int priority;
    int remainingBt;

    public Processes (int job, int at, int bt, int priority, int remainingBt) {
        this.job = job;
        this.at = at;
        this.bt = bt;
        this.priority = priority;
        this.remainingBt = remainingBt;
    }
}