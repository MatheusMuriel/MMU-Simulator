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
        List<String> parametros = new ArrayList<>();

        entrada = entrada.substring(entrada.indexOf(" ")+1); //Remove se tiver espaços antes do nome do comando
        String aux = entrada.substring(0, entrada.indexOf(" ")); //pega o nome do comando



        //while (entrada.length() > 0){}


    }

}
