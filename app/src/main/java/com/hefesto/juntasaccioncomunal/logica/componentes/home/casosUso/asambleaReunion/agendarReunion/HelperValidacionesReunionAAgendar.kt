package com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.asambleaReunion.agendarReunion

import com.hefesto.juntasaccioncomunal.logica.excepciones.AsuntoAsambleaNoValidoExcepcion
import com.hefesto.juntasaccioncomunal.logica.excepciones.AsuntoAsambleaVacioExcepcion
import com.hefesto.juntasaccioncomunal.logica.excepciones.FalloCreacionDetalleReunionAAgendarModelExcepcion
import com.hefesto.juntasaccioncomunal.logica.excepciones.NoHaSeleccionadoLaFechaDeLaReunionExcepcion
import com.hefesto.juntasaccioncomunal.logica.excepciones.NoHaSeleccionadoLaHoraDeLaReunionExcepcion
import com.hefesto.juntasaccioncomunal.logica.excepciones.NoHaSeleccionadoTipoReunionExcepcion
import com.hefesto.juntasaccioncomunal.logica.excepciones.NoTieneElMinimoDePuntosDeUnaAsambleaExcepcion
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.agendarReunion.DetalleReunionAAgendarModel

class HelperValidacionesReunionAAgendar constructor(
    private var detalleReunionAAgendarModel: DetalleReunionAAgendarModel?
) {
    //region variables
    private lateinit var modelo : DetalleReunionAAgendarModel
    //endregion
    fun validarModelo() : HelperValidacionesReunionAAgendar {
        modelo = detalleReunionAAgendarModel?:throw FalloCreacionDetalleReunionAAgendarModelExcepcion()
        return this
    }

    fun validarTitulo() : HelperValidacionesReunionAAgendar {
        if (modelo.tituloReunion.isNullOrEmpty()) throw AsuntoAsambleaVacioExcepcion()
        if (modelo.tituloReunion!!.length < 5 ) throw AsuntoAsambleaNoValidoExcepcion()
        return this
    }

    fun validarTipoReunion() : HelperValidacionesReunionAAgendar{
        if(modelo.tipoReunion == null) throw NoHaSeleccionadoTipoReunionExcepcion()
        return  this
    }

    fun validarFecha(): HelperValidacionesReunionAAgendar {
        if(modelo.fechaReunion == null) throw NoHaSeleccionadoLaFechaDeLaReunionExcepcion()
        return  this
    }

    fun validarHora(): HelperValidacionesReunionAAgendar {
        if(modelo.horaReunion == null) throw NoHaSeleccionadoLaHoraDeLaReunionExcepcion()
        return this
    }

    fun validarListaPuntos() : HelperValidacionesReunionAAgendar {
        if (modelo.puntosReunion.isEmpty()) throw NoTieneElMinimoDePuntosDeUnaAsambleaExcepcion()
        return this
    }
}