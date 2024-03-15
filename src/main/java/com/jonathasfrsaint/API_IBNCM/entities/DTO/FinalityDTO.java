package com.jonathasfrsaint.API_IBNCM.entities.DTO;

public class FinalityDTO {

	private Long id_finality;
	private String description;
	
	
	public FinalityDTO() {
		
	}


	public FinalityDTO(Long id_finality, String description) {
		this.id_finality = id_finality;
		this.description = description;
	}


	public Long getId() {
		return id_finality;
	}


	public void setId(Long id_finality) {
		this.id_finality = id_finality;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
	
}
