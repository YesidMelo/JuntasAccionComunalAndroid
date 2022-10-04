package com.hefesto.juntasaccioncomunal.logica.excepciones

import com.hefesto.juntasaccioncomunal.R

class ApellidoAfiliadoNoValidoExcepcion : LogicaExcepcion(
    mensaje = "El apellido no es valido",
    stringResMensaje = R.string.apellido_invalido,
    stringResTitulo = R.string.registro_afiliado_jac,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)

class CampoApellidosVacioRegistroAfiliadosExcepcion : LogicaExcepcion(
    mensaje = "No ha llenado el campo de Apellidos",
    stringResMensaje = R.string.no_ha_ingresado_apellidos,
    stringResTitulo = R.string.registro_afiliado_jac,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)

class CampoNombresVacioRegistroAfiliadosExcepcion : LogicaExcepcion(
    mensaje = "No ha llenado el campo de nombres",
    stringResMensaje = R.string.no_ha_ingresado_nombre,
    stringResTitulo = R.string.registro_afiliado_jac,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)

class CampoTelefonoVacionRegistroAfiliadosExcepcion : LogicaExcepcion(
    mensaje = "No ha llenado el campo de nombres",
    stringResMensaje = R.string.campo_telefono_vacio,
    stringResTitulo = R.string.registro_afiliado_jac,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)

class CodigoJACNoValidoRegistroJACExcepcion : LogicaExcepcion(
    mensaje = "El codigo de la JAC no es valido",
    stringResMensaje = R.string.el_codigo_jac_invalido,
    stringResTitulo = R.string.registro_jac,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)

class ContraseniaInvalidaIniciarSesionExcepcion : LogicaExcepcion(

    mensaje = "No es una contraseña valida",
    stringResMensaje = R.string.la_contrasenia_es_invalida,
    stringResTitulo = R.string.iniciar_sesion,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)

class ContraseniaInvalidaRegistrarAfiliadoExcepcion : LogicaExcepcion(

    mensaje = "No es una contraseña valida",
    stringResMensaje = R.string.la_contrasenia_es_invalida,
    stringResTitulo = R.string.registro_afiliado_jac,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)

class ContraseniaVaciaIniciarSesionExcepcion : LogicaExcepcion(
    mensaje = "No ha ingresado una contrasenia",
    stringResMensaje = R.string.el_campo_contrasenia_vacio,
    stringResTitulo = R.string.iniciar_sesion,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)

class ContraseniaVaciaRegistroAfiliadoExcepcion : LogicaExcepcion(
    mensaje = "No ha ingresado una contrasenia",
    stringResMensaje = R.string.el_campo_contrasenia_vacio,
    stringResTitulo = R.string.registro_afiliado_jac,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)

class CorreoInvalidoLoginExcepcion :  LogicaExcepcion(
    mensaje = "No es un correo valido",
    stringResMensaje = R.string.no_ha_ingresado_un_correo_electronico_valido,
    stringResTitulo = R.string.iniciar_sesion,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)

class CorreoInvalidoRegistroJacExcepcion :  LogicaExcepcion(
    mensaje = "No es un correo valido",
    stringResMensaje = R.string.no_ha_ingresado_un_correo_electronico_valido,
    stringResTitulo = R.string.registrar_jac,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)

class CorreoInvalidoRegistroAfiliadoExcepcion :  LogicaExcepcion(
    mensaje = "No es un correo valido",
    stringResMensaje = R.string.no_ha_ingresado_un_correo_electronico_valido,
    stringResTitulo = R.string.registro_afiliado_jac,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)

class DireccinoInvalidoRegistroAfiliadoExcepcion :  LogicaExcepcion(
    mensaje = "No es un correo valido",
    stringResMensaje = R.string.no_ingreso_una_direccion_correcta,
    stringResTitulo = R.string.registro_afiliado_jac,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)

class ElCampoContraseniaYRepetirContraseniaNoCoincidenRegistrarJacException : LogicaExcepcion(
    mensaje = "El campo repetir contrasenia es valido",
    stringResMensaje = R.string.los_campos_contrasenia_repetir_contrasenia_no_coinciden,
    stringResTitulo = R.string.registro_jac,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)

class ElCampoContraseniaYRepetirContraseniaNoCoincidenRegistrarAfiliadoException : LogicaExcepcion(
    mensaje = "El campo repetir contrasenia es valido",
    stringResMensaje = R.string.los_campos_contrasenia_repetir_contrasenia_no_coinciden,
    stringResTitulo = R.string.registro_afiliado_jac,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)

class ElTelefonoNoEsValidoRegistroAfiliadoExcepcion : LogicaExcepcion(
    mensaje = "El telefono no es valido",
    stringResMensaje = R.string.el_telefono_no_es_valido,
    stringResTitulo = R.string.registro_afiliado_jac,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)

class EstaJuntaYaSeEncuentraRegistradaException : LogicaExcepcion(
    mensaje = "JAC ya esta registrada",
    stringResMensaje = R.string.jac_ya_registrada,
    stringResTitulo = R.string.registro_jac,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)

class NoCumpleConLaFechaMinimaParaLaInscripcionExcepcion : LogicaExcepcion(
    mensaje = "No tiene la edad minima para la inscripcion como afiliado",
    stringResMensaje = R.string.no_cumple_edad_minima_afiliacion,
    stringResTitulo = R.string.registro_jac,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)

class NoHaIngresadoUnaDireccionAfiliadoExcepcion : LogicaExcepcion(
    mensaje = "no ha ingresado una direccoin",
    stringResMensaje = R.string.no_ha_ingresado_una_direccion,
    stringResTitulo = R.string.registro_afiliado_jac,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)

class NoHaIngresadoUnaFechaDeNacimientoExcepcion : LogicaExcepcion(
    mensaje = "No ha ingresado una fecha de nacimiento",
    stringResMensaje = R.string.no_ha_ingresado_una_fecha_nacimiento,
    stringResTitulo = R.string.registro_afiliado_jac,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)

class NoHaIngresadoUnaJacRegistradaExcepcion : LogicaExcepcion(
    mensaje = "No ha seleccionado una jac registrada",
    stringResMensaje = R.string.no_ingreso_jac_existente,
    stringResTitulo = R.string.registro_afiliado_jac,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)

class NoHaIngresadoUnNumeroDeDocumentoValidoAfiliadoExcepcion : LogicaExcepcion(
    mensaje = "No ha ingresado un numero de documento",
    stringResMensaje = R.string.no_ha_ingresado_numero_documento,
    stringResTitulo = R.string.registro_afiliado_jac,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)

class NombreAfiliadoNoValidoExcepcion : LogicaExcepcion(
    mensaje = "El nombre del afiliado no es valido",
    stringResMensaje = R.string.el_nombre_afiliado_invalido,
    stringResTitulo = R.string.registro_jac,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)

class NombreJACNoValidoExcepcion :  LogicaExcepcion(
    mensaje = "El nombre de la jac no es valido",
    stringResMensaje = R.string.el_nombre_jac_invalido,
    stringResTitulo = R.string.registro_jac,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)

class NoHaSeleccionadoUnTipoDeDocumentoExcepcion :  LogicaExcepcion(
    mensaje = "No ha seleccionado un tipo de documentp",
    stringResMensaje = R.string.no_ha_seleccionado_un_tipo_documento,
    stringResTitulo = R.string.registro_jac,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)

class NoHaSeleccionadoUnTipoDeTelefonoExcepcion :  LogicaExcepcion(
    mensaje = "No ha seleccionado un tipo de documentp",
    stringResMensaje = R.string.no_ha_seleccionado_un_tipo_de_telefono,
    stringResTitulo = R.string.registro_jac,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)

class NumeroDocumentoInvalidoAfiliadoExcepcion :  LogicaExcepcion(
    mensaje = "El numero de documento no es valido ",
    stringResMensaje = R.string.numero_documento_invalido,
    stringResTitulo = R.string.registro_afiliado_jac,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)

class RepetirContraseniaRegistrarJACNoEsValidoException: LogicaExcepcion(
    mensaje = "El campo repetir contrasenia es valido",
    stringResMensaje = R.string.repetir_contrasenia_no_es_valido,
    stringResTitulo = R.string.registro_jac,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)

class RepetirContraseniaRegistrarAfiliadoNoEsValidoException: LogicaExcepcion(
    mensaje = "El campo repetir contrasenia es valido",
    stringResMensaje = R.string.repetir_contrasenia_no_es_valido,
    stringResTitulo = R.string.registro_afiliado_jac,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)

class RepetirContraseniaRegistroJACVacioExcepcion: LogicaExcepcion(
    mensaje = "El campo repetir contrasenia es valido",
    stringResMensaje = R.string.el_campo_repetir_contrasenia_esta_vacio,
    stringResTitulo = R.string.registro_jac,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)

class RepetirContraseniaRegistroAfiliadoVacioExcepcion: LogicaExcepcion(
    mensaje = "El campo repetir contrasenia es valido",
    stringResMensaje = R.string.el_campo_repetir_contrasenia_esta_vacio,
    stringResTitulo = R.string.registro_afiliado_jac,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)

class RevisaCredencialesExcepcion : LogicaExcepcion(
    mensaje = "El usuario o la contraseña estan equivocadas. Vuelve a intentarlo",
    stringResMensaje = R.string.usuario_o_contrasenia_equivocado,
    stringResTitulo = R.string.iniciar_sesion,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)

class SinCorreoLoginExcepcion : LogicaExcepcion (
    mensaje = "No ha ingresado un correo electronico valido",
    stringResMensaje = R.string.el_campo_del_correo_vacio,
    stringResTitulo = R.string.iniciar_sesion,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)

class SinCorreoRegistrarJACExcepcion : LogicaExcepcion (
    mensaje = "No ha ingresado un correo electronico valido",
    stringResMensaje = R.string.el_campo_del_correo_vacio,
    stringResTitulo = R.string.registrar_jac,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)

class SinCorreoRegistrarAfiliadoExcepcion : LogicaExcepcion (
    mensaje = "No ha ingresado un correo electronico valido",
    stringResMensaje = R.string.el_campo_del_correo_vacio,
    stringResTitulo = R.string.registro_afiliado_jac,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)

class UsuarioNoEstaRegistradoExcepcion : LogicaExcepcion(
    mensaje = "Usuario no esta registrado. Revisar credenciales",
    stringResMensaje = R.string.usuario_no_registrado,
    stringResTitulo = R.string.iniciar_sesion,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)