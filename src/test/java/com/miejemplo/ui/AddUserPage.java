package com.miejemplo.ui;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class AddUserPage extends BasePage {

    @FindBy(xpath = "//h2[normalize-space()='Crear cuenta']")
    private WebElement tituloPagina;

    @FindBy(xpath = "//h1[contains(normalize-space(),'Registro de Administrador')]")
    private WebElement tituloRegistro;

    @FindBy(xpath = "//input[@placeholder='Ej. Juan Pérez']")
    private WebElement campoNombre;

    @FindBy(css = "input[type='email']")
    private WebElement campoEmail;

    @FindBy(xpath = "//input[@placeholder='••••••••']")
    private WebElement campoPassword;

    @FindBy(xpath = "//input[@placeholder='••••••••']/following-sibling::button[@type='button']")
    private WebElement botonTogglePassword;

    @FindBy(css = "button[type='submit']")
    private WebElement botonSubmit;

    @FindBy(xpath = "//button[contains(@class,'rounded-full')]")
    private WebElement botonVolver;

    public AddUserPage() {
        super();
    }

    public boolean isPageVisible() {
        return waitForVisible(tituloPagina).isDisplayed();
    }

    public boolean isTituloRegistroVisible() {
        return waitForVisible(tituloRegistro).isDisplayed();
    }

    public boolean isCampoNombreVisible() {
        return waitForVisible(campoNombre).isDisplayed();
    }

    public boolean isCampoEmailVisible() {
        return waitForVisible(campoEmail).isDisplayed();
    }

    public boolean isCampoPasswordVisible() {
        return waitForVisible(campoPassword).isDisplayed();
    }

    public boolean isBotonSubmitVisible() {
        return waitForVisible(botonSubmit).isDisplayed();
    }

    public String obtenerTipoPassword() {
        return waitForVisible(campoPassword).getAttribute("type");
    }

    public void togglePasswordVisibility() {
        click(botonTogglePassword);
    }

    public void ingresarNombre(String nombre) {
        type(campoNombre, nombre);
    }

    public void ingresarEmail(String email) {
        type(campoEmail, email);
    }

    public void ingresarPassword(String password) {
        type(campoPassword, password);
    }

    public void enviarFormulario() {
        click(botonSubmit);
    }

    public void volverAlDashboard() {
        click(botonVolver);
    }

    public boolean paginaContieneTexto(String texto) {
        return driver.getPageSource().contains(texto);
    }
}
