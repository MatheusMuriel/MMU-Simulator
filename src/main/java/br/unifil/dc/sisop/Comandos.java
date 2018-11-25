package br.unifil.dc.sisop;

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
        switch (cmd.nome){
            case ("terminar"):
                return false;

            case ("relatorio"):
                return false;

            case ("ajuda"):
                return false;

            case ("configurar"):
                return false;

            case ("processo"):
                return false;

            case ("acesso"):
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

    public static void configurar(Terminal.Comando cmd){

    }

    public static void processo(Terminal.Comando cmd){

    }

    public static void acesso(Terminal.Comando cmd){

    }


}
