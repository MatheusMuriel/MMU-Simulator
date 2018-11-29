package br.unifil.dc.sisop;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Terminal {
    MetodosAuxiliares ma = new MetodosAuxiliares();
    public static Simulacao simulacao;



    Terminal() {
        simulacao = new Simulacao();

    }

    public void pegaEntrada(){
        Scanner teclado = new Scanner(System.in);
        System.out.print("> ");

        String entrada = teclado.nextLine();
        //System.out.println(entrada);
        processaEntrada(entrada);
    }

    private void processaEntrada(String entrada){
        Comando comando;
        String nome;
        ArrayList<String> parametros = new ArrayList<>();

        nome = ma.primeiraPalavra(entrada);
        entrada = ma.removePrimeiraPalavra(entrada);
        //entrada = ma.removePrimeiraPalavra(entrada);
        //entrada = entrada.substring(entrada.indexOf(" ") + 1);

        entrada = entrada.toUpperCase();
        entrada = ma.trataTipoMMU(entrada);

        //Pega os parametros
        while(entrada.length() > 0){



            String aux = ma.primeiraPalavra(entrada);
            entrada = ma.removePrimeiraPalavra(entrada);
            parametros.add(aux);
        }


        comando = new Comando(nome, parametros);

        System.out.println((processaComando(comando) != -1) ? "Comando ok." : "Comando está errado.");
        System.out.println("Proximo comando.");
    }

    /**
     * Metodo que processa um comando, vai chamando metodo por metodo
     * de verificação e da um retorno negativo casso falhe.
     * @param cmd comando a testar
     * @return -1 se o comando falhar, e 0 se der certo.
     */
    protected int processaComando(Comando cmd){
        Comandos comandos = new Comandos();

         //Os ifs abaixo estão verificando NEGAÇÃO ("!expressão") por isso retornam -1
        if (!comandos.verificaExistencia(cmd)) return -1;
        if (!comandos.verificaImplementacao(cmd)) return -1;
        if (!comandos.verificaParametros(cmd)) return -1;

        //Se não falhar em nenhum acima, chama o comando


        return 0;
    }

    /**
     * TAD que representa entidade Coamando.
     * Contem nome, lista de parametros, e etc...
     */
    public static class Comando {
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

        protected ArrayList<String> getParametros() {
            return parametros;
        }

    }

    public static class Simulacao{
        public static Comandos.TiposComandos tipoSimulacao = Comandos.TiposComandos.nenhum;
        public static int memoriaInstalada;
        public static int[] bitmap;
        public static LinkedList<No> listaEncadeada;



        public static Comandos.TiposComandos getTipoSimulacao() {
            return tipoSimulacao;
        }

        public static int getMemoriaInstalada() {
            return memoriaInstalada;
        }

        public class No {
            int inicio;
            int fim;
            int tamanho;
        }
    }


}
