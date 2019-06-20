package br.com.bruno.sth.exception;

/**
 * Exception que será lançada ao tentar cadastrar dois usuários com o mesmo
 * e-mail.
 *
 * @author Bruno Andrade
 * @email andradedbruno@gmail.com
 */
public class TicketHolderException extends RuntimeException {

	public TicketHolderException() {
		super("E-mail already registred");
	}
}
