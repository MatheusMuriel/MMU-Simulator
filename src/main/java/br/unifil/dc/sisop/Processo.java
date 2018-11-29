package br.unifil.dc.sisop;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;

public class Processo extends Comandos {
    int pid;
    int tamMemoria;

    public Processo(){
    }

    public boolean verificaComando(ArrayList<String> parametros) {
        if (parametros.size() < 1){
            System.err.println("Especifique o numero pid do novo processo.");
            return false;
        }else {
            this.pid = Integer.parseInt(parametros.get(0));
        }

        if ( !( avaliaSimulacao(parametros) )) return false;


        return true;
    }

    private boolean avaliaSimulacao(ArrayList<String> parametros) {
        TiposComandos simulacaoAtual = Terminal.Simulacao.getTipoSimulacao();
        if ((simulacaoAtual.equals(TiposComandos.RegistradoresBitmap)) || (simulacaoAtual.equals(TiposComandos.RegistradoresLista))){
            if (parametros.size() < 2){
                System.err.println("Especifique o tamanho da memoria do processo.");
                return false;
            }
            String tamanho = parametros.get(1);
            tamMemoria = Integer.parseInt(tamanho);

            if (tamMemoria > Terminal.Simulacao.getMemoriaInstalada()){
                System.err.println("O tamanho do processo não pode ser maior que a memoria instalada.");
                return false;
            }

            //FirtFit
            fristFit(pid, tamMemoria);

        }
        return true;
    }

    private void fristFit(int pid, int tamMemoria) {



    }


}
