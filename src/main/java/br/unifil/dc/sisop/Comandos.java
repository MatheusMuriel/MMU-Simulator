package br.unifil.dc.sisop;

public class Comandos {

    /**
     * Metodo que diz se o comando existe ou não
     * @param cmd comando a ser verificado
     * @return true se sim, false se não
     */
    public boolean verificaExistencia(Comando cmd){

        switch (cmd.nome){
            case "terminar":
                return true;

            case "relatorio":
                return true;

            case "ajuda":
                return true;

            case "configurar":
                return true;

            case "processo":
                return true;

            case "acesso":
                return true;

            default:
                return false;
        }

    }

    /**
     * Metodo que diz se o comando esta ou não implementados
     * @param cmd comando a ser verificado
     * @return true se sim, false se não
     */
    public boolean verificaImplementacao(Comando cmd){

        switch (cmd.nome){
            case "terminar":
                return false;

            case "relatorio":
                return false;

            case "ajuda":
                return false;

            case "configurar":
                return false;

            case "processo":
                return false;

            case "acesso":
                return false;

            default:
                return false;
        }
    }


    public void chamaComando(Comando comando) {
        

    }
}
