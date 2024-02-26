package SimulationLibrary.State;

import java.util.Observable;

public class State extends Observable {


    private double currentCalculatedTime ;
    protected boolean stopSimulator;

    public State (){
        this.stopSimulator = false;
    }

    public void stopSim(){
        this.stopSimulator = true;
    }

    public boolean SimulatorRunning()
    {
        return !this.stopSimulator;
    }

    public void infoObserver(){ //Notify observers with updated state
        setChanged();
        notifyObservers(this);
    }

    protected void setTime(double time) {
        if(time >= this.currentCalculatedTime) {
            this.currentCalculatedTime = time;
        }

    }

    public double getTime(){
        return this.currentCalculatedTime;
    }



}
