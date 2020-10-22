package com.mauricio.olakoa.drinks;

import java.net.URL;

public class Drink {
	private String ownerId;
	private String id;
	private String name;
	private URL thumbnail;
	private String description;
	private int cost;
	private boolean posted;

	private Drink() {
	}

	private Drink(Builder builder) {
		this.ownerId = builder.ownerId;
		this.id = builder.id;
		this.name = builder.name;
		this.thumbnail = builder.thumbnail;
		this.description = builder.description;
		this.cost = builder.cost;
		this.posted = builder.posted;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public URL getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(URL thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCost() {
		return cost;
	}

	public String getCostString() {
		return Integer.toString(cost);
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public boolean isPosted() {
		return posted;
	}

	public String isPostedString() {
		return posted ? "1" : "0";
	}

	public void setPosted(boolean posted) {
		this.posted = posted;
	}

	public static class Builder {
		private String ownerId;
		private String id;
		private String name;
		private URL thumbnail;
		private String description;
		private int cost;
		private boolean posted;

		public Drink build() {
			return new Drink(this);
		}

		public Builder ownerId(String ownerId) {
			this.ownerId = ownerId;
			return this;
		}

		public Builder id(String id) {
			this.id = id;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder thumbnail(URL thumbnail) {
			this.thumbnail = thumbnail;
			return this;
		}

		public Builder description(String description) {
			this.description = description;
			return this;
		}

		public Builder cost(int cost) {
			this.cost = cost;
			return this;
		}

		public Builder posted(boolean posted) {
			this.posted = posted;
			return this;
		}
	}
}