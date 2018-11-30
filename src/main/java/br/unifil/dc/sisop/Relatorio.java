package br.unifil.dc.sisop;



public class Relatorio extends Comandos{



    public void tipoSimulacao(){
        System.out.println("O tipo de simulação atual é: " + Terminal.simulacao.getTipoSimulacao());
    }

    public void quantidadeDeMemoria(){
        System.out.println("A simulação tem " + Terminal.simulacao.getMemoriaInstalada() + " de memoria instalada");

        TiposComandos simu = Terminal.simulacao.getTipoSimulacao();
        switch (simu){
            case RegistradoresLista:
            case MemoriaVirtual:
            case RegistradoresBitmap:
                int[] bitmap = Terminal.simulacao.getBitmap();
                int tamBlocosExibidos = Terminal.simulacao.quantBitsEspacoMemoria;

                System.out.println("Cada pedaço de alocação de memória tem tamanho de: " + Terminal.simulacao.blocoBitmap);
                StringBuilder saida = new StringBuilder();
                for (int i=0; i < bitmap.length;){
                    saida.append("~| ");
                    for (int j = i; j < i+tamBlocosExibidos; j++){
                        if (j < bitmap.length) saida.append(" ").append(bitmap[j]);
                        saida.append(" |");
                    }
                    i += tamBlocosExibidos;
                    saida.append("~ \n");
                }
                System.out.println(saida);

            case nenhum:break;
            default:break;
        }

    }

}
