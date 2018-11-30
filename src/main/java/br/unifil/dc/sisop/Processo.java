package br.unifil.dc.sisop;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Optional;

public class Processo extends Comandos {
    int pid;
    int tamMemoriaProcesso;
    TiposComandos simulacaoAtual;

    public Processo(){

        simulacaoAtual = Terminal.Simulacao.getTipoSimulacao();
    }

    public boolean verificaComando(ArrayList<String> parametros) {
        if (parametros.size() < 1){
            System.err.println("Especifique o numero pid do novo processo.");
            return false;
        }else {
            this.pid = Integer.parseInt(parametros.get(0));
        }

        if ( !( avaliaSimulacao(parametros) )) return false;

        //Pasando em todas as verificações ele faz o algoritmo
        fristFit(pid, tamMemoriaProcesso);


        return true;
    }

    private boolean avaliaSimulacao(ArrayList<String> parametros) {
        //Swapping
        if ((simulacaoAtual.equals(TiposComandos.RegistradoresBitmap))
                || (simulacaoAtual.equals(TiposComandos.RegistradoresLista))){

            if (parametros.size() < 2){
                System.err.println("Especifique o tamanho da memoria do processo.");
                return false;
            }

            String tamanho = parametros.get(1);

            switch (ma.verificaBaseNumerica(tamanho)){
                case 1:
                    System.out.println("Decimal");
                    tamMemoriaProcesso = ma.pegaNumero(tamanho);
                    break;
                case 2:
                    System.out.println("Hexa");
                    tamMemoriaProcesso = ma.pegaHexa(tamanho);
                    break;
                case -1:
                    System.err.println("Digite um numero valido.");
                    return false;
            }

            if (tamMemoriaProcesso > Terminal.Simulacao.getMemoriaInstalada()){
                System.err.println("O tamanho do processo não pode ser maior que a memoria instalada.");
                return false;
            }

        }


        return true;
    }

    private boolean fristFit(int pid, int tamMemoriaProcesso) {
        if (simulacaoAtual.equals(TiposComandos.RegistradoresBitmap)){
            int[] listaBit = Terminal.simulacao.getBitmap();

            verificaEspacoBitmap();
            //se chegou aqui é por não encontrou nenhum espaço do tamanho necessario
            swapping();

        }else if (simulacaoAtual.equals(TiposComandos.RegistradoresLista)){
            //Percore a lista
            LinkedList<Terminal.Simulacao.No> lista = Terminal.simulacao.getListaEncadeada();

            //Metodo recursivo que percore a lista ate encontrar


        } else {
            //Faz nada, pois é uma memoria virtual
        }

        return false;
    }

    private Optional<EspacoBitmap> verificaEspacoBitmap(int[] listaBit, int espacoBuscado) {

        int contadorDeEspaco = 0;
        int inicioContador = 0;
        boolean estaContando = false;
        for (int i=0; i < listaBit.length; i++){


            if (listaBit[i] == 0){
                if (estaContando == false) {
                    inicioContador = i;
                    estaContando = true;
                }
                contadorDeEspaco++;
            }

            if (contadorDeEspaco >= espacoBuscado){
                //encaixa aqui
                pincelCorredor(inicioContador, i, listaBit, 1);
                break;
            }


            if (estaContando == true && listaBit[i] == 1){
                //parar contagem
                estaContando = false;
                contadorDeEspaco = 0;
            }
        }

    }

    private void swappingBitMap(int tamBuscado, int[] bitmap) {

        //
        int tail = 0; //index
        int limite = bitmap.length;

        int resultadoSwapping = recSwappinBitmap(tamBuscado, bitmap, tail, limite);
        if (resultadoSwapping == 1); //Deu certo
        if (resultadoSwapping == 2); //Nao encontrou espaço
        if (resultadoSwapping == 1); //deu certo


    }

    /**
     * Metodo Tail recursivo
     * @param tamBuscado
     * @param bitmap
     * @param tail
     * @param limite
     * @return
     */
    private int recSwappinBitmap(int tamBuscado, int[] bitmap, int tail, int limite) {

        if ()
        if (tail == limite) return 2;


    }


    /**
     * Metodo que prenche um array com um determinado valor de um ponto até outro
     * @param inicio
     * @param fim
     * @param listaBit
     * @param valor
     */
    private void pincelCorredor(int inicio, int fim, int[] listaBit, int valor) {

        for (int i = inicio; i <= fim; i++ ){
            listaBit[i] = valor;
        }

    }


    private class EspacoBitmap {
        boolean ocupado;
        int inicio;
        int fim;

        public EspacoBitmap(boolean ocupado, int inicio, int fim) {
            this.ocupado = ocupado;
            this.inicio = inicio;
            this.fim = fim;
        }

        public boolean isOcupado() {
            return ocupado;
        }

        public void setOcupado(boolean ocupado) {
            this.ocupado = ocupado;
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

