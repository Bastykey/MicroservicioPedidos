package cl.duoc.MicroservicioPedidos.Controller;

import cl.duoc.MicroservicioPedidos.Model.Pedido;
import cl.duoc.MicroservicioPedidos.Service.PedidoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(PedidoController.class)
class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PedidoService pedidoService;

    @Test
    void testListarTodos() throws Exception {
        Pedido pedido = new Pedido(1L, LocalDate.now(), "12345678-9", "Ejemplo");
        when(pedidoService.obtenerTodos()).thenReturn(List.of(pedido));

        mockMvc.perform(get("/api/v1/pedidos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idPedido").value(1L));
    }

    @Test
    void testObtenerPorId_existente() throws Exception {
        Pedido pedido = new Pedido(1L, LocalDate.now(), "12345678-9", "Prueba ID");
        when(pedidoService.obtenerPorId(1L)).thenReturn(pedido);

        mockMvc.perform(get("/api/v1/pedidos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idPedido").value(1L));
    }

    @Test
    void testObtenerPorId_noExistente() throws Exception {
        when(pedidoService.obtenerPorId(99L)).thenThrow(new RuntimeException("Pedido no encontrado"));

        mockMvc.perform(get("/api/v1/pedidos/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testEliminar_existente() throws Exception {
        doNothing().when(pedidoService).eliminar(1L);

        mockMvc.perform(delete("/api/v1/pedidos/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testCrearPedido() throws Exception {
        Pedido nuevo = new Pedido(null, LocalDate.of(2025, 6, 21), "11222333-4", "Delivery de sushi");
        Pedido guardado = new Pedido(10L, nuevo.getFechaPedido(), nuevo.getIdUsuario(), nuevo.getDetalle());

        when(pedidoService.guardar(org.mockito.ArgumentMatchers.any(Pedido.class))).thenReturn(guardado);

        String bodyJson = """
            {
                "fechaPedido": "2025-06-21",
                "idUsuario": "11222333-4",
                "detalle": "Envio de Productos de...."
            }
        """;

        mockMvc.perform(post("/api/v1/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bodyJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idPedido").value(10L));
    }
}