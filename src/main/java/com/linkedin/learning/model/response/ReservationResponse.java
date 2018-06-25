package com.linkedin.learning.model.response;

import com.linkedin.learning.model.Links;

public class ReservationResponse {

	private Long id;
	private Integer noomNumber;
	private Integer price;
	private Links links;
	
	
	public ReservationResponse() {
		super();
	}
	
	public ReservationResponse(Long id, Integer noomNumber, Integer price) {
		super();
		this.id = id;
		this.noomNumber = noomNumber;
		this.price = price;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getNoomNumber() {
		return noomNumber;
	}
	public void setNoomNumber(Integer noomNumber) {
		this.noomNumber = noomNumber;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Links getLinks() {
		return links;
	}
	public void setLinks(Links links) {
		this.links = links;
	}
}
