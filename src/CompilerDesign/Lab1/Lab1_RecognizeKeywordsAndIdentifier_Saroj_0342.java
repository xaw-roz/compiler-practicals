package CompilerDesign.Lab1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by rakesh-a on 8/10/2016.
 */
public class Lab1_RecognizeKeywordsAndIdentifier_Saroj_0342 {

    public void identifyKeywordsAndIdentifier(ArrayList<String> keywordsAndIdentifiers,String textData)
    {
        ArrayList<String> keywords=new ArrayList<>();
        ArrayList<String> identifiers=new ArrayList<>();
        String []parsed=parseKeywordsAndIdentifier(textData);
        for (int i = 0; i < parsed.length; i++) {
            if(keywordsAndIdentifiers.contains(parsed[i]))
            {
                keywords.add(parsed[i]);
            }
            else{
                if(checkIdentifierCondition(parsed[i])==true)
                {
                    identifiers.add(parsed[i]);
                }
            }
        }
        System.out.println("the keywords are");
        for (String keyword:keywords)
        {
            System.out.println(keyword);
        }

        System.out.println("the identifiers are");
        for (String identifier:identifiers)
        {
            System.out.println(identifier);
        }

    }
    //method to checkIdentifier
    public boolean checkIdentifierCondition(String identifier)
    {
        boolean isIdentifier=false;
        if(identifier.matches("[a-zA-Z_$][0-9a-zA-Z_]*")==true )//[a-zA-Z_$][0-9a-zA-Z_]* first letter is _ or a-z or A-Z and other character of the identifier can contain 0-9 or a-z or A-Z
        {
            isIdentifier=true;
        }

        return isIdentifier;
    }
    public String[] parseKeywordsAndIdentifier(String code)
    {
        ArrayList<String> parsedWords=new ArrayList<>();
        String []parsed=code.split(";|\\ |\\\n");//split the keywords or words for newline space and  ;

        return parsed;//returnes the splitted array of keywords and identifier


    }

    public static void main(String[] args) {
        Lab1_RecognizeKeywordsAndIdentifier_Saroj_0342 recognizeKeywordsAndIdentifier=new Lab1_RecognizeKeywordsAndIdentifier_Saroj_0342();
        ArrayList<String> keywords=new ArrayList<>();//arraylist for keywords
        try {
            String newlineRead="";
            BufferedReader br = new BufferedReader(new FileReader("C:\\compiler\\keywords.txt"));

            while ((newlineRead=br.readLine())!=null)
            {
                keywords.add(newlineRead);//addition of keywords to tha arraylist
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        String code="";
        Scanner inp=new Scanner(System.in);
        System.out.println("Enter the code");
        code=inp.nextLine();
        recognizeKeywordsAndIdentifier.identifyKeywordsAndIdentifier(keywords,code);
    }


}
