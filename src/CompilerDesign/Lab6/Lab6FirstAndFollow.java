package CompilerDesign.Lab6;
import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by rocks on 11/14/2016.
 */
public class Lab6FirstAndFollow {

    //function to check if the left recursion exists or not

    public ArrayList<String> rightexp=new ArrayList<>();
    HashMap<String,ArrayList<String>> productions=new HashMap<>();
    public boolean checkLeftRecurison(String exp)
    {


        Boolean leftRecursion=false;
        String []leftAndRightExps=exp.split("=");//split the left symbol and production sets
        ArrayList<String> rightExps= new ArrayList<>( Arrays.asList(leftAndRightExps[1].split("|")));//split the production sets and place them in arrayList
        char firstChar=leftAndRightExps[1].charAt(0);

        rightexp=rightExps;
        for (String exps: rightExps) {
            if (leftAndRightExps[0].equals(Character.toString(exps.charAt(0)))) {//if true left recursion detected
                leftRecursion = true;
                break;
            }
        }
        return leftRecursion;
    }

    //method to eliminate left recursion
    public String eliminateRecursion(String exp)
    {
        String production="";
        if(checkLeftRecurison(exp))
        {
            System.out.println("Left recursion detected");
            String []leftAndRightExps=exp.split("=");//split the left symbol and production sets
            ArrayList <String> rightExps= new ArrayList<>( Arrays.asList(leftAndRightExps[1].split("\\|")));//split the production sets and place them in arrayList


            ArrayList <String> productionsWithLeftTerminal=new ArrayList<>();//arraylist to store productions with left recursion
            ArrayList <String> terminals=new ArrayList<>();//arraylist to store terminals
            for (String exps: rightExps) {
                if (leftAndRightExps[0].equals(Character.toString(exps.charAt(0)))) {
                    productionsWithLeftTerminal.add(exps);
                }
                else {
                    terminals.add(exps);
                }
            }
            production=leftAndRightExps[0]+"=";
            for (String prod:terminals)
            {
                production=production+prod+leftAndRightExps[0]+"'|";
            }

            production=production+"\n"+leftAndRightExps[0]+"'=";
            for (String prod:productionsWithLeftTerminal)
            {
                prod=prod.replaceAll(leftAndRightExps[0],"");
                production=production+prod+leftAndRightExps[0]+"'|";
            }
            production=production+"ε\n\n";


            System.out.println(production);
        }
        else {
            System.out.println("The expression doesn't have left recursion");
        }
        return production;
    }

    private ArrayList<Character> firstPos(String production){

        String[] productionTokens = production.split("\\|");

        ArrayList<Character> firstPosSet= new ArrayList<>();

        for (String productionToken : productionTokens) {

            if (productionToken.equals("ε")) {

                firstPosSet.add(productionToken.charAt(0));
            } else if (productionToken.charAt(0) < 67 || productionToken.charAt(0) > 90) {

                firstPosSet.add(productionToken.charAt(0));

            } else {

                for (int i = 0; i < rightexp.size(); i++)

                    if (rightexp.get(i).equals(productionToken.substring(0, 1))) {
                        firstPosSet.addAll(firstPos(rightexp.get(i)));
                        break;
                    }
                }
            }

        return firstPosSet;
    }


    public static void main(String[] args) {

        Lab6FirstAndFollow lab6FirstAndFollow=new Lab6FirstAndFollow();
        Scanner inp=new Scanner(System.in);
        String input="";
        while (true) {
            System.out.println("Enter the expression to check the left recursion and eliminate if detected");
            input=inp.nextLine();
            String expwithoutLeftRecursion= lab6FirstAndFollow.eliminateRecursion(input);
            ArrayList<Character> firstPos= lab6FirstAndFollow.firstPos(expwithoutLeftRecursion);
            System.out.println(firstPos);
        }

    }

}

