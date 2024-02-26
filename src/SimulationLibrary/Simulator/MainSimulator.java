package SimulationLibrary.Simulator;

import SimulationLibrary.EventQueue;
import SimulationLibrary.Events.Event;
import SimulationLibrary.State.State;

public class MainSimulator {

    private EventQueue eventQueue;
    private State state;

    public MainSimulator(State state,EventQueue eventQueue ){
        this.state = state;
        this.eventQueue = eventQueue;
    }

    public void run(State state,EventQueue eventQueue){
        while(state.SimulatorRunning()){
            Event nextEvent = eventQueue.removeFirst();
            nextEvent.execute();
        }
    }
}
