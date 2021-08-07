package br.edu.ifnmg.tcc.util;

public class StringHelper {
    
    /**
	 * Metodo remove caracters especiais (acentos, pontuaįões e etc.) de uma string;
	 * @since 12/12/2010
	 * </br> 1.0
	 * @author Danilo Souza Almeida
	 * @param texto
	 * @return
	 */
	public static String replaceAllSpecialCharacters(String texto){
		texto = texto.replaceAll("[ÂĀÁÄÃ]","A");
		texto = texto.replaceAll("[âãāáä]","a");  
		texto = texto.replaceAll("[ĘČÉË]","E");  
		texto = texto.replaceAll("[ęčéë]","e");  
		texto = texto.replaceAll("[ÎÍĖÏ]","I");  
		texto = texto.replaceAll("[îíėï]","i");  
		texto = texto.replaceAll("[ÔÕŌÓÖ]","O");  
		texto = texto.replaceAll("[ôõōóö]","o");  
		texto = texto.replaceAll("[ÛŲÚÜ]","U");  
		texto = texto.replaceAll("[ûúųü]","u");  
		texto = texto.replaceAll("[Į]","C");  
		texto = texto.replaceAll("[į]","c");   
		texto = texto.replaceAll("[ýĸ]","y");  
		texto = texto.replaceAll("[Ý]","Y");  
		texto = texto.replaceAll("[ņ]","n");  
		texto = texto.replaceAll("[Ņ]","N");  
		texto = texto.replaceAll("[^0-9a-zA-Z\\s]","");  
		return texto;  
	}
	
	/**
	 * Adiciona o caractere informado para a direita do texto até completar o tamanho informado.
	 * @param text
	 * @param character
	 * @param size
	 * @since 12/12/2010
	 * </br> 1.0
	 * @author Danilo Souza Almeida
	 * @return
	 */
	public static String addsCharacterRight(String text, char character, int size){
		if (text != null && text.length() < size){
			for (int i=text.length(); i < size; i++){
				text += character;
			}
		}
		return text;
	}

	/**
	 * Adiciona o caractere informado para a esquera do texto até completar o tamanho informado.
	 * @param text
	 * @param character
	 * @param size
	 * @since 12/12/2010
	 * </br> 1.0
	 * @author Danilo Souza Almeida
	 * @return
	 */
	public static String addsCharacterLeft(String text, char character, int size){
		String newText = "";
		if (text != null && text.length() < size){
			for (int i=text.length(); i < size; i++){
				newText += character;
			}
		}
		return newText+text;
	}
	
	/**
	 * Verifica se uma string é nula e/ou vazia. Usa Trim por default.
	 * @since 12/12/2010
	 * </br> 1.0
	 * @author Danilo Souza Almeida
	 * @return
	 */
	public static boolean isEmpty(String text){
		return StringHelper.isEmpty(text, true);
	}
	
	/**
	 * Verifica se uma string é nula e/ou vazia. O useTrim remove os espaįõs do inico e do final.
	 * @since 12/12/2010
	 * </br> 1.0
	 * @author Danilo Souza Almeida
	 * @return
	 */
	public static boolean isEmpty(String text, boolean useTrim){
		if (text == null){
			return true;
		}
		if (useTrim){
			text = text.trim();
		}
		if (text.equals("")){
			return true;
		}
		return false;
	}
        
          /**
    * Remove espaços da internos da string.
    *
    * @param text
    * @since 13/05/2016
    * </br> 1.0
    * @author Robson Paiva Diniz
    * @return String
    */
   public static String spaceRemover(String text) {
       text = text.replaceAll("\\s+", " ");
       return text;
   }
    
}
