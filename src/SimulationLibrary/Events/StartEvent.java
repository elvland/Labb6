package SimulationLibrary.Events;

import SimulationLibrary.State.State;
import SimulationLibrary.EventQueue;

public class StartEvent extends Event{

    public StartEvent(double eventTime, State state, EventQueue queue) {
        super(eventTime, state, queue);
    }

    @Override
    public void execute(){

    }
    public  String toString(){
        return "Start";
    }
}
