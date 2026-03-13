#language: es
Característica: Pruebas E2E de la aplicación frontend

  Suite completa de pruebas sobre el Dashboard, la navegación entre páginas,
  el formulario de registro de administrador y el formulario de creación de pedidos.

  Escenario: Validación completa del sistema frontend

    # ── Dashboard: consulta de pedidos ────────────────────────────────────
    Dado que el usuario está en el dashboard
    Cuando observa la lista de pedidos
    Entonces el encabezado de pedidos debería contener "Todos los Pedidos"
    Cuando filtra pedidos por el usuario "Todos los clientes"
    Entonces el encabezado de pedidos debería contener "Todos los Pedidos"

    # ── Navegación: Dashboard → Agregar Usuario ──────────────────────────
    Dado que el usuario está en el dashboard
    Cuando el usuario hace clic en el botón de navegación "Agregar Usuario"
    Entonces la URL debería contener "/addUser"
    Y la página de registro debería ser visible

    # ── Navegación: Volver desde formulario de usuario ───────────────────
    Y el usuario hace clic en el botón volver del formulario
    Entonces la URL debería contener "/"
    Y el título del dashboard debería ser visible

    # ── Navegación: Dashboard → Agregar Pedido ───────────────────────────
    Dado que el usuario está en el dashboard
    Cuando el usuario hace clic en el botón de navegación "Agregar Pedido"
    Entonces la URL debería contener "/addOrder"
    Y la página de nuevo pedido debería ser visible

    # ── Navegación: Volver desde formulario de pedido ────────────────────
    Y el usuario hace clic en el botón volver del formulario
    Entonces la URL debería contener "/"
    Y el título del dashboard debería ser visible

    # ── Navegación: Cancelar desde formulario de pedido ──────────────────
    Dado que el usuario está en el dashboard
    Cuando el usuario hace clic en el botón de navegación "Agregar Pedido"
    Y el usuario hace clic en el botón "Cancelar"
    Entonces la URL debería contener "/"
    Y el título del dashboard debería ser visible

    # ── Formulario de usuario: campos visibles ───────────────────────────
    Dado que el usuario está en la página de registro de administrador
    Entonces el campo "Nombre completo" debería ser visible en el formulario
    Y el campo de correo electrónico debería ser visible en el formulario
    Y el campo de contraseña debería ser visible en el formulario
    Y el botón de envío del formulario de usuario debería ser visible

    # ── Formulario de usuario: título ────────────────────────────────────
    Entonces la página de usuario debería contener el texto "Registro de Administrador"

    # ── Formulario de usuario: contraseña oculta por defecto ─────────────
    Entonces el tipo del campo de contraseña debería ser "password"

    # ── Formulario de usuario: toggle de visibilidad de contraseña ───────
    Cuando el usuario hace clic en el botón de mostrar contraseña
    Entonces el tipo del campo de contraseña debería ser "text"
    Cuando el usuario hace clic en el botón de mostrar contraseña
    Entonces el tipo del campo de contraseña debería ser "password"

    # ── Formulario de usuario: requiere nombre ───────────────────────────
    Dado que el usuario está en la página de registro de administrador
    Cuando el usuario ingresa solo email "test@test.com" y contraseña "password123" en el formulario de registro
    Y el usuario intenta enviar el formulario de registro
    Entonces la URL de la página de registro debería contener "/addUser"

    # ── Formulario de usuario: contraseña mínima 8 caracteres ────────────
    Dado que el usuario está en la página de registro de administrador
    Cuando el usuario completa el formulario de registro con nombre "Juan Pérez" email "juan@test.com" y contraseña "123"
    Y el usuario intenta enviar el formulario de registro
    Entonces la URL de la página de registro debería contener "/addUser"

    # ── Formulario de usuario: email inválido ────────────────────────────
    Dado que el usuario está en la página de registro de administrador
    Cuando el usuario completa el formulario de registro con nombre "Juan Pérez" email "no-es-un-email" y contraseña "password123"
    Y el usuario intenta enviar el formulario de registro
    Entonces la URL de la página de registro debería contener "/addUser"

    # ── Formulario de pedido: campos visibles ────────────────────────────
    Dado que el usuario está en la página de nuevo pedido
    Entonces el campo de email del usuario debería ser visible en el formulario de pedido
    Y el campo de nombre del producto debería ser visible en el formulario de pedido
    Y el campo de notas adicionales debería ser visible en el formulario de pedido
    Y el botón de envío del formulario de pedido debería ser visible

    # ── Formulario de pedido: títulos ────────────────────────────────────
    Entonces la página de pedido debería contener el texto "Nuevo Pedido"
    Y la página de pedido debería contener el texto "Crear Pedido"

    # ── Formulario de pedido: notas opcionales ───────────────────────────
    Entonces el campo de notas adicionales no debería ser requerido en el formulario de pedido

    # ── Formulario de pedido: Cancelar regresa al dashboard ──────────────
    Dado que el usuario está en la página de nuevo pedido
    Cuando el usuario hace clic en "Cancelar" en el formulario de pedido
    Entonces la URL del formulario de pedido debería contener "/"
    Y el título del dashboard debería ser visible

    # ── Formulario de pedido: requiere email ─────────────────────────────
    Dado que el usuario está en la página de nuevo pedido
    Cuando el usuario ingresa solo el producto "Laptop Pro" en el formulario de pedido
    Y el usuario intenta enviar el formulario de pedido
    Entonces la URL del formulario de pedido debería contener "/addOrder"

    # ── Formulario de pedido: requiere producto ──────────────────────────
    Dado que el usuario está en la página de nuevo pedido
    Cuando el usuario ingresa solo email "cliente@test.com" en el formulario de pedido
    Y el usuario intenta enviar el formulario de pedido
    Entonces la URL del formulario de pedido debería contener "/addOrder"

    # ── Formulario de pedido: valida formato de email ────────────────────
    Dado que el usuario está en la página de nuevo pedido
    Cuando el usuario ingresa email inválido "no-es-email" y producto "Laptop" en el formulario de pedido
    Y el usuario intenta enviar el formulario de pedido
    Entonces la URL del formulario de pedido debería contener "/addOrder"
