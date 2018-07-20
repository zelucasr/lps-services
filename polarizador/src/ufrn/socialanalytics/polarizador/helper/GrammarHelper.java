package ufrn.socialanalytics.polarizador.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;

import opennlp.tools.cmdline.PerformanceMonitor;
import opennlp.tools.cmdline.postag.POSModelLoader;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;

/**
 *
 * @author Mickael
 */
public class GrammarHelper {

    static public int vet1, vet2;
    
	private ServletContext context;

    public ServletContext getContext() {
		return context;
	}

	public void setContext(ServletContext context) {
		this.context = context;
	}
	
	public GrammarHelper(ServletContext context){
		this.context = context;
	}

	public ArrayList<String> getNouns(String phrase) throws IOException {
    	
        ArrayList<String> sentimentNouns = new ArrayList<String>();
        //System.out.println(context.getRealPath("WEB-INF/data/pt-pos-maxent.bin"));
        POSModel model = new POSModelLoader().load(new File(context.getRealPath("WEB-INF/data/pt-pos-maxent.bin")));
        PerformanceMonitor perfMon = new PerformanceMonitor(System.err, "sent");
        POSTaggerME tagger = new POSTaggerME(model);
        ObjectStream<String> lineStream = new PlainTextByLineStream(new StringReader(phrase));
        
        perfMon.start();
        String line;
        
        while ((line = lineStream.read()) != null) {
            
            String whitespaceTokenizerLine[] = WhitespaceTokenizer.INSTANCE.tokenize(line);
            vet1 = whitespaceTokenizerLine.length;

            String[] tags = tagger.tag(whitespaceTokenizerLine);
            vet2 = tags.length;

            for (int i = 1; i < vet1; i = i + 1) {
                if (tags[i].equalsIgnoreCase("adj") || tags[i].equalsIgnoreCase("n") || tags[i].equalsIgnoreCase("v-fin")) {
                    sentimentNouns.add(whitespaceTokenizerLine[i]);
                }
            }
            perfMon.incrementCounter();
        }
        return sentimentNouns;
    }

    public boolean[] getCases(String phrase) throws IOException {

        POSModel model = new POSModelLoader().load(new File(context.getRealPath("WEB-INF/data/pt-pos-maxent.bin")));
        SemanticHelper analise = new SemanticHelper();
        
        String whitespaceTokenizerLine[] = WhitespaceTokenizer.INSTANCE.tokenize(phrase);
        
        return analise.verifySemanticStruct(whitespaceTokenizerLine);

    }

    public static double calcSimilarity(String s1, String s2) {
        String longer = s1, shorter = s2;
        if (s1.length() < s2.length()) { 
            longer = s2;
            shorter = s1;
        }
        int longerLength = longer.length();
        if (longerLength == 0) {
            return 1.0;

        }
        return (longerLength - editDistance(longer, shorter)) / (double) longerLength;

    }

    public int consultPhraseDatabase(String p1) throws FileNotFoundException, IOException {

        String line = null;
        int lineCount = 0;
        BufferedReader bReader = new BufferedReader(new FileReader(context.getRealPath("WEB-INF/data/DataPhrases.txt")));
        
        while ((line = bReader.readLine()) != null) {

            lineCount++;
            String p2 = bReader.readLine();
            double sem = calcSimilarity(p1, p2);

            if (sem >= 0.80) {
                if (p2.contains("N0=-1") == true) {
                    return -1;
                } else if (p2.contains("N0=1") == true) {
                    return 1;
                } else {
                    return 0;
                }
            }

        }
        bReader.close();
        return 66;
    }

    public int consultTagDatabase(ArrayList<String> tags) throws FileNotFoundException, IOException {
 
        String line = null;
        int lineCount = 0;
        int lineCount2 = 0;

        BufferedReader bReaderGood = new BufferedReader(new FileReader(context.getRealPath("WEB-INF/data/BoasTags.txt")));
        BufferedReader bReaderBad = new BufferedReader(new FileReader(context.getRealPath("WEB-INF/data/RuinsTags.txt")));
       

        while ((line = bReaderGood.readLine()) != null) {

            lineCount++;
            String p2 = bReaderGood.readLine();
            for (int i = 0; i < tags.size(); i++) {
                if (p2.contains(tags.get(i)) == true) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
        
        bReaderGood.close();

        while ((line = bReaderBad.readLine()) != null) {

            lineCount2++;
            String p3 = bReaderBad.readLine();
            for (int i = 0; i < tags.size(); i++) {
                if (p3.contains(tags.get(i)) == true) {
                    return -1;
                } else {
                    return 0;
                }
            }

            bReaderBad.close();
            return 0;
        }
        return 0;
    }

    public static int editDistance(String s1, String s2) {
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();

        int[] costs = new int[s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            int lastValue = i;
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0) {
                    costs[j] = j;
                } else {
                    if (j > 0) {
                        int newValue = costs[j - 1];
                        if (s1.charAt(i - 1) != s2.charAt(j - 1)) {
                            newValue = Math.min(Math.min(newValue, lastValue),
                                    costs[j]) + 1;
                        }
                        costs[j - 1] = lastValue;
                        lastValue = newValue;
                    }
                }
            }
            if (i > 0) {
                costs[s2.length()] = lastValue;
            }
        }
        return costs[s2.length()];
    }

}
