package com.brigido.pedidos.entity;

import com.brigido.pedidos.enumeration.StatusPedidoEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;
import static java.util.Objects.*;
import static java.util.Optional.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "pedido")
public class PedidoEntity {

    public PedidoEntity() {
        status = StatusPedidoEnum.ABERTO;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "uuid", unique = true, nullable = false, updatable = false)
    private UUID id;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedidoEntity> itens;

    @Enumerated(EnumType.STRING)
    private StatusPedidoEnum status;

    @Column(name = "valor_total_liquido")
    private Double valorTotalLiquido;

    @Column(name = "valor_total_bruto")
    private Double valorTotalBruto;

    @Column(name = "percentual_desconto")
    private Double percentualDesconto;

    public List<ItemPedidoEntity> getItens() {
        return ofNullable(itens).orElseGet(() -> itens = new ArrayList<>());
    }

    public void setItens(List<ItemPedidoEntity> itens) {
        itens.forEach(itemPedido -> itemPedido.setPedido(this));
        this.itens = itens;
    }

    public void calcularTotaisDoPedido() {
        calcularTotaisDoPedido(percentualDesconto);
    }

    public void calcularTotaisDoPedido(Double percentualDesconto) {
        Double valorTotalBruto = 0D;
        Double valorTotalLiquido = 0D;

        for (ItemPedidoEntity itemPedido : getItens()) {
            valorTotalBruto += itemPedido.getValorTotal();

            if (nonNull(percentualDesconto) && percentualDesconto > 0 && itemPedido.getProduto().isTipoProduto()) {
                Double desconto = itemPedido.getValorTotal() * (percentualDesconto / 100);
                valorTotalLiquido += (itemPedido.getValorTotal() - desconto);
            } else {
                valorTotalLiquido += itemPedido.getValorTotal();
            }
        }
        this.percentualDesconto = percentualDesconto;
        this.valorTotalBruto = valorTotalBruto;
        this.valorTotalLiquido = valorTotalLiquido;
    }

    public void fechar() {
        status = StatusPedidoEnum.FECHADO;
    }

    @JsonIgnore
    public boolean isFechado() {
        return status == StatusPedidoEnum.FECHADO;
    }
}
