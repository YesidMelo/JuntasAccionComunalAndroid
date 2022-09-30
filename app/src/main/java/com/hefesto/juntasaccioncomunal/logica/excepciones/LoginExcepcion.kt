package com.hefesto.juntasaccioncomunal.logica.excepciones


import com.hefesto.juntasaccioncomunal.R

class SinCorreoLoginExcepcion() : LogicaExcepcion(
    mensaje = "No ha ingresado un correo electronico valido",
    stringResMensaje = R.string.no_ha_ingresado_un_correo_electronico_valido,
    stringResTitulo = R.string.iniciar_sesion
)