package edu.grinnell.csc207;
import java.util.Arrays;
import java.io.PrintWriter;

/**
 * A quick and dirty experiment with binary search.
 */
public class BSExperiment {

  // +-----------+---------------------------------------------------
  // | Constants |
  // +-----------+

  /**
   * The maximum array size we test. (I'd recommend starting small and
   * then going up to 32.)
   */
  public static final int MAXSIZE = 4;

  /**
   * Are we being verbose? In this case, we print the call every
   * time and then an error message if it doesn't succeed. It's used
   * mostly to find the cases in which incorrect implementations
   * loop forever.
   */
  public static final boolean VERBOSE = true;

  /**
   * Do we skip the "well known" issue of "search for -1 in { 0 }"?
   * Sam added this hack because it makes some incorrect implementations
   * loop forever and he wanted to be able to see more issues in
   * such code.
   */
  public static final boolean SKIP_NEG_ONE_IN_ZERO = false;

  // +---------------+-----------------------------------------------
  // | Binary Search |
  // +---------------+

  // public static int binarySearch(int[] vals, int i) 
  //     throws NotFoundException {
  //   throw new NotFoundException("unimplemented");
  // } // binarySearch

  /**
   * Search for val in values, return the index of an instance of val.
   *
   * @param values
   *   A sorted array of integers
   * @param val
   *   An integer we're searching for
   * @return
   *   index, an index of val (if one exists)
   * @throws Exception
   *   If there is no i s.t. values[i] == val
   * @pre
   *   values is sorted in increasing order.  That is, values[i] <
   *   values[i+1] for all reasonable i.
   * @post
   *   values[index] == val
   */
  public static int binarySearch(int[] vals, int i) throws Exception {
    //return 0;
    return iterativeBinarySearch(vals, i);
    //return recursiveBinarySearch(vals, i);
  } // binarySearch


  /**
   * Search for val in values, return the index of an instance of val.
   *
   * @param values
   *   A sorted array of integers
   * @param val
   *   An integer we're searching for
   * @return
   *   index, an index of val (if one exists) throws Exception {
    throw new Exception("Unimplemented");
   * @throws Exception
   *   If there is no i s.t. values[i] == val
   * @pre
   *   values is sorted in increasing order.  That is, values[i] <
   *   values[i+1] for all reasonable i.
   * @post
   *   values[index] == val
   */
  static int iterativeBinarySearch(int[] vals, int i) throws Exception {
    int lb = 0;
    int ub = vals.length - 1;
   // int mid = lb + (ub - lb) / 2;
    while (lb <= ub){
      int mid = lb + (ub - lb) / 2;
      if (vals[mid] == i){
        return mid;
      } else if (vals[mid] < i){
        lb = mid + 1;
      } else {
        ub = mid - 1;
      }
    }
    
  
    throw new Exception ("there is no i");
  } // iterativeBinarySearch
  // +---------------------------+-----------------------------------
  // | Experiments and utilities |
  // +---------------------------+

  /**
   * Print out a call in such a way that we might use it.
   */
  public static void printCall(PrintWriter pen, int[] vals, int val) {
    pen.printf("binarySearch(new int[] %s, %d);\n",
               Arrays.toString(vals).replace('[','{').replace(']','}'),
               val);
    pen.flush();
  } // printCall

  public static void tester(PrintWriter pen) {
    int pos;

    for (int size = 0; size <= MAXSIZE; size++) {
      // Build the appropriate array
      int vals[] = new int[size];
      for (int i = 0; i < size; i++) {
        vals[i] = 2*i;
      } // for

      // Make sure we can find all the values in the array.
      for (int i = 0; i < size; i++) {
        if (VERBOSE) { printCall(pen, vals, 2*i); }
        try {
          pos = binarySearch(vals, 2*i);
          if (pos != i) {
            if (!VERBOSE) { printCall(pen, vals, 2*i); }
            pen.printf("  expected %d, got %d\n", i, pos);
          } // if
        } catch (Exception e) {
          if (!VERBOSE) { printCall(pen, vals, 2*i); }
          pen.printf("  threw exception '%s'\n", e.toString());
        } // try/catch
      } // for

      // Check between elements (and after the last one).
      for (int i = 0; i < size; i++) {
        if (VERBOSE) { printCall(pen, vals, 2*i+1); }
        try {
          pos = binarySearch(vals, 2*i+1);
          if (!VERBOSE) { printCall(pen, vals, 2*i+1); }
          pen.printf("  expected an exception, got %d\n", pos);
        } catch (NotFoundException e) {
          // We expected this.
        } catch (Exception e) {
          if (!VERBOSE) { printCall(pen, vals, 2*i+1); }
          pen.printf("  threw exception '%s'\n", e.toString());
        } // try/catch
      } // for i

      // Check before the first element
      if ((!SKIP_NEG_ONE_IN_ZERO) || (vals.length != 1)) {
        if (VERBOSE) { printCall(pen, vals, -1); }
        try {
          pos = binarySearch(vals, -1);
          if (!VERBOSE) { printCall(pen, vals, -1); }
          pen.println("  expected exception, got " + pos);
        } catch (NotFoundException e) {
          // We expected this.
        } catch (Exception e) {
            if (!VERBOSE) { printCall(pen, vals, -1); }
            pen.printf("  threw exception '%s'\n", e.toString());
        } // try/catch
      } // skipping -1 in { 0 } 
    } // for size
  } // tester(PrintWriter)

  // +------+--------------------------------------------------------
  // | Main |
  // +------+

  public static void main(String[] args) throws Exception {
    PrintWriter pen = new PrintWriter(System.out, true);
    tester(pen);
    pen.close();
  } // main(String[])
} // BSExperiment
