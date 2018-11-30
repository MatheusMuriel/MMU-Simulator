package br.unifil.dc.sisop;

import java.text.Normalizer;
import java.util.Optional;

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

    /**
     * Metodo que pega o numero de um String
     * Se capturar um erro Null, não tem um numero nessa string ou a string é vazia
     * @param I entrada
     * @return Optional contendo o numero se ele tiver sucesso, caso contrario um optional vazio
     */
    ////refazer javadoc
    public int pegaNumero(String I){
        try{
            int numero = Integer.parseInt(I);
            return numero;
        }catch (NullPointerException e){
            return -1;
        }catch (IndexOutOfBoundsException e){
            return -2;
        } catch (NumberFormatException e){
            return -3;
        }
    }

    /**
     * @Manhani
     * Metodo que converte um Hexadecimal para decimal
     * @param I Input
     * @return
     */
    public int pegaHexa(String I) {
        if (I.matches("^0[xX][0-9A-Fa-f]+$")){
            //System.out.println("Hexa começa com 0x");
            I = I.replaceFirst("^0[xX]", "0");
        }
        String digits = "0123456789ABCDEF";
        I = I.toUpperCase();
        int val = 0;
        for (int i = 0; i < I.length(); i++) {
            char c = I.charAt(i);
            int d = digits.indexOf(c);
            val = 16*val + d;
        }
        return val;
    }

    public String trataTipoMMU(String I) {

        I = I.replaceAll("(Á|Â|À|Ã|Ä)","A");
        I = I.replaceAll("(É|È|Ë|Ê)","E");
        I = I.replaceAll("(Í|Ì|Î|Ï)","I");
        I = I.replaceAll("(Ó|Ò|Ô|Õ|Ö)","O");
        I = I.replaceAll("(Ú|Ù|Û|Ü)","U");

        I = I.replaceAll("((REGISTRADOR)(ES)*( )*(BASE)( )*(E)*( )*(LIMITE))", "REGISTRADORES-BASE-LIMITE");
        I = I.replaceAll("((MEMORIA)( )*(VIRTUAL))", "MEMORIA-VIRTUAL");
        I = I.replaceAll("((BIT)( |-)*(MAP))", "BITMAP");
        I = I.replaceAll(" {2,}", " ");
        I = I.replaceAll("((LISTA)( |-)*(ENCADEADA))", "LISTA-ENCADEADA");

        return I;
    }

    public boolean validaNumerico(String I){
        return I.matches("[0-9]+");
    }

    public boolean isValidadeDacimal(String I){
        return I.matches("^[0-9]+$");
    }
    public boolean isValidadeHexa(String I){
        return I.matches("^0[xX][0-9A-Fa-f]+$|^[0-9A-Fa-f]+$");
    }

    /**
     *
     * @param I
     * @return 1 se for decimal, 2 se for hexa, -1 se não for nenhum dos 2
     */
    public int verificaBaseNumerica(String I){

        boolean validadeDacimal = isValidadeDacimal(I);
        boolean validadeHexa    = isValidadeHexa(I);

        if (validadeDacimal)    return 1;
        else if (validadeHexa)  return 2;
        else                    return -1;

    }

}
