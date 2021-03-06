package br.unifil.dc.sisop;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author Matheus Muriel
 *
 * Um comando de moria precisa ter alguns requisitos:
 *
 * 1. Quantidade de bits do espaço de memória (Variavel quantBitsEspacoMemoria)
 *
 *
 * 2. Quantidade de memória física instalada EM PALAVRAS (Varievel quantMemoriaFisicaInstalada)
 *      *A entrada vai ser dada em HexaDecimal ou em int, porem armazenada internamente como um Integer
 *
 *
 *
 * 3. Qual é o tipo dessa memoria (Variavel "tipoDeMemoria")
 *      *Usando um Enum (Tipo Comandos) pra padronizar o nome interno e não ter que ficar comparando Strings.
 *
 *      3.1. Case seja Registradores base e limite, qual tipo de algorito de swapping
 *              *Isso pode ser:
 *                  bitmap
 *                  lista encadeada
 *
 *
 * Deve ter um metodo que verifica a validade dos parametros,
 *
 */
public class Configurar extends Comandos{
    TiposComandos   tipoDeMemoria;
    int             quantBitsEspacoMemoria;
    Integer         quantMemoriaFisicaInstalada;
    int             tamPedacoAlocacaoMemoria;
    int             quantBitsPosMSB;

    public Configurar(){

    }

    public boolean verificaComando(ArrayList<String> parametros){

        //Verificação de vazios
        if (parametros.isEmpty()) {
            System.err.println("Sem parametros");
            return false;
        }

        if ( !( verificaEntradaQBEM(parametros) ) ) return false;
        Terminal.simulacao.quantBitsEspacoMemoria = quantBitsEspacoMemoria;

        if ( !( verificaEntradaQMFI(parametros) ) ) return false;
        Terminal.simulacao.memoriaInstalada = quantMemoriaFisicaInstalada;

        if ( !( verificaEntradaTM(parametros) ) )   return false;
        Terminal.simulacao.tipoSimulacao = tipoDeMemoria;

        if ( !( verificaLogica(parametros) ) )   return false;

        if ( !( criaMemoria() )) return false;



        return false;
    }

    private boolean verificaLogica(ArrayList<String> parametros) {
        //Ver com o Manhani
        return true;
    }


    private boolean verificaEntradaQBEM(ArrayList<String> parametros){

        if (parametros.get(0).isEmpty()){
            //Não existe um primeiro parametro
            System.err.println("Não existe um parametro de Quantidade de bits de espaço de memoria.");
            return false;
        }else {
            String bitsEM = parametros.get(0);
            //Verifica a validade do dado
            boolean validadeNumero = bitsEM.matches("[0-9]+");

            if (validadeNumero){
                //System.out.println("Regex aceito");
                quantBitsEspacoMemoria = Integer.parseInt(bitsEM);

                System.out.println("Quantidade de bits do espaço de memoria ficou: " + quantBitsEspacoMemoria);
                return true;
            }else{
                System.out.println("Parametro de Quantidade de bits do espaço de memoria invalido");
                return false;
            }
        }

    }

    private boolean verificaEntradaQMFI(ArrayList<String> parametros) {

        //if (parametros.size() < 2 && parametros.get(1).isEmpty()){
        if (parametros.size() < 2){
            System.err.println("Não existe um parametro de Quantidade de memoria fisica instalada.");
            return false;
        } else {
            String quantMFI = parametros.get(1);

            boolean validadeDacimal = quantMFI.matches("^[0-9]+$");
            boolean validadeHexa    = quantMFI.matches("^0[xX][0-9A-Fa-f]+$|^[0-9A-Fa-f]+$");

            if (validadeDacimal){
                //É decimal
                quantMemoriaFisicaInstalada = Integer.parseInt(quantMFI);
                System.out.println("A memoria fisica instalada é " + quantMemoriaFisicaInstalada);
                return true;
            } else if(validadeHexa){
                //É hexa (Diferente do brasil)
                //quantMemoriaFisicaInstalada = converteHex(quantMFI);
                quantMemoriaFisicaInstalada = ma.pegaHexa(quantMFI);
                System.out.println("A memoria fisica instalada é " + quantMemoriaFisicaInstalada);
                return true;
            } else {
                //Não é nenhuma das validades acima
                System.err.println("Parametro de Quantidade de memoria fisica instalada invalido.");
                return false;
            }

        }

    }

    private boolean verificaEntradaTM(ArrayList<String> parametros) {
        if (parametros.size() < 3){
            System.err.println("Não existe um parametro de Tipo de MMU.");
            return false;
        } else {
            String tipoMMU = parametros.get(2);
            tipoMMU = tipoMMU.toUpperCase();

            switch (tipoMMU){
                case ("MEMORIA-VIRTUAL"):
                    if (parametros.size() < 4){
                        System.err.println("Especifique a quantidade de bits reservados para indexar páginas de um processo.");
                        return false;
                    }
                    String qbMSB = parametros.get(3);
                    if (ma.isValidadeDacimal(qbMSB)) {
                        quantBitsPosMSB = Integer.parseInt(qbMSB);
                        Terminal.simulacao.quantBitsPosMSB = this.quantBitsPosMSB;
                        tipoDeMemoria = TiposComandos.MemoriaVirtual;
                        return true;
                    } else {
                        System.err.println("Quantidade de bits pos MSB invalida.");
                        return false;
                    }

                case ("REGISTRADORES-BASE-LIMITE"):
                    if (parametros.size() < 4){
                        System.err.println("Especifique o tipo de Algoritmo de Swapping.");
                        return false;
                    }
                    String algoritmoSwapping = parametros.get(3);
                    switch (algoritmoSwapping){
                        case ("BITMAP"):
                            if (parametros.size() < 5){
                                System.err.println("Especifique o tamanho de cada pedaço de alocação de memória");
                                return false;
                            }
                            String tamAM = parametros.get(4);
                            if (ma.isValidadeDacimal(tamAM)){
                                tamPedacoAlocacaoMemoria = Integer.parseInt(tamAM);
                                tipoDeMemoria = TiposComandos.RegistradoresBitmap;
                                return true;
                            }else {
                                System.err.println("Tamanho do pedaço de alocação de memoria invalido.");
                                return false;
                            }
                        case ("LISTA-ENCADEADA"):
                            tipoDeMemoria = TiposComandos.RegistradoresLista;
                            return true;
                        default:
                            System.err.println("Tipo de algoritimo de swapping invalido.");
                            return false;
                    }
                default:
                    System.err.println("Especifique o tipo de MMU");
                    return false;
            }

        }
    }

    private boolean criaMemoria() {

        if (tipoDeMemoria.equals(TiposComandos.RegistradoresBitmap)){
            Terminal.simulacao.blocoBitmap = tamPedacoAlocacaoMemoria;
            int granulacao = Terminal.simulacao.blocoBitmap;

            int tamLista = quantMemoriaFisicaInstalada / granulacao;

            Terminal.simulacao.bitmap = new int[tamLista];
            return true;
        }

        if (tipoDeMemoria.equals(TiposComandos.RegistradoresLista)){
            Terminal.simulacao.listaEncadeada = new LinkedList<>();
            return true;
        }

        if (tipoDeMemoria.equals(TiposComandos.MemoriaVirtual)){
            Terminal.simulacao.listaQuadros = new ArrayList<>();

            //????

            return false;
        }

        System.out.println("CriaMemoria não achou o tipo.");
        return false;
    }

}
