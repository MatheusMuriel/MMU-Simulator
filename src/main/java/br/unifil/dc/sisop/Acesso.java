package br.unifil.dc.sisop;

import java.util.ArrayList;
import java.util.Optional;


import static br.unifil.dc.sisop.Terminal.simulacao;

public class Acesso extends Comandos {
    int pid;
    int posicao;
    Processo processo;


    Acesso(){

    }

    public boolean processaAceso (ArrayList<String> parametros){
        //PID, Posição da memoria
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
            String enderecamento = ma.pegaEnderecamento(this.posicao);
            int nAux = Terminal.simulacao.quantBitsEspacoMemoria;
            String nsAux = Integer.toString(nAux);
            int ultimoDigito = Integer.parseInt(nsAux.substring(nsAux.length()-1));
            int tamReferenciavel = (int) Math.pow(2,ultimoDigito);


            int bitsPosMSB = Terminal.simulacao.quantBitsPosMSB;

            int[] bitsPagina = ma.pegaBitsPagina(enderecamento, bitsPosMSB);



            System.out.println("Tamanho referenciavel é: " + tamReferenciavel);


            verificaFalhaDePagina();
        }


        return false;
    }

    private boolean verificaFalhaDePagina() {
        //int processo = pid;
        int posicaoDaMemoria  = posicao;

        ArrayList<Terminal.Simulacao.NoDePagina> listaPaginas;

        listaPaginas = this.processo.listaPaginas;

        for (Terminal.Simulacao.NoDePagina n: listaPaginas) {
            int in = n.getInicio();
            int fi = n.getFim();
            if (posicaoDaMemoria >= in && posicaoDaMemoria <= fi){
                //Achou a pagina

                if (n.isMapeado()){
                    //OK
                    System.out.println("Pagina mapeada");
                }else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("O processo ");
                    sb.append(pid);
                    sb.append(" tentou acessar a pagina ");
                    sb.append(n.getNumero());
                    sb.append(" e isso causou uma falha de pagina");

                    if (posicionaPagina(n)){
                        sb.append(", agora essa pagina foi mapeada para o quadro ");
                        sb.append(n.getQuandro().getNumero());
                        System.out.println(sb);
                        return true;
                    } else {
                        sb.append(" e não foi encontrado nenhum quadro livre.");
                        System.err.println(sb);
                        return false;
                    }
                }

            }

        }
        System.err.println("Pagina não encontrada.");
        return false;
    }

    private boolean posicionaPagina(Terminal.Simulacao.NoDePagina nP) {

        for (Terminal.Simulacao.NoDeQuadro nQ:simulacao.listaQuadros) {

            if ( !( nQ.isMapeado() ) ) {
                //Quadro livre
                nQ.setMapeado(true, nP);
                nP.setMapeado(true, nQ);
                return true;
            }

        }
        return false;
    }

    private boolean verificaEmDisco() {
        ArrayList<Processo> disco = simulacao.disco;

        for (Processo p:disco) {
            if (p.pid == pid){
                System.err.println("Esse processo esta em disco.");
                return true;
            }
        }
        return false;
    }

    private boolean verificaAcessoIlegal() {
        ArrayList<Processo> listaProcessos = simulacao.listaProcessos;
        int posicaoAVerificar = posicao;

        for (Processo p: listaProcessos) {
            if (p.pid == pid){
                int inicio  = p.localizacao.getInicio();
                int fim     = p.localizacao.getFim();
                if (posicaoAVerificar >= inicio && posicaoAVerificar <= fim){
                    return false;
                }else return true;
            }

        }


        System.out.println("Inplementar acesso Ilegal");
        return false;
    }

    private Optional<Processo> processoExiste(int _pid) {

        for (Processo p:simulacao.listaProcessos) {
            if (p.pid == _pid){
                return Optional.of(p);
            }
        }

        return Optional.empty();
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
            boolean validadeNumerico = ma.isValidadeDacimal(inputPid);

            if (validadeNumerico){
                pid = ma.pegaNumero(inputPid);

                for (Processo p:simulacao.listaProcessos) {
                    if (p.pid == pid){
                        processo = p;
                        return true;
                    }
                }
                System.err.println("Processo não encontrado.");
                return false;

            }else{
                System.err.println("Digite um numero PID valido.");
            }
        }
        if(parametros.size() < 2){
            System.err.println("Por favor informe a posição da memoria.");
            return false;
        }else {
            String inputPosicao = parametros.get(1);

            int baseNumerica = ma.verificaBaseNumerica(inputPosicao);
            switch (baseNumerica){
                case 1:
                    posicao = ma.pegaNumero(inputPosicao);
                case 2:
                    posicao = ma.pegaHexa(inputPosicao);
                case -1:
                    System.err.println("Digite uma posicao de memoria valida");
            }
            return true;
        }
    }
}
