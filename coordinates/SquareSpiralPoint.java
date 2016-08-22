
/* This point class creates a natural ordering according to a square spiral around the origin */
package coordinates;
public class SquareSpiralPoint extends Point{
	private int x;
	private int y;
	private int order;
    
    public SquareSpiralPoint(int x, int y){
    	super(x,y);
    	//Super class sets all by calling dFO and ordering.
    }
    public SquareSpiralPoint(int order){
        super(order);
    }

    public int[] initByOrder(int order){
        int x,y;
    	//Function that creates the square spiral.
        //sh is which layer of the square around the origin that we are at.
    	int sh = (int) ((Math.sqrt(order)+1)/2);
        //l is determined by how close to each axis we are.
    	int l =  (int) ((order-Math.pow((2*sh-1),2))/(2*sh));
        //e is how far away from the axis we are.
    	int e = (order-(int)Math.pow(2*sh-1,2))-2*sh*l-sh+1;
    	x = l == 0 ? sh : l == 1 ? -e : l == 2 ? -sh : e;
    	y = l == 0 ? e : l == 1 ? sh : l == 2 ? -e : -sh;
        int[] ret = {x,y};
        return ret;
    }
    
    
    //Uses taxicab metric
    public int distanceFrom(Point p){
    	return Math.abs(this.x - p.getX()) + Math.abs(this.y - p.getY());
    }

    //This is taxicab distance again
    public int distanceFromOrigin(){
    	return Math.abs(this.x) + Math.abs(this.y);
    }
    public int distanceFromOrigin(int x, int y){
    	return Math.abs(x) + Math.abs(y);
    }

    public int ordering(int x, int y){
    	//Inverse function from a point in ZxZ to a positive integer.
    	if(x == y && x == 0){
    		return 0;
    	}
    	int sh = Math.max(Math.abs(x),Math.abs(y));
    	int l = (y <= x && y > -x) ? 0 : (y >= -x && y > x) ? 1 : (y >= x && y < -x) ? 2 : 3;
    	int e = (l == 0) ? y : (l == 1) ? -x : (l == 2) ? -y : x;
    	return 4*(int)Math.pow(sh,2) + (2*l-3)*sh + e;
    }

    
    public static void main(String[] args){
    	SquareSpiralPoint p = new SquareSpiralPoint(-5,5);
    	SquareSpiralPoint q = new SquareSpiralPoint(21);
        System.out.println("Order of p: " + p.getOrder());
        System.out.println("Order of q: " + q.getOrder());
    	System.out.println(p.toString());
    	System.out.println(q.toString());
    }



}
