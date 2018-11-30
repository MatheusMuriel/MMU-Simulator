package br.unifil.dc.sisop;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Optional;

public class Processo extends Comandos {
    int pid;
    int tamMemoriaProcesso;
    TiposComandos simulacaoAtual;
    EspacoBitmap localizacao;
    public ArrayList<Terminal.Simulacao.NoDePagina> listaPaginas;

    public Processo(){

        simulacaoAtual = Terminal.simulacao.getTipoSimulacao();
        listaPaginas = new ArrayList<>();
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
        fristFit(pid, tamMemoriaProcesso / 4);


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

            if (tamMemoriaProcesso > Terminal.simulacao.getMemoriaInstalada()){
                System.err.println("Processo: " + tamMemoriaProcesso);
                System.err.println("Memoria instalada: " + Terminal.simulacao.getMemoriaInstalada());
                System.err.println("O tamanho do processo não pode ser maior que a memoria instalada.");
                return false;
            }

        }


        return true;
    }

    private boolean fristFit(int pid, int tamMemoriaProcesso) {
        if (simulacaoAtual.equals(TiposComandos.RegistradoresBitmap)){
            int[] listaBit = Terminal.simulacao.getBitmap();

            Optional<EspacoBitmap> espacoLivre = verificaEspacoBitmap(listaBit, 0, tamMemoriaProcesso);
            if (espacoLivre.isPresent()){
                //Existe um espaço então pinte ele de 1
                EspacoBitmap espacoAPintar = espacoLivre.get();
                pincelCorredor(espacoAPintar, listaBit, 1);
            }else{
                //Faz swapping
            }


        }else if (simulacaoAtual.equals(TiposComandos.RegistradoresLista)){
            //Percore a lista
            LinkedList<Terminal.Simulacao.No> lista = Terminal.simulacao.getListaEncadeada();

            //Metodo recursivo que percore a lista ate encontrar


        } else {
            //Faz nada, pois é uma memoria virtual
        }

        return false;
    }

    /**
     * VERIFICANDO ESPAÇOS LIVRES apartir de um determinado ponto
     * @param listaBit
     * @param espacoBuscado
     * @return
     */
    private Optional<EspacoBitmap> verificaEspacoBitmap(int[] listaBit, int inicio, int espacoBuscado) {

        int contadorDeEspaco = 0;
        int inicioContador = 0;
        boolean estaContando = false;
        for (int i=inicio; i < listaBit.length; i++){


            if (listaBit[i] == 0){
                if (estaContando == false) {
                    inicioContador = i;
                    estaContando = true;
                }
                contadorDeEspaco++;
            }

            if (contadorDeEspaco >= espacoBuscado){
                //ENCONTROU LIVRE
                EspacoBitmap espacoEncontrado = new EspacoBitmap(false,inicioContador,i);
                return Optional.of(espacoEncontrado);
            }


            if (estaContando == true && listaBit[i] == 1){
                //parar contagem
                estaContando = false;
                contadorDeEspaco = 0;
            }
        }
        return Optional.empty();
    }

    private Optional<EspacoBitmap> ContaBloco(int[] listaBit, int inicio, int valorBuscado) {
        int valorContrario = (valorBuscado == 0) ? 1 : 0;

        int contadorDeEspaco = 0;
        int inicioContador = 0;
        boolean estaContando = false;

        for (int i=inicio; i < listaBit.length; i++){
            if (listaBit[i] == valorContrario){
                boolean ocupado = (valorBuscado == 1) ? false : true;
                EspacoBitmap aux = new EspacoBitmap(ocupado,inicio,i);
                return Optional.of(aux);
            }
        }
        return Optional.empty();
    }


    private void swappingBitMap(int tamBuscado, int[] bitmap) {

        //
        int tail = 0; //index
        int limite = bitmap.length;
        ArrayList<EspacoBitmap> memoriaSecundaria = new ArrayList<>();

        Optional<EspacoBitmap> resultadoSwapping = recSwappinBitmap(tamBuscado, bitmap, tail, limite, memoriaSecundaria);


    }

    /**
     * Metodo Tail recursivo
     * @param tamBuscado
     * @param bitmap
     * @param tail
     * @param limite
     * @param memoriaSecundaria
     * @return
     */
    private Optional<EspacoBitmap> recSwappinBitmap(int tamBuscado, int[] bitmap, int tail, int limite, ArrayList<EspacoBitmap> memoriaSecundaria) {

        //Olha se tem espaço necessario vago
        Optional<EspacoBitmap> olheiro = verificaEspacoBitmap(bitmap, tail, tamBuscado);
        if (olheiro.isPresent()){
            //tem tamanho
            return olheiro;
        }

        if (tail == limite ) return Optional.empty(); //Fim da recursão



        if (bitmap[tail] == 1){
            //pega o tamanho do bloco ocupado
            Optional<EspacoBitmap> blocoCheio = ContaBloco(bitmap,tail,1);
            if (blocoCheio.isPresent()){
                memoriaSecundaria.add(blocoCheio.get());
                pincelCorredor(blocoCheio.get(),bitmap,0);

                Optional<EspacoBitmap> novoEspaco = verificaEspacoBitmap(bitmap,0,blocoCheio.get().getTamanho());
                if (novoEspaco.isPresent()){
                    pincelCorredor(novoEspaco.get(),bitmap,1);
                    memoriaSecundaria.remove(blocoCheio.get());
                    tail += novoEspaco.get().getFim();
                }else {
                    //Não tem expaço pra encaixar esse processo
                    pincelCorredor(blocoCheio.get(),bitmap,1);
                    memoriaSecundaria.remove(blocoCheio.get());
                    tail += blocoCheio.get().getTamanho();
                }
            } else System.out.println("Não foi encontrado o fim desse pedaço de memoria.");
        }
        tail ++;
        return recSwappinBitmap(tamBuscado,bitmap, tail,limite,memoriaSecundaria);
    }


    /**
     * Metodo que prenche um array com um determinado valor de um ponto até outro
     * @param espaco TAD contendo inicio e fim de aonde deve ser pintado
     * @param listaBit
     * @param valor
     */
    public void pincelCorredor(EspacoBitmap espaco, int[] listaBit, int valor) {
        int inicio  = espaco.getInicio();
        int fim     = espaco.getFim();

        for (int i = inicio; i <= fim; i++ ){
            listaBit[i] = valor;
        }

    }


    protected static class EspacoBitmap {
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

        public int getTamanho(){
            return (fim - inicio);
        }

    }
}

