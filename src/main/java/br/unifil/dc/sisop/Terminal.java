package br.unifil.dc.sisop;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Terminal {


    Terminal() {
        Scanner teclado = new Scanner(System.in);
        System.out.print("> ");


        String entrada = teclado.nextLine();
        System.out.println(entrada);
        processaEntrada(entrada);
    }

    private void processaEntrada(String entrada){
        Comando comando;
        String nome;
        ArrayList<String> parametros = new ArrayList<>();

        /* Todo ( GLO )*/
        //entrada = entrada.substring(entrada.indexOf(" ")+1); //Remove se tiver espaços antes do nome do comando

        nome = entrada.substring(0, entrada.indexOf(" ")); //pega o nome do comando

        //Pega os parametros
        while(entrada.length() > 0){
            int indexEspaco = entrada.indexOf(" ");
            String aux = entrada.substring(0, indexEspaco); //Pegando a primeira palavra inteira
            entrada = entrada.substring(indexEspaco+1); // remove a palavra pega da String
            parametros.add(aux);
        }


        comando = new Comando(nome, parametros);




    }

}
