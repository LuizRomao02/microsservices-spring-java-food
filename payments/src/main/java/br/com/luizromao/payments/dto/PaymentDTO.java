package br.com.luizromao.payments.dto;

import java.math.BigDecimal;

import br.com.luizromao.payments.enums.StatusEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentDTO {

	private Long id;
	private BigDecimal valor;
	private String nome;
	private String numero;
	private String expiracao;
	private String codigo;
	private StatusEnum status;
	private Long formaDePagamentoId;
	private Long pedidoId;
}
