package br.unifil.dc.sisop;

import java.util.Scanner;

public class Terminal {


    public Terminal() {
        Scanner teclado = new Scanner(System.in);
        System.out.print("> ");


        String entrada = teclado.nextLine();
        System.out.println(entrada);

    }
}
