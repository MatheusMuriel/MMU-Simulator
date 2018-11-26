package br.unifil.dc.sisop;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Optional;

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
        System.out.println("Metodo Comandos.chama comando ainda não implementado.");

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
                int quantBits;
                TiposComandos comando;
                if ((quantBits = ma.pegaNumero(parametros.get(0))) == -1) {
                    System.err.println("Quantidade de Bits invalida ou inexistente.");
                    return false;
                }

                //Capturar a entrada de Inteiro e haxadecimal aqui

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
                        }
                    case ("memoria-virtual"):
                        int qBitsPosMSB;

                        if ((qBitsPosMSB = ma.pegaNumero(parametros.get(3))) == -1){
                            System.err.println("Quantidade de Bits invalida ou inexistente.");
                            System.err.println("Digite a quantidade de bits a partir do MSB para a indexação de páginas de um processo.");

                            return false;
                        }else {
                            comando = TiposComandos.Memoria;
                            //System.out.println("memoria virtual com quantidade de bits " + qBitsPosMSB);
                            tipoSimulacao = comando;
                            return true;
                        }
                    default:
                        System.err.println("Especifique o tipo de MMU (Sem espaços ou acentos e separado por traço)");
                        return false;
                }

                //Fazer a checagem de logica entre os parametros aqui

            case ("processo"):
                //Precisa receber o PID do novo processo
                int pid = ma.pegaNumero(parametros.get(0));
                if (pid <= -1){
                    System.err.println("Digite um numero PID valido.");
                    return false;
                }

                if(tipoSimulacao == TiposComandos.RegistradoresBitmap
                        || tipoSimulacao == TiposComandos.RegistradoresLista){
                    int tamMemProc = ma.pegaNumero(parametros.get(1));
                    if (tamMemProc <= -1) System.err.println("É precisso indicar o tamanho da memoria do novo processo.");
                    if (tamMemProc > memoriaInstalada) System.err.println("O tamanho do processo não pode ser maior que a memoria instalada.");

                    //(b) utilizar o algoritmo de first-fit para encaixar o processo na memória.
                    //Ler no PDF

                }

                return false;

            case ("acesso"):
                //Parametros: pid e a posição da memória


                return false;

            default:
                return true;
        }
    }


    public static void terminar(Terminal.Comando cmd){

    }

    public static void relatorio(Terminal.Comando cmd){

    }

    public static void ajuda(Terminal.Comando cmd){

    }

    /**
     * cria um novo ambiente de simulação de gerenciamento de memória
     *
     * @param cmd
     */
    public static void configurar(Terminal.Comando cmd){

    }

    public static void processo(Terminal.Comando cmd){

    }

    public static void acesso(Terminal.Comando cmd){

    }

    public enum TiposComandos {
        TERMINAR
        ,RELATORIO
        ,AJUDA
        ,Configurar
        ,Memoria
        ,RegistradoresBitmap
        ,RegistradoresLista
        ,nenhum
        ,PROCESSO
        ,ACESSO
        ;
    }
    MetodosAuxiliares ma = new MetodosAuxiliares();
}
