package br.com.bruno.sth.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.bruno.sth.domain.TicketHolder;

/**
 * Interface utilizada para CRUD no MongoDB.
 *
 * @author Bruno Andrade
 * @email andradedbruno@gmail.com
 */
@Repository
public interface TicketHolderRepository extends MongoRepository<TicketHolder, String> {

	/**
	 * Método de busca do usuário através do e-mail.
	 *
	 * @param ticketHolderEmail
	 * @return
	 */
	Optional<TicketHolder> findByTicketHolderEmailIgnoreCase(String ticketHolderEmail);
}
