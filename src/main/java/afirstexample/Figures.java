package afirstexample;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by alvaro on 11/04/15.
 */
public class Figures implements Iterable<Figure>{
	private List<Figure> values = new ArrayList<>();

	public void add (final Figure figure) {
		values.add(figure);
	}

	@Override
	public Iterator iterator () {
		return values.iterator();
	}
}
