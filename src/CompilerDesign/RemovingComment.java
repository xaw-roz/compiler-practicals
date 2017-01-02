package CompilerDesign;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Created by rakesh-a on 8/15/2016.
 */
public class RemovingComment {

    public static void main(String[] args) {
        RemovingComment removingComment=new RemovingComment();
        String line;
        String completeCodeWithoutComment="";
        String originalCode="";

        try{
        BufferedReader bufferedReader=new BufferedReader(new FileReader("C:\\compiler\\program.txt"));
            while ((line=bufferedReader.readLine())!=null)
            {
                originalCode=originalCode+line+"\n";
            }
            completeCodeWithoutComment=originalCode.replaceAll("(?s)\\/\\*.*?\\*\\/|\\/\\/.*?\\n","");//it will remove single line and multiple line comment
            System.out.println(completeCodeWithoutComment);
            /*
                (?s) -> Access for newline too
                \\ -> Escape sequence for java
                | -> OR operator
                . -> Any Character
                ? -> Stop if pattern pile
                * -> repeatation any no of time
                (?s)\\/\\*.*?\\*\\/ -> Characters that starts with \\/* and end with *\\/ and valid with newLine too
                \\/\\/.*?\\n -> Characters that starts with \\ and end at newLine character
                x
                if any of above sequence is found it will be replaced by empty string*/
    }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }


}
