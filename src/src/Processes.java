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

    public int getJob() {
        return job;
    }

    public int getAt() {
        return at;
    }

    public int getBt() {
        return bt;
    }

    public int getFt() {
        return ft;
    }

    public int getTat() {
        return tat;
    }

    public int getWat() {
        return wat;
    }

    public int getPriority() {
        return priority;
    }

    public void setJob(int job) {
        this.job = job;
    }

    public void setAt(int at) {
        this.at = at;
    }

    public void setBt(int bt) {
        this.bt = bt;
    }

    public void setFt(int ft) {
        this.ft = ft;
    }

    public void setTat(int tat) {
        this.tat = tat;
    }

    public void setWat(int wat) {
        this.wat = wat;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    
}