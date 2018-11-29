package br.unifil.dc.sisop;

import java.util.ArrayList;
import java.util.Scanner;

import static br.unifil.dc.sisop.Terminal.Simulacao.memoriaInstalada;
import static br.unifil.dc.sisop.Terminal.Simulacao.tipoSimulacao;

public class Comandos {


    /**
     * Metodo que diz se o comando existe ou não
     * @param cmd comando a ser verificado
     * @return true se sim, false se não
     */
    public boolean verificaExistencia(Terminal.Comando cmd){

        switch (cmd.nome){
            case ("terminar"):
                return true;

            case ("relatorio"):
                return true;

            case ("ajuda"):
                return true;

            case ("configurar"):
                return true;

            case ("processo"):
                return true;

            case ("acesso"):
                return true;

            default:
                System.out.println("Comando inexistente.");
                return false;
        }

    }

    /**
     * Metodo que diz se o comando esta ou não implementados.
     *
     * Para fazer uma alteração de implementação, deve se alterar aqui.
     * @param cmd comando a ser verificado
     * @return true se sim, false se não
     */
    public boolean verificaImplementacao(Terminal.Comando cmd){

        switch (cmd.nome){
            case ("terminar"):
                return false;

            case ("relatorio"):
                return false;

            case ("ajuda"):
                return false;

            case ("configurar"):
                return true;

            case ("processo"):
                return true;

            case ("acesso"):
                return false;
            default:
                System.out.println("Comando não implementado.");
                return false;
        }
    }

    /**
     * Metodo feito para chamar os comandos. De fato começar a executarlos,
     * Foi feito um metodo especifico para isso pois pode haver um tratamento de passagem e etc...
     *
     * @param comando
     */
    public void chamaComando(Terminal.Comando comando) {
        System.out.println("Metodo Comandos.chamaComando ainda não implementado.");

    }

    /**
     * Metodo que verifica o parametro dos metodos.
     * @param cmd Comando a ser verificado.
     * @return Verdadeiro se estiver certo
     * e false se estiver errado. Printando um motivo de erro também.
     */
    public boolean verificaParametros(Terminal.Comando cmd) {
        System.out.println("Metodo Comandos.verificaParametro em implementação");
        ArrayList<String> parametros = cmd.getParametros();


        switch (cmd.nome){
            case ("terminar"):
                return false;

            case ("relatorio"):
                return false;

            case ("ajuda"):
                return false;

            case ("configurar"):
                //tamanhoDaMemoria();
                configurar(cmd, cmd.getParametros());
                //Definir aqui a variavem Terminal.Simulacao.Memoriainstalada
                return true;

            case ("processo"):


                return false;

            case ("acesso"):
                //Parametros: pid e a posição da memória


                return false;

            default:
                return true;
        }
    }


    public static void terminar(Terminal.Comando cmd){
        //Implemente o comando terminar, que termina espontaneamente o processo identificado por
        //um pid, parâmetro obrigatório do comando. A memória, os registros e a tabela de processos
        //deve ser totalmente limpa do processo terminado.
    }

    public static void relatorio(Terminal.Comando cmd){
        //Implemente o comando relatorio, que faz com que o terminal exiba todo o estado de
        //simulação, que inclui processos existentes e alocação de memória.
    }

    public static void ajuda(Terminal.Comando cmd){

    }

    /**
     * cria um novo ambiente de simulação de gerenciamento de memória
     * Esse metodo é chamado para processar um entrada com os parametros já passados.
     *
     *Tbm define aqui a variavel publica estatica de qual tipo de simulação se trata
     */
    public static boolean configurar(Terminal.Comando cmd, ArrayList<String> parametros){
        MetodosAuxiliares ma = new MetodosAuxiliares();
        int quantBits;
        TiposComandos comando;
        if ((quantBits = ma.pegaNumero(parametros.get(0))) == -1) {
            System.err.println("Quantidade de Bits invalida ou inexistente.");
            return false;
        }

        //Capturar a entrada de Inteiro e haxadecimal aqui
        tamanhoDaMemoria(parametros.get(1), quantBits);

        switch (parametros.get(2)){
            case ("registradores-base-e-limite"):
                switch (parametros.get(3)){
                    case ("bitmap"):
                        int t; //Tamanho de cada pedaço de alocação de memoria.
                        //Optional<Integer> op = ma.pegaNumero(parametros.get(3));
                        if ((t = ma.pegaNumero(parametros.get(4))) == -1) {
                            System.err.println("Quantidade de Bits do bitmap invalida ou inexistente.");
                            return false;
                        } else {
                            //CHEGADA FINAL
                            comando = TiposComandos.RegistradoresBitmap;
                            //System.out.println("bitmap com tamanho " + t);
                            tipoSimulacao = comando;
                            return true;
                        }
                    case ("lista-encadeada"):
                        comando = TiposComandos.RegistradoresLista;
                        //System.out.println("Lista encadeada");
                        tipoSimulacao = comando;
                        return true;

                    default:
                            //fazer
                }
            case ("memoria-virtual"):
                int qBitsPosMSB;

                if ((qBitsPosMSB = ma.pegaNumero(parametros.get(3))) == -1){
                    System.err.println("Quantidade de Bits invalida ou inexistente.");
                    System.err.println("Digite a quantidade de bits a partir do MSB para a indexação de páginas de um processo.");

                    return false;
                }else {
                    comando = TiposComandos.MemoriaVirtual;
                    //System.out.println("memoria virtual com quantidade de bits " + qBitsPosMSB);
                    tipoSimulacao = comando;
                    return true;
                }
            default:
                System.err.println("Especifique o tipo de MMU (Sem espaços ou acentos e separado por traço)");
                return false;
        }

        //Fazer a checagem de logica entre os parametros aqui
        //verificarValidadeDaMemoria(quantBits, memoriaInstalada);
        //Comandos.criaMemoria(quantBits, );

    }

    /*Processamento da entrada com apenas o comando configurar (Sem parametros) */

    /**
     *
     * numBits      = Quantidade de bits do espaço de memória.
     * MemoriaReal  = MemoriaVirtual fisica instalada (Armazenada em int).
     * @return
     */
    private static void tamanhoDaMemoria(String entrada, int numBits) {
        int memoriaReal; //EM K Palavras
        String procurarPor = "0x";
        //Scanner bits = new Scanner(System.in);
        //int numBits;
        //System.out.println("Digite os bits da memoria");
        //numBits = bits.nextInt();
        //Scanner entradaMemoriaFisica = new Scanner(System.in);
        String memoriaFisica;
        //System.out.println("Digite o tamanho da memoria fisica");
        memoriaFisica = entrada;
        if(memoriaFisica.contains(procurarPor)){
            String novaString = memoriaFisica.replace("x", "");
            memoriaReal = converteHex(novaString);
        }else{
            memoriaReal = Integer.parseInt(memoriaFisica);
        }

        memoriaInstalada = memoriaReal;
        verificarValidadeDaMemoria(numBits, memoriaReal);

        //*** Verificar aqui essas saidas
        System.out.println();
        System.out.println(numBits);
        System.out.println(memoriaFisica);
        System.out.println(memoriaReal);

        //return false;
    }

    /**
     * Metodo que verifica a coerencia entre os parametros da memoria
     * @param numBits
     * @param memoriaReal
     */
    private static void verificarValidadeDaMemoria(int numBits, int memoriaReal) {
        int quantidadeQuadros = 1;
        int contador = 0;
        while (quantidadeQuadros < memoriaReal){
            quantidadeQuadros = quantidadeQuadros*2;
            contador++;
        }

        if(quantidadeQuadros == memoriaReal){
            //Define variavel global da simulação 
            memoriaInstalada = memoriaReal;
            criaMemoria(numBits, contador);
        }else{
            System.out.println("entrada invalida! você precisa digitar uma memoria valida");
        }
    }

    /**
     * Metodo que de fato cria uma nova simulação
     * @param numBits
     * @param contador Quantidade de quadros?
     */
    private static void criaMemoria(int numBits, int contador) {

        if (tipoSimulacao == TiposComandos.RegistradoresBitmap) System.out.println("BITMAP");
        if (tipoSimulacao == TiposComandos.RegistradoresLista) System.out.println("LISTA");
        if (tipoSimulacao == TiposComandos.MemoriaVirtual) System.out.println("MEMORIA");





        int[] arryMemoria = new int[contador];
        int quantBits = numBits;
        for (int i = 0; i < arryMemoria.length-1; i++){
            arryMemoria[i]= numBits;
            numBits = numBits + quantBits;
            System.out.print(arryMemoria[i]+ ",");
        }

    }

    private static int converteHex(String memoriaFisica) {
        String digits = "0123456789ABCDEF";
        memoriaFisica = memoriaFisica.toUpperCase();
        int val = 0;
        for (int i = 0; i < memoriaFisica.length(); i++) {
            char c = memoriaFisica.charAt(i);
            int d = digits.indexOf(c);
            val = 16*val + d;
        }
        return val;
    }
    /* */

    /**
     * Metodo que faz a checagem dos parametros do comando processo
     *
     * Se a simulação atual (Terminal.tipoSimulação) for de algum tipo que utilize swapping
     *
     * @param cmd Comando de entrada
     * @return
     */
    public static boolean processo(Terminal.Comando cmd){
        ArrayList<String> parametros = cmd.getParametros();

        //Precisa receber o PID do novo processo
        int pid = ma.pegaNumero(parametros.get(0));
        if (pid <= -1){
            System.err.println("Digite um numero PID valido.");
            return false;
        }

        //Verifica se a simuação atual é do tipo Swapping
        if(tipoSimulacao == TiposComandos.RegistradoresBitmap
                || tipoSimulacao == TiposComandos.RegistradoresLista){
            System.out.println("Processo Swapping");




            //Tamanho de memoria do novo processo
            int tamMemProc = ma.pegaNumero(parametros.get(1));

            if (tamMemProc <= -1) System.err.println("É precisso indicar o tamanho da memoria do novo processo.");
            if (tamMemProc > memoriaInstalada) System.err.println("O tamanho do processo não pode ser maior que a memoria instalada.");

            //(b) utilizar o algoritmo de first-fit para encaixar o processo na memória.
            //Ler no PDF
        }else if (tipoSimulacao == TiposComandos.MemoriaVirtual){
            System.out.println("Memoria Virtual processo");


        }else{
            System.err.println("Tipo de memoria não reconhecido.");
            return false;
        }

        return false;
    }

    public static void acesso(Terminal.Comando cmd){

        if (tipoSimulacao == TiposComandos.RegistradoresBitmap
                || tipoSimulacao == TiposComandos.RegistradoresLista){
            //(a) Caso esteja-se simulando o swapping, verificar se o acesso foi ilegal, e em caso afirmativo,
            //o processo é automaticamente encerrado, e o terminal avisa o ocorrido ao usuário.
            //(b) Ainda em caso de swapping, se o processo estiver no disco, o sistema recusa o comando
            //e gera uma mensagem de erro para o usuário.
        }

        if (tipoSimulacao == TiposComandos.MemoriaVirtual){
            //(c) Em caso de memória virtual, se o acesso causar uma falha de página, o sistema deverá
            //informar os detalhes para o usuário: página acessada que causou falha, quadro liberado
            //e onde consequentemente a página foi posicionada.
        }


    }

    public enum TiposComandos {

        MemoriaVirtual
        ,RegistradoresBitmap
        ,RegistradoresLista
        ,nenhum;
    }
    static MetodosAuxiliares ma = new MetodosAuxiliares();
}
