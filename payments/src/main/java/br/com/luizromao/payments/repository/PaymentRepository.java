package br.com.luizromao.payments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.luizromao.payments.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>{

}
