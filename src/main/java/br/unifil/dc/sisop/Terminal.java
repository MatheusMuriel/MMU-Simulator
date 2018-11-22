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


        nome = entrada.substring(0, entrada.indexOf(" ")); //pega o nome do comando
        entrada = entrada.substring(entrada.indexOf(" ") + 1);

        //Pega os parametros
        while(entrada.length() > 0){

            //Indice do caractere espaço; se não ouver, é o index do ultimo caractere
            int indexEspaco = (entrada.contains(" ")) ? entrada.indexOf(" ") : entrada.length()-1;

            //SubString até o espeço
            String aux = entrada.substring(0, indexEspaco);

            //Remove da string principal a palavra que foi copiada pelo aux
            entrada = entrada.substring(indexEspaco+1);

            parametros.add(aux);
        }

        //comando = new Comando(nome, parametros);

    }

}
