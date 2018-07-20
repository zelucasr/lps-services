package com.ufrn.social.handler;

public class HashtagHandler implements Handler {
	
	//Retira Hashtag da sentença
	@Override
	public String manipulateSentence(String sentence) { 
		
		sentence = sentence.replace("#", "");
		sentence = sentence.trim(); //retira espaços desnecessários
		
		return sentence;
	}

}
