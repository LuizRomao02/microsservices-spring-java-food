package br.com.luizromao.payments.service;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.luizromao.payments.dto.PaymentDTO;
import br.com.luizromao.payments.enums.StatusEnum;
import br.com.luizromao.payments.model.Payment;
import br.com.luizromao.payments.repository.PaymentRepository;

@Service
public class PaymentService {

	@Autowired
	PaymentRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Page<PaymentDTO> getAll(Pageable paginacao) {
		return repository.findAll(paginacao).map(p -> modelMapper.map(p, PaymentDTO.class));
	}
	
	public PaymentDTO getById(Long id) {
		Payment payment = repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
		
		return modelMapper.map(payment, PaymentDTO.class);
	}
	
	public PaymentDTO createPayment(PaymentDTO dto) {
		Payment payment = modelMapper.map(dto, Payment.class);
		payment.setStatus(StatusEnum.CREATED);
		repository.save(payment);
		
		return modelMapper.map(payment, PaymentDTO.class);
	}
	
	public PaymentDTO updatePayment(Long id, PaymentDTO dto) {
		Payment payment = modelMapper.map(dto, Payment.class);
		payment.setId(id);
		payment = repository.save(payment);
		
		return modelMapper.map(payment, PaymentDTO.class);
	}
	
	public void deletePayment(Long id) {
		repository.deleteById(id);
	}
}
