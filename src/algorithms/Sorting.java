package algorithms;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Sorting {
    /**
     * Merge two ArrayLists efficiently, in ascending order. Does not modify either argument.
     * @param a The first list.
     * @param b The second list.
     * @return A new sorted ArrayList containing elements from both lists.
     */
    public static ArrayList<Integer> merge(ArrayList<Integer> a, ArrayList<Integer> b) {
        ArrayList<Integer> result = new ArrayList<>();
        int i=0;
        int j=0;
        while(i<a.size()&&j<b.size()){
            if(a.get(i)<b.get(j)){
                result.add(a.get(i));
            }
            else{
                result.add(b.get(j));
            }
        }
        while(!a.isEmpty()&&b.isEmpty()){
            result.add(a.get(i));
        }
        while(a.isEmpty()&&!b.isEmpty()){
            result.add(b.get(i));

        }

        /* YOUR CODE HERE */

        return result;
    }

    public static ArrayList<Integer> mergeSort(ArrayList<Integer> list) {
        if(list==null||list.size()==1 ){
            return list;
        }
        else{
            List<Integer> minh= list.subList(0,list.size()/2+1);
            ArrayList<Integer> depTrai= new ArrayList<>();
            depTrai.addAll(list.size()/2+1,list);


            return mergeSort(merge((ArrayList<Integer>) minh,depTrai));

        }        /* YOUR CODE HERE */
    }
}
