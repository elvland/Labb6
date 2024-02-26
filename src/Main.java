import SupermarketSimulation.Extra.CheckoutHelper;
import SupermarketSimulation.Extra.Enum.CheckoutStatus;
import SupermarketSimulation.Extra.TimeHandler.ArrivalTime;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
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

        ArrivalTime a1 = new ArrivalTime(1,1234);
        double tal = 0;
        for (int i = 0 ; i < 10; i++){
            System.out.println(tal);
            tal += a1.finishEventTime(0);
        }



    }
}