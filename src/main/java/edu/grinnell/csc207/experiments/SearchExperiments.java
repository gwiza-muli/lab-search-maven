package edu.grinnell.csc207.experiments;

import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.function.Predicate;

import edu.grinnell.csc207.util.SearchUtils;

/**
 * Assorted experiments for searching structures.
 *
 * @author Your Name Here
 * @author Your Name Here
 * @author Samuel A. Rebelsky (starter code)
 */
public class SearchExperiments {
  /**
   * Run our experimens.
   *
   * @param args
   *   Command-line arguments. Ignored.
   * @throws Exception 
   */
  public static void main(String[] args) throws Exception {
    PrintWriter pen = new PrintWriter(System.out, true);

    String[] tmp =
        new String[] { "alpha", "bravo", "charlie", "delta", "echo",
                       "foxtrot", "golf", "hotel", "india",
                       "juliett", "kilo", "lima", "mike",
                       "november", "oscar", "papa", "quebec",
                       "romeo", "sierra", "tango", "uniform",
                       "victor", "whiskey", "xray", "yankee", "zulu" };
    ArrayList<String> strings = new ArrayList<String>(Arrays.asList(tmp));

    String ex1c = SearchUtils.search(strings, (str) -> str.length() < 5);
    String ex2c = SearchUtils.search(strings, (str) -> str.contains("u"));
    System.out.println(ex1c);
    System.out.println(ex2c);
    pen.close();
  } // main(String[])
} // class SearchUtils
