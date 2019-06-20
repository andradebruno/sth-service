package br.com.bruno.sth.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.bruno.sth.exception.TicketHolderException;
import br.com.bruno.sth.repository.TicketHolderRepository;
import br.com.bruno.sth.response.CampaignResponse;
import br.com.bruno.sth.response.TicketHolderResponse;
import br.com.bruno.sth.service.CampaignService;
import br.com.bruno.sth.utils.Generator;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TicketHolderControllerTest {

	@MockBean
	CampaignService campaignService;

	@Autowired
	private TicketHolderController ticketHolderController;

	@Autowired
	private TicketHolderRepository ticketHolderRepository;

	@Before
	public void setUp() {
		ticketHolderRepository.deleteAll();

	}

	/**
	 * Teste de criação do usuário, mockando o campaign-service como online.
	 */
	@Test
	public void testToCreateSeasonTicketHolder() {
		TicketHolderResponse ticketHolderRequest = new TicketHolderResponse("Bruno", "andradedbruno@gmail.com",
				LocalDate.of(2019, 06, 19), "Team-01");
		when(campaignService.findCampaignByTeam("Team-01")).thenReturn(Generator.createCampaignRequest());
		ResponseEntity<List<CampaignResponse>> responseEntity = ticketHolderController
				.createTicketHolder(ticketHolderRequest);
		assertThat(responseEntity).isNotNull();
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertThat(responseEntity.getBody()).hasSize(1);
	}

	/**
	 * Teste de criação do usuário, mockando o campaign-service como offline.
	 */
	@Test
	public void testToCreateSeasonTicketHolderFallback() {
		TicketHolderResponse ticketHolderRequest = new TicketHolderResponse("Bruno", "andradedbruno@gmail.com",
				LocalDate.of(2019, 06, 19), "Team-01");
		given(campaignService.findCampaignByTeam(anyString())).willThrow(RuntimeException.class);
		ResponseEntity<List<CampaignResponse>> responseEntity = ticketHolderController
				.createTicketHolder(ticketHolderRequest);
		assertThat(responseEntity).isNotNull();
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);

	}

	/**
	 * Teste de criação de dois usuários com o mesmo e-mail.
	 */
	@Test(expected = TicketHolderException.class)
	public void testCreateTwoSeasonTicketHoldersWithSameEmail() {
		Generator.createSeasonTicketHolders().forEach(ticketHolder -> {
			given(campaignService.findCampaignByTeam("Team-01")).willReturn(Generator.createCampaignRequest());
			ticketHolderController.createTicketHolder(ticketHolder);

		});

	}

}
