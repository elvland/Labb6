package SupermarketSimulation.Extra;

import SupermarketSimulation.Extra.Enum.CheckoutStatus;

import java.util.ArrayList;

public class CheckoutHelper {
    private CheckoutStatus status;

    private final int totCheckouts;
    private int freeCashCounter;

    public CheckoutHelper(CheckoutStatus[] checkOutArray) {
        fillEmptyCheckOuts(checkOutArray);
        totCheckouts = checkOutArray.length;
    }

    public void fillEmptyCheckOuts(CheckoutStatus[] checkOutArray){

        for (int i = 0; i <checkOutArray.length; i++) {
            checkOutArray[i] = CheckoutStatus.Free;
        }
        this.freeCashCounter = checkOutArray.length; // Start from the last cashier [0,1,2,3,4,5] <----
    }
    //protected
     public int countFreeCashiers(CheckoutStatus[] checkOutArray) {
        int freeCount = 0;
        for (CheckoutStatus checkStatus : checkOutArray) {
            if (checkStatus == CheckoutStatus.Free) {
                freeCount++;
            }
        }
        return freeCount;
    }

    public void occupyCheckout(CheckoutStatus[] checkOutArray) {
        if(availableCashouts(checkOutArray)){
        checkOutArray[--freeCashCounter] = CheckoutStatus.Occupied;
        } else {
            System.out.println("all checkoutsFull");
        }

    }

    public void freeCheckout(CheckoutStatus[] checkOutArray) {
        if(allCheckoutsEmpty(checkOutArray)){
            System.out.println("all Chechouts Empty");

        } else{
            checkOutArray[freeCashCounter++] = CheckoutStatus.Free;
        }
    }

    private boolean allCheckoutsEmpty(CheckoutStatus[] checkOutArray){
        return countFreeCashiers(checkOutArray) == totCheckouts;
    }


    public boolean availableCashouts(CheckoutStatus[] checkOutArray) {

        return countFreeCashiers(checkOutArray) > 0;
    }
}
