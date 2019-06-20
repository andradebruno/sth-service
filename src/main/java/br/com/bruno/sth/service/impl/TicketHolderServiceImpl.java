package br.com.bruno.sth.service.impl;

import java.time.LocalDate;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.com.bruno.sth.domain.TicketHolder;
import br.com.bruno.sth.exception.TicketHolderException;
import br.com.bruno.sth.repository.TicketHolderRepository;
import br.com.bruno.sth.service.TicketHolderService;

/**
 * Serviço utilizado para o CRUD da aplicação.
 *
 * @author Bruno Andrade
 * @email andradedbruno@gmail.com
 */
@Service
@Validated
public class TicketHolderServiceImpl implements TicketHolderService {

	private static final Logger log = LoggerFactory.getLogger(TicketHolderServiceImpl.class);

	@Autowired
	private TicketHolderRepository ticketHolderRepository;

	/**
	 * Método de criação do usuário no sistema.
	 *
	 * Verifica se o usuário não existe ainda no sistema, caso não exista, cadastra.
	 * Caso o usuário exista, porém não tem as campanhas associadas, cadastra. Caso
	 * o usuário exista e já esteja com as campanhas associadas, retorna erro.
	 *
	 * @param ticketHolderName
	 * @param ticketHolderEmail
	 * @param ticketHolderDate
	 * @param teamId
	 * @param isAssociated
	 *
	 * @return
	 *
	 * @throws TicketHolderException
	 */
	@Override
	public TicketHolder createTicketHolder(String ticketHolderName, String ticketHolderEmail,
			LocalDate ticketHolderDate, String teamId, boolean isAssociated) throws TicketHolderException {

		Optional<TicketHolder> optionalTicketHolder = ticketHolderRepository
				.findByTicketHolderEmailIgnoreCase(ticketHolderEmail);

		if (!optionalTicketHolder.isPresent()) {
			TicketHolder ticketHolder = new TicketHolder(ticketHolderName, ticketHolderEmail, ticketHolderDate, teamId,
					isAssociated);
			return ticketHolderRepository.save(ticketHolder);
		} else if (optionalTicketHolder.isPresent() && !optionalTicketHolder.get().isAssociated()) {
			TicketHolder ticketHolder = new TicketHolder(ticketHolderName, ticketHolderEmail, ticketHolderDate, teamId,
					isAssociated);
			ticketHolderRepository.delete(optionalTicketHolder.get());
			return ticketHolderRepository.save(ticketHolder);
		} else {
			log.error("Usuário {} já cadastrado!", optionalTicketHolder);
			throw new TicketHolderException();
		}
	}

}
