package SimulationLibrary.Events;

import SimulationLibrary.State.State;

import java.awt.*;

public abstract class Event {

    protected State state;
    protected EventQueue queue;
    protected double calculatedTime;


    public Event (double eventTime, State state, EventQueue queue){
        this.calculatedTime = eventTime;
        this.state = state;
        this.queue = queue;
    }

    public double getCalculatedTime() {
        return this.calculatedTime;
    }

    public void execute(){

    }
}
