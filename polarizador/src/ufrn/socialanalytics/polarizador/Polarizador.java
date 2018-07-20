package ufrn.socialanalytics.polarizador;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;

import ufrn.socialanalytics.polarizador.analiser.SentilexAnaliser;
import ufrn.socialanalytics.polarizador.helper.GrammarHelper;
import ufrn.socialanalytics.polarizador.helper.TagsHelper;

/**
 * @author Mickael R. C Figueredo, Natal, Rio Grande do Norte, Brasil.
 */

public class Polarizador {

	private ServletContext context;

	public Polarizador(ServletContext context) {
		this.context = context;
	}

	public Integer polarizar(String texto) throws IOException {

		int sentiment = 0;
		ArrayList<String> noums = null;
		String newPhrase = null;
		// String phrase="Simples nostalgia ao nível máximo !" +
		// "E ao mesmo tempo um toque pra realidade de hoje em dia" +
		// "... sai da sala de cinema sorrindo pelo lindo filme ! ";

		String phrase = texto;

		TagsHelper cleaner = new TagsHelper();
		GrammarHelper posphrase = new GrammarHelper(context);
		SentilexAnaliser sentilex = new SentilexAnaliser(context);

		newPhrase = cleaner.removeTags(phrase);

		noums = posphrase.getNouns(newPhrase);
		sentiment = sentilex.getSentiment(noums);

		// -1 negativo
		// 1 positivo
		// 0 neutro
		// 55 indefinido
		return sentiment;
	}

}
