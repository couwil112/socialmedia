package com.cooksys.socialmedia.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("repost")
public class RepostTweet extends Tweet {
	
	private String repostOf;

	public RepostTweet() {
		super();
	}

	public String getRepostOf() {
		return repostOf;
	}

	public void setRepostOf(String repostOf) {
		this.repostOf = repostOf;
	}
}
