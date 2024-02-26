package SupermarketSimulation.Extra;



public class CustomerFactory {
    private int customerID = 0;

    public Customer createCustomer(){
        Customer customer = new Customer(this.customerID);
        this.customerID++;

        return customer;
    }

}
