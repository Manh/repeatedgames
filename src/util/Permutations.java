package util;
import java.math.BigInteger;
import java.util.*;

// used in creating State Domain to find all the combinations of the possible state.
// Source : http://kosbie.net/cmu/spring-08/15-100/handouts/combinatorial/Permutations.java
public class Permutations {
	  private int n, r;
	  private int[] index;
	  private boolean hasNext = true;
	    
	  public Permutations(int n, int r) {
	    this.n = n;
	    this.r = r;
	    index = new int[n];
	    for (int i = 0; i < n; i++) index[i] = i;
	    reverseAfter(r - 1);
	  }

	  public boolean hasNext() { return hasNext; }


	  private void moveIndex() {
	    // find the index of the first element that dips
	    int i = rightmostDip();
	    if (i < 0) {
	        hasNext = false;
	        return;
	    }

	    // find the smallest element to the right of the dip
	    int smallestToRightIndex = i+1;
	    for (int j=i+2; j<n; j++)
	      if ((index[j] < index[smallestToRightIndex]) &&  (index[j] > index[i]))
	          smallestToRightIndex = j;

	    // switch dip element with smallest element to its right
	    swap(index,i,smallestToRightIndex);

	    if (r - 1 > i) {
	      // reverse the elements to the right of the dip
	      reverseAfter(i);
	      // reverse the elements to the right of r - 1
	      reverseAfter(r - 1);
	    }
	  }
	  
	  public int[] next() {
	    if (!hasNext) return null;
	    int[] result = new int[r];
	    for (int i=0; i<r; i++) result[i] = index[i];
	    moveIndex();
	    return result;
	  }

	  // Reverse the index elements to the right of the specified index.
	  private void reverseAfter(int i) {
	    int start = i+1;
	    int end = n-1;
	    while (start < end) {
	      swap(index,start,end);
	      start++;
	      end--;
	    }
	  }

	  // return int the index of the first element from the right
	  // that is less than its neighbor on the right.
	  private int rightmostDip() {
	    for (int i=n-2; i>=0; i--)
	        if (index[i] < index[i+1])
	            return i;
	    return -1;
	  }
	  
	  private void swap(int[] a, int i, int j) {
	    int temp = a[i];
	    a[i] = a[j];
	    a[j] = temp;
	  }
	  
}
