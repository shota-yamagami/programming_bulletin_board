package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "replys")
@NamedQueries({
    @NamedQuery(
        name = "getAllReplys",
        query = "SELECT r FROM Reply AS r ORDER BY r.reply_id DESC"
    ),
    @NamedQuery(
        name = "getReplysCount",
        query = "SELECT COUNT(r) FROM Reply AS r"
    ),
})
@Entity
public class Reply {
    @Id
    @Column(name = "reply_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reply_id;

    @ManyToOne
    @JoinColumn(name = "participant_id", nullable = false)
    private Participant participant_id;

    @ManyToOne
    @JoinColumn(name = "tweet_id", nullable = false)
    private Tweet tweet_id;

    @Lob
    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "created_at", nullable = false)
    private Timestamp created_at;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updated_at;

	public Integer getReply_id() {
		return reply_id;
	}

	public void setReply_id(Integer reply_id) {
		this.reply_id = reply_id;
	}

	public Participant getParticipant_id() {
		return participant_id;
	}

	public void setParticipant_id(Participant participant_id) {
		this.participant_id = participant_id;
	}

	public Tweet getTweet_id() {
		return tweet_id;
	}

	public void setTweet_id(Tweet tweet_id) {
		this.tweet_id = tweet_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public Timestamp getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}


}
