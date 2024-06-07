package br.com.luizromao.payments.controller;

import java.net.URI;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.luizromao.payments.dto.PaymentDTO;
import br.com.luizromao.payments.service.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController {

	@Autowired
	private PaymentService service;

	@GetMapping
	public Page<PaymentDTO> listAll(@PageableDefault(size = 10) Pageable paginacao) {
		return service.getAll(paginacao);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PaymentDTO> detail(@PathVariable @NotNull Long id) {
		PaymentDTO dto = service.getById(id);
		return ResponseEntity.ok(dto);
	}

	@PostMapping
	public ResponseEntity<PaymentDTO> register(@RequestBody @Valid PaymentDTO dto, UriComponentsBuilder uriBuilder) {
		PaymentDTO payment = service.createPayment(dto);
		URI address = uriBuilder.path("/payments/{id}").buildAndExpand(payment.getId()).toUri();

		return ResponseEntity.created(address).body(payment);
	}

	@PutMapping("/{id}")
	public ResponseEntity<PaymentDTO> toUpdate(@PathVariable @NotNull Long id, @RequestBody @Valid PaymentDTO dto) {
		PaymentDTO updated = service.updatePayment(id, dto);
		return ResponseEntity.ok(updated);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<PaymentDTO> toRemove(@PathVariable @NotNull Long id) {
		service.deletePayment(id);
		return ResponseEntity.noContent().build();
	}
}
