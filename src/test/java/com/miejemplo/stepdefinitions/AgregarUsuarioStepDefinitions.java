package com.miejemplo.stepdefinitions;

import com.miejemplo.ui.AddUserPage;
import com.miejemplo.util.ConfigReader;
import com.miejemplo.util.DriverManager;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;

import static org.assertj.core.api.Assertions.assertThat;

public class AgregarUsuarioStepDefinitions {

    private final AddUserPage addUserPage = new AddUserPage();

    @Dado("que el usuario está en la página de registro de administrador")
    public void queElUsuarioEstaEnLaPaginaDeRegistro() {
        DriverManager.getDriver().get(ConfigReader.getBaseUrl() + "/addUser");
    }

    @Entonces("el campo {string} debería ser visible en el formulario")
    public void elCampoDeberiaSerVisibleEnElFormulario(String campo) {
        if ("Nombre completo".equals(campo)) {
            assertThat(addUserPage.isCampoNombreVisible())
                    .as("El campo de nombre completo debería ser visible")
                    .isTrue();
        } else {
            throw new IllegalArgumentException("Campo desconocido: " + campo);
        }
    }

    @Y("el campo de correo electrónico debería ser visible en el formulario")
    public void elCampoDeCorreoDeberiaSerVisible() {
        assertThat(addUserPage.isCampoEmailVisible())
                .as("El campo de correo electrónico debería ser visible")
                .isTrue();
    }

    @Y("el campo de contraseña debería ser visible en el formulario")
    public void elCampoDePasswordDeberiaSerVisible() {
        assertThat(addUserPage.isCampoPasswordVisible())
                .as("El campo de contraseña debería ser visible")
                .isTrue();
    }

    @Y("el botón de envío del formulario de usuario debería ser visible")
    public void elBotonDeEnvioDeberiaSerVisible() {
        assertThat(addUserPage.isBotonSubmitVisible())
                .as("El botón de envío del formulario debería ser visible")
                .isTrue();
    }

    @Entonces("el tipo del campo de contraseña debería ser {string}")
    public void elTipoDelCampoDePasswordDeberiaSer(String tipo) {
        String tipoActual = addUserPage.obtenerTipoPassword();
        assertThat(tipoActual)
                .as("El tipo del campo contraseña debería ser '%s', pero fue '%s'", tipo, tipoActual)
                .isEqualToIgnoringCase(tipo);
    }

    @Cuando("el usuario hace clic en el botón de mostrar contraseña")
    public void elUsuarioHaceClicEnElBotonDeMostrarPassword() {
        addUserPage.togglePasswordVisibility();
    }

    @Cuando("el usuario ingresa solo email {string} y contraseña {string} en el formulario de registro")
    public void elUsuarioIngresaSoloEmailYPassword(String email, String password) {
        addUserPage.ingresarEmail(email);
        addUserPage.ingresarPassword(password);
    }

    @Cuando("el usuario completa el formulario de registro con nombre {string} email {string} y contraseña {string}")
    public void elUsuarioCompletaElFormulario(String nombre, String email, String password) {
        addUserPage.ingresarNombre(nombre);
        addUserPage.ingresarEmail(email);
        addUserPage.ingresarPassword(password);
    }

    @Y("el usuario intenta enviar el formulario de registro")
    public void elUsuarioIntentaEnviarElFormularioDeRegistro() {
        addUserPage.enviarFormulario();
    }

    @Entonces("la URL de la página de registro debería contener {string}")
    public void laUrlDeLaPaginaDeRegistroDeberiaContener(String segmento) {
        String url = DriverManager.getDriver().getCurrentUrl();
        assertThat(url)
                .as("La URL debería contener '%s', pero fue: '%s'", segmento, url)
                .contains(segmento);
    }

    @Entonces("la página de usuario debería contener el texto {string}")
    public void laPaginaDeUsuarioDeberiaContenerTexto(String texto) {
        assertThat(addUserPage.paginaContieneTexto(texto))
                .as("La página debería contener el texto: '%s'", texto)
                .isTrue();
    }
}
