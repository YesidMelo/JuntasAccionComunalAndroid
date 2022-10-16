package com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.registroAfiliado

import com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.registroAfiliado.helpers.HelperValidacionContacto
import com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.registroAfiliado.helpers.HelperValidacionDatosBasicos
import com.hefesto.juntasaccioncomunal.logica.modelos.home.registroAfiliado.CompiladoInformacionAfiliadoParaRegistroModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.registroAfiliado.ContactoParaRegistrarModel
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
        validarContacto(contactoParaRegistrarModel = compiladoInformacionAfiliadoParaRegistroModel.contactoParaRegistrarModel)
        delay(5000)
        emit(true)
    }

    //region metodos privados
    //region validar datos basicos
    private fun validarDatosBasicos(datosBasicosParaRegistrarModel: DatosBasicosParaRegistrarModel?) = HelperValidacionDatosBasicos().validar(datosBasicosParaRegistrarModel = datosBasicosParaRegistrarModel)
    private fun validarContacto(contactoParaRegistrarModel: ContactoParaRegistrarModel?) = HelperValidacionContacto().validar(contactoParaRegistrarModel = contactoParaRegistrarModel)
    //endregion
    //endregion


}