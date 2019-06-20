package br.com.bruno.sth.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import br.com.bruno.sth.domain.TicketHolder;
import br.com.bruno.sth.response.CampaignResponse;
import br.com.bruno.sth.response.TicketHolderResponse;
import br.com.bruno.sth.service.CampaignService;
import br.com.bruno.sth.service.TicketHolderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Controller para expor os métodos da aplicação.
 *
 * @author Bruno Andrade
 * @email andradedbruno@gmail.com
 */
@RestController
@RequestMapping("/v1/ticketholder")
@Api(value = "Season Ticket Holder", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class TicketHolderController {

	private static final Logger log = LoggerFactory.getLogger(TicketHolderController.class);

	@Autowired
	private TicketHolderService ticketHolderService;

	@Autowired
	private CampaignService campaignService;

	/**
	 * Controller para receber a requisição de criação do usuário.
	 *
	 * Verifica se o serviço campaign-service está up, caso esteja cadastra. Caso
	 * não esteja, retorna para o fallback createTicketHolderFallback.
	 *
	 * @param ticketHolderRequest
	 * @return
	 */
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@HystrixCommand(fallbackMethod = "createTicketHolderFallback", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "30000") })
	@ApiOperation(value = "Cria um novo usuário", response = ResponseEntity.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created"),
			@ApiResponse(code = 400, message = "Conflict"), @ApiResponse(code = 409, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public ResponseEntity<List<CampaignResponse>> createTicketHolder(
			@Valid @RequestBody TicketHolderResponse ticketHolderRequest) {

		TicketHolder ticketHolder;
		final List<CampaignResponse> campaignResponseList = campaignService
				.findCampaignByTeam(ticketHolderRequest.getTeamId());

		if (campaignResponseList.isEmpty()) {
			ticketHolder = ticketHolderService.createTicketHolder(ticketHolderRequest.getTicketHolderName(),
					ticketHolderRequest.getTicketHolderEmail(), ticketHolderRequest.getTicketHolderDate(),
					ticketHolderRequest.getTeamId(), false);
		} else {
			ticketHolder = ticketHolderService.createTicketHolder(ticketHolderRequest.getTicketHolderName(),
					ticketHolderRequest.getTicketHolderEmail(), ticketHolderRequest.getTicketHolderDate(),
					ticketHolderRequest.getTeamId(), true);
		}

		log.info("Usuário {} cadastrado com sucesso!", ticketHolder);
		return new ResponseEntity<>(campaignResponseList, HttpStatus.CREATED);

	}

	/**
	 * Método de fallback, caso o serviço campaign-service esteja offline.
	 *
	 * @param ticketHolderRequest
	 * @return
	 */
	private ResponseEntity<List<CampaignResponse>> createTicketHolderFallback(
			@Valid TicketHolderResponse ticketHolderRequest) {

		TicketHolder ticketHolder = ticketHolderService.createTicketHolder(ticketHolderRequest.getTicketHolderName(),
				ticketHolderRequest.getTicketHolderEmail(), ticketHolderRequest.getTicketHolderDate(),
				ticketHolderRequest.getTeamId(), false);

		log.info("Usuário {} cadastrado com sucesso! Não foi possível se conectar ao serviço de campanhas!",
				ticketHolder);

		return new ResponseEntity<>(HttpStatus.CREATED);

	}
}
