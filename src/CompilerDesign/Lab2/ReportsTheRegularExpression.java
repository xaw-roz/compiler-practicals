package CompilerDesign.Lab2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;


/**
 * Created by rakesh-a on 8/31/2016.
 *  Program: Write a program which accepts a string from the user and reports which regular expression accepts that string
 */
public class ReportsTheRegularExpression {
    public static void main(String[] args) {
        boolean repeat = true;
        String fileContent = "";
        String[] regExpressions = new String[4];
        Boolean matches = false;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\rakesh-a\\IdeaProjects\\MachineLearning\\src\\CompilerDesign\\Lab2\\regularExpression.txt"));
            while ((fileContent = bufferedReader.readLine()) != null) {
                regExpressions = fileContent.split(",");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        Scanner inp = new Scanner(System.in);
        String inputString;
        while (true) {
            System.out.println("Enter the string");
            inputString = inp.nextLine();
            matches=false;
            for (int i = 0; i < regExpressions.length; i++) {

                if (inputString.matches(regExpressions[i].toString())) {//use of matches  function to check acceptances
                    System.out.println("accpeted by regular expression " + regExpressions[i]);
                    matches = true;

                }
            }
            if(matches==false)
            {
                System.out.println("no regular expression accepts the given language");
            }
        }


    }
}
