package com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.helpers.registroAfiliado

import androidx.lifecycle.MutableLiveData
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.AfiliadoDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.Afiliado_Jac_EstadoAfiliacionDao
import com.hefesto.juntasaccioncomunal.logica.excepciones.EsteAfiliadoYaSeEncuentraRegistrado
import com.hefesto.juntasaccioncomunal.logica.excepciones.LogicaExcepcion
import com.hefesto.juntasaccioncomunal.logica.modelos.login.registrarAfiliado.AfiliadoARegistrarModel
import javax.inject.Inject

class HelperValidarExisteUsuarioEnJAC constructor(
    @JvmField @Inject var afiliadoDao: AfiliadoDao,
    @JvmField @Inject var afiliadoJacEstadoafiliaciondao: Afiliado_Jac_EstadoAfiliacionDao
) {

    //region variables
    private lateinit var afiliadoARegistrarModel: AfiliadoARegistrarModel
    private lateinit var escuchadorExcepciones: MutableLiveData<LogicaExcepcion?>
    //endregion

    fun conAfiliadoARegistrarModel (afiliadoARegistrarModel: AfiliadoARegistrarModel) : HelperValidarExisteUsuarioEnJAC {
        this.afiliadoARegistrarModel = afiliadoARegistrarModel
        return this
    }

    fun conEscuchadorExcepciones(escuchadorExcepciones: MutableLiveData<LogicaExcepcion?>) : HelperValidarExisteUsuarioEnJAC {
        this.escuchadorExcepciones = escuchadorExcepciones
        return this
    }

    suspend fun existeUsuario() : Boolean {
        val afiliadoId = afiliadoDao.traerIdPorTipoDocumentoYDocumento(
            tipoDocumento = afiliadoARegistrarModel.tipoDocumento!!.tipoDocumento.traerId(),
            documento = afiliadoARegistrarModel.numeroDocumento!!
        )?: return false

        val registroId = afiliadoJacEstadoafiliaciondao.traerIdRegistroConAfiliadoIdYJACID(
            afiliadoId = afiliadoId,
            jacId = afiliadoARegistrarModel.jacSeleccionado!!.id,
        )

        if(registroId != null) {
            escuchadorExcepciones.postValue(EsteAfiliadoYaSeEncuentraRegistrado())
            return true
        }
        return false
    }
}