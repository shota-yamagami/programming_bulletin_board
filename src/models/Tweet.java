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

@Table(name = "tweets")
@NamedQueries({
	@NamedQuery(
		name = "getAllTweets",
		query = "SELECT t FROM Tweet AS t ORDER BY t.tweet_id DESC"
	),
	@NamedQuery(
		name = "getTweetsCount",
		query = "SELECT COUNT(t) FROM Tweet AS t"
	),
})
@Entity
public class Tweet {
	@Id
    @Column(name = "tweet_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tweet_id;

	@ManyToOne
    @JoinColumn(name = "participant_id", nullable = false)
    private Participant participant;

	@Column(name = "title", length = 255, nullable = false)
    private String title;

    @Lob
    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "created_at", nullable = false)
    private Timestamp created_at;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updated_at;

    @Column(name = "language_flag", nullable = false)
    private Integer language_flag;

	public Integer getTweet_id() {
		return tweet_id;
	}

	public void setTweet_id(Integer tweet_id) {
		this.tweet_id = tweet_id;
	}

	public Participant getParticipant() {
		return participant;
	}

	public void setParticipant(Participant participant) {
		this.participant = participant;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public Integer getLanguage_flag() {
		return language_flag;
	}

	public void setLanguage_flag(Integer language_flag) {
		this.language_flag = language_flag;
	}


}
