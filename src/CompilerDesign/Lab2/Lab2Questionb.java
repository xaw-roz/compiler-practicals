package CompilerDesign.Lab2;



import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.exit;

/**
 * Created by rakesh-a on 8/22/2016.
 */

//RE (ab*c | (def)+ | a*d+e)+
public class Lab2Questionb {
    int currentState=0;
    int i=0;
    String str;
    ArrayList<Integer> acceptedState=new ArrayList<Integer>(){{
        add(7);
        add(4);
        add(8);}};

    public void checkAcceptance()
    {
//        System.out.println(currentState);
        if(i>=(str.length())) {
            if (acceptedState.contains(currentState)) {
                System.out.println("the string is accepted");
            } else {
                System.out.println("the string is not accepted");

            }
            exit(0);
        }

    }
    public void stateq0(){
        checkAcceptance();
        if (str.charAt(i)=='a')
        {
            currentState=1;
            i++;
            stateq1();
        }
        else if(str.charAt(i)=='d')
        {
            currentState=6;
            i++;
            stateq6();
        }
        else {
            currentState=-1;
            System.out.println("the string is invalid");
            exit(0);
        }
    }
    public void stateq1()
    {
        checkAcceptance();
        if (str.charAt(i)=='b')
        {
            currentState=3;
            i++;
            stateq3();
        }
        else if(str.charAt(i)=='c')
        {
            currentState=4;
            i++;
            stateq4();
        }
        else if(str.charAt(i)=='a')
        {
            currentState=5;
            i++;
            stateq5();
        }
        else if(str.charAt(i)=='d')
        {
            currentState=6;
            i++;
            stateq6();
        }
        else {
            currentState=-1;
            System.out.println("the string is invalid");
            exit(0);
        }
    }
    public void stateq5()
    {
        checkAcceptance();
        if (str.charAt(i)=='a')
        {
            currentState=5;
            i++;
            stateq5();
        }
        else if(str.charAt(i)=='d')
        {
            currentState=6;
            i++;
            stateq6();
        }
        else {
            currentState=-1;
            System.out.println("the string is invalid");
            exit(0);
        }
    }
    public void stateq6()
    {
        checkAcceptance();
        if (str.charAt(i)=='d')
        {
            currentState=6;
            i++;
            stateq6();
        }
        else if(str.charAt(i)=='e')
        {
            currentState=7;
            i++;
            stateq7();
        }
        else {
            currentState=-1;
            System.out.println("the string is invalid");
            exit(0);
        }
    }

    public void stateq3()
    {
        checkAcceptance();
        if (str.charAt(i)=='b')
        {
            currentState=3;
            i++;
            stateq3();
        }
        else if(str.charAt(i)=='c')
        {
            currentState=4;
            i++;
            stateq4();
        }
        else {
            currentState=-1;
            System.out.println("the string is invalid");
            exit(0);
        }
    }
    public void stateq4()
    {
        checkAcceptance();
        if (str.charAt(i)=='a')
        {
            currentState=1;
            i++;
            stateq1();
        }
        else if(str.charAt(i)=='d')
        {
            currentState=6;
            i++;
            stateq6();
        }
        else {
            currentState=-1;
            System.out.println("the string is invalid");
            exit(0);
        }
    }
    public void stateq7()
    {
        checkAcceptance();
        if(str.charAt(i)=='f')
        {
            currentState=8;
            i++;
            stateq8();
        }
        if (str.charAt(i)=='a')
        {
            currentState=1;
            i++;
            stateq1();
        }
        else if(str.charAt(i)=='d')
        {
            currentState=6;
            i++;
            stateq6();
        }
        else {
            currentState=-1;
            System.out.println("the string is invalid");
            exit(0);
        }
    }
    public void stateq8()
    {
        checkAcceptance();
        if (str.charAt(i)=='a')
        {
            currentState=1;
            i++;
            stateq1();
        }
        else if(str.charAt(i)=='d')
        {
            currentState=6;
            i++;
            stateq6();
        }
        else {
            currentState=-1;
            System.out.println("the string is invalid");
            exit(0);
        }
    }




    public static void main(String[] args) {
        Scanner inp=new Scanner(System.in);
        System.out.println("Enter the string");

        Lab2Questionb lab2Questionb=new Lab2Questionb();
        lab2Questionb.str=inp.nextLine();
        lab2Questionb.stateq0();
    }
}
