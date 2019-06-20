package br.com.bruno.sth.response;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Classe que representa os dados recebidos e enviados pela API.
 *
 * @author Bruno Andrade
 * @email andradedbruno@gmail.com
 */
@ApiModel(value = "TicketHolderResponse", description = "Ticket Holder API Data")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TicketHolderResponse {

    @JsonIgnore
    private String ticketHolderId;

    @NotNull(message = "Nomer é obrigatório!")
    @ApiModelProperty(value = "Nome do usuário", dataType = "string", required = true)
    private String ticketHolderName;

    @NotNull(message = "E-mail é obrigatório!")
    @Email(message = "O e-mail esta com formato inválido")
    @ApiModelProperty(value = "E-mail do usuário", dataType = "string", required = true)
    private String ticketHolderEmail;

    @NotNull(message = "A data de inicio da campanha não pode ser vazia!")
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @ApiModelProperty(value = "Data de nascimento do usuário", dataType = "date", required = true)
    private LocalDate ticketHolderDate;

    @NotNull(message = "O id do time não pode ser vazio!")
    @ApiModelProperty(value = "Time do usuário", dataType = "string", required = true)
    private String teamId;

    public TicketHolderResponse() {
    }

    public TicketHolderResponse(String ticketHolderName, String ticketHolderEmail, LocalDate ticketHolderDate,
                                String teamId) {
        this.ticketHolderName = ticketHolderName;
        this.ticketHolderEmail = ticketHolderEmail;
        this.ticketHolderDate = ticketHolderDate;
        this.teamId = teamId;
    }

    public String getTicketHolderName() {
        return ticketHolderName;
    }

    public void setTicketHolderName(String ticketHolderName) {
        this.ticketHolderName = ticketHolderName;
    }

    public String getTicketHolderEmail() {
        return ticketHolderEmail;
    }

    public void setTicketHolderEmail(String ticketHolderEmail) {
        this.ticketHolderEmail = ticketHolderEmail;
    }

    public LocalDate getTicketHolderDate() {
        return ticketHolderDate;
    }

    public void setTicketHolderDate(LocalDate ticketHolderDate) {
        this.ticketHolderDate = ticketHolderDate;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    @Override
    public String toString() {
        return "TicketHolderResponse{" + "ticketHolderName='" + ticketHolderName + '\'' + ", ticketHolderEmail='"
                + ticketHolderEmail + '\'' + ", ticketHolderDate=" + ticketHolderDate + ", teamId='" + teamId + '\''
                + '}';
    }
}
