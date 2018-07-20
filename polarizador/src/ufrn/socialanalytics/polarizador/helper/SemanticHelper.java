package ufrn.socialanalytics.polarizador.helper;

public class SemanticHelper {

    public boolean[] verifySemanticStruct(String[] palavras) {
        boolean[] verif = new boolean[3];
        verif[0] = verifyConj(palavras);
        verif[1] = verifyTags(palavras);
        verif[2] = verifyNeg(palavras);

        return verif;
    }

    public boolean verifyConj(String[] palavras) {
        int limite = palavras.length;
        for (int i = 0; i < limite; i = i + 1) {
            if (palavras[i].equalsIgnoreCase("mas") || palavras[i].equalsIgnoreCase("porém") || palavras[i].equalsIgnoreCase("entretanto") || palavras[i].equalsIgnoreCase("todavia")) {
                return true;
            }
        }
        return false;
    }

    public boolean verifyTags(String[] palavras) {
        int limite = palavras.length;
        for (int i = 0; i < limite; i = i + 1) {
            if (palavras[i].equalsIgnoreCase("")) {
                return true;
            }
        }
        return false;
    }

    public boolean verifyNeg(String[] palavras) {
        int limite = palavras.length;
        for (int i = 0; i < limite; i = i + 1) {
            if (palavras[i].equalsIgnoreCase("não") || palavras[i].equalsIgnoreCase("nunca") || palavras[i].equalsIgnoreCase("jamais") || palavras[i].equalsIgnoreCase("n")) {
                return true;
            }
        }
        return false;
    }

}
