package br.unifil.dc.sisop;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Optional;

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
                Integer quantBits;

                Optional<Integer> o = ma.pegaNumero(parametros.get(0));
                if (o.isPresent()) {
                    quantBits = o.get();
                } else {
                    System.err.println("Quantidade de Bits invalida ou inexistente.");
                    return false;
                }

                switch (parametros.get(1)){
                    case ("registradores-base-e-limite"):
                        switch (parametros.get(2)){
                            case ("bitmap"):
                                Integer t;
                                Optional op = ma.pegaNumero(parametros.get(3));
                                //op.ifPresent(c -> t = o.get());



                            case ("lista-encadeada"):
                        }

                    case ("memoria-virtual"):

                    default:
                        System.err.println("Especifique o tipo de MMU (Sem espaços ou acentos e separado por traço)");
                        return false;
                }



                //return false;

            case ("processo"):
                //Precisa receber o PID do novo processo



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
        ,CONFIGURAR
        ,PROCESSO
        ,ACESSO
        ;
    }
    MetodosAuxiliares ma = new MetodosAuxiliares();
}
