package com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.asambleaReunion.guardarActa

import com.hefesto.juntasaccioncomunal.logica.excepciones.EstaReunionNoTienePuntosADiscutirExcepcion
import com.hefesto.juntasaccioncomunal.logica.excepciones.LaListaDeAsistenciaEstaVaciaExcepcion
import com.hefesto.juntasaccioncomunal.logica.excepciones.NoHaIngresadoDetalleAAlgunPuntoExcepcion
import com.hefesto.juntasaccioncomunal.logica.excepciones.NoHaIngresadoUnaCantidadDeVotosValidaExcepcion
import com.hefesto.juntasaccioncomunal.logica.excepciones.NoHaIngresadoVotosAFavorPuntoExcepcion
import com.hefesto.juntasaccioncomunal.logica.excepciones.NoHaIngresadoVotosEnContraPuntoExcepcion
import com.hefesto.juntasaccioncomunal.logica.excepciones.NoHaSeleccionadoHoraFinReunionExcepcion
import com.hefesto.juntasaccioncomunal.logica.excepciones.NoHaSeleccionadoHoraFinValidaExcepcion
import com.hefesto.juntasaccioncomunal.logica.excepciones.NoHaSeleccionadoHoraInicioReunionExcepcion
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.crearActa.AfiliadoActaAsistenciaModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.crearActa.ReunionAsambleaCreacionActaModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.FormatosFecha
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.TipoReunion
import com.hefesto.juntasaccioncomunal.logica.utilidades.extenciones.convertirADate
import com.hefesto.juntasaccioncomunal.logica.utilidades.extenciones.convertirAFormato
import com.hefesto.juntasaccioncomunal.logica.utilidades.extenciones.esMenorAFecha
import com.hefesto.juntasaccioncomunal.logica.utilidades.extenciones.esMenorAFechaConMinimo
import java.util.*

class HelperValidacionesActaAGuardar constructor(
    private val asistencia: MutableList<AfiliadoActaAsistenciaModel>,
    private val detalleReunion: ReunionAsambleaCreacionActaModel
) {

    fun horaInicioSeleccionada() : HelperValidacionesActaAGuardar {
        val fecha = detalleReunion.horaInicio?:throw NoHaSeleccionadoHoraInicioReunionExcepcion()
        return this
    }

    fun horaFinSeleccionada() : HelperValidacionesActaAGuardar {
        val fecha = detalleReunion.horaFin?: throw NoHaSeleccionadoHoraFinReunionExcepcion()
        val fechaInicio = detalleReunion.horaInicio!!.convertirAFormato(formato = FormatosFecha.HORA_MINUTO_12H).convertirADate(formatoEntrada = FormatosFecha.HORA_MINUTO_12H)
        val fechaFin = detalleReunion.horaFin!!.convertirAFormato(formato = FormatosFecha.HORA_MINUTO_12H).convertirADate(formatoEntrada = FormatosFecha.HORA_MINUTO_12H)
        if (!fechaInicio!!.esMenorAFecha(fecha = fechaFin!!)) throw NoHaSeleccionadoHoraFinValidaExcepcion()
        if (!fechaInicio.esMenorAFechaConMinimo(fecha = fechaFin, identificador = Calendar.HOUR, tiempo = 1)) throw NoHaSeleccionadoHoraFinValidaExcepcion()
        return this
    }

    fun completoElDetalleTodosLosPuntos() : HelperValidacionesActaAGuardar {
        val listaPuntos = detalleReunion.listaPuntos?: throw EstaReunionNoTienePuntosADiscutirExcepcion()
        for (punto in listaPuntos) {
            val detalle = punto.detallePunto?: throw NoHaIngresadoDetalleAAlgunPuntoExcepcion()
            if (detalle.isEmpty()) throw NoHaIngresadoDetalleAAlgunPuntoExcepcion()
        }
        return this
    }

    fun ingresoVotosAFavorYEncontra() : HelperValidacionesActaAGuardar{
        if(!seDebenTomarEnCuentaLosVotos()) return this
        val listaPuntos = detalleReunion.listaPuntos?: throw EstaReunionNoTienePuntosADiscutirExcepcion()
        for (punto  in listaPuntos) {
            if (punto.votosAFavor == null) throw NoHaIngresadoVotosAFavorPuntoExcepcion()
            if (punto.votosEnContra == null) throw NoHaIngresadoVotosEnContraPuntoExcepcion()
            if (punto.votosAFavor!! < 0) throw NoHaIngresadoUnaCantidadDeVotosValidaExcepcion()
            if (punto.votosEnContra!! < 0) throw NoHaIngresadoUnaCantidadDeVotosValidaExcepcion()
        }
        return  this
    }

    fun asistencia(): HelperValidacionesActaAGuardar {
        if (asistencia.isEmpty()) throw LaListaDeAsistenciaEstaVaciaExcepcion()
        return this
    }

    //region metodos privados
    private fun seDebenTomarEnCuentaLosVotos() :  Boolean{
        if(detalleReunion.tipoReunion == TipoReunion.REUNION_DIRECTIVA_DECISORIA) return true
        if(detalleReunion.tipoReunion == TipoReunion.ASAMBLEA_DECISORIA) return true
        return false
    }
    //endregion

}