package br.unifil.dc.sisop;

public class MetodosAuxiliares {


    /**
     * Metodo que retorna a primeira palavra de uma String
     * Faz isso usando um if ternario.
     * Se conter um "espaço" ele devolve de 0 ~ "espaço"
     * Se não ele devolver a propria string (Pois ela já é uma palavra inteira)
     * @param I String de Entrada (input)
     * @return Primeira palavra
     */
    public String primeiraPalavra(String I){
        return (I.contains(" ")) ? I.substring(0, I.indexOf(" ")) : I;
    }

    /**
     * Metodo que remove a primeira palavra de uma String
     * Faz isso usando if ternario.
     * Se conter um espaço devolve de "espaço" + 1 ~ 0
     * @param I String de Entrada (input)
     * @return String sem a primeira palavra
     */
    public String removePrimeiraPalavra(String I) {
        return (I.contains(" ")) ? I.substring(I.indexOf(" ") + 1) : "";
    }
}
