package com.jonathasfrsaint.API_IBNCM.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_finality")
public class Finality implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id_finality;
	private String description;
	
	@OneToMany(mappedBy = "finality")
	@JsonIgnore
	private List<Transaction> transactions;
	
	
	public Finality () {
		
	}


	public Finality(Long id_finality, String description) {

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
	
	public List<Transaction> getTransactions(){
		return transactions;
	}


	@Override
	public int hashCode() {
		return Objects.hash(description, id_finality);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Finality other = (Finality) obj;
		return Objects.equals(description, other.description) && Objects.equals(id_finality, other.id_finality);
	}
	
	
	
	

}
