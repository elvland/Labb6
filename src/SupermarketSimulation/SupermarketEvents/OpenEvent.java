package SupermarketSimulation.SupermarketEvents;

import SimulationLibrary.Events.StartEvent;
import SimulationLibrary.State.State;
import SimulationLibrary.EventQueue;
import SupermarketSimulation.Extra.Customer;
import SupermarketSimulation.SupermarketState.SupermarketState;

public class OpenEvent extends StartEvent {
    private Customer customer;

    public OpenEvent(State state, EventQueue queue) {
        super(0, state, queue);
    }

    @Override
    public void execute() {

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
