package com.live_the_city;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class Event {
	
	public enum Planner_type {
		Tour_Guide, Host
	};

	private int id;
	private String title;
	private List<String> tags;
	private Planner_type planner;
	private String description;
	private Date date_of_event;
	private String location;
	private float rating;
	private int attends;

	public Event(int id, String title, List<String> tags, Planner_type planner, String description,
			Date date_of_event, String location, float rating, int attends) {
		this.id = id;
		this.title = title;
		this.tags = tags;
		this.planner = planner;
		this.description = description;
		this.date_of_event = date_of_event;
		this.location = location;
		this.rating = rating;
		this.attends = attends;
	}


	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getTags() {
		return this.tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public Planner_type getPlanner() {
		return this.planner;
	}

	public void setPlanner(Planner_type planner) {
		this.planner = planner;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate_of_event() {
		return this.date_of_event;
	}

	public void setDate_of_event(Date date_of_event) {
		this.date_of_event = date_of_event;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public float getRating() {
		return this.rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public int getAttends() {
		return this.attends;
	}

	public void setAttends(int attends) {
		this.attends = attends;
	}

}
