package com.tfs.q1jwa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfs.q1jwa.repo.ReagentRepository;
import com.tfs.q1jwa.model.Reagent;

@Service
public class ReagentService {
	@Autowired
	private ReagentRepository reagentRepository;

	public List<Reagent> findAll() {
		return reagentRepository.findAll();
	}

	public Optional<Reagent> findById(Long id) {
		return reagentRepository.findById(id);
	}

	public Reagent save(Reagent reagent) {
		return reagentRepository.save(reagent);
	}

	public void delete(Long id) {
		reagentRepository.deleteById(id);
	}

	public List<Reagent> search(String query) {
		return reagentRepository.findByNameContainingOrPartNumberContaining(query, query);
	}
}