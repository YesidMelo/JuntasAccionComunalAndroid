package com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers

import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.registroAfiliado.HelperRegistroActualizacionAfiliadoDetalleEnJacHome
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.registroAfiliado.HelperRegistroActualizacionContactoAfiliadoHome
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.registroAfiliado.HelperRegistroActualizacionDatosBasicosAfiliadoHome
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.registroAfiliado.HelperRegistroActualizacionSeguridadAfiliadoHome
import com.hefesto.juntasaccioncomunal.logica.modelos.home.registroAfiliado.CompiladoInformacionAfiliadoParaRegistroModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface HelperRegistrarActualizarAfiliadoDB  {
    fun registrarActualizarAfiliado(compiladoInformacionAfiliadoParaRegistroModel: CompiladoInformacionAfiliadoParaRegistroModel) : Flow<Boolean?>
}

class HelperRegistrarActualizarAfiliadoDBImpl constructor(
    @JvmField @Inject var helperRegistroActualizacionAfiliadoDetalleEnJacHome: HelperRegistroActualizacionAfiliadoDetalleEnJacHome,
    @JvmField @Inject var helperRegistroActualizacionContactoAfiliadoHome: HelperRegistroActualizacionContactoAfiliadoHome,
    @JvmField @Inject var helperRegistroActualizacionDatosBasicosAfiliadoHome: HelperRegistroActualizacionDatosBasicosAfiliadoHome,
    @JvmField @Inject var helperRegistroActualizacionSeguridadAfiliadoHome: HelperRegistroActualizacionSeguridadAfiliadoHome,
) : HelperRegistrarActualizarAfiliadoDB {

    override fun registrarActualizarAfiliado(compiladoInformacionAfiliadoParaRegistroModel: CompiladoInformacionAfiliadoParaRegistroModel): Flow<Boolean?>  = flow {
        delay(5000)
        helperRegistroActualizacionSeguridadAfiliadoHome.registrarSeguridadAfiliado(compiladoInformacionAfiliadoParaRegistroModel = compiladoInformacionAfiliadoParaRegistroModel)
        helperRegistroActualizacionDatosBasicosAfiliadoHome.registarDatosBasicos(compiladoInformacionAfiliadoParaRegistroModel = compiladoInformacionAfiliadoParaRegistroModel)
        helperRegistroActualizacionContactoAfiliadoHome.registrarContacto(compiladoInformacionAfiliadoParaRegistroModel = compiladoInformacionAfiliadoParaRegistroModel)
        helperRegistroActualizacionAfiliadoDetalleEnJacHome.registrarDetalleAfiliadoEnJac(compiladoInformacionAfiliadoParaRegistroModel = compiladoInformacionAfiliadoParaRegistroModel)
        emit(true)
    }

}