package com.jonathasfrsaint.API_IBNCM.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jonathasfrsaint.API_IBNCM.entities.Finality;
import com.jonathasfrsaint.API_IBNCM.entities.DTO.FinalityDTO;
import com.jonathasfrsaint.API_IBNCM.services.FinalityService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/finalities")
public class FinalityController {
	
	@Autowired
	private FinalityService finalityService;
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Finality> create(@RequestBody @Validated Finality Finality){
		return ResponseEntity.ok().body(finalityService.create(Finality));
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<FinalityDTO> findById(@PathVariable Long id){
		return ResponseEntity.ok().body(finalityService.findById(id));
	}
	
	@GetMapping
	public ResponseEntity<List<FinalityDTO>> findAll(){
		return ResponseEntity.ok().body(finalityService.findAll());
	}
		
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<FinalityDTO> update(@PathVariable Long id, @RequestBody FinalityDTO dto) {
		dto = finalityService.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		finalityService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
