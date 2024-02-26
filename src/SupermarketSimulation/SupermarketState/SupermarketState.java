package SupermarketSimulation.SupermarketState;

import SimulationLibrary.Events.Event;
import SimulationLibrary.State.State;

import SupermarketSimulation.Extra.Customer;
import SupermarketSimulation.Extra.CustomerFactory;
import SupermarketSimulation.Extra.Enum.CheckoutStatus;
import SupermarketSimulation.Extra.Enum.StoreStatus;
import SupermarketSimulation.Extra.FIFIOqueue;
import SupermarketSimulation.Extra.TimeHandler.ArrivalTime;
import SupermarketSimulation.Extra.TimeHandler.GatherTime;
import SupermarketSimulation.Extra.TimeHandler.PayTime;

import java.util.ArrayList;
import java.util.Arrays;

public class SupermarketState extends State {
    private final CheckoutHelper checkoutHelper;

    private final CheckoutStatus[] checkouts;
    private final FIFIOqueue checkoutQueue;
    private final CustomerFactory customerFactory;
    private final ArrayList<Customer> customers;
    private double currentTime;
    private StoreStatus storeStatus;
    private int missedBuyers = 0;
    private int purchasCount;
    private int rejectionCount;

    private final ArrayList<Object> parameters ;

    private double totFreeCashoutTime;
    private double totQueueTime;
    private final ArrivalTime arrivalTime;
    private final GatherTime gatherTime;
    private final PayTime payTime;
    private String eventName;
    private int totCustomersInStore;
    private final int maxCustomers;
    private double lastPayTime;
    private int customerID;

    private int freeCashoutCount;
    private int totQueueCustomer;


    private int numberinstore;
    private double preEventTime;

    private final int totcheck;
    private int overallqueuePeople;

    private final boolean optimize;
    private int totCheckouts;

    public double getCloseTime() {
        return closeTime;
    }
    public void setCloseTime(double closeTime){
        this.closeTime = closeTime;
    }
    private boolean printRes;
    private  double closeTime;

    public boolean isPrintRes() {
        return printRes;
    }

    public void setPrintRes(boolean printRes) {
        this.printRes = printRes;
    }

    public SupermarketState (int nAmountOfCheckOuts, int maxCustomers, double lambda,
                             double pmin, double pmax, double kmin,
                             double kmax, long seed , boolean optimize,boolean printRes){
        this.parameters = new ArrayList<>();
        this.parameters.addAll(Arrays.asList(nAmountOfCheckOuts,maxCustomers,lambda,pmin,pmax,kmin,kmax,seed));

        this.printRes = printRes;
        this.optimize = optimize;
        this.totCheckouts = nAmountOfCheckOuts;

        this.checkouts = new CheckoutStatus[nAmountOfCheckOuts];
        this.checkoutHelper = new CheckoutHelper(this.checkouts);
        this.totcheck=nAmountOfCheckOuts ;

        //Store & Customer & checkots setup
        this.storeStatus = StoreStatus.Open;
        this.maxCustomers = maxCustomers;
        this.checkoutQueue = new FIFIOqueue();
        this.customerFactory = new CustomerFactory();
        this.customers = new ArrayList<>();

        // TIME MANAGEMENT RANDOMNESS

        this.arrivalTime = new ArrivalTime((long) lambda,seed);
        this.gatherTime = new GatherTime(pmin,pmax,seed);
        this.payTime = new PayTime(kmin,kmax,seed);


        //Stats
        this.currentTime = 0;                                               //TID)
        this.eventName = "";                                                //HÄNDELSE)
        this.customerID = 0;                                                //KUND NUMMER )
        this.storeStatus = StoreStatus.Open;                                // ?)
        this.freeCashoutCount = checkoutHelper.countFreeCashiers(checkouts);//led
        this.totFreeCashoutTime = 0;                                        //ledT)
        this.totCustomersInStore = 0;                                       //I)
        this.purchasCount = 0;                                              //$)
        this.rejectionCount = 0;                                            // :-()
        this.totQueueCustomer = 0;                                          //köat)
        this.totQueueTime = 0;                                              //köT)
        //Köar)
        //[Kassakö]

        //Results
        this.lastPayTime = 0;
        this.overallqueuePeople = 0;

    }
    public boolean isOptimize() {
        return optimize;
    }

    public ArrayList<Object> getParameters() {
        return parameters;
    }

    //------------------View GETTERS-------------------//

    public int getCheckoutTOTAL(){
        return totCheckouts;
    }
    public double getEventTime(){ //EVENT START-TIME
        return this.currentTime;
    }
    public String getEventName(){ //EVENTNAME
        return this.eventName;
    }
    public int getCustomerID(){ //CUSTOMER ID
        return customerID;
    }

    public String getStoreStatus(){ // Storestatus string (ö = öppen, s = stängt)
        return this.storeStatus == StoreStatus.Open ? "ö" : "s";
    }


    public int getNumberInStore() {
        return numberinstore;
    }

    public void setNumberInStore() {
        this.numberinstore++;
    }
    public double getfreeCashOutTime(){ //TOTAL FREE/WASTED CASHOUT TIME
        return totFreeCashoutTime;
    }
    public int getFreeCashoutCount(){ // NUMBEER OF FREE CASHOTS
        freeCashoutCount = checkoutHelper.countFreeCashiers(checkouts);
        return freeCashoutCount;
    }

    public int getTotCustomersInStore(){ //NUMBER OF CUSTOMERS IN STORE
            return totCustomersInStore;
    }

    public int getNumValidBuyers(){ //NUMBER OF SUCCESFULL PURCHASES
        return purchasCount;
    }

    public int getRejectionCount(){ //NUMBER OF MISSED CUSTOMERS
        return rejectionCount;
    }
    public int getTotQueueCustomer(){ //NUMBEER OF CUSTOMERS QUEUED
        return totQueueCustomer;
    }

    public double getTotQueuTime(){ //CURRENTLY NUMBER OF CUSTOMER IN QUEUE
        return totQueueTime;
    }

    public int getTotCheckouts(){
        return totcheck;
    }
    public void setPreEventTime(double time){
        preEventTime = time;
    }

    public double getPreEventTime(){
        return preEventTime ;
    }

    //------------------CustomerStats-------------------//
    public void setPreCustomer(int customerID){
        this.customerID =customerID;
    }
    public void addRejectedCustomer(){
        if(storeStatus == StoreStatus.Open){
            this.rejectionCount++;
        }
    }
    public void addValidBuyer(){
        this.purchasCount++;
        if(storeStatus == StoreStatus.Closed) {
            addTooLate();
        }

    }

    private void addTooLate() {
        missedBuyers++;
    }
    public int getLateBuyers() {
        return  missedBuyers;
    }
    public int getOverallQueueppl() {
        return  overallqueuePeople;
    }
    public void addCustomerInStore(){
        setNumberInStore();
        this.totCustomersInStore++;
    }

    public void removeCustomer(Customer customer){
        this.totCustomersInStore--;
        customers.remove(customer);
    }

    //------------------CheckoutFunctions------------------//

    public String getQueue() {
        return checkoutQueue.toString();
    }
    public void useCheckout(){
        checkoutHelper.occupyCheckout(checkouts);
    }
    public void freeCheckout(){
        checkoutHelper.freeCheckout(checkouts);
    }

    public void addCustomerQueue(Customer c){
        totQueueCustomer++;
        this.overallqueuePeople++;
        checkoutQueue.läggtill(c);
    }
    public Customer nextInLine(){
        totQueueCustomer--;
        return checkoutQueue.getFirstInLine();
    }

    public boolean hasCustomerInQueue(){
        return !checkoutQueue.isQueueEmpty();
    }
    public boolean hasFreeChckout(){
        return freeCashoutCount > 0 ;
    }


    /**
     * Calculates total time all the cashouts stood unused in the store
     */
    public void freeCashoutTime() {
        double eventTimeDifference =  getEventTime() - getPreEventTime() ;
        double cashoutTime = eventTimeDifference * getFreeCashoutCount();

        //Customers arriving after closetime shouldn't affect free cashout time
        if (storeStatus == StoreStatus.Closed && !getEventName().equals("Ankomst")) {
            this.totFreeCashoutTime += cashoutTime;
        } else if (storeStatus == StoreStatus.Open) {
            this.totFreeCashoutTime += cashoutTime;
        }

        setPreEventTime(getEventTime()); //Assign eventime to ease calculations for the next events time
    }

    /**
     * Calculates total time all the customers stood in queue waiting for a chashout in the store
     */
    private void calcQueueTime(double calculatedTime) {
        if(hasCustomerInQueue() ){
            totQueueTime += (calculatedTime - getPreEventTime())   * getTotQueueCustomer();
        }
    }



    //------------------StoreStatus------------------//

    public boolean isStoreOpen(){
        return storeStatus == StoreStatus.Open;
    }
    public void changeStoreStatus() {
        //Swaps storeStatus to opposite store status (open or closed)
        storeStatus = (storeStatus == StoreStatus.Open) ? StoreStatus.Closed : StoreStatus.Open;
    }
    public boolean isStoreFree(){
        return getTotCustomersInStore() < this.maxCustomers;
    }
    //---------------------TIME---------------------//

    public double getArriveTime(double preEndTime) {
        Object eventType = arrivalTime.getEventType();
        return nextEventTime(preEndTime, eventType);
    }

    public double getGatherTime(double preEndTime) {
        Object eventType = gatherTime.getEventType();
        return nextEventTime(preEndTime, eventType);
    }

    public double getPayTime(double preEndTime) {
        Object eventType = payTime.getEventType();
        return nextEventTime(preEndTime, eventType);
    }

    public Customer createNewCustomer() {
        return customerFactory.createCustomer();
    }


    public double getLastPayTime() {
        return lastPayTime;
    }

    private double nextEventTime(double preEndTime, Object eventType) {
        double nextEventTime;

        setPreEventTime(preEndTime);

        if (eventType instanceof ArrivalTime || preEndTime == 0) {
            nextEventTime = arrivalTime.finishEventTime(preEndTime);
        } else if (eventType instanceof GatherTime) {
            nextEventTime = gatherTime.finishEventTime(preEndTime);
        } else if (eventType instanceof PayTime) {
            nextEventTime = payTime.finishEventTime(preEndTime);
            this.lastPayTime = nextEventTime;
        } else {
            return 0;
        }

        super.setTime(nextEventTime); //Sets new Simulator time to next event

        return nextEventTime;
    }

    public void setEventTime(double eventTime) {
        this.currentTime = eventTime;
    }

    private void setEventName(String eventName) {
        this.eventName = eventName;
    }

    /**
     * Updates state based on event and customer ID:
     * Calculates queue time
     * Sets event time and name
     * Updates free cashout time
     * Sets current customer ID
     * Notifies observer about state update.
     *
     * @param preEvent    The event preceding the current event.
     * @param customerID  The ID of the customer associated with the event.
     */
    public void info(Event preEvent, int customerID) {
        calcQueueTime(preEvent.getCalculatedTime());
        setEventTime(preEvent.getCalculatedTime());
        setEventName(preEvent.toString());
        freeCashoutTime();
        setPreCustomer(customerID);
        super.infoObserver();
    }





}
