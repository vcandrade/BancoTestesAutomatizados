package service;

public class ContaInexistenteException extends Exception {

	public ContaInexistenteException(String mensagem) {
		
		super(mensagem);
	}
}
