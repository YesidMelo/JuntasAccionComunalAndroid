package com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.registroAfiliado

import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.Afiliado_Correo_Dao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.Afiliado_Direccion_Dao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.Afiliado_Telefono_Dao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.DireccionDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.Telefono_Dao
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.mappers.traerAfiliadoCorreoEntity
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.mappers.traerAfiliadoDireccionEntity
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.mappers.traerAfiliadoTelefonoEntity
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.mappers.traerDireccionEntity
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.mappers.traerTelefonoEntity
import com.hefesto.juntasaccioncomunal.logica.modelos.home.registroAfiliado.CompiladoInformacionAfiliadoParaRegistroModel
import javax.inject.Inject

interface HelperRegistroActualizacionContactoAfiliadoHome {
    suspend fun registrarContacto(compiladoInformacionAfiliadoParaRegistroModel: CompiladoInformacionAfiliadoParaRegistroModel)
}

class HelperRegistroActualizacionContactoAfiliadoHomeImpl constructor(
    @JvmField @Inject var afiliadoCorreoDao: Afiliado_Correo_Dao,
    @JvmField @Inject var direccionDao: DireccionDao,
    @JvmField @Inject var afiliadoDireccionDao: Afiliado_Direccion_Dao,
    @JvmField @Inject var telefonoDao: Telefono_Dao,
    @JvmField @Inject var afiliadoTelefonoDao: Afiliado_Telefono_Dao,
): HelperRegistroActualizacionContactoAfiliadoHome{

    override suspend fun registrarContacto(compiladoInformacionAfiliadoParaRegistroModel: CompiladoInformacionAfiliadoParaRegistroModel) {
        registrarCorreoConAfiliado(compiladoInformacionAfiliadoParaRegistroModel = compiladoInformacionAfiliadoParaRegistroModel)
        registrarDireccion(compiladoInformacionAfiliadoParaRegistroModel = compiladoInformacionAfiliadoParaRegistroModel)
        registrarAfiliadoDireccion(compiladoInformacionAfiliadoParaRegistroModel = compiladoInformacionAfiliadoParaRegistroModel)
        registrarTelefono(compiladoInformacionAfiliadoParaRegistroModel = compiladoInformacionAfiliadoParaRegistroModel)
        registrarAfiliadoTelefono(compiladoInformacionAfiliadoParaRegistroModel = compiladoInformacionAfiliadoParaRegistroModel)
    }

    //region metodos privados
    private fun registrarCorreoConAfiliado(compiladoInformacionAfiliadoParaRegistroModel: CompiladoInformacionAfiliadoParaRegistroModel) {
        val afiliadoCorreoEntity = compiladoInformacionAfiliadoParaRegistroModel.traerAfiliadoCorreoEntity()
        val id = afiliadoCorreoDao.traerRegistroId(afiliadoId = afiliadoCorreoEntity.afiliadoId!!, correoId = afiliadoCorreoEntity.correoId!!)
        afiliadoCorreoEntity.registro = id
        afiliadoCorreoDao.insertarElemento(elemento = afiliadoCorreoEntity)
    }

    private fun registrarDireccion(compiladoInformacionAfiliadoParaRegistroModel: CompiladoInformacionAfiliadoParaRegistroModel) {
        val direccionEntity = compiladoInformacionAfiliadoParaRegistroModel.traerDireccionEntity()
        if (direccionEntity.direccionId == null) {
            direccionDao.insertarElemento(elemento = direccionEntity)
            val id = direccionDao.traerIdPorDireccion(direccion = direccionEntity.direccion!!)
            compiladoInformacionAfiliadoParaRegistroModel.contactoParaRegistrarModel?.direccionId = id
            return
        }
        direccionDao.actualizarElemento(elemento = direccionEntity)
    }

    private fun registrarAfiliadoDireccion(compiladoInformacionAfiliadoParaRegistroModel: CompiladoInformacionAfiliadoParaRegistroModel) {
        val afiliadoDireccionEntity = compiladoInformacionAfiliadoParaRegistroModel.traerAfiliadoDireccionEntity()
        if(afiliadoDireccionEntity.registro == null) {
            afiliadoDireccionDao.insertarElemento(elemento = afiliadoDireccionEntity)
            val id = afiliadoDireccionDao.traerNumeroRegistro(afiliadoId = afiliadoDireccionEntity.afiliadoId!!, direccionId = afiliadoDireccionEntity.direccionId!!)
            compiladoInformacionAfiliadoParaRegistroModel.contactoParaRegistrarModel?.afiliadoDireccionId = id
            return
        }
        afiliadoDireccionDao.actualizarElemento(elemento = afiliadoDireccionEntity)
    }

    private fun registrarTelefono(compiladoInformacionAfiliadoParaRegistroModel: CompiladoInformacionAfiliadoParaRegistroModel) {
        val telefonoEntity = compiladoInformacionAfiliadoParaRegistroModel.traerTelefonoEntity()
        if (telefonoEntity.telefonoId == null) {
            telefonoDao.insertarElemento(elemento = telefonoEntity)
            val id = telefonoDao.traerIdPorTipoTelefonoYNumero(tipoTelefono = telefonoEntity.tipoTelefonoId!!, numeroTelefono = telefonoEntity.telefono!!)
            compiladoInformacionAfiliadoParaRegistroModel.contactoParaRegistrarModel?.telefonoId = id
            return
        }
        telefonoDao.actualizarElemento(elemento = telefonoEntity)
    }

    private fun registrarAfiliadoTelefono(compiladoInformacionAfiliadoParaRegistroModel: CompiladoInformacionAfiliadoParaRegistroModel) {
        val afiliadoTelefonoEntity = compiladoInformacionAfiliadoParaRegistroModel.traerAfiliadoTelefonoEntity()
        if(afiliadoTelefonoEntity.registroID == null) {
            afiliadoTelefonoDao.insertarElemento(elemento = afiliadoTelefonoEntity)
            val id = afiliadoTelefonoDao.traerRegistroId(afiliadoId = afiliadoTelefonoEntity.afiliadoId!!, telefonoId = afiliadoTelefonoEntity.telefonoId!!)
            compiladoInformacionAfiliadoParaRegistroModel.contactoParaRegistrarModel?.afiliadoTelefonoId = id
            return
        }
        afiliadoTelefonoDao.actualizarElemento(elemento = afiliadoTelefonoEntity)
    }

    //endregion
}