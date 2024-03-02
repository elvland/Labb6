package Optimize;

import SimulationLibrary.EventQueue;
import SimulationLibrary.Events.StopEvent;
import SimulationLibrary.Simulator.MainSimulator;
import SupermarketSimulation.SupermarketEvents.CloseEvent;
import SupermarketSimulation.SupermarketEvents.OpenEvent;
import SupermarketSimulation.SupermarketState.SupermarketState;
import SupermarketSimulation.View.SupermarketView;

import java.util.Random;


/**
 * @author Anton Alexandersson
 * @author Olle Elvland
 * @author Lukas Eriksson
 * @author Vincent Gustafsson
 *
 * Optimize handlar om att hitta minsta antalet kassor som minimerar antal missade
 * kunder (dvs maximerar antalet kunder som handlar). Kund missas om den inte
 * kommer in då den anländer (måste vända och handla nån annanstans) pga att det
 * redan är maximalt med kunder i butiken, vilket antas bero på att för få kassor är
 * igång.
*/

public class Optimize {
    public static void main(String[] args) {

        /*
         * OPTIMIZE DELEN
         */
        Optimize opt = new Optimize();
        //METHOD 1
        //opt.optimizeMethod1(3,K.M,K.L,
        // K.LOW_COLLECTION_TIME,K.HIGH_COLLECTION_TIME,K.LOW_PAYMENT_TIME
        // ,K.HIGH_PAYMENT_TIME,K.SEED,K.END_TIME,K.STOP_TIME,true);

        //METHOD 2
/*        System.out.println(opt.minimalMissedCustomOPT(K.M, K.L,K.LOW_COLLECTION_TIME,
                K.HIGH_COLLECTION_TIME,K.LOW_PAYMENT_TIME,K.HIGH_PAYMENT_TIME
                ,K.SEED,K.END_TIME,K.STOP_TIME) + " ST KASSOR ÄR DET BÄSTA FÖR MINIMALT MISSADE KUNDER");*/
        //METHOD3
        opt.maxCashoutMinMiss(K.M, K.L,K.LOW_COLLECTION_TIME,
                K.HIGH_COLLECTION_TIME,K.LOW_PAYMENT_TIME,K.HIGH_PAYMENT_TIME
                ,K.SEED,K.END_TIME,K.STOP_TIME);
        //System.out.println(metod3);
    }

    public SupermarketState optimizeMethod1(int nCheckouts, int nMaxCustomer, double lambda,
                                            double pmin, double pmax, double kmin, double kmax,
                                            long seed, double close, double stopx,boolean printOk ) {
        SupermarketState state = new SupermarketState(nCheckouts, nMaxCustomer, lambda, pmin, pmax, kmin, kmax, seed, true,printOk );
        SupermarketView vy = new SupermarketView(state);
        state.addObserver(vy);

        EventQueue eventQueue = new EventQueue();

        OpenEvent openEvent = new OpenEvent(state, eventQueue);
        CloseEvent closeEvent = new CloseEvent(close, state, eventQueue);
        StopEvent stopEvent = new StopEvent(state, eventQueue);

        eventQueue.add(openEvent);
        eventQueue.add(closeEvent);
        eventQueue.add(stopEvent);

        MainSimulator simulator = new MainSimulator();
        return (SupermarketState) simulator.run(state, eventQueue);

    }

    /**
     * Vaad vi är ute efter är minsta antal kassor som minimerar antal missade kunder.
     * Finds and returns the minimum number of checkouts needed to minimize the number of missed customers.
     */
        public int minimalMissedCustomOPT(int nMaxCustomer, double lambda, double pmin,
                                            double pmax, double kmin, double kmax,
                                            long seed, double close, double stop) {
        int minCheckOuts = 1;           //Keeps track of the best least checkouts to minimize amount of missed customer
        int preMinMissed = 9999999;     // number of missed customer for n used to compare n+1 checkouts
        int checkoutNumber = 1;         //Checkout number to test possible values
        boolean printOk = false;
        //
        // N Checkouts        1 2 3 4 5 6 7
        // N Missed customer [7,5,3,2,1,0,0]
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



  /*  public int optimizeMetod2b(int nMaxCustomer, long lamda, double pmin, double pmax, double kmin, double kmax, long seed,double close, double stop) {
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


    }*/



   /*
     *
     * Metod 3" söker i blindo med okända slumptalsfrön efter ett "troligt" antal kassor
     * som i värsta fall är det minsta som behövs för att missa så få kunder som möjligt.
     * (Om största minsta antal kassor som hittats inte ändras under ytterligare 100
     * körningar av "Metod 2" från "Metod 3" och med olika slumptalsfrön så antas svaret
     * ha hittats, men det kan alltså vara så att ytterligare körningar hittar ännu högre
     * minsta antal.
     *
     * Metod 2 hittar minsta bästa antal kassor
     * Metod 3 checkar med massa rabdom olika slumpade (tiden för kunderna att göra eventen blir olika ) tal för
     * att se om det ändrar missade kunder
     * istället för att bara testa olika kassor så ändras också tiden
     */

    /**
     *  //Finds highest possible cashouts to minimize number of missed customer
     */
    public void maxCashoutMinMiss(int nMaxCustomer, double lamda, double pmin,
                                  double pmax, double kmin, double kmax,
                                  int seed, double close, double stop) {
        int randSeed = 0;
        int sameCounter = 0;
        int highestMinMisses = 0;
        Random rnd = new Random(seed);

        while (true) {
            randSeed = rnd.nextInt();
            int newMinCheckouts = minimalMissedCustomOPT(nMaxCustomer, lamda, pmin,
                    pmax, kmin, kmax,
                    randSeed, close, stop);

            if (newMinCheckouts > highestMinMisses) {
                highestMinMisses = newMinCheckouts;
                sameCounter = 0;
            } else if (newMinCheckouts <= highestMinMisses) { //Always true if not previous if statement accepts
                sameCounter++;
                System.out.println(highestMinMisses + " is the best");
            }

            if (sameCounter == 100) {
                //Prints out the results with the parameters of the highest cashout number found for minimize misses
                optimizeMethod1(highestMinMisses, nMaxCustomer, lamda, pmin, pmax, kmin, kmax, seed, close, stop, true);
                break;
            }
        }
    }

}