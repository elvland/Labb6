package SupermarketSimulation;

import SimulationLibrary.EventQueue;
import SimulationLibrary.Events.StopEvent;
import SimulationLibrary.Simulator.MainSimulator;
import SupermarketSimulation.SupermarketEvents.CloseEvent;
import SupermarketSimulation.SupermarketEvents.OpenEvent;
import SupermarketSimulation.SupermarketState.SupermarketState;
import SupermarketSimulation.View.SupermarketView;
/**
 * @author Anton Alexandersson
 * @author Olle Elvland
 * @author Lukas Eriksson
 * @author Vincent Gustafsson
 *
 This class serves as a simulation runner for a supermarket scenario, executing test cases and managing event flow.
 */
public class runSim {

        public static void main(String[] args) {
                // FOR THE EXAMPLE TEST 1 & 2

                /**
                 * RUNSIM Delen
                 */
                 new runSim().run(1);
                //new runSim().run(2);
                //   System.out.println("nice");
        }
                private void run(int test) {
                        SupermarketState superstate = null;

                        EventQueue eventQ = new EventQueue();

                        if (test == 1) {
                                superstate = new SupermarketState(2, 5, 1, 0.5, 1, 2, 3, 1234, false,false);
                                CloseEvent close = new CloseEvent(10,superstate,eventQ);
                                eventQ.add(close);
                        } else if (test == 2) {

                                superstate = new SupermarketState(2, 7, 3, 0.6, 0.9, 0.35, 0.6, 13, false,false);
                                CloseEvent close = new CloseEvent(8,superstate,eventQ);
                                eventQ.add(close);
                        }

                        if (superstate != null) {
                                SupermarketView marketView = new SupermarketView(superstate);
                                superstate.addObserver(marketView);

                                OpenEvent open = new OpenEvent(superstate, eventQ);


                                StopEvent stop = new StopEvent(superstate, eventQ);


                                eventQ.add(open);
                                eventQ.add(stop);
                                System.out.println(eventQ.size() + " storlek");
                                for(int i = 0 ; i <= 2; i++) {
                                        System.out.println(eventQ.getEvent(i));
                                }
                                MainSimulator simulator = new MainSimulator();
                                simulator.run(superstate, eventQ);
                        }
                }
}
