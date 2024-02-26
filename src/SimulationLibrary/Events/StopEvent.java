package SimulationLibrary.Events;

import SimulationLibrary.State.State;
import SimulationLibrary.EventQueue;
import SupermarketSimulation.SupermarketState.SupermarketState;
import SupermarketSimulation.View.SupermarketView;

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
