package SimulationLibrary.Events;

import SimulationLibrary.State.State;

import java.awt.*;

public class StopEvent extends Event{


    public StopEvent(State state, EventQueue queue) {
        super(999, state, queue);
    }

    @Override
    public void execute(){
        state.stopSim();
    }
}
