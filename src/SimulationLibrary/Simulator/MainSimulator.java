package SimulationLibrary.Simulator;

import SimulationLibrary.EventQueue;
import SimulationLibrary.Events.Event;
import SimulationLibrary.State.State;

public class MainSimulator {



    public MainSimulator( ){

    }

    public State run(State state, EventQueue eventQueue){
        do{
            Event nextEvent = eventQueue.removeFirst();
            nextEvent.execute();
        }while(state.SimulatorRunning() || !eventQueue.isEmpty());

        return state;
    }
}
