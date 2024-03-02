package SimulationLibrary.Simulator;

import SimulationLibrary.EventQueue;
import SimulationLibrary.Events.Event;
import SimulationLibrary.State.State;
/**
 * @author Anton Alexandersson
 * @author Olle Elvland
 * @author Lukas Eriksson
 * @author Vincent Gustafsson
 *
 * The MainSimulator class runs the simulation by running events until the simulation stops.
 */

public class MainSimulator {



    public MainSimulator( ){

    }
    /**
     * Runs the simulation by continuously executing events until the simulation stops.
     *
     * @param state The current state of the simulation.
     * @param eventQueue The event queue containing events to be executed.
     * @return The final state of the simulation after all events have been executed.
     */
    public State run(State state, EventQueue eventQueue){
        do{
            Event nextEvent = eventQueue.removeFirst();
            nextEvent.execute();
        }while(state.SimulatorRunning() || !eventQueue.isEmpty());

        return state;
    }
}
