package com.hefesto.juntasaccioncomunal.logica.excepciones

import com.hefesto.juntasaccioncomunal.R

class FalloCreacionDetalleReunionAAgendarModelExcepcion : LogicaExcepcion(
    mensaje = "fallo la creacion del modelo detalleReunionAAgendarModel",
    stringResMensaje = R.string.surgio_un_problema_al_momento_de_agendar_reunion,
    stringResTitulo = R.string.agendar_reunion,
    tipoExcepcion = TiposExcepciones.GENERADO_SISTEMA
)

//region agendar reunion

class AsuntoAsambleaVacioExcepcion : LogicaExcepcion(
    mensaje = "El asunto de la reunion esta vacio",
    stringResMensaje = R.string.asunto_reunionasamblea_vacio,
    stringResTitulo = R.string.agendar_reunion,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)

class AsuntoAsambleaNoValidoExcepcion : LogicaExcepcion(
    mensaje = "El asunto de la reunion es invalido.",
    stringResMensaje = R.string.asunto_reunionasamblea_invalido,
    stringResTitulo = R.string.agendar_reunion,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)

class NoHaSeleccionadoTipoReunionExcepcion : LogicaExcepcion(
    mensaje = "Sin seleccion tipo reunion",
    stringResMensaje = R.string.no_ha_seleccionado_tipo_reunion,
    stringResTitulo = R.string.agendar_reunion,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)

class NoHaSeleccionadoLaFechaDeLaReunionExcepcion : LogicaExcepcion(
    mensaje = "No se ha seleccionado la fecha de la reunion",
    stringResMensaje = R.string.no_ha_seleccionado_fecha_reunion,
    stringResTitulo = R.string.agendar_reunion,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)

class NoHaSeleccionadoLaHoraDeLaReunionExcepcion : LogicaExcepcion(
    mensaje = "No ha seleccionado la hora de la reunion",
    stringResMensaje = R.string.no_ha_seleccionado_hora_reunion,
    stringResTitulo = R.string.agendar_reunion,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)

class NoTieneElMinimoDePuntosDeUnaAsambleaExcepcion : LogicaExcepcion(
    mensaje = "No ha ingresado el numero minimo de puntos para la reunion",
    stringResMensaje = R.string.no_ha_ingresado_numero_minimo_de_puntos,
    stringResTitulo = R.string.agendar_reunion,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)
//endregion