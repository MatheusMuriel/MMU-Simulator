package br.unifil.dc.sisop;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Terminal {
    MetodosAuxiliares ma = new MetodosAuxiliares();
    public static Simulacao simulacao;



    Terminal() {
        simulacao = new Simulacao();
        simulacao.listaProcessos = new ArrayList<>();


    }

    public void pegaEntrada(){
        Scanner teclado = new Scanner(System.in);
        System.out.print("> ");

        String entrada = teclado.nextLine();
        //System.out.println(entrada);
        if (entrada.equals("paradaTeste")){
            int a = 1+1;
            int[] bitPoison = simulacao.getBitmap();

            processaEntrada("relatorio");
            Processo pc = new Processo();
            Processo.EspacoBitmap e1 = new Processo.EspacoBitmap(false,100, 130);
            Processo.EspacoBitmap e2 = new Processo.EspacoBitmap(false,150, 190);
            Processo.EspacoBitmap e3 = new Processo.EspacoBitmap(false,10, 20);
            pc.pincelCorredor(e1,bitPoison,0);
            pc.pincelCorredor(e2,bitPoison,0);
            pc.pincelCorredor(e3,bitPoison,0);
            int b = bitPoison[0];
            processaEntrada("relatorio");
        }

        if (entrada.equals("paradaTeste2")){
            int a = 1+1;
            int[] bitPoison = simulacao.getBitmap();
        }
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
        public Comandos.TiposComandos tipoSimulacao = Comandos.TiposComandos.nenhum;
        public int memoriaInstalada;
        public ArrayList<NoDeQuadro> listaQuadros;
        public int[] bitmap;
        public int blocoBitmap;
        public int quantBitsEspacoMemoria;
        public ArrayList<Processo> listaProcessos;
        public ArrayList<Processo> disco;
        public LinkedList<No> listaEncadeada;
        public int quantBitsPosMSB;


        public int[] getBitmap() {
            return bitmap;
        }

        public LinkedList<No> getListaEncadeada() {
            return listaEncadeada;
        }

        public Comandos.TiposComandos getTipoSimulacao() {
            return tipoSimulacao;
        }

        public int getMemoriaInstalada() {
            return memoriaInstalada;
        }



        public class No {
            int inicio;
            int fim;
            int tamanho;
        }
        public class NoDePagina {
            int numero;
            int inicio;
            int fim;
            boolean mapeado;
            NoDeQuadro quandro;

            public int getNumero() {
                return numero;
            }

            public NoDeQuadro getQuandro() {
                return quandro;
            }

            public boolean isMapeado() {
                return mapeado;
            }

            public void setMapeado(boolean mapeado, NoDeQuadro nQ) {
                this.quandro = nQ;
                this.mapeado = mapeado;
            }

            public int getInicio() {
                return inicio;
            }

            public void setInicio(int inicio) {
                this.inicio = inicio;
            }

            public int getFim() {
                return fim;
            }

            public void setFim(int fim) {
                this.fim = fim;
            }


        }
        public class NoDeQuadro {
            int numero;
            int inicio;
            int fim;
            boolean mapeado;
            NoDePagina pagina;

            public int getNumero() {
                return numero;
            }

            public NoDePagina getPagina() {
                return pagina;
            }

            public boolean isMapeado() {
                return mapeado;
            }

            public void setMapeado(boolean mapeado, NoDePagina pagina) {
                this.pagina = pagina;
                this.mapeado = mapeado;
            }

            public int getInicio() {
                return inicio;
            }

            public void setInicio(int inicio) {
                this.inicio = inicio;
            }

            public int getFim() {
                return fim;
            }

            public void setFim(int fim) {
                this.fim = fim;
            }


        }

    }


}
