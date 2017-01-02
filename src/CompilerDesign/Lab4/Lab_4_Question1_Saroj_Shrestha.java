package CompilerDesign.Lab4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by rocks on 11/5/2016.
 */
public class Lab_4_Question1_Saroj_Shrestha {

    public class LeftAndRightPos
    {
        ArrayList <Integer> leftpos=new ArrayList<Integer>();
        ArrayList <Integer> rightpos=new ArrayList<>();
    }
    HashMap <Integer,LeftAndRightPos> integerLeftAndRightHashMap=new HashMap<>();
    String postFix="";

    /*function to check if a character is nullable of not*/
    public boolean checkNullable(char exp)
    {
        boolean isNullable=false;
        int value = (int)exp;

        if(exp=='*')
        {
            isNullable=true;
        }
        return isNullable;
    }
    /*function to check if a character is alphabet or numeric or not*/
    public boolean isChar(char ch)
    {
        boolean isChar=false;
        int value = (int)ch;

        if((value >= 65 && value <= 90)|| ch=='#'|| (value >= 48 && value <= 57))
        {
            isChar=true;
         //   System.out.println("in char checker");
        }
        return isChar;
    }

    /*function to detect starting of the bracket*/
    public boolean checkBracket(char ch)
    {
        Boolean isBracket=false;

            if(ch=='(')
            {
                isBracket=true;

            }

        return isBracket;
    }

    /*function to check if the character is operator or not*/
    public boolean isOperatior(char ch)
    {
        boolean isOperator=false;
        if(ch=='*' || ch=='|')
        {
            isOperator=true;
        }
        return isOperator;
    }

    /*function to calculate postfix of given infix expression*/
    public String toPostfix(String input)
    {
        System.out.println(input);
        String postfix="";
        int i=0;
        while (i<input.length())
        {
            if (checkBracket(input.charAt(i))==true)
            {
                String temp=new String();
                i++;
                while (input.charAt(i)!=')')
                {


                    if(isChar(input.charAt(i)))
                    {

                        if (input.charAt(i+1)=='*')
                        {
                            temp=temp+input.charAt(i)+"*"+".";
                            i=i+2;
                        }
                        else {
                            temp=temp+input.charAt(i);
                            i++;

                        }

                    }
                    else if(input.charAt(i)=='|')
                    {
                        temp=temp+input.charAt(i+1)+input.charAt(i);
                        i=i+2;
                    }
                    else if(input.charAt(i)=='*')
                    {
                        temp=temp+input.charAt(i);
                        i++;
                    }

                }
                i++;
                postfix=postfix+temp;

            }

           else if(isChar(input.charAt(i)))
            {



                    if ((i<(input.length()-1))&&(input.charAt(i+1)=='*'))
                    {
                        postfix=postfix+input.charAt(i)+"*"+".";
                        i=i+2;
                    }
                    else {
                        postfix=postfix+input.charAt(i)+".";

                        i++;
                    }


            }
            else if(input.charAt(i)=='*')
            {
                postfix=postfix+input.charAt(i);
                i++;
            }


        }

        System.out.println("the postfix expression is "+postfix);
        postFix=postfix;

        return postfix;
    }


    /*Function to calculate firtpos and last pos*/
    public void calculateFirstPosAndLastPos(String exp)
    {
        System.out.println("\nthe firstpos and lastpos for the each character is given below\n");
        int charcount=1;
        for (int i = 0; i < exp.length(); i++)
              {

            if(isChar(exp.charAt(i)))
            {

                LeftAndRightPos LeftAndRight=new LeftAndRightPos();
                LeftAndRight.leftpos.add(charcount);
                LeftAndRight.rightpos.add(charcount);
                charcount++;
                integerLeftAndRightHashMap.put(i,LeftAndRight);
            }
        }
        for (int i = 0; i < exp.length(); i++) {
            if(!isChar(exp.charAt(i))) {
                if (exp.charAt(i) == '|') {
                    LeftAndRightPos LeftAndRight = new LeftAndRightPos();
                    LeftAndRight.leftpos.addAll(integerLeftAndRightHashMap.get(i - 2).leftpos);
                    LeftAndRight.leftpos.addAll(integerLeftAndRightHashMap.get(i - 1).leftpos);
                    LeftAndRight.rightpos.addAll(integerLeftAndRightHashMap.get(i - 2).rightpos);
                    LeftAndRight.rightpos.addAll(integerLeftAndRightHashMap.get(i - 1).rightpos);
                    integerLeftAndRightHashMap.put(i, LeftAndRight);
                } else if (exp.charAt(i) == '*') {
                    LeftAndRightPos LeftAndRight = new LeftAndRightPos();
                    LeftAndRight.leftpos.addAll(integerLeftAndRightHashMap.get(i - 1).leftpos);
                    LeftAndRight.rightpos.addAll(integerLeftAndRightHashMap.get(i - 1).rightpos);
                    integerLeftAndRightHashMap.put(i, LeftAndRight);

                } else if (exp.charAt(i) == '.') {
                    LeftAndRightPos LeftAndRight = new LeftAndRightPos();
                    if (exp.charAt(i - 1) == '*') {

                        if (checkNullable(exp.charAt(i - 2))) {
                            LeftAndRight.leftpos.addAll(integerLeftAndRightHashMap.get(i - 3).leftpos);
                            LeftAndRight.leftpos.addAll(integerLeftAndRightHashMap.get(i - 1).leftpos);

                        } else {
                            LeftAndRight.leftpos.addAll(integerLeftAndRightHashMap.get(i - 3).leftpos);
                        }
                        if (checkNullable(exp.charAt(i - 1))) {

                            LeftAndRight.rightpos.addAll(integerLeftAndRightHashMap.get(i - 3).rightpos);
                            LeftAndRight.rightpos.addAll(integerLeftAndRightHashMap.get(i - 1).rightpos);
                        } else {
                            LeftAndRight.rightpos.addAll(integerLeftAndRightHashMap.get(i - 1).rightpos);
                        }
                        integerLeftAndRightHashMap.put(i, LeftAndRight);
                    } else {
                        if (checkNullable(exp.charAt(i - 2))) {
                            LeftAndRight.leftpos.addAll(integerLeftAndRightHashMap.get(i - 2).leftpos);
                            LeftAndRight.leftpos.addAll(integerLeftAndRightHashMap.get(i - 1).leftpos);

                        } else {
                            LeftAndRight.leftpos.addAll(integerLeftAndRightHashMap.get(i - 2).leftpos);
                        }
                        if (checkNullable(exp.charAt(i - 1))) {

                            LeftAndRight.rightpos.addAll(integerLeftAndRightHashMap.get(i - 2).rightpos);
                            LeftAndRight.rightpos.addAll(integerLeftAndRightHashMap.get(i - 1).rightpos);
                        } else {
                            LeftAndRight.rightpos.addAll(integerLeftAndRightHashMap.get(i - 1).rightpos);
                        }
                        integerLeftAndRightHashMap.put(i, LeftAndRight);
                    }
                }
            }
        }




        for (int i = 0; i < exp.length(); i++) {
            try{

                if(integerLeftAndRightHashMap.containsKey(i)) {

                    System.out.println("For char "+exp.charAt(i)+" the first pos is "+integerLeftAndRightHashMap.get(i).leftpos + " and the last pos is " + integerLeftAndRightHashMap.get(i).rightpos);
                }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        }
    }


   /* function to calculate the followPos*/
    public void calculateFollowPos()
    {
        HashMap <Integer, ArrayList<Integer>> followPos=new HashMap<>();
        for (int i = 0; i < postFix.length(); i++) {
            if(postFix.charAt(i)=='*')
            {
               // System.out.println(i);
                ArrayList <Integer> firstpos=new ArrayList<>();
                firstpos= integerLeftAndRightHashMap.get(i).leftpos;
                ArrayList <Integer> lastpos=integerLeftAndRightHashMap.get(i).rightpos;


                for (Integer last: lastpos)
                {
                    if(followPos.containsKey(last))
                    {
                        for (Integer first:firstpos)
                        {
                            if(!followPos.get(last).contains(first))
                            {
                                followPos.get(last).add(first);
                            }
                        }
                    }
                    else {
                        ArrayList <Integer> arrayList=new ArrayList<>();

                            for (Integer lastPos:lastpos)
                            {
                                    arrayList.add(lastPos);
                            }



                        followPos.put(last,arrayList);


                    }
                }
            }
            else if(postFix.charAt(i)=='.')
            {
                    for (Integer lastPosOfFirstChild:integerLeftAndRightHashMap.get(i-2).rightpos)
                {
                    if(followPos.containsKey(lastPosOfFirstChild))
                    {

                        for (Integer firstOfSecondChild: integerLeftAndRightHashMap.get(i-1).leftpos)
                        {
                            if(!followPos.get(lastPosOfFirstChild).contains(firstOfSecondChild))
                            {
                                followPos.get(lastPosOfFirstChild).add(firstOfSecondChild);
                            }
                        }
                    }

                    else {
                        ArrayList <Integer> ae=new ArrayList();
                        for (Integer firstOfSecondChild:integerLeftAndRightHashMap.get(i-1).leftpos)
                        {
                            ae.add(firstOfSecondChild);
                        }
                        followPos.put(lastPosOfFirstChild,ae);
                    }
                }

            }
        }
        System.out.println("\nthe follow Pos is calculated as \n "+followPos);
    }

    public static void main(String[] args) {
        Lab_4_Question1_Saroj_Shrestha question1=new Lab_4_Question1_Saroj_Shrestha();
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter the regular expression");
        String inp=scanner.nextLine();
        inp=inp+"#";
        String postfix=question1.toPostfix(inp.toUpperCase());
        question1.calculateFirstPosAndLastPos(postfix);
        question1.calculateFollowPos();
    }
}





