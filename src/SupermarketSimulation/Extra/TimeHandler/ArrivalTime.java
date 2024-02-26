package SupermarketSimulation.Extra.TimeHandler;

public class ArrivalTime implements TimeStrategy {

    private ExponentialRandomStream expo;
    public ArrivalTime(long lambda, long seed){
        this.expo = new ExponentialRandomStream(lambda,seed);
    }

    @Override
    public double finishEventTime(double currentTime) {

        return currentTime + expo.next();
    }


}
