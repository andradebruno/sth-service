package br.com.bruno.sth.domain;

import java.time.LocalDate;
import java.util.Objects;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

/**
 * Entidade utilizada para mapear os dados Java - MongoDB.
 *
 * @author Bruno Andrade
 * @email andradedbruno@gmail.com
 */
@Document
public class TicketHolder {

	@Id
	private String ticketHolderId;

	@Indexed
	@NotNull(message = "Nomer é obrigatório!")
	@Field(value = "ticket_holder_mame")
	private String ticketHolderName;

	@Indexed
	@NotNull(message = "E-mail é obrigatório!")
	@Field(value = "ticket_holder_email")
	@Email(message = "O e-mail esta com formato inválido")
	private String ticketHolderEmail;

	@Indexed
	@NotNull(message = "A data de inicio da campanha não pode ser vazia!")
	@Field("ticket_holder_date")
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate ticketHolderDate;

	@Indexed
	@NotNull(message = "O id do time não pode ser vazio!")
	@Field("team_id")
	private String teamId;

	@Indexed
	@Field("flg_associated")
	private boolean isAssociated;

	public TicketHolder() {
	}

	public TicketHolder(String ticketHolderName, String ticketHolderEmail, LocalDate ticketHolderDate, String teamId,
			boolean isAssociated) {
		this.ticketHolderName = ticketHolderName;
		this.ticketHolderEmail = ticketHolderEmail;
		this.ticketHolderDate = ticketHolderDate;
		this.teamId = teamId;
		this.isAssociated = isAssociated;
	}

	public boolean isAssociated() {
		return isAssociated;
	}

	public void setAssociated(boolean associated) {
		isAssociated = associated;
	}

	public String getTicketHolderId() {
		return ticketHolderId;
	}

	public void setTicketHolderId(String ticketHolderId) {
		this.ticketHolderId = ticketHolderId;
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
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		TicketHolder that = (TicketHolder) o;
		return ticketHolderId.equals(that.ticketHolderId) && ticketHolderName.equals(that.ticketHolderName)
				&& ticketHolderEmail.equals(that.ticketHolderEmail) && ticketHolderDate.equals(that.ticketHolderDate)
				&& teamId.equals(that.teamId) && isAssociated == that.isAssociated;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ticketHolderId, ticketHolderName, ticketHolderEmail, ticketHolderDate, teamId,
				isAssociated);
	}

	@Override
	public String toString() {
		return "TicketHolder{" + "ticketHolderName='" + ticketHolderName + '\'' + ", ticketHolderEmail='"
				+ ticketHolderEmail + '\'' + ", ticketHolderDate=" + ticketHolderDate + ", teamId='" + teamId + '\''
				+ ", isAssociated=" + isAssociated + '}';
	}
}
