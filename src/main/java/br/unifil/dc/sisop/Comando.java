package br.unifil.dc.sisop;

import java.util.Arrays;
import java.util.List;

public class Comando {
    String nome;
    String descAjuda;
    List<String> parametros;
    Boolean implementado;

    /**
     * Construtor que recebe um nome
     * @param nome String contendo o nome do Comando
     */
    public Comando(String nome) {
        this.nome = nome;
    }

    /**
     * Recebe um nome e uma seguencia X de parametros,
     * Os "String..." transformar esses parametros em um Array
     * @param nome String contendo o nome do Comando
     * @param parametros Numero X de parametros
     */
    public Comando(String nome, String... parametros) {
        this.nome = nome;

        this.parametros = Arrays.asList(parametros);
    }

    private String getAjuda(){
        return this.descAjuda;
    }

    private List<String> getParametros() {
        return parametros;
    }

    public Boolean getImplementado() {
        return implementado;
    }










    //Comandos com referencia
    public List<String> getParametros(Comando comando) {
        return comando.getParametros();
    }

    public String getAjuda(Comando comando){
        return comando.getAjuda();
    }

    public Boolean getImplementado(Comando comando){
        return comando.getImplementado();
    }


}
