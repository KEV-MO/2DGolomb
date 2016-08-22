/* Finds displacement vector between two points */

package coordinates;
public class DisplacementVector{
    double direction;
    double magnitude;
    public DisplacementVector(Point a, Point b){
        double x1 = (double)a.getX() - b.getX();
        double y1 = (double)a.getY() - b.getY();
        magnitude = Math.sqrt(Math.pow(x1, 2) + Math.pow(y1, 2));
        direction = Math.atan2(y1,x1);
    }
    
    
    public boolean equals(Object obj){
        if(obj instanceof DisplacementVector){
            DisplacementVector v = (DisplacementVector) obj;
            return this.direction == v.direction && this.magnitude == v.magnitude; 
        }
        else{
            return false;
        }
    }
                              
    public int hashCode(){
        Double add = direction + magnitude;
        return add.hashCode();
    }
    public String toString(){
        return ("Magnitude: " + magnitude + ", Direction: " + direction);
    }
	

}
