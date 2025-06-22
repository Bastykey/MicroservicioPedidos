package cl.duoc.MicroservicioPedidos.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Schema(description = "DTO que representa la información pública de un pedido")
public class PedidoDTO {

    @Schema(description = "Identificador único del pedido", example = "101")
    private Long id;

    @Schema(description = "Fecha en la que se realizó el pedido", example = "2025-06-21")
    private LocalDate fecha;

    @Schema(description = "Identificador del usuario que realizó el pedido", example = "12345678-9")
    private String idUsuario;

    @Schema(description = "Detalle del pedido", example = "2 pizzas familiares y 1 bebida")
    private String detalle;
}