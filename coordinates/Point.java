
package coordinates;
public abstract class Point{
	private int x;
	private int y;
	private int order;

    //abstract methods
    //We may have different distance metrics so...
    public abstract int[] initByOrder(int order); //Needs to return an {x,y}
    public abstract int distanceFromOrigin(int x, int y);
    public abstract int distanceFromOrigin();
    public abstract int distanceFrom(Point p);
    //method ordering maps (x,y) in Z x Z, to a number in Z^+. This is for purposes of greedily picking an element
    public abstract int ordering(int x, int y);
    //Defined methods

    public Point(int x, int y){
        this.x = x;
        this.y = y;
        order = ordering(this.x,this.y);
    }

    public Point(int order){
    	int[] a = initByOrder(order);
    	this.x = a[0];
    	this.y = a[1];
    	this.order = order;
    }

    
	public int getOrder(){
		return this.order;
	}
	public int getY(){
		return this.y;
	}
	public int getX(){
		return this.x;
	}
	public void setX(int newX){
		this.x = newX;
		this.order = ordering(this.x,this.y);
	}
	public void setY(int newY){
		this.y = newY;
		this.order = ordering(this.x,this.y);
	}
	public void setXY(int x, int y){
		this.x = x;
		this.y = y;
		order = ordering(x,y);
	}
    public boolean equals(Point p){
        return this.x == p.x && this.y == p.y;
    }
 	public String toString(){
		return "(" + x + ", " + y + ")";
	}

}
