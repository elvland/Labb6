package SupermarketSimulation.Extra.TimeHandler;

public interface TimeStrategy {
     double finishEventTime(double currentTime);

     Object getEventType();
}
