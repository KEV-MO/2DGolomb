package coordinates;

import java.util.Comparator;
public class SquarePointComparator implements Comparator<Point>{
	public int compare(Point a, Point b){
		return a.getOrder() - b.getOrder();
	}
}
