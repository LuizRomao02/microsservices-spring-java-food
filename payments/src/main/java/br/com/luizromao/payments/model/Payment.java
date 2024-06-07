package br.com.luizromao.payments.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import br.com.luizromao.payments.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TBOD_PAYMENTS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Positive
	private BigDecimal value;
	
	@NotBlank
	@Size(max=100)
	private String name;
	
	@NotBlank
	@Size(max=19)
	private String number;
	
	@NotBlank
	@Size(max=7)
	private String expiration;
	
	@NotBlank
	@Size(min=3, max=3)
	private String codigo;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private StatusEnum status;
	
	@NotNull
	private Long orderId;
	
	@NotNull
	private Long paymentFormId;
}
