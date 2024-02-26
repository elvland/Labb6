package SupermarketSimulation.SupermarketState;

import SimulationLibrary.State.State;

import SupermarketSimulation.Extra.CheckoutHelper;
import SupermarketSimulation.Extra.Customer;
import SupermarketSimulation.Extra.CustomerFactory;
import SupermarketSimulation.Extra.Enum.CheckoutStatus;
import SupermarketSimulation.Extra.Enum.StoreStatus;
import SupermarketSimulation.Extra.FIFIOqueue;
import SupermarketSimulation.Extra.TimeHandler.ArrivalTime;
import SupermarketSimulation.Extra.TimeHandler.GatherTime;
import SupermarketSimulation.Extra.TimeHandler.PayTime;

import java.util.ArrayList;
import java.util.Queue;

public class SupermarketState extends State {
    private CheckoutHelper checkoutHelper;

    private CheckoutStatus[] checkouts;
    private Queue<Customer> checkoutQueue;
    private CustomerFactory customerFactory;
    private ArrayList<Customer> customers;
    private double currentTime;
    private StoreStatus storeStatus;

    private int purchasCount;
    private int rejectionCount;

    private ArrayList<Object> parameters ;

    private double openHours;

    private int totFreeCashoutTime;
    private int totQueueTime;

    private ArrivalTime arrivalTime;
    private GatherTime gatherTime;
    private PayTime payTime;
    private String eventName;
    private int totCustomersInStore;
    private int maxCustomers;
    private double lastPayTime;
    private int customerID;

    private int freeCashoutCount;
    private int totQueueCustomer;
    private int getTotQueueTime;

    private int customerQueueSize;
    private String queueString;
    private void notifyObs(){
        super.infoObserver();
    }

    public SupermarketState (int nAmountOfCheckOuts,int maxCustomers,
                             long lambda,
                             double pmin, double pmax, double kmin,
                             double kmax, double openHours, long seed){
        this.checkouts = new CheckoutStatus[nAmountOfCheckOuts];

        this.checkoutHelper = new CheckoutHelper(this.checkouts);

        //Store & Customer & checkots setup
        this.storeStatus = StoreStatus.Open;
        this.maxCustomers = maxCustomers;
        this.checkoutQueue = new FIFIOqueue();
        this.customerFactory = new CustomerFactory();
        this.customers = new ArrayList<>();

        // TIME MANAGEMENT RANDOMNESS
        this.arrivalTime = new ArrivalTime(lambda,seed);
        this.gatherTime = new GatherTime(pmin,pmax,seed);
        this.payTime = new PayTime(kmin,kmax,seed);


        //Stats
        this.currentTime = 0;               //a)
        this.eventName = "";              //b)
        this.customerID = 0;                //c)
        this.storeStatus = StoreStatus.Open; //d)
        this.freeCashoutCount = checkoutHelper.countFreeCashiers(checkouts); //e
        this.totFreeCashoutTime = 0; //f)
        this.totCustomersInStore = 0; //g)
        this.purchasCount = 0;//h)
        this.rejectionCount = 0;  //i)
        this.totQueueCustomer = 0; //j)
        this.getTotQueueTime = 0; //k)
        this.customerQueueSize = checkoutQueue.size(); //l)
        this.queueString = checkoutQueue.toString(); //m


        this.lastPayTime = 0;



    }



}
