package SupermarketSimulation.SupermarketEvents;

import SimulationLibrary.Events.StartEvent;
import SimulationLibrary.State.State;
import SimulationLibrary.EventQueue;
import SupermarketSimulation.Extra.Customer;
import SupermarketSimulation.SupermarketState.SupermarketState;
/**
 * @author Anton Alexandersson
 * @author Olle Elvland
 * @author Lukas Eriksson
 * @author Vincent Gustafsson
 *
 * The OpenEvent class represents the event of opening a supermarket simulation.
 * It initializes the simulation by creating the first customer and scheduling their arrival.
 */

public class OpenEvent extends StartEvent {
    private Customer customer;

    public OpenEvent(State state, EventQueue queue) {
        super(0, state, queue);
    }

    @Override
    public void execute() {
        //Convert into supermarketState subclass
        SupermarketState superState = (SupermarketState) state;
        //Creates first Customer
        this.customer = superState.createNewCustomer();

        // Provides information about the event to the state
        superState.info(this,this.customer.getCustomerID());


        double arriveStartTime = superState.getArriveTime(super.getCalculatedTime());

        queue.add(new ArriveEvent(arriveStartTime,state,queue,customer));
    }

    public  String toString(){
        return "Start" ;
    }
}
