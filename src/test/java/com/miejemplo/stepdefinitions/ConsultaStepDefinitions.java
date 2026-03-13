package com.miejemplo.stepdefinitions;

import com.miejemplo.ui.DashboardPage;
import com.miejemplo.util.ConfigReader;
import com.miejemplo.util.DriverManager;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;

import static org.assertj.core.api.Assertions.assertThat;

public class ConsultaStepDefinitions {

    private final DashboardPage dashboardPage = new DashboardPage();

    @Dado("que el usuario está en el dashboard")
    public void queElUsuarioEstaEnElDashboard() {
        DriverManager.getDriver().get(ConfigReader.getBaseUrl());
        assertThat(dashboardPage.isDashboardVisible())
                .as("El dashboard debería estar visible")
                .isTrue();
    }

    @Cuando("observa la lista de pedidos")
    public void observaLaListaDePedidos() {
        dashboardPage.verTodosLosClientes();
    }

    @Entonces("debería ver al menos un pedido en pantalla")
    public void deberiaVerAlMenosUnPedidoEnPantalla() {
        int cantidad = dashboardPage.obtenerCantidadPedidos();
        assertThat(cantidad)
                .as("Se esperaba al menos un pedido en pantalla, pero se encontraron: " + cantidad)
                .isGreaterThan(0);
    }

    @Cuando("filtra pedidos por el usuario {string}")
    public void filtraPedidosPorElUsuario(String nombreUsuario) {
        dashboardPage.filtrarPorUsuario(nombreUsuario);
    }

    @Entonces("el encabezado de pedidos debería contener {string}")
    public void elEncabezadoDePedidosDeberiaContener(String textoEsperado) {
        String titulo = dashboardPage.obtenerTextoCabeceraPedidos();
        assertThat(titulo)
                .as("El título de pedidos debería contener: '%s', pero fue: '%s'", textoEsperado, titulo)
                .containsIgnoringCase(textoEsperado);
    }

    @Entonces("el contador de pedidos debería mostrar {int} items")
    public void elContadorDePedidosDeberiaMostrar(int cantidad) {
        String textoContador = dashboardPage.obtenerTextoContador();
        assertThat(textoContador)
                .as("El contador debería mostrar %d items", cantidad)
                .contains(String.valueOf(cantidad));
    }
}
