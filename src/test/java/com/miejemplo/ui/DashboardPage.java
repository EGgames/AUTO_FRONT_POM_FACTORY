package com.miejemplo.ui;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;


public class DashboardPage extends BasePage {

    @FindBy(xpath = "//h1[normalize-space()='Dashboard']")
    private WebElement tituloDashboard;

    @FindBy(css = "select")
    private WebElement filtroUsuario;

    @FindBy(xpath = "//h3[contains(@class,'font-black')]")
    private WebElement tituloPedidos;

    @FindBy(xpath = "//span[contains(@class,'bg-blue-50') and contains(@class,'text-blue-600')]")
    private WebElement contadorPedidos;

    @FindBy(xpath = "//div[contains(@class,'rounded-3xl') and contains(@class,'bg-white') and contains(@class,'shadow-sm')]")
    private List<WebElement> tarjetasPedidos;

    @FindBy(xpath = "//span[normalize-space()='Agregar Usuario']/parent::button")
    private WebElement botonAgregarUsuario;

    @FindBy(xpath = "//span[normalize-space()='Agregar Pedido']/parent::button")
    private WebElement botonAgregarPedido;

    public DashboardPage() {
        super();
    }

    public boolean isDashboardVisible() {
        return waitForVisible(tituloDashboard).isDisplayed();
    }

    public void filtrarPorUsuario(String textoOpcion) {
        Select select = new Select(waitForVisible(filtroUsuario));
        select.selectByVisibleText(textoOpcion);
    }

    public void verTodosLosClientes() {
        Select select = new Select(waitForVisible(filtroUsuario));
        select.selectByValue("");
    }

    public int obtenerCantidadPedidos() {
        return tarjetasPedidos.size();
    }

    public String obtenerTextoCabeceraPedidos() {
        return waitForVisible(tituloPedidos).getText();
    }

    public String obtenerTextoContador() {
        return waitForVisible(contadorPedidos).getText();
    }

    public void irAAgregarPedido() {
        click(botonAgregarPedido);
    }

    public void irAAgregarUsuario() {
        click(botonAgregarUsuario);
    }
}
