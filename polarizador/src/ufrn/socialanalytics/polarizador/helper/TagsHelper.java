package ufrn.socialanalytics.polarizador.helper;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TagsHelper {

    public ArrayList<String> findTags(String phrase) {
        ArrayList<String> tags = new ArrayList<String>();

        if (phrase.indexOf("#") != -1) {
            Matcher matcher = Pattern.compile("#\\s*(\\w+)").matcher(phrase);
            while (matcher.find()) {
                tags.add(matcher.group(1));
            }
        }
        return tags;
    }

    public String removeTags(String phrase) {
        String pure = phrase.replaceAll("#\\p{L}+", "");
        String pure2 = pure.replaceAll("vc", "você");
        
        return pure2;
    }
    
    public String removeLinks(String phrase) { //Implementar
    	return phrase;
    }

}
