package com.brigido.pedidos.entity;

import com.brigido.pedidos.dto.ProdutoDto;
import com.brigido.pedidos.enumeration.StatusProdutoEnum;
import com.brigido.pedidos.enumeration.TipoProdutoEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;
import static org.modelmapper.internal.util.Objects.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "produto")
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "uuid", unique = true, nullable = false, updatable = false)
    private UUID id;

    private String nome;

    private Double valor;

    @Enumerated(EnumType.STRING)
    private TipoProdutoEnum tipo;

    @Enumerated(EnumType.STRING)
    private StatusProdutoEnum status;

    @JsonIgnore
    public boolean isTipoProduto() {
        return tipo == TipoProdutoEnum.PRODUTO;
    }

    @JsonIgnore
    public boolean isInativo() {
        return status == StatusProdutoEnum.INATIVO;
    }

    public void update(ProdutoDto dto) {
        nome = firstNonNull(dto.getNome(), nome);
        valor = firstNonNull(dto.getValor(), valor);
        tipo = firstNonNull(dto.getTipo(), tipo);
        status = firstNonNull(dto.getStatus(), status);
    }
}
