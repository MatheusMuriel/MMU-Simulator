package br.unifil.dc.sisop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Comando {
    public String nome;
    String descAjuda;
    ArrayList<String> parametros;
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
    public Comando(String nome, ArrayList<String> parametros) {
        this.nome = nome;

        this.parametros = parametros;
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


    private boolean verificaComando(Comando comando){
        Comandos comandos = new Comandos();

        if (comandos.verificaExistencia(comando)){
            if(comandos.verificaImplementacao(comando)){
                comandos.chamaComando(comando);
                return true;
            }
        }else {
            System.out.println("Comando inexistente");
            return false;
        }

    }

    /**
     * Metodo que verifica a coerencia entre os parametros
     * Os  parametros necessariamente na ordem:
     * quantBits;
     * quantMemoria;
     * tipoMMU;
     *
     *  O programa deverá validar se os parâmetros do comando fazem sentido entre si. Por exemplo,
     * o terminal deverá retornar erro, com indicação do ocorrido, se o tipo de MMU for “memória
     * virtual” com lista encadeada. Outro exemplo é caso o usuário indique mais memória física
     * instalada do que a capacidade de endereçamento de memória, ou uma quantidade de memória
     * instalada menor que o tamanho de um único quadro (o que inviabiliza o sistema).
     * @return boolean se os parametros estão certos ou não
     */
    private boolean verificaParametros(){


        return false;
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
