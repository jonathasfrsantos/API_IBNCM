package com.jonathasfrsaint.API_IBNCM.factory;

import com.jonathasfrsaint.API_IBNCM.entities.Finality;
import com.jonathasfrsaint.API_IBNCM.entities.Transaction;
import com.jonathasfrsaint.API_IBNCM.entities.DTO.FinalityDTO;
import com.jonathasfrsaint.API_IBNCM.entities.DTO.TransactionDTO;

public class DTOFactory {

	public static FinalityDTO createDTO(Finality finality) {
		FinalityDTO dto = new FinalityDTO();
		dto.setId(finality.getId());
		dto.setDescription(finality.getDescription());
		return dto;

	}

	public static TransactionDTO createDTO(Transaction transaction) {
		TransactionDTO dto = new TransactionDTO();
		dto.setId(transaction.getId());
		dto.setTransactionDate(transaction.getTransactionDate());
		dto.setRevenue(transaction.getRevenue());
		dto.setExpense(transaction.getExpense());
		dto.setHistoric(transaction.getHistoric());
		dto.setBankCash(transaction.getBankCash());
		if (transaction.getFinality() != null) {
			dto.setFinality(transaction.getFinality().getDescription());
		}
		return dto;
	}

}
