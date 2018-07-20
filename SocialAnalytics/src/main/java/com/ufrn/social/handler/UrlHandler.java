package com.ufrn.social.handler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlHandler implements Handler {

	//Retira url da sentença
	@Override
	public String manipulateSentence(String sentence) {
		
		String urlPattern = "((https?|ftp|gopher|telnet|file|Unsure|http):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
        Pattern p = Pattern.compile(urlPattern,Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(sentence);
        int i = 0;
        while (m.find()) {
        	sentence = sentence.replaceAll(m.group(i),"").trim();
            i++;
        }
        return sentence;
	}

}
