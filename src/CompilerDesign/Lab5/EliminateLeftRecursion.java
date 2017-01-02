package CompilerDesign.Lab5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by rocks on 11/14/2016.
 */
public class EliminateLeftRecursion {

    //function to check if the left recursion exists or not
    public boolean checkLeftRecurison(String exp)
    {
        Boolean leftRecursion=false;
        String []leftAndRightExps=exp.split("=");//split the left symbol and production sets
        ArrayList <String> rightExps= new ArrayList<>( Arrays.asList(leftAndRightExps[1].split("|")));//split the production sets and place them in arrayList
        char firstChar=leftAndRightExps[1].charAt(0);

        for (String exps: rightExps) {
            if (leftAndRightExps[0].equals(Character.toString(exps.charAt(0)))) {//if true left recursion detected
                leftRecursion = true;
                break;
            }

        }
        return leftRecursion;
    }

    //method to eliminate left recursion
    public void eliminateRecursion(String exp)
    {
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
            String production=leftAndRightExps[0]+"=";
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
    }


    public static void main(String[] args) {

        Scanner inp=new Scanner(System.in);
        String input="";
        while (true) {
            System.out.println("Enter the expression to check the left recursion and eliminate if detected");
            input=inp.nextLine();
            EliminateLeftRecursion eliminateLeftRecursion = new EliminateLeftRecursion();
            eliminateLeftRecursion.eliminateRecursion(input);
        }
    }

}
