package com.brigido.pedidos.entity;

import com.brigido.pedidos.dto.AtualizaItemPedidoDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "item_pedido")
public class ItemPedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "uuid", unique = true, nullable = false, updatable = false)
    private UUID id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private PedidoEntity pedido;

    @ManyToOne
    private ProdutoEntity produto;

    @Column(name = "valor_unitario")
    private Double valorUnitario;

    private Integer quantidade;

    public Double getValorTotal() {
        return quantidade * valorUnitario;
    }

    public void update(AtualizaItemPedidoDto dto) {

    }
}
