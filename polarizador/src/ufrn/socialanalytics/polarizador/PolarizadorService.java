package ufrn.socialanalytics.polarizador;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ufrn.socialanalytics.polarizador.wrapper.SentenceWrapper;
import ufrn.socialanalytics.polarizador.wrapper.SentimentWrapper;

@Path("/sentiment")
public class PolarizadorService {
	
	@Context 
	ServletContext context;

	@GET
	@Produces("text/plain")
	public String getMethod() throws IOException {
		 //Polarizador p = new Polarizador(context);
		//int sentiment = p.polarizar("você é feio");
		//System.out.println(sentiment);
		
		return "Faça uma requisição do tipo POST enviando a lista de sentenças no formato de json";
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getSentiment(SentenceWrapper sentences) throws IOException
	{
		Polarizador p = new Polarizador(context);
		
		ArrayList<Integer> sentiments = new ArrayList<Integer>();
		for(String sentence: sentences.getSentences()){
			//System.out.println(sentence);
			int sentiment = p.polarizar(sentence);
			sentiments.add(sentiment);
		}
		SentimentWrapper sentimentWrapper = new SentimentWrapper();
		sentimentWrapper.setSentiments(sentiments);
		
		//return sentiments;
		return Response.ok(sentimentWrapper).build();
	   
	}
	
}