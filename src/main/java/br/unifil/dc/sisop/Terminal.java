package br.unifil.dc.sisop;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import br.unifil.dc.sisop.MetodosAuxiliares.*;

class Terminal {
    MetodosAuxiliares ma = new MetodosAuxiliares();


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

        nome = ma.primeiraPalavra(entrada);
        entrada = ma.removePrimeiraPalavra(entrada);
        //entrada = ma.removePrimeiraPalavra(entrada);
        //entrada = entrada.substring(entrada.indexOf(" ") + 1);

        //Pega os parametros
        while(entrada.length() > 0){

            String aux = ma.primeiraPalavra(entrada);
            entrada = ma.removePrimeiraPalavra(entrada);
            parametros.add(aux);
        }

        comando = new Comando(nome, parametros);

        processaComando(comando);

    }

    protected int processaComando(Comando cmd){
        Comandos comandos = new Comandos();

         //Os ifs abaixo estão verificando NEGAÇÃO ("!expreção") por isso retornam -1
        if (!comandos.verificaExistencia(cmd)) return -1;
        if (!comandos.verificaImplementacao(cmd)) return -1;
        if (!comandos.verificaParametros(cmd)) return -1;

        return 0;
    }

}
