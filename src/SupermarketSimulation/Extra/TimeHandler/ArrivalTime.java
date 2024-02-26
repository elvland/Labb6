package SupermarketSimulation.Extra.TimeHandler;

public class ArrivalTime implements TimeStrategy {

    private ExponentialRandomStream expo;
    private long lamdba;
    public ArrivalTime(long lambda, long seed){
        this.expo = new ExponentialRandomStream(lambda,seed);
        this.lamdba = lambda;
    }

    @Override
    public double finishEventTime(double currentTime) {

        return currentTime + expo.next(); //CURRENT TIME + DELTA = END EVENTTIME
    }

    @Override
    public Object getEventType() {
        return this;
    }



}
