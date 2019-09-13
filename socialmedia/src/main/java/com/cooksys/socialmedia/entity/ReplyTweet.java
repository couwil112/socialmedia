package com.cooksys.socialmedia.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("reply")
public class ReplyTweet extends Tweet {

	private String content;	
	private String inReplyTo;
	
	public ReplyTweet() {
		super();
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getInReplyTo() {
		return inReplyTo;
	}
	
	public void setInReplyTo(String inReplyTo) {
		this.inReplyTo = inReplyTo;
	}
}
