package com.devcoi.dclog.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.devcoi.dclog.domain.model.StatusEntrega;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EntregaDTO {

	private Long id;
	private ClienteResumoDTO cliente;
	private DestinatarioDTO destinatario;
	private BigDecimal taxa;
	private StatusEntrega status;
	private OffsetDateTime dataPedido;
	private OffsetDateTime dataFinalizacao;

//	public EntregaDTO(Entrega entrega) {
//		this.id = entrega.getId();
//		this.idCliente = entrega.getCliente().getId();
//		this.destinatario = new DestinatarioDTO(entrega.getDestinatario());
//		this.taxa = entrega.getTaxa();
//		this.status = entrega.getStatus();
//		this.dataPedido = entrega.getDataPedido();
//		this.dataFinalizacao = entrega.getDataFinalizacao();
//	}

}
