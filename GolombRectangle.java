import coordinates.Point;
import coordinates.SquareSpiralPoint;
import coordinates.DisplacementVector;
import coordinates.SquarePointComparator;
import java.util.*;
public class GolombRectangle extends TwoDimGolomb{
    private ArrayList<Point> ptArray;
    private static HashMap<DisplacementVector,Integer> displacementMap;

    public GolombRectangle(ArrayList<Point> a){
        super(a);
        displacementMap = new HashMap<>();
    }
    public GolombRectangle(){
        super();
        displacementMap = new HashMap<>();
    }
    @Override
    public boolean isGolomb(Point p){
        DisplacementVector z;
        for(int i = 0; i < size(); i++){
            z = new DisplacementVector(p,pointAtIndex(i));
            if(displacementMap.containsKey(z)){
                return false;
            }
        }
        return true;
    }

    @Override
    public void greedyRotate(){
    	removeMinPoint();
        canonicalForm();
        greedilyAppend(getMaxOrder(),1);

    }
    public void greedilyAppend(int begin, int numElements){
        Point p;
        addKeys();
        while(numElements > 0){
            p = new SquareSpiralPoint(begin);//p = SquareSpiralPoint.initByOrder(begin);
            if(isGolomb(p)){
                append(p);
                displacementMap.clear();
                addKeys();
                numElements--;
            }
            begin++;
        }
    }

    public void greedyRotateInner(){
        removeMinPoint();
        canonicalForm();
        greedilyAppendInner(1);

    }
    public void greedilyAppendInner(int numElements){
        Point p;
        addKeys();
        int i = 0;
        while(numElements > 0){
            p = new SquareSpiralPoint(i);//SquareSpiralPoint.initByOrder(i);
            if(isGolomb(p)){
                append(p);
                displacementMap.clear();
                addKeys();
                numElements--;
            }
            i++;
        }
    }

    @Override
    public void createGreedyOfLength(int a){
        int i = 0;
        Point p;
        while(i < a){
             p = new SquareSpiralPoint(i);//SquareSpiralPoint.initByOrder(i);
             if(isGolomb(p)){
                append(p);
                addKeys();  
             }
             i++;
        }

    }
    @Override
    public void createGreedyWithElements(int e){
        int i = 0;
        Point p;
        while(size() < e){
            p = new SquareSpiralPoint(i);//SquareSpiralPoint.initByOrder(i);
            if(isGolomb(p)){
                append(p);
                addKeys();
            }
            i++;
        }
    }

    protected Point findMinFromOrigin(){
        SquarePointComparator spc = new SquarePointComparator();
        sort(spc);
        return pointAtIndex(0);
    }

    private void addKeys(){
        for(int i = 0; i < size(); i++){
            for(int j = i+1; j < size(); j++){
                displacementMap.put(new DisplacementVector(pointAtIndex(i),pointAtIndex(j)),1);
                displacementMap.put(new DisplacementVector(pointAtIndex(j),pointAtIndex(i)),1);
            }
        }
    }

    public static void main(String[] args){

        GolombRectangle gr = new GolombRectangle();
        gr.createGreedyWithElements(30);
        displacementMap.clear();
        System.out.println(gr);
        /*
        int u = 20;
        for(int i = 0; i < u; i++){
            gr.greedyRotate();
            displacementMap.clear();
            if(i > u-100){
                System.out.println(gr);
            }
        }
        */
        
    }
 
}


/*

    public static void main(String[] args){
   
        GolombRectangle gr = new GolombRectangle();
        gr.createGreedyWithElements(4);
        displacementMap.clear();
        System.out.println(gr);
        int u = 5000;
        for(int i = 0; i < 20; i++){
            gr.greedyRotate();
            displacementMap.clear();
            //if(i > u-30){
            System.out.println(gr);
            //}
        }
        System.out.println(displacementMap.keySet().size());
        
    }
 
}

*/
