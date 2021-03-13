package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "participants")
@NamedQueries({
	@NamedQuery(
		name = "getAllParticipants",
		query = "SELECT p FROM Participant AS p ORDER BY p.participant_id DESC"
	),
	@NamedQuery(
			name = "getParticipantsCount",
			query = "SELECT COUNT(p) FROM Participant AS p"
	),
	@NamedQuery(
			name = "checkRegisteredName",
			query = "SELECT COUNT(p) FROM Participant AS p WHERE p.name = :name"
	),
	@NamedQuery(
		name = "checkLoginNameAndPassword",
		query = "SELECT p FROM Participant AS p WHERE p.name = :name AND p.password = :pass"
	),
})
@Entity
public class Participant {
	@Id
    @Column(name = "participant_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer participant_id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "mail", nullable = false)
    private String mail;

    @Column(name = "password", length = 64, nullable = false)
    private String password;

	public Integer getParticipant_id() {
		return participant_id;
	}

	public void setParticipant_id(Integer participant_id) {
		this.participant_id = participant_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


}
