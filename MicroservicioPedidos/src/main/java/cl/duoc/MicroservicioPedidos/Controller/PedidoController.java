package cl.duoc.MicroservicioPedidos.Controller;

import cl.duoc.MicroservicioPedidos.Model.Pedido;
import cl.duoc.MicroservicioPedidos.Service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @Operation(summary = "Listar todos los pedidos", description = "Retorna todos los pedidos registrados.")
    @GetMapping
    public ResponseEntity<List<Pedido>> listarTodos() {
        List<Pedido> pedidos = pedidoService.obtenerTodos();
        return pedidos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(pedidos);
    }

    @Operation(summary = "Buscar pedido por ID", description = "Obtiene un pedido específico a partir de su identificador.")
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> obtenerPorId(@PathVariable Long id) {
        try {
            Pedido pedido = pedidoService.obtenerPorId(id);
            return ResponseEntity.ok(pedido);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(
        summary = "Crear un nuevo pedido",
        description = "Crea y guarda un pedido en el sistema.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Pedido creado exitosamente",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pedido.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
        }
    )
    @PostMapping
    public ResponseEntity<Pedido> crearPedido(@RequestBody Pedido pedido) {
        Pedido guardado = pedidoService.guardar(pedido);
        return ResponseEntity.ok(guardado);
    }

    @Operation(summary = "Eliminar pedido por ID", description = "Elimina un pedido existente mediante su ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPedido(@PathVariable Long id) {
        try {
            pedidoService.eliminar(id);
            return ResponseEntity.ok("Pedido eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @Operation(summary = "Buscar pedidos por fecha", description = "Filtra los pedidos realizados en una fecha específica (formato: yyyy-MM-dd).")
    @GetMapping("/por-fecha")
    public ResponseEntity<List<Pedido>> buscarPorFecha(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        List<Pedido> pedidos = pedidoService.buscarPorFecha(fecha);
        return pedidos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(pedidos);
    }
}