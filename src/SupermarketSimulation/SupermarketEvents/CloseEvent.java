package SupermarketSimulation.SupermarketEvents;

import SimulationLibrary.EventQueue;
import SimulationLibrary.Events.Event;
import SimulationLibrary.State.State;
import SupermarketSimulation.SupermarketState.SupermarketState;
/**
 * @author Anton Alexandersson
 * @author Olle Elvland
 * @author Lukas Eriksson
 * @author Vincent Gustafsson
 *
 * The CloseEvent class represents the event of closing a supermarket simulation.
 */

public class CloseEvent extends Event {
        private double closetime;


        public CloseEvent(double eventTime, State state, EventQueue queue) {
            super(eventTime, state, queue);
            this.closetime = eventTime;
        }


    @Override
    public void execute() {
        SupermarketState superState = (SupermarketState) state;

        superState.info(this,-999);
        superState.setCloseTime(this.closetime);
        superState.changeStoreStatus();


    }

    public  String toString(){
        return "Stängning ";
    }

}
