package br.unifil.dc.sisop;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        System.out.println("Vamos para o terminal :D");
        Terminal terminal = new Terminal();
        while (true){
            terminal.pegaEntrada();
        }


    }
}
