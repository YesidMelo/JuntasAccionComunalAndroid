package com.hefesto.juntasaccioncomunal.logica.excepciones


import com.hefesto.juntasaccioncomunal.R

class SinCorreoLoginExcepcion() : LogicaExcepcion (
    mensaje = "No ha ingresado un correo electronico valido",
    stringResMensaje = R.string.el_campo_del_correo_vacio,
    stringResTitulo = R.string.iniciar_sesion,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)

class CorreoInvalidoLoginExcepcion :  LogicaExcepcion(
    mensaje = "No es un correo valido",
    stringResMensaje = R.string.no_ha_ingresado_un_correo_electronico_valido,
    stringResTitulo = R.string.iniciar_sesion,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)

class ContraseniaVaciaExcepcion : LogicaExcepcion(
    mensaje = "No ha ingresado una contrasenia",
    stringResMensaje = R.string.el_campo_contrasenia_vacio,
    stringResTitulo = R.string.iniciar_sesion,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)

class ContraseniaInvalidaExcepcion : LogicaExcepcion(
    mensaje = "No es una contraseña valida",
    stringResMensaje = R.string.la_contrasenia_es_invalida,
    stringResTitulo = R.string.iniciar_sesion,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)

class UsuarioNoEstaRegistradoExcepcion : LogicaExcepcion(
    mensaje = "Usuario no esta registrado. Revisar credenciales",
    stringResMensaje = R.string.usuario_no_registrado,
    stringResTitulo = R.string.iniciar_sesion,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)

class NombreJACNoValidoExcepcion :  LogicaExcepcion(
    mensaje = "El nombre de la jac no es valido",
    stringResMensaje = R.string.el_nombre_jac_invalido,
    stringResTitulo = R.string.registro_jac,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)

class CodigoJACNoValidoExcepcion : LogicaExcepcion(
    mensaje = "El codigo de la JAC no es valido",
    stringResMensaje = R.string.el_codigo_jac_invalido,
    stringResTitulo = R.string.registro_jac,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)

class RepetirContraseniaJACVacioExcepcion: LogicaExcepcion(
    mensaje = "El campo repetir contrasenia es valido",
    stringResMensaje = R.string.el_campo_repetir_contrasenia_esta_vacio,
    stringResTitulo = R.string.registro_jac,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)

class RepetirContraseniaJACNoEsValidoException: LogicaExcepcion(
    mensaje = "El campo repetir contrasenia es valido",
    stringResMensaje = R.string.repetir_contrasenia_no_es_valido,
    stringResTitulo = R.string.registro_jac,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)

class ElCampoContraseniaYRepetirContraseniaNoCoincidenException : LogicaExcepcion(
    mensaje = "El campo repetir contrasenia es valido",
    stringResMensaje = R.string.los_campos_contrasenia_repetir_contrasenia_no_coinciden,
    stringResTitulo = R.string.registro_jac,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)