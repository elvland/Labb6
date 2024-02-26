package SupermarketSimulation.SupermarketEvents;

import SimulationLibrary.EventQueue;
import SimulationLibrary.Events.Event;
import SimulationLibrary.State.State;
import SupermarketSimulation.Extra.Customer;
import SupermarketSimulation.SupermarketState.SupermarketState;

public class GatherEvent extends Event {
    private Customer customer;

    public GatherEvent(double eventTime, State state, EventQueue queue, Customer customer) {
        super(eventTime, state, queue);
        this.customer = customer;
    }

    @Override
    public void execute() {
       SupermarketState superState = (SupermarketState) state;
        superState.info(this,this.customer.getCustomerID());


        if(superState.hasFreeChckout()){ // Checks if free Checkout available

            superState.useCheckout(); //Removes a cashout slot

            double paymentTime = superState.getPayTime(super.getCalculatedTime());
            PayEvent payEvent = new PayEvent(paymentTime,state,queue,this.customer);

            queue.add(payEvent);

        } else { //If all Cashiers are occupied add customer to Cashout queue

            superState.addCustomerQueue(this.customer);

        }
    }

    public  String toString(){
        return "Plock";
    }
}
