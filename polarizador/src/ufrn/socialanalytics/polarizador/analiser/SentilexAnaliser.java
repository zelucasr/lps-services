package ufrn.socialanalytics.polarizador.analiser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;

import opennlp.tools.tokenize.WhitespaceTokenizer;
import ufrn.socialanalytics.polarizador.helper.GrammarHelper;

/**
 *
 * @author Mickael
 */
public class SentilexAnaliser {
	
	private ServletContext context;
	
	public SentilexAnaliser(ServletContext context){
		this.context = context;
	}

    public int getSentiment(ArrayList<String> nomes) throws FileNotFoundException, IOException {
        int neg = 0, neut = 0, posi = 0;
        for (int i = 0; i < nomes.size(); i++) {
            if (nomes.get(i).length() > 0) {
                try {
                    int lineCount = 0;
                    String line = "";
                    BufferedReader bReader = new BufferedReader(new FileReader(context.getRealPath("WEB-INF/data/SentiLex-flex-PT02.txt")));
                    while ((line = bReader.readLine()) != null) {
                        lineCount++;
                        if (line.indexOf(nomes.get(i)) == 0) {
                            if (line.contains("N0=-1") == true || line.contains("N1=-1")) {
                                neg = neg + 1;
                                break;
                            } else if (line.contains("N0=1") == true || line.contains("N1=1") == true) {
                                posi = posi + 1;
                                break;
                            } else {
                                neut = neut + 1;
                                break;
                            }
                        }
                    }
                    bReader.close();

                } catch (IOException e) {
                    System.out.println("Error: " + e.toString());
                }

            }

        }

        return calcSentiment(neg, posi, neut);

    }

    public int calcSentiment(int neg, int posi, int neut) {
        if (posi > neg) {
            return 1;
        } else if (neg > posi) {
            return -1;
        } else if (neut > posi && neut > neg) {
            return 0;
        } else {
            return 55;
        }
    }

    public int getSentimentConj(String phrase) throws IOException {
        System.out.println("chamou");
        int limite = 0;
        String novaFrase = "";
        String whitespaceTokenizerLine[] = WhitespaceTokenizer.INSTANCE.tokenize(phrase);
        GrammarHelper posphrase = new GrammarHelper(this.context);
        
        
        for (int i = 0; i < whitespaceTokenizerLine.length; i++) {
            if ("mas".equals(whitespaceTokenizerLine[i]) || "porém".equals(whitespaceTokenizerLine[i]) || "entretanto".equals(whitespaceTokenizerLine[i])) {
                limite = i;
            }
        }

        for (int i = 0; i < limite; i++) {
            novaFrase = novaFrase + " " + whitespaceTokenizerLine[i];
        }
        
        return getSentiment(posphrase.getNouns(novaFrase)) * (-1);

    }

}
