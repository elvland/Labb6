package SupermarketSimulation.Extra;



public class CustomerFactory {
    private int customerID = 0;

    public Customer createCustomer(){
        Customer customer = new Customer(customerID++);

        return customer;
    }

}
