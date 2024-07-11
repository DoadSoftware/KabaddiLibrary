package com.kabaddi.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name = "Bugs")
public class Bugs {
	@Id
	  @Column(name = "BugId")
	  private int bugId;

	  @Column(name = "PROMPT")
	  private String prompt;

	  @Column(name = "TEXT1")
	  private String text1;
	  
	  @Column(name = "TEXT2")
	  private String text2;
	  
	  @Column(name = "TEXT3")
	  private String text3;
	  
	  @Column(name = "TEXT4")
	  private String text4;
	  
	  @Column(name = "TEXT5")
	  private String text5;

	  public Bugs() {
			super();
	  }

	  public Bugs(int bugId) {
		super();
		this.bugId = bugId;
	  }

	public int getBugId() {
		return bugId;
	}

	public void setBugId(int bugId) {
		this.bugId = bugId;
	}

	public String getPrompt() {
		return prompt;
	}

	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}

	public String getText1() {
		return text1;
	}

	public void setText1(String text1) {
		this.text1 = text1;
	}

	public String getText2() {
		return text2;
	}

	public void setText2(String text2) {
		this.text2 = text2;
	}

	public String getText3() {
		return text3;
	}

	public void setText3(String text3) {
		this.text3 = text3;
	}

	public String getText4() {
		return text4;
	}

	public void setText4(String text4) {
		this.text4 = text4;
	}

	public String getText5() {
		return text5;
	}

	public void setText5(String text5) {
		this.text5 = text5;
	}
	
}
