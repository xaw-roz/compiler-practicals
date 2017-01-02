package RecommendationSystem;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by rakesh-a on 8/2/2016.
 */


public class EuclidianDistance {
    /*public void add(Integer key, HashMap<String,Float> value,HashMap<Integer,ArrayList<HashMap<String,Float>>> usermovieRating ) {
        if (usermovieRating.containsKey(key)) {
            ArrayList<HashMap<String,Float>> al = usermovieRating.get(key);
            al.add(value);
        } else {
            ArrayList al = new ArrayList<HashMap<String,Float>>();
            al.add(value);
            usermovieRating.put(key, al);
        }
    }*/
    public void add(Integer key, HashMap<String,Float> value,HashMap<Integer,HashMap<String,Float>> usermovieRating ) {
        if (usermovieRating.containsKey(key)) {
            HashMap<String,Float> al = usermovieRating.get(key);
            al.putAll(value);
        } else {
            HashMap<String,Float> al = new HashMap<String,Float>();
            al.putAll(value);
            usermovieRating.put(key, al);
        }
    }
    public void calculateEucledian(Integer userId, HashMap<Integer,HashMap<String,Float>> usermovieRating)
    {

        Set watchedMovies=usermovieRating.get(userId).keySet();
        System.out.println(watchedMovies);
    }




    public static void main(String[] args) {
        EuclidianDistance euclidianDistance=new EuclidianDistance();
        String rating_path="C:\\Users\\rakesh-a\\Documents\\gitfiles\\ai\\AI-Labs\\0338_Bikash\\project\\movielens\\ratings.csv";
        String personMoviePath="C:\\Users\\rakesh-a\\Documents\\gitfiles\\ai\\AI-Labs\\0338_Bikash\\project\\movielens\\links.csv";
        BufferedReader bufferedReader=null;
        String line="";
        String seperator=",";
        HashMap<Integer,HashMap<String,Float>> usermovieRating=new HashMap<>();


            try{
            bufferedReader=new BufferedReader(new FileReader(rating_path));
            while ((line=bufferedReader.readLine())!=null)
            {
                String [] values=line.split(seperator);
                euclidianDistance.add(Integer.parseInt(values[0]),new HashMap<String,Float>(){{put(values[1],Float.parseFloat(values[2]));}},usermovieRating);
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        euclidianDistance.calculateEucledian(1,usermovieRating);

    }
}
