package com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.helpers

import androidx.lifecycle.MutableLiveData
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.CorreoDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.CredencialesSesionDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.JacDao
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.mapper.convertirAJACEntity
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.mapper.traerCorreoEntity
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.mapper.traerCredencialesSesionEntity
import com.hefesto.juntasaccioncomunal.logica.excepciones.EstaJuntaYaSeEncuentraRegistradaException
import com.hefesto.juntasaccioncomunal.logica.excepciones.LogicaExcepcion
import com.hefesto.juntasaccioncomunal.logica.modelos.login.registrarJAC.JACRegistroModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class HelperRegistroJAC constructor(
    @JvmField @Inject var jacDao: JacDao,
    @JvmField @Inject var correoDao: CorreoDao,
    @JvmField @Inject var credencialesSesionDao: CredencialesSesionDao
) {

    //region variables
    private lateinit var jacRegistroModel: JACRegistroModel
    private lateinit var registroExitosoJAC : MutableLiveData<Boolean?>
    private lateinit var escuchadorExcepciones: MutableLiveData<LogicaExcepcion?>
    //endregion

    fun conJACRegistroModel(jacRegistroModel: JACRegistroModel) : HelperRegistroJAC {
        this.jacRegistroModel = jacRegistroModel
        return this
    }

    fun conRegistroExitosoLiveData(registroExitosoJAC : MutableLiveData<Boolean?>) : HelperRegistroJAC {
        this.registroExitosoJAC = registroExitosoJAC
        return this
    }

    fun conEscuchadorExcepciones(escuchadorExcepciones: MutableLiveData<LogicaExcepcion?>) : HelperRegistroJAC {
        this.escuchadorExcepciones = escuchadorExcepciones
        return this
    }

    fun registrarJAC(){
        GlobalScope.launch {
            registroExitosoJAC.postValue(null)
            if (existeCorreo()) {
                registroExitosoJAC.postValue(false)
                return@launch
            }
            adicionarRegistroCorreo()
            adicionarRegistroCredencialesSesion()
            adicionarRegistroJAC()
            delay(5000)
            registroExitosoJAC.postValue(true)
        }
    }

    //region metodos privados
    private fun existeCorreo(): Boolean {
        if(traerCorreoId() != null) {
            escuchadorExcepciones.postValue(EstaJuntaYaSeEncuentraRegistradaException())
            return true
        }
        return false
    }

    private fun adicionarRegistroCorreo() {
        val correosEntity = jacRegistroModel.traerCorreoEntity()
        correoDao.insertarElemento(elemento = correosEntity)
    }

    private fun adicionarRegistroCredencialesSesion() {
        val credencialesSesionEntity = jacRegistroModel.traerCredencialesSesionEntity()
        credencialesSesionEntity.correoId = traerCorreoId()!!
        credencialesSesionDao.insertarElemento(elemento = credencialesSesionEntity)
    }

    private fun adicionarRegistroJAC() {
        val jacEntity = jacRegistroModel.convertirAJACEntity()
        jacEntity.credencialesSesion = traerRegistroCredencialesSesion()
        jacDao.insertarElemento(elemento = jacEntity)
    }

    private fun traerCorreoId() : Int? {
        return correoDao.traerId(correo = jacRegistroModel.Correo!!)
    }

    private fun traerRegistroCredencialesSesion(): Int? {
        return credencialesSesionDao.traerRegistroId(correo = jacRegistroModel.Correo!!)
    }

    //endregion

}