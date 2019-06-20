package br.com.bruno.sth.service;

import java.time.LocalDate;

import br.com.bruno.sth.domain.TicketHolder;
import br.com.bruno.sth.exception.TicketHolderException;

public interface TicketHolderService {

	TicketHolder createTicketHolder(String ticketHolderName, String ticketHolderEmail, LocalDate ticketHolderDate,
			String teamId, boolean isAssociated) throws TicketHolderException;

}
