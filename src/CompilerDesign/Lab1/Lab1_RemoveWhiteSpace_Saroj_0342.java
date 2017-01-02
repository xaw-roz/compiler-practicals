package CompilerDesign.Lab1;


import java.io.BufferedReader;
import java.io.FileReader;


/**
 * Created by rakesh-a on 8/10/2016.
 */
public class Lab1_RemoveWhiteSpace_Saroj_0342 {

    //method to remove whitespace tab and new line
    public void removeWhiteSpaceTabAndEnter(String inp)
    {
        inp=inp.replaceAll(" ","");//remove space with empty string
        inp=inp.replaceAll("\n","");//remove newline with empty string
        inp=inp.replaceAll("\t","");//remove tab with empty string
        System.out.println(inp);
    }

    public static void main(String[] args) {
        Lab1_RemoveWhiteSpace_Saroj_0342 removeWhiteSpace=new Lab1_RemoveWhiteSpace_Saroj_0342();
        String textFromFile="";
        String newline="";
        try {

            BufferedReader br = new BufferedReader(new FileReader("C:\\compiler\\input.txt"));//read from the text file
            while ((newline=br.readLine())!=null)
            {
                textFromFile=textFromFile+newline;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        removeWhiteSpace.removeWhiteSpaceTabAndEnter(textFromFile);
    }
}
