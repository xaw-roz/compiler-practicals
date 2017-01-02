package CompilerDesign.Lab2;

/**
 * Created by rakesh-a on 9/2/2016.
 */

//RE ( (a |b)* (c|d)* )| ab*c*d

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.exit;

public class Lab2questionC {
    int currentState=0;
    int i=0;
    String str;
    ArrayList<Integer> acceptedState=new ArrayList<Integer>() {
        {
            add(1);
        }};

        public void checkAcceptance() {
          //  System.out.println(currentState);
            if (i >= (str.length())) {
                if (acceptedState.contains(currentState)) {
                    System.out.println("the string is accepted");
                } else {
                    System.out.println("the string is not accepted");

                }
                exit(0);
            }

        }

        public void stateq0() {
            checkAcceptance();
            if (str.charAt(i) == 'a') {
                currentState = 1;
                i++;
                stateq1();
            } else if (str.charAt(i) == 'b') {
                currentState = 1;
                i++;
                stateq1();
            } else if (str.charAt(i) == 'c') {
                currentState = 1;
                i++;
                stateq1();
            } else if (str.charAt(i) == 'd') {
                currentState = 1;
                i++;
                stateq1();
            } else {
                currentState = -1;
                System.out.println("the string is invalid");
                exit(0);
            }
        }

        public void stateq1() {
            checkAcceptance();
            if (str.charAt(i) == 'a') {
                currentState = 1;
                i++;
                stateq1();
            } else if (str.charAt(i) == 'b') {
                currentState = 1;
                i++;
                stateq1();
            } else if (str.charAt(i) == 'c') {
                currentState = 1;
                i++;
                stateq1();
            } else if (str.charAt(i) == 'd') {
                currentState = 1;
                i++;
                stateq1();
            } else {
                currentState = -1;
                System.out.println("the string is invalid");
                exit(0);
            }
        }

    public static void main(String[] args) {
        Scanner inp=new Scanner(System.in);
        System.out.println("Enter the string");

        Lab2questionC lab2questionC = new Lab2questionC();
            lab2questionC.str =inp.nextLine() ;
            lab2questionC.stateq0();


    }
}
