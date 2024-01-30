package com.tfs.q1jwa.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Reagent {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "name is mandatory")
	@Size(min = 3, max = 128, message = "name must be between 3 and 256 characters")
	@Column(nullable = false, length = 256)
	private String name;

	@NotBlank(message = "partNumber is mandatory")
	@Size(min = 3, max = 128, message = "partNumber must be between 3 and 128 characters")
	@Column(nullable = false, length = 128)
	private String partNumber;

	@NotBlank(message = "lotNumber is mandatory")
	@Size(min = 3, max = 128, message = "lotNumber must be between 3 and 128 characters")
	@Column(nullable = false, length = 128)
	private String lotNumber;

	@NotNull(message = "expiryDate cannot be null")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(nullable = false)
	private LocalDate expiryDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public String getLotNumber() {
		return lotNumber;
	}

	public void setLotNumber(String lotNumber) {
		this.lotNumber = lotNumber;
	}

	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}

}