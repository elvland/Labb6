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
    public void infoObserver(){
        setChanged();
        notifyObservers(this);

    }

    public void setTime(double time) {
        this.currentCalculatedTime = time;
    }



}
