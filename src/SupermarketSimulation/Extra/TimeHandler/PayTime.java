package SupermarketSimulation.Extra.TimeHandler;

public class PayTime implements TimeStrategy{
    private UniformRandomStream uniRandom;
    public PayTime(double kmin, double kmax,long seed){
        this.uniRandom = new UniformRandomStream(kmin,kmax,seed);
    }
    @Override
    public double finishEventTime(double currentTime) {
        return  currentTime + uniRandom.next();
    }

    @Override
    public Object getEventType() {
        return this;
    }
}
