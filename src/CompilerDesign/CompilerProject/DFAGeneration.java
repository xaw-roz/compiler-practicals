package CompilerDesign.CompilerProject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by rocks on 12/26/2016.
 */
public class DFAGeneration {
    public ArrayList <String> productions=new ArrayList<>();
    public class ProdWithLookAhead{
        int pointer=0;

        public int getPointer() {
            return pointer;
        }

        public void setPointer(int pointer) {
            this.pointer = pointer;
        }

        public ArrayList<Character> getLookAhead() {
            return lookAhead;
        }

        public void setLookAhead(ArrayList<Character> lookAhead) {
            this.lookAhead = lookAhead;
        }

        public ArrayList<String> getProduction() {
            return production;
        }

        public void setProduction(ArrayList<String> production) {
            this.production = production;
        }

        ArrayList<String> production=new ArrayList<>();
        ArrayList <Character> lookAhead=new ArrayList<>();
    }


    public class Node{
        HashMap <Character, String> rules=new HashMap<>();
        HashMap<String,ProdWithLookAhead> prodInsideNode=new HashMap<>();
    }

    public HashMap<String,ProdWithLookAhead> prodForDFA=new HashMap<>();

    public void initializeProdWithLookAhead(ArrayList<String> arr)
    {
        for (String prod:arr)
        {
            ProdWithLookAhead pr=new ProdWithLookAhead();
            String []leftRight=prod.split("=");
            if(leftRight[1].contains("\\|"))
            {
                String []splittedProd=leftRight[1].split("\\|");
                for (int i = 0; i < splittedProd.length; i++) {
                    pr.production.add(splittedProd[i]);
                }
            }
            else {
                pr.production.add(leftRight[1]);
            }
            prodForDFA.put(leftRight[0],pr);
        }
    }

    public String getRight(String prod)
    {
        String [] leftRight=prod.split("=");
        return leftRight[1];
    }
    public String getLeft(String prod)
    {
        String [] leftRight=prod.split("=");
        return leftRight[0];
    }

    public void CreateTree(ArrayList <String> prod)
    {

        Node root=new Node();
        ProdWithLookAhead p=new ProdWithLookAhead();
        p.production.add(getLeft(prod.get(0)));
        p.lookAhead.add('$');
        HashMap<String,ProdWithLookAhead> prodInside=new HashMap<>();
        root.prodInsideNode.put(getLeft(prod.get(0)),p);






    }

    public void CreateNode(String prod,int ind)
    {

        Node root=new Node();
        ProdWithLookAhead p=new ProdWithLookAhead();
        p.production.add(getRight(prod));
        p.lookAhead.add('$');
        HashMap<String,ProdWithLookAhead> prodInside=new HashMap<>();
        root.prodInsideNode.put(getLeft(prod),p);
        for (int i = 0; i < p.production.size(); i++) {
            Character c=p.production.get(0).charAt(p.pointer);
            String prev=getLeft(prod);
            if(Character.isUpperCase(c))
            {
                ProdWithLookAhead p1=new ProdWithLookAhead();
                p1=prodForDFA.get(c.toString());
                p1.pointer=p.pointer;
                p1.lookAhead.addAll(root.prodInsideNode.get(prev).lookAhead);
                p1.lookAhead.addAll(FirstAndFollow.firstSet.get(p.production.get(0).charAt(p.pointer+1)));
                root.prodInsideNode.put(c.toString(),p1);
                prev=c.toString();
            }
        }

    }



    public void getProductionsAndAugment()
    {

        String line;
        try {

            BufferedReader br=new BufferedReader(new FileReader("C:\\Users\\rocks\\IdeaProjects\\MachineLearning\\src\\CompilerDesign\\CompilerProject\\Input.txt"));
            line=br.readLine();
            String [] leftAndRight=line.split("=");
            productions.add("S'="+leftAndRight[0]);//added augmentation

            productions.add(line);
            while ((line=br.readLine())!=null)
            {
                if(line!=null) {
                    productions.add(line);
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FirstAndFollow.findFirstAndFollow(productions);
    }



    public static void main(String[] args) {
        DFAGeneration dfaGeneration=new DFAGeneration();
        dfaGeneration.getProductionsAndAugment();

    }


}
