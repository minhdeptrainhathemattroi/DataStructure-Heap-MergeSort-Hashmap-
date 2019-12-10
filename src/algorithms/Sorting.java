package algorithms;

import java.lang.reflect.Array;
import java.util.*;

public class Sorting {
    /**
     * Merge two ArrayLists efficiently, in ascending order. Does not modify either argument.
     * @param c The first list.
     * @param d The second list.
     * @return A new sorted ArrayList containing elements from both lists.
     */
    public static ArrayList<Integer> merge(ArrayList<Integer> c, ArrayList<Integer> d) {
        ArrayList<Integer> result = new ArrayList<>();
        int i=0;
        int j=0;
        ArrayList<Integer> a= new ArrayList<>(c);
        ArrayList<Integer> b= new ArrayList<>(d);
        while(!a.isEmpty()&&!b.isEmpty()){
            if(a.get(i)<b.get(j)){
                result.add(a.get(i));
                a.remove(i);
            }
            else{
                result.add(b.get(j));
                b.remove(j);
            }
        }
        if (!a.isEmpty()&&b.isEmpty()){
            result.addAll(a);

        }
        if (a.isEmpty()&&!b.isEmpty()){
            result.addAll(b);

        }

        /* YOUR CODE HERE */

        return result;
    }

    public static ArrayList<Integer> mergeSort(ArrayList<Integer> list) {
        if(list.size()<2 ){
            return list;
        }
        ArrayList<Integer> minh= new ArrayList();
        List<Integer> depTrai= list.subList(0, list.size()/2);
        List<Integer> quaDi=list.subList(list.size()/2,list.size());
        ArrayList<Integer> hu= new ArrayList<>(depTrai);
        ArrayList<Integer> hi= new ArrayList<>(quaDi);
        if(hu.size()>1){
            hu=mergeSort(hu);
        }
        if(hi.size()>1){
            hi=mergeSort(hi);
        }
        minh= merge(hu,hi);
        return minh;




               /* YOUR CODE HERE */
    }
}
