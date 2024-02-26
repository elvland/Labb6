package Optimize;

import SimulationLibrary.EventQueue;
import SimulationLibrary.Events.StopEvent;
import SimulationLibrary.Simulator.MainSimulator;
import SupermarketSimulation.SupermarketEvents.CloseEvent;
import SupermarketSimulation.SupermarketEvents.OpenEvent;
import SupermarketSimulation.SupermarketState.SupermarketState;
import SupermarketSimulation.View.SupermarketView;

import java.util.Random;

public class Optimize {


    public SupermarketState optimizeMethod1(int nCheckouts, int nMaxCustomer, double lambda,
                                            double pmin, double pmax, double kmin, double kmax,
                                            long seed, double close, double stopx,boolean printOk ) {
        SupermarketState state = new SupermarketState(nCheckouts, nMaxCustomer, lambda, pmin, pmax, kmin, kmax, seed, true,printOk );
        SupermarketView vy = new SupermarketView(state);
        state.addObserver(vy);
        EventQueue q = new EventQueue();
        OpenEvent s1 = new OpenEvent(state, q);
        CloseEvent c2 = new CloseEvent(close, state, q);
        StopEvent stop = new StopEvent(state, q);

        q.add(c2);
        q.add(s1);
        q.add(stop);

        MainSimulator simulator = new MainSimulator();
        return (SupermarketState) simulator.run(state, q);

    }

    /**
     *
     * Finds and returns the minimum number of checkouts needed to minimize the number of missed customers.
     */
        public int minimalMissedCustomOPT(int nMaxCustomer, double lambda, double pmin,
                                            double pmax, double kmin, double kmax,
                                            long seed, double close, double stop) {
        int minCheckOuts = 0;
        int preMinMissed = 9999999;
        int checkoutNumber = 1;
        boolean printOk = false;
        // N Checkouts        1 2 3 4 5 6 7 8
        // N Missed customer [5,4,3,2,0,0,0,0]
        // DECLINING FUNCTION MissedCustomers(checkouts) = - ((Cashouts * N) + 1) EXAMPLE
        // max misses when only one checkout
        // minimum MISSES when checkoutnumbeer == total custoemrs in store
        while (checkoutNumber < nMaxCustomer) {
            SupermarketState superState = optimizeMethod1(checkoutNumber, nMaxCustomer, lambda,
                                                            pmin, pmax, kmin,
                                                          kmax, seed, close, stop,printOk );
            int numMissedCustomer = superState.getRejectionCount();

            // Update the minimum number of checkouts and missed customers if a new minimum is found compared to previous


            if (numMissedCustomer < preMinMissed) {
                preMinMissed = numMissedCustomer;
                minCheckOuts = checkoutNumber;
            }
            if (numMissedCustomer == 0 ) { //Can't find a smaller number of checkouts than when numMissedCustomer == 0
               return minCheckOuts;
            }

            checkoutNumber++;
        }
        return  minCheckOuts;


    }



    public int optimizeMetod2b(int nMaxCustomer, long lamda, double pmin, double pmax, double kmin, double kmax, long seed,double close, double stop) {
        SupermarketState superState;
        int numMissedCustomer = 0;
        int maxPPLMiss = 1; // Right maximal misses
        int minPPLMiss = nMaxCustomer; // Left minimal misses
        int currentminCASHOUTS = 0;
        int midCheckoutCheck = 0;

        //      CHECKOUTS     1 2 3 4 5 6
        //num missed customer[5,4,3,0,0,0] HITTAR TVÅ
        while (maxPPLMiss <= minPPLMiss) {
            midCheckoutCheck = maxPPLMiss + (maxPPLMiss - maxPPLMiss) / 2; // Calculate midpoint LEFT + RIGHT / 2


            superState = optimizeMethod1(midCheckoutCheck, nMaxCustomer, lamda, pmin, pmax, kmin, kmax, seed,stop,close,true);

            //total missed customers in midcheckout
            numMissedCustomer = superState.getRejectionCount();

            // Update currentminCASHOUTS and minPPLMiss based on the number of missed customers
            if (numMissedCustomer < minPPLMiss) {
                currentminCASHOUTS = midCheckoutCheck;
                minPPLMiss = numMissedCustomer;
                maxPPLMiss = midCheckoutCheck + 1;
            } else if (numMissedCustomer == 0) {
                return currentminCASHOUTS;
            }

        }


        return currentminCASHOUTS;


    }

    //Finds highest possible cashouts to minimize number of missed customer
    public void maxCashoutMinMiss(int nMaxCustomer, double lamda, double pmin,
                                  double pmax, double kmin, double kmax,
                                  int seed, double close, double stop) {
        //int randSeed = 0;
        int sameCounter = 0;
        int highestMinMisses = 0;
        Random rnd = new Random(seed);

        while (true) {
            //randSeed = rnd.nextInt();
            int newMinCheckouts = minimalMissedCustomOPT(nMaxCustomer, lamda, pmin,
                    pmax, kmin, kmax,
                    seed, close, stop);

            if (newMinCheckouts > highestMinMisses) {
                highestMinMisses = newMinCheckouts;
                sameCounter = 0;
            } else if (newMinCheckouts <= highestMinMisses) {
                sameCounter++;
                System.out.println(highestMinMisses);
            }

            if (sameCounter == 100) {
                optimizeMethod1(highestMinMisses, nMaxCustomer, lamda, pmin, pmax, kmin, kmax, seed, close, stop, true);
                break;
            }
        }
    }

}