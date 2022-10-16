package com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.registroAfiliado

import com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.registroAfiliado.helpers.HelperValidacionContacto
import com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.registroAfiliado.helpers.HelperValidacionDatosBasicos
import com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.registroAfiliado.helpers.HelperValidacionDetalleJAC
import com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.registroAfiliado.helpers.HelperValidacionSeguridad
import com.hefesto.juntasaccioncomunal.logica.modelos.home.registroAfiliado.CompiladoInformacionAfiliadoParaRegistroModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.registroAfiliado.ContactoParaRegistrarModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.registroAfiliado.DatosBasicosParaRegistrarModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.registroAfiliado.DetalleEnJACParaRegistroModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.registroAfiliado.SeguridadParaRegistroModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface RegistrarAfiliadoHomeCasoUso {
    fun invoke(compiladoInformacionAfiliadoParaRegistroModel: CompiladoInformacionAfiliadoParaRegistroModel) : Flow<Boolean?>
}

class RegistrarAfiliadoHomeCasoUsoImpl : RegistrarAfiliadoHomeCasoUso {

    override fun invoke(compiladoInformacionAfiliadoParaRegistroModel: CompiladoInformacionAfiliadoParaRegistroModel): Flow<Boolean?> = flow {
        emit(null)
        validarDatosBasicos(datosBasicosParaRegistrarModel = compiladoInformacionAfiliadoParaRegistroModel.datosBasicosParaRegistrarModel)
        validarContacto(contactoParaRegistrarModel = compiladoInformacionAfiliadoParaRegistroModel.contactoParaRegistrarModel)
        validarDetalleEnJac(detalleEnJACParaRegistroModel = compiladoInformacionAfiliadoParaRegistroModel.detalleEnJACParaRegistroModel)
        validarSeguridadParaRegistro(seguridadParaRegistroModel = compiladoInformacionAfiliadoParaRegistroModel.seguridadParaRegistroModel)
        delay(5000)
        emit(true)
    }

    //region metodos privados
    //region validar datos basicos
    private fun validarDatosBasicos(datosBasicosParaRegistrarModel: DatosBasicosParaRegistrarModel?) = HelperValidacionDatosBasicos().validar(datosBasicosParaRegistrarModel = datosBasicosParaRegistrarModel)

    private fun validarContacto(contactoParaRegistrarModel: ContactoParaRegistrarModel?) = HelperValidacionContacto().validar(contactoParaRegistrarModel = contactoParaRegistrarModel)

    private fun validarDetalleEnJac(detalleEnJACParaRegistroModel: DetalleEnJACParaRegistroModel?)  = HelperValidacionDetalleJAC().validar(detalleEnJACParaRegistroModel = detalleEnJACParaRegistroModel)

    private fun validarSeguridadParaRegistro(seguridadParaRegistroModel: SeguridadParaRegistroModel?) = HelperValidacionSeguridad().validar(seguridadParaRegistroModel = seguridadParaRegistroModel)
    //endregion
    //endregion


}