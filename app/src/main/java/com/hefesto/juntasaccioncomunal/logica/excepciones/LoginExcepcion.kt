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
    mensaje = "No es una contrasenai valida",
    stringResMensaje = R.string.la_contrasenia_es_invalida,
    stringResTitulo = R.string.iniciar_sesion,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)