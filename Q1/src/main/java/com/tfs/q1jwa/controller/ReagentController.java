package com.tfs.q1jwa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tfs.q1jwa.model.Reagent;
import com.tfs.q1jwa.service.ReagentService;

@RestController
@RequestMapping("/api/reagents")
public class ReagentController {
	@Autowired
	private ReagentService reagentService;

	@GetMapping
	public List<Reagent> getAllReagents() {
		return reagentService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Reagent> getReagentById(@PathVariable Long id) {
		return reagentService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public Reagent createReagent(@RequestBody Reagent reagent) {
		return reagentService.save(reagent);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Reagent> updateReagent(@PathVariable Long id, @RequestBody Reagent reagentDetails) {
		return reagentService.findById(id).map(reagent -> {
			reagent.setName(reagentDetails.getName());
			reagent.setPartNumber(reagentDetails.getPartNumber());
			reagent.setLotNumber(reagentDetails.getLotNumber());
			reagent.setExpiryDate(reagentDetails.getExpiryDate());
			return ResponseEntity.ok(reagentService.save(reagent));
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteReagent(@PathVariable Long id) {
		return reagentService.findById(id).map(reagent -> {
			reagentService.delete(reagent.getId());
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/search")
	public List<Reagent> searchReagents(@RequestParam String query) {
		return reagentService.search(query);
	}
}