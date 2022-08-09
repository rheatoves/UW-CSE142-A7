// Rhea Toves
// 3/4/2021
// TA: Jeremy Chen
// Assignment #7: Personality.java
//
// This program produces an external output file, "output.txt", after scanning
// through the input file, "personality.txt". The program runs through several lines
// alternating between names and sets of data. In the end, this will compile a list of
// the names with attached personality results.

import java.io.*;
import java.util.*;

public class Personality {
   public static final int DIMENSIONS = 4;
   
   // The main defines each file, includes multiple scanners and places each
   // method in a functional order.
   public static void main(String[] args) throws FileNotFoundException {
      Scanner input = new Scanner(new File("personality.txt"));
      PrintStream output = new PrintStream(new File("output.txt"));
      Scanner console = new Scanner(System.in);
      giveIntro();
      double outputFile = files(console);
      while (input.hasNextLine()) {
         String name = input.nextLine();
         String line = input.nextLine();
         int[] answerAs = computeAs(line);
         int[] sums = sumAs(answerAs);
         int[] answerBs = computeBs(line);
         int[] sums1 = sumBs(answerBs);
         int[] sums2 = sumsPersonality(sums1, answerAs, answerBs);
         output.print(name + ": " + Arrays.toString(sums2));
         personality(output, sums2);
         
      }
   }
   
   // This method generates the introduction.
   public static void giveIntro() {
      System.out.println("This program processes a file of answers to the");
      System.out.println("Keirsey Temperament Sorter. It converts the");
      System.out.println("various A and B answers for each person into");
      System.out.println("a sequence of B-percentages and then into a");
      System.out.println("four-letter personality type.");
      
   }
   
   // This method passes the Scanner console as a parameter and proceeds to
   // ask the user for the desired input and output files.
   public static double files(Scanner console) {
      System.out.println();
      System.out.print("input file name? ");
      double inputFile = console.nextInt();
      System.out.print("output file name? ");
      double outputFile = console.nextDouble();
      return outputFile;
      
   }
   
   // This method is turning the set of lines from the input lines into a set
   // of ones and zeros, then proceeds to count/keep track of the A's while taking
   // the data from the input file as a parameter.
   public static int[] computeAs(String data) {
      int[] answerAs = new int[data.length()];
      
      int count = 0;
      for (int i = 0; i < data.length(); i++) {
         char next = data.charAt(i);
         if (next == 'A' || next == 'a') {
            answerAs[i] = 1;
            count++;
         } else {
         
         }
      }
      return answerAs;
   }
   
   // This method takes in the array 'answerAs' as a parameter and returns the data into the
   // array 'sums' after calculating the amount of A's that falls into the desired dimension.
   public static int[] sumAs(int[] answerAs) {
      int[] groups = new int[7];
      int[] sums = new int[DIMENSIONS];
      
      for (int i = 0; i < 10; i++)
         groups[1 % 7]++;
      
      for (int j = 0; j < answerAs.length; j++) {
         sums[(j % 7 + 1) / 2] += answerAs[j];
         
      }
      return sums;
   }
   
   // This method is turning the set of lines from the input lines into a set
   // of ones and zeros, then proceeds to count/keep track of the B's while taking
   // the data from the input file as a parameter.
   public static int[] computeBs(String data) {
      int[] answerBs = new int[data.length()];
      
      int count = 0;
      for (int i = 0; i < data.length(); i++) {
         char next = data.charAt(i);
         if (next == 'B' || next == 'b') {
            answerBs[i] = 1;
            count++;
         } else {
         
         }
      }
      return answerBs;
   }
   
   // This method takes in the array 'answerBs' as a parameter and returns the data into the
   // array 'sums' after calculating the amount of B's that falls into the desired dimension.
   public static int[] sumBs(int[] answerBs) {
      int[] groups = new int[7];
      int[] sums1 = new int[DIMENSIONS];
      
      for (int i = 0; i < 10; i++)
         groups[1 % 7]++;
      
      for (int j = 0; j < answerBs.length; j++) {
         sums1[(j % 7 + 1) / 2] += answerBs[j];
         
      }
      return sums1;
   }
   
   // This method passes the array 'sums1' as a parameter and calculates percentages of
   // the overall scores of each person. This is then used to figure out the corresponding
   // personality dimensions from the Keirsey Temperament Sorter.
   public static int[] sumsPersonality(int[] sums1, int[] answerAs, int[] answerBs) {
      int[] sums2 = new int[DIMENSIONS];
      
      sums2[0] = (int)(Math.round(100.0 * sums1[0] / (answerAs[0] + answerBs[0]) / 10.0));
      
      for (int j = 1; j < sums1.length; j++) {
         sums2[j] = (int)(Math.round(100.0 * sums1[j] / (answerAs[j] + answerBs[j]) / 20.0));
         
      }
      return sums2;
   }
   
   // Returns the result of rounding n to 1 digit after the decimal point.
   public static double round1(double n) {
      return Math.round(n * 10.0) / 10.0;
   }
   
   // This method then prints out the personality dimensions from the Keirsey Temperament
   // Sorter and prints out the computed combination of letters for each person by taking
   // the array, 'sums2' as a parameter. While taking in the PrintStream output as a parameter,
   // this inserts all the data to a seperate output file.
   public static void personality(PrintStream output, int[] sums2) {
      if (sums2[0] < 50) {
         output.print(" = E");
      } else if (sums2[0] > 50) {
         output.print(" = I");
      } else if (sums2[0] == 50) {
         output.print(" = X");
      }
      
      if (sums2[1] < 50) {
         output.print("S");
      } else if (sums2[1] > 50) {
         output.print("N");
      } else if (sums2[1] == 50) {
         output.print("X");
      }
      
      if (sums2[2] < 50) {
         output.print("T");
      } else if (sums2[2] > 50) {
         output.print("F");
      } else if (sums2[2] == 50) {
         output.print("X");
      }
      
      if (sums2[3] < 50) {
         output.println("J");
      } else if (sums2[3] > 50) {
         output.println("P");
      } else if (sums2[3] == 50) {
         output.println("X");
      }
   }
}