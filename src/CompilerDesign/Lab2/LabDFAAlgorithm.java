package CompilerDesign.Lab2;

import java.util.Scanner;

import static java.lang.System.exit;
import static java.lang.System.setOut;

/**
 * Created by rakesh-a on 8/29/2016.
 */

//RE that accepts string ending with aaa
public class LabDFAAlgorithm
{
    public static String  move(String s,char c)//function that evaluates the next state given current state and input
    {
        String newState="";
        switch (s)
        {
            case "q0":{
                if(c=='a')
                {
                    newState="q1";
                }
                else if(c=='b')
                {
                    newState="q0";
                }
                else {
                    System.out.println("invalid input");
                    exit(0);
                }
                break;

            }
            case "q1":{
                if(c=='a')
                {
                    newState="q2";
                }
                else if(c=='b')
                {
                    newState="q0";
                }
                else {
                    System.out.println("invalid input");
                    exit(0);
                }
                break;

            }
            case "q2":
            {
                if(c=='a')
                {
                    newState="q3";
                }
                else if(c=='b')
                {
                    newState="q0";
                }
                else {
                    System.out.println("invalid input");
                    exit(0);
                }
                break;
            }
            case "q3":
            {
                if(c=='a')
                {
                    newState="q3";
                }
                else if(c=='b')
                {
                    newState="q0";
                }
                else {
                    System.out.println("invalid input");
                    exit(0);
                }
                break;
            }
            default:{
                System.out.println("Invalid state");
                break;
            }
        }
        return newState;

    }
    public static void main(String[] args) {

        System.out.println("Enter the string");
        Scanner inp=new Scanner(System.in);
        String inputString=inp.nextLine();
        String s="q0";
        int i=0;
        char c=inputString.charAt(i);
        int length=inputString.length();
        i++;
        s=move(s,c);
        while(i<(length))
        {
//            System.out.println(i);


            c=inputString.charAt(i);
            s=move(s,c);
            i++;
       //     System.out.println(s);



        }
        if (s=="q3")//checking if string is accepted or not(q3 state is accepting state)
        {
            System.out.println("the string is accepted");
        }
        else {
            System.out.println("the string is rejected");
        }

    }

}
