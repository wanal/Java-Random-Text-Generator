
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package randomtextgenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author awan18
 */
public class RandomTextGenerator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        String source = "Book1";
        String result = "Result";
        File f = new File(result);
        Scanner r = new Scanner(System.in);
        System.out.println("What value of analysis would you like? (This number is somewhat ambiguous; higher numbers will yield a result that more closely follows source text) ");
        int k = r.nextInt();
        System.out.println("Enter a length (greater number will yield more words): ");
        int length = r.nextInt();
        if (k <= 0)
            System.err.println("The value of analysis has to be greater than 0");
        if (length <= 0)
            System.err.println("length has to be greater than 0");
        if (k > 0 && length > 0)
            generateText(k, length, source, f);
    }
    
    
    public static void generateText(int k, int length, String source, File f) throws FileNotFoundException{
        
        String content = "";
        
        try 
        {
            content = new Scanner(new File(source)).useDelimiter("\\Z").next();
        }
        catch(Exception ex) 
        {
            System.err.println("Unable to open source file ");
        }
        
        String listAll = "";
        int aNum;
        int contentLength = content.length();
        if (contentLength <= k)
            System.err.println("File has to be longer than the level of analysis. ");
        else /* which means k > 0 and length > 0 and contentLength > k)*/ {
            PrintWriter writer = null;
            try
            {
                writer = new PrintWriter(f);
            }
            catch (FileNotFoundException e)
            {
                System.err.println("Unable to open result file "); 
            }
            int start = (int) (Math.random()*(contentLength-k));
            String temp = content.substring(start, start+k);
            listAll = temp;
            for (int i = 0; i < length-k; i++){
                ArrayList<String> list = new ArrayList<String>();
                int n = content.indexOf(temp, 0);
                while (n > 0)
                {
                    if (n < contentLength-k)
                          list.add(content.substring(n+k, n+k+1)); 
                    n = content.indexOf(temp, n + k);
                }
                if (list.size() > 0)
                {
                    int randNum = (int) (Math.random()*list.size());
                    temp = temp.substring(1, k) + list.get(randNum);
                    listAll = listAll + list.get(randNum);
                }
                else
                {
                    start = (int) (Math.random()*contentLength);
                    temp = temp.substring(1, k) + content.charAt(start);
                    listAll = listAll + content.charAt(start);
                }
            }
                    writer.print(listAll);
                    writer.close();
            
        }
    }
    
}


