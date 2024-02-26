import SimulationLibrary.EventQueue;
import SimulationLibrary.Events.StopEvent;
import SimulationLibrary.Simulator.MainSimulator;
import SupermarketSimulation.SupermarketEvents.CloseEvent;
import SupermarketSimulation.SupermarketEvents.OpenEvent;
import SupermarketSimulation.SupermarketState.SupermarketState;
import SupermarketSimulation.View.SupermarketView;

public class runSim {
                public void run(int test) {
                        SupermarketState superstate = null;

                        EventQueue eventQ = new EventQueue();

                        if (test == 1) {
                                superstate = new SupermarketState(2, 5, 1, 0.5, 1, 2, 3, 1234, false,false);
                        } else if (test == 2) {
                                superstate = new SupermarketState(2, 7, 3, 0.6, 0.9, 0.35, 0.6, 13, false,false);
                        }

                        if (superstate != null) {
                                SupermarketView marketView = new SupermarketView(superstate);
                                superstate.addObserver(marketView);

                                OpenEvent open = new OpenEvent(superstate, eventQ);
                                CloseEvent close = new CloseEvent((test == 1) ? 10 : 8, superstate, eventQ);
                                StopEvent stop = new StopEvent(superstate, eventQ);

                                eventQ.add(close);
                                eventQ.add(open);
                                eventQ.add(stop);
                                MainSimulator simulator = new MainSimulator();
                                simulator.run(superstate, eventQ);
                        }
                }
}
