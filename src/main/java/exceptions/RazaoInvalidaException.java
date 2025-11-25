package exceptions;

public class RazaoInvalidaException extends Exception{
    public RazaoInvalidaException(){
        super("Razão Social Invalida");
    }
}