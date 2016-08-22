import coordinates.Point;
import java.util.*;
public abstract class TwoDimGolomb{
    private ArrayList<Point> ptArray;

    public abstract boolean isGolomb(Point p); //The arraylist of points will maintain Golomb property when p is added
    public abstract void greedyRotate(); //Should call removeMinPoint,canonicalForm and append
    public abstract void createGreedyOfLength(int a);
    public abstract void createGreedyWithElements(int e);
    protected abstract Point findMinFromOrigin();
    public TwoDimGolomb(ArrayList<Point> a){
        ptArray = a;
    }
    public TwoDimGolomb(){
        ptArray = new ArrayList<Point>();
    }
    public Point getMinPoint(){
    	if(ptArray.size() < 1){
    		throw new IllegalStateException("Point array is empty.");
    	}
    	else return ptArray.get(0);
    }
    //Appends a point, DOES NOT check if we preserve Golomb property.
    protected void append(Point p){
        ptArray.add(p);
    }
    protected Point pointAtIndex(int i){
        return ptArray.get(i);
    }

    public int getMaxOrder(){
        int ret = Integer.MIN_VALUE;
        int o;
        Iterator itr = ptArray.listIterator();
        while(itr.hasNext()){
            Point a = (Point) itr.next();
            o = a.getOrder();
            if(o > ret){
                ret = o;
            }
        }
        if(ret == Integer.MIN_VALUE){
            throw new IllegalStateException("Error: There was no max order.");
        }
        return ret;
    }
    
    public void canonicalForm(){
        Point p = findMinFromOrigin();
        int translateX = -1*p.getX();
        int translateY = -1*p.getY();
        Iterator itr = ptArray.listIterator();
        while(itr.hasNext()){
            Point c = (Point) itr.next();
            int cX = c.getX();
            int cY = c.getY();
            c.setXY((translateX + cX),(translateY + cY));
        }
    }

    public void removeMaxPoint(){
        if(ptArray.size() < 1){
            throw new IllegalStateException("Point array is empty.");
        }
        else{
            //Collections.sort(ptArray); We are assuming the array is always in sorted order...
            ptArray.remove(size()-1);
        }
    }
    public void removePoint(Point p){
     	if(ptArray.size() < 1){
    		throw new IllegalStateException("Point array is empty.");
    	}
    	Iterator itr = ptArray.listIterator();
    	while(itr.hasNext()){
    		Point c = (Point) itr.next();
    		if(p.equals(c)){
    			itr.remove();
    			return;
    		}

    	}	
        throw new IllegalArgumentException("Point p: " + p + " can not be found.");
    }
    public void removeMinPoint(){
        if(size() < 1){
            throw new IllegalStateException("Point array is empty.");
        }
        else{
            ptArray.remove(0);
        }
    }

    public int size(){
        return ptArray.size();
    }

    public void sort(Comparator c){
        Collections.sort(ptArray,c);
    }

    public String toString(){
    	StringBuilder sb = new StringBuilder("");
    	Iterator itr = ptArray.listIterator();
    	while(itr.hasNext()){
            Point out = (Point) itr.next();
    		String s = out.toString();
    		sb.append(s+ ", ");
    	}
    	return sb.toString();
    }
}
