package com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.JacDao
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.mapper.convertirAJACEntity
import com.hefesto.juntasaccioncomunal.logica.excepciones.EstaJuntaYaSeEncuentraRegistradaException
import com.hefesto.juntasaccioncomunal.logica.excepciones.LogicaExcepcion
import com.hefesto.juntasaccioncomunal.logica.modelos.login.registrarJAC.JACRegistroModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HelperRegistroJAC constructor(
    private val jacRegistroModel: JACRegistroModel,
    private val registroExitosoJAC : MutableLiveData<Boolean?>,
    private val escuchadorExcepciones: MutableLiveData<LogicaExcepcion?>,
    private val jacDao: JacDao
) {

    fun registrarJAC(){
        GlobalScope.launch {
            registroExitosoJAC.postValue(null)
            if (existeCorreo()) {
                registroExitosoJAC.postValue(false)
                return@launch
            }
            adicionarRegistro()
            delay(5000)
            registroExitosoJAC.postValue(true)
        }
    }

    //region metodos privados
    private fun existeCorreo(): Boolean {
        val jacEntity = jacDao.encontrarRegistro(codigoJAC = jacRegistroModel.CodigoJAC!! , email = jacRegistroModel.Correo!!)
        if(jacEntity != null) {
            escuchadorExcepciones.postValue(EstaJuntaYaSeEncuentraRegistradaException())
            return true
        }
        return false
    }

    private fun adicionarRegistro() {
        val jacEntity = jacRegistroModel.convertirAJACEntity()
        jacDao.insertarElemento(elemento = jacEntity)
    }

    //endregion

}