package com.fdmgroup.WanderersBlog.util;

public class Filtering {
	

	private String category;
	private String location;
	
	public Filtering(String category, String location) {
		super();
		this.category = category;
		this.location = location;
	}
	
	public Filtering() {
		super();

		this.category = "";
		this.location = "";
	}


	public Filtering(String location) {
		this.location = location;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Filtering [category=" + category + ", location=" + location + "]";
	}

	


}
