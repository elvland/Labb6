package SupermarketSimulation.SupermarketEvents;

import SimulationLibrary.EventQueue;
import SimulationLibrary.Events.Event;
import SimulationLibrary.State.State;
import SupermarketSimulation.Extra.Customer;
import SupermarketSimulation.SupermarketState.SupermarketState;

public class PayEvent extends Event {

    private Customer customer;

    public PayEvent(double eventTime, State state, EventQueue queue, Customer customer) {
        super(eventTime, state, queue);
        this.customer = customer;
    }

    @Override
    public void execute() {
        SupermarketState superState = (SupermarketState) state;
        superState.info(this,customer.getCustomerID());

        superState.removeCustomer(this.customer); //Customer leaves store
        superState.addValidBuyer();


        if(superState.hasCustomerInQueue()){ //Checks if theres a custoemr in the Cashout queue


            Customer nextInLine = superState.nextInLine();


            double paymentTime = superState.getPayTime( this.getCalculatedTime()) ;

            PayEvent payEvent = new PayEvent(paymentTime,superState,queue,nextInLine);
            queue.add(payEvent);

        } else { // If a Checkout is free and waiting to get used
            superState.freeCheckout();
        }
    }

    public  String toString(){
        return "Betalning";
    }
}

