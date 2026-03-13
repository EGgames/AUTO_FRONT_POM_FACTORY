package com.miejemplo.ui;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddOrderPage extends BasePage {

    @FindBy(xpath = "//h2[normalize-space()='Nuevo Pedido']")
    private WebElement tituloNuevoPedido;

    @FindBy(css = "input[type='email']")
    private WebElement campoEmail;

    @FindBy(xpath = "//input[@placeholder='Ej. Zapatillas Running X']")
    private WebElement campoProducto;

    @FindBy(css = "textarea")
    private WebElement campoNotas;

    @FindBy(css = "button[type='submit']")
    private WebElement botonCrearPedido;

    @FindBy(xpath = "//button[contains(@class,'rounded-full')]")
    private WebElement botonVolver;

    @FindBy(xpath = "//button[normalize-space()='Cancelar']")
    private WebElement botonCancelar;

    public AddOrderPage() {
        super();
    }

    public boolean isPageVisible() {
        return waitForVisible(tituloNuevoPedido).isDisplayed();
    }

    public boolean isCampoEmailVisible() {
        return waitForVisible(campoEmail).isDisplayed();
    }

    public boolean isCampoProductoVisible() {
        return waitForVisible(campoProducto).isDisplayed();
    }

    public boolean isCampoNotasVisible() {
        return waitForVisible(campoNotas).isDisplayed();
    }

    public boolean isBotonSubmitVisible() {
        return waitForVisible(botonCrearPedido).isDisplayed();
    }

    public boolean isCampoNotasRequerido() {
        String required = campoNotas.getAttribute("required");
        return required != null && !required.equals("false");
    }

    public void ingresarEmail(String email) {
        type(campoEmail, email);
    }

    public void ingresarProducto(String producto) {
        type(campoProducto, producto);
    }

    public void ingresarNotas(String notas) {
        if (notas != null && !notas.isBlank()) {
            type(campoNotas, notas);
        }
    }

    public void enviarFormulario() {
        click(botonCrearPedido);
    }

    public void volverAlDashboard() {
        click(botonVolver);
    }

    public void cancelar() {
        click(botonCancelar);
    }

    public boolean paginaContieneTexto(String texto) {
        return driver.getPageSource().contains(texto);
    }
}

