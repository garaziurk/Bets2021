package iterator;

import java.util.Vector;

import domain.Event;

public class IteratzaileHedatua implements ExtendedIterator<Event> {
	Vector<Event> events;
	int position=0;
	
	public IteratzaileHedatua(Vector<Event> ev) {
		events = ev;
	}
	
	@Override
	public boolean hasNext() {
		return position<events.size();
	}

	@Override
	public Event next() {
		Event ev = events.get(position);
		position++;
		return ev;
	}

	@Override
	public Event previous() {
		Event ev = events.get(position);
		position--;
		return ev;
	}

	@Override
	public boolean hasPrevious() {
		return position>=0;
	}

	@Override
	public void goFirst() {
		this.position=0;

	}

	@Override
	public void goLast() {
		this.position=events.size()-1;
	}

}
