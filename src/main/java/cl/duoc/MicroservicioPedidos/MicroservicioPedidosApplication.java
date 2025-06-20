package cl.duoc.MicroservicioPedidos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"cl.duoc.MicroservicioPedidos", "cl.duoc.MicroservicioPedidos.Config"})
public class MicroservicioPedidosApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(MicroservicioPedidosApplication.class, args);
	}
}

