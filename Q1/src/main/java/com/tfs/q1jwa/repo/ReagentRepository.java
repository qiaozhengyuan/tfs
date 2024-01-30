package com.tfs.q1jwa.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tfs.q1jwa.model.Reagent;

public interface ReagentRepository extends JpaRepository<Reagent, Long> {
	List<Reagent> findByNameContainingOrPartNumberContaining(String name, String partNumber);

	Page<Reagent> findByNameContainingOrPartNumberContaining(String name, String partNumber, Pageable pageable);
}