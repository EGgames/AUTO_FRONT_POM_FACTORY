package com.miejemplo.stepdefinitions;

import com.miejemplo.ui.AddOrderPage;
import com.miejemplo.util.ConfigReader;
import com.miejemplo.util.DriverManager;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;

import static org.assertj.core.api.Assertions.assertThat;

public class AgregarPedidoStepDefinitions {

    private final AddOrderPage addOrderPage = new AddOrderPage();

    @Dado("que el usuario está en la página de nuevo pedido")
    public void queElUsuarioEstaEnLaPaginaDeNuevoPedido() {
        DriverManager.getDriver().get(ConfigReader.getBaseUrl() + "/addOrder");
    }

    @Entonces("el campo de email del usuario debería ser visible en el formulario de pedido")
    public void elCampoEmailDeberiaSerVisible() {
        assertThat(addOrderPage.isCampoEmailVisible())
                .as("El campo de email del usuario debería ser visible")
                .isTrue();
    }

    @Y("el campo de nombre del producto debería ser visible en el formulario de pedido")
    public void elCampoProductoDeberiaSerVisible() {
        assertThat(addOrderPage.isCampoProductoVisible())
                .as("El campo de nombre del producto debería ser visible")
                .isTrue();
    }

    @Y("el campo de notas adicionales debería ser visible en el formulario de pedido")
    public void elCampoNotasDeberiaSerVisible() {
        assertThat(addOrderPage.isCampoNotasVisible())
                .as("El campo de notas adicionales debería ser visible")
                .isTrue();
    }

    @Y("el botón de envío del formulario de pedido debería ser visible")
    public void elBotonDeEnvioDeberiaSerVisible() {
        assertThat(addOrderPage.isBotonSubmitVisible())
                .as("El botón de envío del formulario debería ser visible")
                .isTrue();
    }

    @Entonces("el campo de notas adicionales no debería ser requerido en el formulario de pedido")
    public void elCampoNotasNoDeberiaSerRequerido() {
        assertThat(addOrderPage.isCampoNotasRequerido())
                .as("El campo de notas no debería ser requerido")
                .isFalse();
    }

    @Entonces("la página de pedido debería contener el texto {string}")
    public void laPaginaDePedidoDeberiaContenerTexto(String texto) {
        assertThat(addOrderPage.paginaContieneTexto(texto))
                .as("La página debería contener el texto: '%s'", texto)
                .isTrue();
    }

    @Cuando("el usuario hace clic en {string} en el formulario de pedido")
    public void elUsuarioHaceClicEnElFormularioDePedido(String boton) {
        if ("Cancelar".equals(boton)) {
            addOrderPage.cancelar();
        } else {
            throw new IllegalArgumentException("Botón desconocido: " + boton);
        }
    }

    @Entonces("la URL del formulario de pedido debería contener {string}")
    public void laUrlDelFormularioDePedidoDeberiaContener(String segmento) {
        String url = DriverManager.getDriver().getCurrentUrl();
        assertThat(url)
                .as("La URL debería contener '%s', pero fue: '%s'", segmento, url)
                .contains(segmento);
    }

    @Cuando("el usuario ingresa solo el producto {string} en el formulario de pedido")
    public void elUsuarioIngresaSoloElProducto(String producto) {
        addOrderPage.ingresarProducto(producto);
    }

    @Cuando("el usuario ingresa solo email {string} en el formulario de pedido")
    public void elUsuarioIngresaSoloEmail(String email) {
        addOrderPage.ingresarEmail(email);
    }

    @Cuando("el usuario ingresa email inválido {string} y producto {string} en el formulario de pedido")
    public void elUsuarioIngresaEmailInvalidoYProducto(String email, String producto) {
        addOrderPage.ingresarEmail(email);
        addOrderPage.ingresarProducto(producto);
    }

    @Y("el usuario intenta enviar el formulario de pedido")
    public void elUsuarioIntentaEnviarElFormularioDePedido() {
        addOrderPage.enviarFormulario();
    }
}
