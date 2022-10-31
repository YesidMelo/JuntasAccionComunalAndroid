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

class NoTieneConvocantesAsambleaReunionExcepcion : LogicaExcepcion(
    mensaje = "No ha ingresado los convocantes para la reunion.",
    stringResMensaje = R.string.no_ha_ingresado_lista_convocantes,
    stringResTitulo = R.string.agendar_reunion,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)

class NoHaIngresadoUnSitioReunionExcepcion : LogicaExcepcion(
    mensaje = "No ha ingresado sitio",
    stringResMensaje = R.string.no_ha_ingresado_sitio_reunion,
    stringResTitulo = R.string.agendar_reunion,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)
//endregion

//region Guardar acta
class NoHaSeleccionadoHoraInicioReunionExcepcion: LogicaExcepcion(
    mensaje = "Hora inicio reunion/asamblea null",
    stringResMensaje = R.string.no_ha_seleccionado_hora_inicio_reunion,
    stringResTitulo = R.string.crear_acta_reunion,
    TiposExcepciones.GENERADO_USUARIO
)

class NoHaSeleccionadoHoraFinReunionExcepcion: LogicaExcepcion(
    mensaje = "Hora fin reunion/asamblea null",
    stringResMensaje = R.string.no_ha_seleccionado_hora_fin_reunion,
    stringResTitulo = R.string.crear_acta_reunion,
    TiposExcepciones.GENERADO_USUARIO
)

class NoHaSeleccionadoHoraFinValidaExcepcion: LogicaExcepcion(
    mensaje = "Hora fin reunion/asamblea menor a hora de inicio",
    stringResMensaje = R.string.no_ha_seleccionado_hora_fin_reunion_valida,
    stringResTitulo = R.string.crear_acta_reunion,
    TiposExcepciones.GENERADO_USUARIO
)

class EstaReunionNoTienePuntosADiscutirExcepcion: LogicaExcepcion(
    mensaje = "Reunion sin puntos a discutir",
    stringResMensaje = R.string.esta_reunion_no_tiene_puntos_ha_discutir,
    stringResTitulo = R.string.crear_acta_reunion,
    TiposExcepciones.GENERADO_USUARIO
)

class NoHaIngresadoDetalleAAlgunPuntoExcepcion: LogicaExcepcion(
    mensaje = "detalle de punto esta null",
    stringResMensaje = R.string.no_ha_ingresado_detalle_a_punto_reunion,
    stringResTitulo = R.string.crear_acta_reunion,
    TiposExcepciones.GENERADO_USUARIO
)

class NoHaIngresadoVotosAFavorPuntoExcepcion: LogicaExcepcion(
    mensaje = "Sin votos a favor",
    stringResMensaje = R.string.no_ha_ingresado_votos_a_favor,
    stringResTitulo = R.string.crear_acta_reunion,
    TiposExcepciones.GENERADO_USUARIO
)

class NoHaIngresadoVotosEnContraPuntoExcepcion: LogicaExcepcion(
    mensaje = "Sin votos en contra",
    stringResMensaje = R.string.no_ha_ingresado_votos_en_contra,
    stringResTitulo = R.string.crear_acta_reunion,
    TiposExcepciones.GENERADO_USUARIO
)

class NoHaIngresadoUnaCantidadDeVotosValidaExcepcion: LogicaExcepcion(
    mensaje = "Sin votos en contra",
    stringResMensaje = R.string.no_ha_ingresado_una_cantidad_de_votos_valida,
    stringResTitulo = R.string.crear_acta_reunion,
    TiposExcepciones.GENERADO_USUARIO
)

class LaListaDeAsistenciaEstaVaciaExcepcion: LogicaExcepcion(
    mensaje = "Lista asistencia vacia",
    stringResMensaje = R.string.no_ha_seleccionado_los_asistentes_a_la_reunion,
    stringResTitulo = R.string.crear_acta_reunion,
    TiposExcepciones.GENERADO_USUARIO
)

class NoHaIngresadoElNumeroDeActaExcepcion: LogicaExcepcion(
    mensaje = "Sin numero acta",
    stringResMensaje = R.string.no_ha_ingresado_numero_acta,
    stringResTitulo = R.string.crear_acta_reunion,
    TiposExcepciones.GENERADO_USUARIO
)

//endregion