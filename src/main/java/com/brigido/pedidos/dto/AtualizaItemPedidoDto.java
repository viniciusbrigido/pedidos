package com.brigido.pedidos.dto;

import lombok.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AtualizaItemPedidoDto {
    private UUID itemId;

}
