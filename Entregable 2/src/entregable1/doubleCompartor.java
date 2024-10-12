package entregable1;

import java.util.Comparator;

public class doubleCompartor implements Comparator<Coin> {

	@Override
	public int compare(Coin o1, Coin o2) {
		
		return (int) (o2.getStock() - o1.getStock());
	}

}
