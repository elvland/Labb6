package SimulationLibrary.Events;

import SimulationLibrary.State.State;
import SimulationLibrary.EventQueue;
/**
 * @author Anton Alexandersson
 * @author Olle Elvland
 * @author Lukas Eriksson
 * @author Vincent Gustafsson
 *
 * The Event class represents an abstract event in the simulation.
 */

public abstract class Event {


     // Protected varaibles avaiable for subclasses

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


    public abstract void execute();
}
