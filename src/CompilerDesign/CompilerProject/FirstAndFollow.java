
package CompilerDesign.CompilerProject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by rocks on 11/23/16.
 */
public class FirstAndFollow {
    private static String node[] = new String[5];
    private static String child[] = new String[5];
    public static HashMap<String, ArrayList<Character>> firstSet = new HashMap<>();
    public static HashMap<String, Set<Character>> followSet = new HashMap<>();


    public static void findFirstAndFollow(ArrayList<String> prod)
    {
        System.out.println(prod);
        ArrayList<String> recursionFree = new ArrayList<>();

        String[] leftAndRight=prod.get(0).split("=");
        ArrayList <Character> initialChar=new ArrayList<>();
        initialChar.add('$');
        firstSet.put(leftAndRight[0],initialChar);
        for (int i = 1; i < prod.size() ; i++) {

            recursionFree.addAll(removeLeft(prod.get(i)));
        }
        System.out.println(recursionFree);
        for (int i = 0; i < recursionFree.size(); i++) {
            if(recursionFree.get(i)!=null){
                node[i] = recursionFree.get(i).split("=")[0]; //divide rootnode and 		production
                child[i] = recursionFree.get(i).split("=")[1];
            }
        }

        for (int i = 0; i < node.length; i++) {
            firstSet.put(node[i],getFirst1(child[i]));
            followSet.put(node[i],getFollow1(node[i],""));
        }

        System.out.println("First Set:"+firstSet);
        System.out.println("Follow Set:"+followSet);

    }



    public static void main(String[] args) {
        ArrayList<String> recursionFree = new ArrayList<>();



        String productions[] = new String[5];
        productions[0] = "E=E+T|T";
        productions[1] = "T=T*F|F";
        productions[2] = "F=(E)|d"; //d represents ID

        recursionFree.addAll(removeLeft(productions[0]));
        recursionFree.addAll(removeLeft(productions[1]));
        recursionFree.addAll(removeLeft(productions[2]));

        System.out.println("RecursionFree:"+recursionFree);

        for (int i = 0; i < recursionFree.size(); i++) {
            if(recursionFree.get(i)!=null){
                node[i] = recursionFree.get(i).split("=")[0]; //divide rootnode and 		production
                child[i] = recursionFree.get(i).split("=")[1];
            }
        }

        for (int i = 0; i < node.length; i++) {
            firstSet.put(node[i],getFirst1(child[i]));
            followSet.put(node[i],getFollow1(node[i],""));
        }

        System.out.println("First Set:"+firstSet);
        System.out.println("Follow Set:"+followSet);

    }

    private static ArrayList<String> removeLeft(String inputProduction) {
        boolean flag = false;
        String[] input = inputProduction.split("="); //divide rootnode and 		production
        String rootNode = input[0];
        String newProduction =  rootNode+"'";
        ArrayList<String> finalProductions = new ArrayList<>(); //stores 		production after removal of left recursion
        finalProductions.add(0, rootNode + "=");
        finalProductions.add(1, newProduction+ "=");
        String[] productions = input[1].split("\\|");

        for(String production : productions) {//checks if there 		exists left recursion
            if (production.charAt(0) == rootNode.charAt(0)) {
                flag = true;
            }
        }

        if(flag){ //if there exists left recursion
            for (int i=0;i<productions.length;i++){
                if(productions[i].charAt(0) != rootNode.charAt(0)){//if 			production is not left recursive
                    productions[i] += newProduction;
                    finalProductions.set(0, finalProductions.get(0) + 				productions[i] + "|");
                } else {//if production is left recursive
                    productions[i] = productions[i].substring(1,productions[i].length()) + newProduction;
                    finalProductions.set(1, finalProductions.get(1) + productions[i] + "|");
                }
            }
            finalProductions.set(0,finalProductions.get(0).substring(0,finalProductions.get(0).length()-1));
            finalProductions.set(1,finalProductions.get(1) + "ε");
        }else{
            //System.out.println("Left Recursion don't exists");
            finalProductions.set(0,inputProduction);
            finalProductions.set(1,null);
        }
        return finalProductions;
    }

    private static Set<Character> getFollow1(String prod, String temp){
        Boolean end = false;

        //System.out.println("Prod:"+prod);
        ArrayList<Integer> found =  find(prod);
        //System.out.println("Found:"+found);
        Set<Character> follow =  new HashSet<>();
        if(prod.equals(node[0])){
            follow.add('$');
        }

        for (int i = 0; i < found.size()-2; i=i+3) {
            //System.out.printf(temp+":"+node[found.get(i)]);
            if (!temp.equals(node[found.get(i)])){
                String curStr = child[found.get(i)].split("\\|")[found.get(i+1)];
                String subStr = curStr.substring(found.get(i+2)+1,curStr.length());
                if(subStr.length()==0){
                    follow.addAll(getFollow1(node[found.get(i)],prod));
                }
                ArrayList<String> subStrs = tokenizer(subStr);
                for (int j = 0; j < subStrs.size(); ) {
                    if(subStrs.get(j).charAt(0)<65||subStrs.get(j).charAt(0)>90){
                        follow.add(subStrs.get(j).charAt(0));
                        break;
                    }else {
                        Set<Character> first =  new HashSet<>(firstSet.get(subStrs.get(j)));
                        //System.out.println("FirstSetOf:"+subStrs.get(j)+"is:"+first);
                        if(first.contains('ε')){
                            j++;
                            first.remove('ε');
                            follow.addAll(first);
                            if(j==subStrs.size()){
                                follow.addAll(getFollow1(node[found.get(i)], prod));
                            }
                        }else {
                            follow.addAll(first);
                            break;
                        }

                    }
                }

            }}
        return follow;
    }

    private static ArrayList<Integer> find(String prod){
        ArrayList<Integer> index = new ArrayList<>();
        for (int i = 0; i < child.length; i++) {
            String[] outcomeTokens = child[i].split("\\|");
            for(int k=0; k<outcomeTokens.length; k++) {
                for(int j = 0; j < outcomeTokens[k].length()-prod.length()+1; j++) {
                    if(prod.equals(outcomeTokens[k].substring(j,j+prod.length()))){
                        //System.out.println(outcomeToken.substring(j,j+prod.length()));
                        if(outcomeTokens[k].charAt(j+1)!='\'' || prod.length()==2){
                            index.add(i);
                            index.add(k);
                            index.add(j+prod.length()-1);
                        }
                    }
                }
            }
        }
        return index;
    }

    private static ArrayList<Character> getFirst1(String outcome){
        String[] outcomeTokens = outcome.split("\\|");
        ArrayList<Character> firstSets= new ArrayList<>();
        for (String outcomeToken : outcomeTokens) {
            if (outcomeToken.equals("ε")) {
                firstSets.add(outcomeToken.charAt(0));
            } else if (outcomeToken.charAt(0) < 67 || outcomeToken.charAt(0) > 90) {
                firstSets.add(outcomeToken.charAt(0));
            } else {
                for (int j = 0; j < node.length; j++) {
                    if (node[j].equals(outcomeToken.substring(0, 1))) {
                        firstSets.addAll(getFirst1(child[j]));
                        break;
                    }
                }
            }
        }
        return firstSets;
    }

    private static ArrayList<String> tokenizer(String input){
        ArrayList<String> tokens = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            if(input.length()!=i+1 && input.charAt(i+1)=='\'' ){
                tokens.add(input.substring(i,i+2));
                i++;
            }
            else
                tokens.add(String.valueOf(input.charAt(i)));
        }
        return tokens;
    }
}
