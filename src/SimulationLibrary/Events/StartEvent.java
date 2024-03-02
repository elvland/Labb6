package SimulationLibrary.Events;

import SimulationLibrary.State.State;
import SimulationLibrary.EventQueue;
/**
 * @author Anton Alexandersson
 * @author Olle Elvland
 * @author Lukas Eriksson
 * @author Vincent Gustafsson
 *
 * The StartEvent class represents an event that starts the simulation.
 */
public class StartEvent extends Event{

    public StartEvent(double eventTime, State state, EventQueue queue) {
        super(eventTime, state, queue);
    }

    @Override
    public void execute(){
        state.infoObserver();
    }
    public  String toString(){
        return "Start";
    }
}
