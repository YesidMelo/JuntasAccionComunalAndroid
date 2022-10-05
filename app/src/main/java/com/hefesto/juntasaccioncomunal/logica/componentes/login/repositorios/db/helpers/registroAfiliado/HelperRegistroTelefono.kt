package com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.helpers.registroAfiliado

import androidx.lifecycle.MutableLiveData
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.AfiliadoDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.Afiliado_Telefono_Dao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.Telefono_Dao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.Afiliado_Telefono_Entity
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.mapper.traerTelefonoEntity
import com.hefesto.juntasaccioncomunal.logica.excepciones.LogicaExcepcion
import com.hefesto.juntasaccioncomunal.logica.modelos.login.registrarAfiliado.AfiliadoARegistrarModel
import javax.inject.Inject

class HelperRegistroTelefono constructor(
    @JvmField @Inject var afiliadoDao: AfiliadoDao,
    @JvmField @Inject var afiliadoTelefonoDao: Afiliado_Telefono_Dao,
    @JvmField @Inject var telefonoDao: Telefono_Dao
) {

    //region variables
    private lateinit var afiliadoARegistrarModel: AfiliadoARegistrarModel
    private lateinit var escuchadorExcepciones: MutableLiveData<LogicaExcepcion?>
    //endregion

    fun conAfiliadoARegistrar(afiliadoARegistrarModel: AfiliadoARegistrarModel) : HelperRegistroTelefono {
        this.afiliadoARegistrarModel = afiliadoARegistrarModel
        return this
    }

    fun conEscuchadorExcepciones(escuchadorExcepciones: MutableLiveData<LogicaExcepcion?>) : HelperRegistroTelefono {
        this.escuchadorExcepciones = escuchadorExcepciones
        return this
    }

    suspend fun guardarTelefono() {
        registrarTelefonoEntity()
        registrarRelacionAfiliadoTelefonoEntity()
    }

    //region metodos privados
    private fun registrarTelefonoEntity() {
        val telefonoEntity = afiliadoARegistrarModel.traerTelefonoEntity()
        telefonoDao.insertarElemento(elemento = telefonoEntity)
    }

    private fun registrarRelacionAfiliadoTelefonoEntity() {
        val afiliadoId = afiliadoDao.traerIdPorTipoDocumentoYDocumento(
            documento = afiliadoARegistrarModel.numeroDocumento!!,
            tipoDocumento = afiliadoARegistrarModel.tipoDocumento!!.tipoDocumento.traerId()
        )

        val numeroTelefonoId = telefonoDao.traerIdPorTipoTelefonoYNumero(
            numeroTelefono = afiliadoARegistrarModel.telefono!!,
            tipoTelefono = afiliadoARegistrarModel.tipoTelefono!!.tipoTelefono.traerId()
        )

        afiliadoTelefonoDao.insertarElemento(elemento = Afiliado_Telefono_Entity(
            afiliadoId = afiliadoId,
            telefonoId = numeroTelefonoId
        ))

    }

    //endregion

}