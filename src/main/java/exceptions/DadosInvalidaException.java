package exceptions;

public class DadosInvalidaException extends Exception{
    public DadosInvalidaException(){
        super("Cliente ou Veiculos Invalidos");
    }
}