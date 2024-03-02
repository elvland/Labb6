package SimulationLibrary.Events;

import SimulationLibrary.State.State;
import SimulationLibrary.EventQueue;
import SupermarketSimulation.SupermarketState.SupermarketState;
import SupermarketSimulation.View.SupermarketView;
/**
 * @author Anton Alexandersson
 * @author Olle Elvland
 * @author Lukas Eriksson
 * @author Vincent Gustafsson
 *
 * The StopEvent class represents an event that stops the simulation.
 */

public class StopEvent extends Event{


    public StopEvent(State state, EventQueue queue) {
        super(999, state, queue);
    }

    @Override
    public void execute(){
        state.stopSim();
        state.infoObserver();

    }
    public  String toString(){
        return "STOP";
    }
}
