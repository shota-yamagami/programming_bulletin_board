package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "likes")
@NamedQueries({
	@NamedQuery(
	        name = "getLikeTweet",
	        query = "SELECT l FROM Like AS l WHERE l.tweet_id = :tweet_id AND l.participant_id = :participant_id ORDER BY l.like_id DESC"
	    ),
	@NamedQuery(
			name = "getLikeTweetCount",
			query = "SELECT COUNT(l) FROM Like AS l WHERE l.tweet_id = :tweet_id AND l.participant_id = :participant_id"
		),
	@NamedQuery(
	        name = "getAllTweet",
	        query = "SELECT l FROM Like AS l WHERE l.tweet_id = :tweet_id ORDER BY l.like_id DESC"
	    ),
	@NamedQuery(
			name = "getAllLikeTweetsCount",
			query = "SELECT COUNT(l) FROM Like AS l WHERE l.tweet_id = :tweet_id"
		),
})
@Entity
public class Like {
	@Id
    @Column(name = "like_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer like_id;

	@ManyToOne
    @JoinColumn(name = "participant_id", nullable = false)
    private Participant participant_id;

	@ManyToOne
	@JoinColumn(name = "tweet_id", nullable = false)
	private Tweet tweet_id;

	@Column(name = "created_at", nullable = false)
    private Timestamp created_at;

	public Integer getLike_id() {
		return like_id;
	}

	public void setLike_id(Integer like_id) {
		this.like_id = like_id;
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

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}


}
