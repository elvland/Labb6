package SupermarketSimulation.View;

import SimulationLibrary.View.View;

import SupermarketSimulation.SupermarketState.SupermarketState;

import java.util.ArrayList;
import java.util.Observable;

public class SupermarketView extends View {

    public SupermarketView(SupermarketState state){
       // printParam(state.getParameters());
        printHeader();
    }

    private void printHeader() {
        System.out.printf("\t%s \t%7s \t%s \t%s \t%s \t%s \t%s \t%s \t%s \t%s \t%s \t%s \t%s \n",
              "Tid", "Händelse", "Kund", "?", "led", "ledT", "I", "$", ":-(", "köat", "köT", "köar", "[Kassakö..]");

    }

    @Override
    public void update(Observable o, Object superState) {
        super.update(o, superState);

        printall((SupermarketState) superState);
    }

    public  void printParam(ArrayList<Object> parametrs){
        System.out.println("PARAMETRAR");
        System.out.println("==========");
        System.out.printf("Antal kassor, N...........: %s \n",parametrs.get(0));
        System.out.printf("Max som ryms, M...........: %s \n",parametrs.get(1));
        System.out.printf("Ankomsthastighet, lambda..: %s \n" ,parametrs.get(2));
        System.out.printf("Plocktider, [P_min..P_max]: [%s..%s] \n" ,parametrs.get(3),parametrs.get(4));
        System.out.printf("betaltider, [K_min..K_max]: [%s..%s] \n" ,parametrs.get(5), parametrs.get(6));
        System.out.printf("Frö, f....................:  %s \n\n", parametrs.get(7) );


    }

    public void printall(SupermarketState state) {

        if (state.isOptimize()) {
            if(state.isPrintRes()) {

                if (!state.SimulatorRunning()) {
                    printParam(state.getParameters());

                    System.out.printf("Stängning sker vid %.2f och stop hädnesle sker vid 999 \n", state.getCloseTime());
                    System.out.printf("Minsta antal kassor som ger minimalt antal missade (%s) (KUNDER): %s (KASOR) 14",
                            state.getRejectionCount(), state.getCheckoutTOTAL());
                }

            }
        } else {


            String queueTime = String.format("%.2f", state.getTotQueuTime());
            String s = String.format("%.2f", state.getEventTime());

            String ledigtid = String.format("%.2f", state.getfreeCashOutTime());
            if (state.getTime() == 0) {
                System.out.printf("\t%s \t%8s \n", s, state.getEventName());
            } else if (state.getEventName().equals("Stängning ")) {

                System.out.printf("\t%s  \t%s" +
                                "\t%5s \t%2s \t%8s " +
                                "\t%s \t%s \t%s " +
                                "\t\t%s \t\t%s \t%s \t\t  %s \n",
                        s, "Stänger \t---",
                        state.getStoreStatus(), state.getFreeCashoutCount(), ledigtid,
                        state.getTotCustomersInStore(), state.getNumValidBuyers(), state.getRejectionCount()
                        , state.getOverallQueueppl(), queueTime, state.getTotQueueCustomer(), state.getQueue());
            } else if (!state.SimulatorRunning()) {
                System.out.println("\t999 \tSTOP  \n");
                printResults(state);
            } else if (!state.getEventName().equals("Stängning")) {
                String st = "---";
                System.out.printf("\t%s \t%8s \t%s" +
                                "\t%5s \t%2s \t%8s " +
                                "\t%s \t%s \t%s " +
                                "\t\t%s \t\t%s \t%s \t\t  %s \n",
                        s, state.getEventName(), state.getCustomerID(),
                        state.getStoreStatus(), state.getFreeCashoutCount(), ledigtid,
                        state.getTotCustomersInStore(), state.getNumValidBuyers(), state.getRejectionCount()
                        , state.getOverallQueueppl(), queueTime, state.getTotQueueCustomer(), state.getQueue());
            } else {
                System.out.printf("\t%s \t%8s \t%... " +
                                "\t%5s \t%2s \t%8s " +
                                "\t%s \t%s \t%s " +
                                "\t\t%s \t\t%s \t%s \t\t  %s \n",
                        s,
                        state.getStoreStatus(), state.getFreeCashoutCount(), ledigtid,
                        state.getTotCustomersInStore(), state.getNumValidBuyers(), state.getRejectionCount()
                        , state.getOverallQueueppl(), queueTime, state.getTotQueueCustomer() + " " + state.getQueue());
            }

        }
    }



    public SupermarketState printResults(SupermarketState state) {
            {
                double avgFree = state.getfreeCashOutTime()/state.getTotCheckouts();
                ArrayList<Object> resultsArr = new ArrayList<>();
                //.add((state.getLateBuyers() + state.getNumValidBuyers()));
               // resultsArr.add((state.getNumValidBuyers()));
                resultsArr.add(state.getLateBuyers());
                resultsArr.add(state.getTotCheckouts() );
                resultsArr.add(avgFree);
                resultsArr.add((avgFree/state.getLastPayTime()) * 100);
                resultsArr.add(state.getOverallQueueppl());
                resultsArr.add(state.getTotQueuTime());
                resultsArr.add(state.getTotQueuTime()/state.getOverallQueueppl());
                if(state.isOptimize()){
                    return state;
                  //  return getResult(resultsArr);
                } else {
                    String s = String.format("%.2f", state.getEventTime());
                    System.out.println("RESULTAT");
                    System.out.println("========");
                    System.out.println("1) Av " + (state.getNumberInStore() + state.getRejectionCount())  + " kunder handlade " + (state.getNumValidBuyers()) +
                            " medan " + state.getRejectionCount() + " missades.");
                    System.out.printf("2) Total tid " + state.getTotCheckouts() + " kassor varit lediga: %.2f te.\n", state.getfreeCashOutTime());

                    System.out.printf("   Genomsnittlig ledig kassatid: %.2f te (dvs %.2f%% av tiden från öppning tills sista kunden betalat).\n",
                            avgFree, (avgFree/state.getLastPayTime()) * 100);
                    System.out.printf("3) Total tid %d kunder tvingats köa: %.2f te.\n", state.getOverallQueueppl(), state.getTotQueuTime());
                    System.out.printf("   Genomsnittlig kötid: %.2f te.\n\n\n", ( state.getTotQueuTime()/state.getOverallQueueppl()));

                    return null;
                }

            }
        }

        public ArrayList<Object> getResult(ArrayList<Object> results){
            return results;
        }



}
