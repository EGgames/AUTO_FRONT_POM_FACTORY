package com.miejemplo.stepdefinitions;

import com.miejemplo.ui.AddOrderPage;
import com.miejemplo.ui.AddUserPage;
import com.miejemplo.ui.DashboardPage;
import com.miejemplo.util.DriverManager;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;

import static org.assertj.core.api.Assertions.assertThat;

public class NavegacionStepDefinitions {

    private final DashboardPage dashboardPage = new DashboardPage();

    @Cuando("el usuario hace clic en el botón de navegación {string}")
    public void elUsuarioHaceClicEnBotonNavegacion(String nombreBoton) {
        if ("Agregar Usuario".equals(nombreBoton)) {
            dashboardPage.irAAgregarUsuario();
        } else if ("Agregar Pedido".equals(nombreBoton)) {
            dashboardPage.irAAgregarPedido();
        } else {
            throw new IllegalArgumentException("Botón desconocido: " + nombreBoton);
        }
    }

    @Y("el usuario hace clic en el botón volver del formulario")
    public void elUsuarioHaceClicEnBotonVolverDelFormulario() {
        String url = DriverManager.getDriver().getCurrentUrl();
        if (url.contains("/addUser")) {
            new AddUserPage().volverAlDashboard();
        } else {
            new AddOrderPage().volverAlDashboard();
        }
    }

    @Y("el usuario hace clic en el botón {string}")
    public void elUsuarioHaceClicEnElBoton(String nombreBoton) {
        if ("Cancelar".equals(nombreBoton)) {
            new AddOrderPage().cancelar();
        } else {
            throw new IllegalArgumentException("Botón desconocido: " + nombreBoton);
        }
    }

    @Entonces("la URL debería contener {string}")
    public void laUrlDeberiaContener(String segmento) {
        String urlActual = DriverManager.getDriver().getCurrentUrl();
        assertThat(urlActual)
                .as("La URL debería contener '%s', pero fue: '%s'", segmento, urlActual)
                .contains(segmento);
    }

    @Y("la página de registro debería ser visible")
    public void laPaginaDeRegistroDeberiaSerVisible() {
        assertThat(new AddUserPage().isPageVisible())
                .as("La página de registro de administrador debería estar visible")
                .isTrue();
    }

    @Y("la página de nuevo pedido debería ser visible")
    public void laPaginaDeNuevoPedidoDeberiaSerVisible() {
        assertThat(new AddOrderPage().isPageVisible())
                .as("La página de nuevo pedido debería estar visible")
                .isTrue();
    }

    @Y("el título del dashboard debería ser visible")
    public void elTituloDelDashboardDeberiaSerVisible() {
        assertThat(dashboardPage.isDashboardVisible())
                .as("El dashboard debería estar visible")
                .isTrue();
    }
}
