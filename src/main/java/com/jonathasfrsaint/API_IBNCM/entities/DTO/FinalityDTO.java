package com.jonathasfrsaint.API_IBNCM.entities.DTO;

public class FinalityDTO {

	private Long id;
	private String description;
	
	
	public FinalityDTO() {
		
	}


	public FinalityDTO(Long id, String description) {
		this.id = id;
		this.description = description;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
	
}
