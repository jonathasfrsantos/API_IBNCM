package com.jonathasfrsaint.API_IBNCM.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jonathasfrsaint.API_IBNCM.entities.Finality;

public interface FinalityRepository extends JpaRepository<Finality, Long> {

	boolean existsByDescription(String description);

	List<Finality> findAllByDescription(String description);

	Finality findByDescription(String description);

}
