package br.unifil.dc.sisop;

import java.util.ArrayList;

public class Acesso extends Comandos {
    int pid;
    int posicao;


    Acesso(){

    }

    public boolean processaAceso (ArrayList<String> parametros){

        if ( !( validaEntrada(parametros) )) return false;
        if ( !( condsideraFatores(parametros) )) return false;




        return true;
    }

    private boolean condsideraFatores(ArrayList<String> parametros) {
        TiposComandos simulacao = Terminal.simulacao.getTipoSimulacao();
        if (simulacao.equals(TiposComandos.RegistradoresBitmap) || simulacao.equals(TiposComandos.RegistradoresLista)){

            boolean acessoIlegal = verificaAcessoIlegal();


            if (acessoIlegal){
                ArrayList<String> parAux = new ArrayList<>();
                parAux.add(Integer.toString(pid));
                Terminal.Comando cmd = new Terminal.Comando("terminar", parAux);
                Comandos.terminar(cmd);
                System.err.println("Acesso ilegal. O processo foi encerrado.");
                return false;
            }
            boolean estaEmDisco = verificaEmDisco();
            if (estaEmDisco){
                System.err.println("O processo está em disco.");
                return false;
            }

            //Acesso efetivo
        }else if (simulacao.equals(TiposComandos.MemoriaVirtual)){
            int ocorido = acessaPagina(posicao);
            boolean falhaDePagina = verificaFalhaDePagina();
            if (falhaDePagina){
                System.err.println("Houve uma falha de pagina.");
                return false;
            }


        }


        return false;
    }

    private boolean verificaFalhaDePagina() {
        System.out.println("Implementar falha de pagina");
        return false;
    }

    private boolean verificaEmDisco() {
        System.out.println("Implementar verifica em disco");

        return false;
    }

    private boolean verificaAcessoIlegal() {
        System.out.println("Inplementar acesso Ilegal");
        return false;
    }

    /**
     *  0 = sucesso
     *  -1 = falha de pagina;
     * @param posicao
     * @return
     */
    private int acessaPagina(int posicao) {
        ArrayList<Terminal.Simulacao.NoDePaginas> paginas = Terminal.simulacao.listaPaginas;



        return 0;
    }


    public boolean validaEntrada(ArrayList<String> parametros){
        if (parametros.isEmpty()){
            System.err.println("Por favor, informe os parametros.");
            return false;
        }

        if (parametros.size() < 1){
            System.err.println("Informe o pid do processo.");
            return false;
        }else{
            String inputPid = parametros.get(0);
            //Valida numerico

            this.pid = Integer.parseInt(inputPid);
        }
        if(parametros.size() < 2){
            System.err.println("Por favor informe a posição da memoria.");
            return false;
        }else {
            String inputPosicao = parametros.get(1);

            //VerificaHexa
            //Valida numerico

            this.posicao = ma.pegaHexa(inputPosicao);
            return true;
        }
    }
}
