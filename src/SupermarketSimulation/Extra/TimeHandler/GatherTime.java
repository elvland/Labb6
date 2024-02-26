package SupermarketSimulation.Extra.TimeHandler;

public class GatherTime implements  TimeStrategy{

    private UniformRandomStream uniRandom;
    public GatherTime(double pmin, double pmax,long seed){
        this.uniRandom = new UniformRandomStream(pmin,pmax,seed);
    }
    @Override
    public double finishEventTime(double currentTime) {
        return  currentTime + uniRandom.next();
    }
}
