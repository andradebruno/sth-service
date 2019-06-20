package br.com.bruno.sth.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.bruno.sth.response.CampaignResponse;

/**
 * Serviço para busca de campanhas ativas para determinado time, utilizando o
 * campaign-service.
 *
 * @author Bruno Andrade
 * @email andradedbruno@gmail.com
 */
@FeignClient(name = "campaignService", url = "http://localhost:8081")
public interface CampaignService {

	@GetMapping("/v1/campaign/team/{teamId}")
	List<CampaignResponse> findCampaignByTeam(@PathVariable("teamId") String teamId);

}
