import Optimize.Optimize;
import Optimize.K;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // FOR THE EXAMPLE TEST 1 & 2
        new runSim().run(1);
        new runSim().run(2);
        System.out.println("nice");
        //runSim gogo = new runSim();
       //gogo.run();

    //   System.out.println("hejsan");
      // Optimize opt = new Optimize();

     // opt.maxCashoutMinMiss(K.M, K.L,K.LOW_COLLECTION_TIME,K.HIGH_COLLECTION_TIME,K.LOW_PAYMENT_TIME,K.HIGH_PAYMENT_TIME,K.SEED,K.END_TIME,K.STOP_TIME);
        //System.out.println(metod3);
    //   opt.optimizeMetod3(K.M, K.L,K.LOW_COLLECTION_TIME,K.HIGH_COLLECTION_TIME,K.LOW_PAYMENT_TIME,K.HIGH_PAYMENT_TIME,K.SEED,K.END_TIME,K.STOP_TIME);

     //        System.out.println(opt.optimizeMetod3(K.M, K.L,K.LOW_COLLECTION_TIME,K.HIGH_COLLECTION_TIME,K.LOW_PAYMENT_TIME,K.HIGH_PAYMENT_TIME,K.SEED,K.END_TIME,K.STOP_TIME));

        //System.out.println("Detta returnas av a1" + opt.optimizeMetod2a(5,1,0.5,1,2,3,1234));

        //opt.optimizeMetod2a(5,1,0.5,1,2,3,1234);
        //opt.optimizeMetod2a(7,3,0.6,0.9,0.35,0.6,13);
       // System.out.println("Detta returnas av a2" + opt.optimizeMetod2a(7,3,0.6,0.9,0.35,0.6,13) );

        //System.out.println("Detta returnas av b" + opt.optimizeMetod2b(7,3,0.6,0.9,0.35,0.6,13) );

        // SupermarketState state = new SupermarketState(2,7,3,0.6,0.9,0.35,0.6,10,13,false);

       // opt.optimizeMetod2(5,1,0.5,1,2,3,1234);
/*
    CheckoutStatus[] checkOutArray = new CheckoutStatus[10];
        CheckoutHelper helper = new CheckoutHelper(checkOutArray);
        System.out.println(helper.availableCashouts(checkOutArray));

        helper.fillEmptyCheckOuts(checkOutArray);
        System.out.println(helper.availableCashouts(checkOutArray) +" nu borde detta funka ");

        System.out.println(helper.countFreeCashiers(checkOutArray) + " st ledga kassor");

        helper.occupyCheckout(checkOutArray);
        System.out.println(helper.countFreeCashiers(checkOutArray) + " st ledga kassor");
        for (int i = 0 ; i < 9; i++){

            helper.occupyCheckout(checkOutArray);
            System.out.println(helper.countFreeCashiers(checkOutArray) + " st ledga kassor");
        }
        helper.occupyCheckout(checkOutArray);
        helper.occupyCheckout(checkOutArray);
        helper.occupyCheckout(checkOutArray);
        System.out.println(helper.countFreeCashiers(checkOutArray) + " st ledga kassor");
        for (int i = 0 ; i < 11; i++){

            helper.freeCheckout(checkOutArray);
            System.out.println(helper.countFreeCashiers(checkOutArray) + " st ledga kassor");
        }

        helper.occupyCheckout(checkOutArray);
        helper.occupyCheckout(checkOutArray);
        System.out.println(helper.countFreeCashiers(checkOutArray) + " st ledga kassor");*/

        /*ArrivalTime a1 = new ArrivalTime(1,1234);
        double tal = 0;
        for (int i = 0 ; i < 10; i++){
            System.out.println(tal);
            tal += a1.finishEventTime(0);
        }*/
        //test ett
         //SupermarketState t = new SupermarketState(2,5,1,0.5,1,2,3,10,1234);
      /*  SupermarketState t = new SupermarketState(K.M, (long) K.L,K.LOW_COLLECTION_TIME,K.HIGH_COLLECTION_TIME,K.LOW_PAYMENT_TIME,K.HIGH_PAYMENT_TIME,K.SEED);
       SupermarketView sw = new SupermarketView();
        t.addObserver(sw);
        EVentQueue q = new EVentQueue();
        OpenEvent s1 = new OpenEvent(t,q);
        //CloseEvent c1 = new CloseEvent(10,t,q);
        CloseEvent c2 = new CloseEvent(8,t,q);

        q.add(c2);
        StopEvent stop = new StopEvent(t,q);
        q.add(s1);
        q.add(stop);
        System.out.println(q.size());
        //!q.isEmpty()

*/
    }
}