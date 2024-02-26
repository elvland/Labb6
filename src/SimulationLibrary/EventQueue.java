package SimulationLibrary;

import SimulationLibrary.Events.Event;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class EventQueue {

   private ArrayList<Event> events;

   public EventQueue(){
       this.events = new ArrayList<>();
   }

    public void add(Event event) {
        events.add(event);
        orderByET();
    }

    public Event first() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return events.get(0);
    }

    public Event removeFirst(){
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return events.remove(0);
    }


    public int size(){
       return this.events.size();
    }

    public boolean isEmpty(){
       return this.events.isEmpty();
    }

    private void orderByET() {
        for (int i = 0; i < events.size() - 1; i++) {
            for (int j = 0; j < events.size() - i - 1; j++) {
                // Compare each event's calculatedTime and swap if necessary
                if (events.get(j).getCalculatedTime() > events.get(j + 1).getCalculatedTime()) {
                    Event temp = events.get(j);
                    events.set(j, events.get(j + 1));
                    events.set(j + 1, temp);
                }
            }
        }
    }

}
