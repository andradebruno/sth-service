package br.com.bruno.sth.utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.bruno.sth.response.CampaignResponse;
import br.com.bruno.sth.response.TicketHolderResponse;

/**
 * Classe auxiliar para gerar campanhas e usuários.
 *
 * @author Bruno Andrade
 * @email andradedbruno@gmail.com
 */
public class Generator {

	public static List<CampaignResponse> createCampaignRequest() {
		List<CampaignResponse> campaignResponseList = new ArrayList<>();
		campaignResponseList
				.add(new CampaignResponse("Campaign 1", "Team-01", LocalDate.now(), LocalDate.now().plusDays(10)));
		return campaignResponseList;

	}

	public static List<TicketHolderResponse> createSeasonTicketHolders() {
		List<TicketHolderResponse> ticketHolderRequestList = new ArrayList<>();
		ticketHolderRequestList.add(
				new TicketHolderResponse("Bruno", "andradedbruno@gmail.com", LocalDate.of(2019, 06, 19), "Team-01"));
		ticketHolderRequestList.add(new TicketHolderResponse("Bruno Andrade", "andradedbruno@gmail.com",
				LocalDate.of(2019, 06, 19), "Team-01"));
		return ticketHolderRequestList;
	}

}
