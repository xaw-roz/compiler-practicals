package CompilerDesign.Lab2;



import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.exit;

/**
 * Created by rakesh-a on 8/22/2016.
 */
//RE (00|11)(0|1)  |  (0|1)*(00|11)

public class Lab2questiona {
    int currentState=0;
    int i=0;
    String str;
    ArrayList<Integer> acceptedState=new ArrayList<Integer>(){{
        add(2);
    add(4);
    add(8);
    add(7);}};

    public void checkAcceptance()
    {
        //System.out.println(currentState);
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
        if (str.charAt(i)=='0')
        {
            currentState=1;
            i++;
            stateq1();
        }
        else if(str.charAt(i)=='1')
        {
            currentState=3;
            i++;
            stateq3();
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
        if (str.charAt(i)=='0')
        {
            currentState=2;
            i++;
            stateq2();
        }
        else if(str.charAt(i)=='1')
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
    public void stateq2()
    {
        checkAcceptance();
        if (str.charAt(i)=='0')
        {
            currentState=2;
            i++;
            stateq2();
        }
        else if(str.charAt(i)=='1')
        {
            currentState=2;
            i++;
            stateq2();
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
        if (str.charAt(i)=='0')
        {
            currentState=7;
            i++;
            stateq7();
        }
        else if(str.charAt(i)=='1')
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
        if (str.charAt(i)=='0')
        {
            currentState=5;
            i++;
            stateq5();
        }
        else if(str.charAt(i)=='1')
        {
            currentState=8;
            i++;
            stateq8();
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
        if (str.charAt(i)=='0')
        {
            currentState=5;
            i++;
            stateq5();
        }
        else if(str.charAt(i)=='1')
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
        if (str.charAt(i)=='0')
        {
            currentState=4;
            i++;
            stateq4();
        }
        else if(str.charAt(i)=='1')
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
    public void stateq7()
    {
        checkAcceptance();
        if(str.charAt(i)=='0')
        {
            currentState=7;
            i++;
            stateq7();
        }
        else if (str.charAt(i)=='1')
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
        if (str.charAt(i)=='0')
        {
            currentState=5;
            i++;
            stateq5();
        }
        else if(str.charAt(i)=='1')
        {
            currentState=8;
            i++;
            stateq8();
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
        Lab2questiona lab2questiona=new Lab2questiona();
        lab2questiona.str=inp.nextLine();
        lab2questiona.stateq0();
    }
}
