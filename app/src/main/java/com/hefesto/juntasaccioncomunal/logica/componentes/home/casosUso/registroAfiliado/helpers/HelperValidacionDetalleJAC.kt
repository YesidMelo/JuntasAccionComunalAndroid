package com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.registroAfiliado.helpers

import com.hefesto.juntasaccioncomunal.logica.excepciones.NoHaCreadoModeloDeDetalleEnJACAfiliadoParaRegistroExcepcion
import com.hefesto.juntasaccioncomunal.logica.excepciones.NoHaSeleccionadoEstadoAfiliadoHomeExcepcion
import com.hefesto.juntasaccioncomunal.logica.excepciones.NoHaSeleccionadoComiteDeLaJACExcepcion
import com.hefesto.juntasaccioncomunal.logica.modelos.home.registroAfiliado.DetalleEnJACParaRegistroModel

class HelperValidacionDetalleJAC {

    fun validar(detalleEnJACParaRegistroModel: DetalleEnJACParaRegistroModel?) {
        val detalleJAC = detalleEnJACParaRegistroModel?: throw NoHaCreadoModeloDeDetalleEnJACAfiliadoParaRegistroExcepcion()
        validarComite(detalleEnJACParaRegistroModel = detalleJAC)
        validarEstadosAfiliado(detalleEnJACParaRegistroModel = detalleJAC)
    }

    //region metodos privados

    private fun validarComite(detalleEnJACParaRegistroModel: DetalleEnJACParaRegistroModel) {
        if (detalleEnJACParaRegistroModel.comitesEnJAC == null) throw NoHaSeleccionadoComiteDeLaJACExcepcion()
    }

    private fun validarEstadosAfiliado(detalleEnJACParaRegistroModel: DetalleEnJACParaRegistroModel) {
        if (detalleEnJACParaRegistroModel.estadoAfiliacion == null) throw NoHaSeleccionadoEstadoAfiliadoHomeExcepcion()
    }

    //endregion
}