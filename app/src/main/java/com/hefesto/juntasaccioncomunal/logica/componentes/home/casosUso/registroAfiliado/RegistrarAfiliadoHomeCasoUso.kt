package com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.registroAfiliado

import com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.registroAfiliado.helpers.HelperValidacionDatosBasicos
import com.hefesto.juntasaccioncomunal.logica.modelos.home.registroAfiliado.CompiladoInformacionAfiliadoParaRegistroModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.registroAfiliado.DatosBasicosParaRegistrarModel
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
        delay(5000)
        emit(true)
    }

    //region metodos privados
    //region validar datos basicos
    private fun validarDatosBasicos(datosBasicosParaRegistrarModel: DatosBasicosParaRegistrarModel?) = HelperValidacionDatosBasicos().validar(datosBasicosParaRegistrarModel = datosBasicosParaRegistrarModel)
    //endregion
    //endregion


}