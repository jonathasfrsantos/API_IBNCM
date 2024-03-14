package com.jonathasfrsaint.API_IBNCM.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.jonathasfrsaint.API_IBNCM.entities.Finality;
import com.jonathasfrsaint.API_IBNCM.entities.DTO.FinalityDTO;
import com.jonathasfrsaint.API_IBNCM.factory.DTOFactory;
import com.jonathasfrsaint.API_IBNCM.repositories.FinalityRepository;
import com.jonathasfrsaint.API_IBNCM.services.exceptions.DataBaseException;
import com.jonathasfrsaint.API_IBNCM.services.exceptions.ResourceNotFoundException;
import com.jonathasfrsaint.API_IBNCM.services.exceptions.ThisValueAlreadyExists;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class FinalityService {

	@Autowired
	private FinalityRepository finalityRepository;

	@Transactional
	public Finality create(Finality finality) {
		if (finalityRepository.existsByDescription(finality.getDescription())) {
			throw new ThisValueAlreadyExists(finality.getDescription(), finality);
		}

		return finalityRepository.save(finality);
	}

	public List<FinalityDTO> findAll() {
		List<Finality> result = finalityRepository.findAll();
		List<FinalityDTO> dto = result.stream().map((x) -> DTOFactory.createDTO(x)).collect(Collectors.toList());
		return dto;
	}

	public FinalityDTO findById(Long id) {
		try {
			Finality result = finalityRepository.findById(id).get();
			FinalityDTO dto = DTOFactory.createDTO(result);
			return dto;
		} catch (NoSuchElementException e) {
			throw new ResourceNotFoundException(id);
		}

	}

	public FinalityDTO update(Long id, FinalityDTO dto) {

		Optional<Finality> oldObj = finalityRepository.findById(id);

		if (oldObj.isPresent()) {
			Finality finality = oldObj.get();

			Finality updatedObj = updateData(finality, dto);

			finalityRepository.save(updatedObj);
			dto = DTOFactory.createDTO(finality);
			return dto;

		} else {
			throw new EntityNotFoundException("Especificação não encontrado com o ID:" + id);
		}
	}

	public void delete(Long id) {
		try {
			finalityRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException(e.getMessage());
		}
	}

	public Finality updateData(Finality oldFinality, FinalityDTO dto) {
		
		oldFinality.setDescription(dto.getDescription());
		return oldFinality;
	}
}
