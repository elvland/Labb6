package SupermarketSimulation.SupermarketEvents;

import SimulationLibrary.EventQueue;
import SimulationLibrary.Events.Event;
import SimulationLibrary.State.State;
import SupermarketSimulation.Extra.Customer;
import SupermarketSimulation.SupermarketState.SupermarketState;


public class ArriveEvent extends Event {
    private Customer customer;
    public ArriveEvent(double eventTime, State state, EventQueue queue, Customer customer) {
        super(eventTime, state, queue);
        this.customer = customer;
    }

    @Override
    public void execute() {
        SupermarketState superState = (SupermarketState) state;
        superState.info(this,this.customer.getCustomerID());

        if(superState.isStoreOpen()){ //Checks if store open

            if(superState.isStoreFree()){ //Checks if Store is free for customer to entry

                superState.addCustomerInStore();

                double gatherItemsTime =  superState.getGatherTime(super.getCalculatedTime()) ;
                // Creates a GatherEvent and adds it to the event queue
                GatherEvent gatherEvent = new GatherEvent(gatherItemsTime,superState,queue, this.customer);

                queue.add(gatherEvent);
            } else{
                superState.addRejectedCustomer(); // STORE IS FULL
            }

            double arriveTime = superState.getArriveTime(super.getCalculatedTime());
            // Creates an ArriveEvent for the next customer and adds it to the event queue
            ArriveEvent arrivalEvent = new ArriveEvent(arriveTime,state,queue,superState.createNewCustomer());

            queue.add(arrivalEvent);
        } else {
            //Store is closed sry bro,do nothing

        }


    }

    public  String toString(){
        return "Ankomst";
    }

}
