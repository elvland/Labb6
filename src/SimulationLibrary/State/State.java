package SimulationLibrary.State;

import java.util.Observable;
/**
 * @author Anton Alexandersson
 * @author Olle Elvland
 * @author Lukas Eriksson
 * @author Vincent Gustafsson
 *
 * The State class represents the state of the simulation. It extends the Observable class
 * to allow view observers to be notified of state changes.
 */


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



}
