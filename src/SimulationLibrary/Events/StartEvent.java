package SimulationLibrary.Events;

import SimulationLibrary.State.State;

import java.awt.*;

public class StartEvent extends Event{

    public StartEvent(double eventTime, State state, EventQueue queue) {
        super(eventTime, state, queue);
    }

    @Override
    public void execute(){

    }

}
