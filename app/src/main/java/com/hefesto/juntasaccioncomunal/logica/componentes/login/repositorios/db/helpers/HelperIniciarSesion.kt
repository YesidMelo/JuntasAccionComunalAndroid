package com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.helpers

import androidx.lifecycle.MutableLiveData
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.JacDao
import com.hefesto.juntasaccioncomunal.logica.excepciones.LogicaExcepcion
import com.hefesto.juntasaccioncomunal.logica.excepciones.RevisaCredencialesExcepcion
import com.hefesto.juntasaccioncomunal.logica.excepciones.UsuarioNoEstaRegistradoExcepcion
import com.hefesto.juntasaccioncomunal.logica.modelos.login.iniciarSesion.UsuarioInicioSesionModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HelperIniciarSesion constructor(
    private var inicioSesionExitosa : MutableLiveData<Boolean?>,
    private var usuarioInicioSesionModel: UsuarioInicioSesionModel,
    private var escuchadorExcepciones: MutableLiveData<LogicaExcepcion?>,
    private val jacDao: JacDao
) {

    fun iniciarSesion() {
        GlobalScope.launch {
            inicioSesionExitosa.postValue(null)
            delay(5000)
            if(!elCorreoExiste()) return@launch
            if(!lasCredencialesSonCorrectas()) return@launch
            inicioSesionExitosa.postValue(true)
        }
    }

    //region metodos privados
    private fun elCorreoExiste() : Boolean {
        val jacEntity = jacDao.encontrarRegistroPorCorreo(email = usuarioInicioSesionModel.correo!!)
        if (jacEntity == null) {
            inicioSesionExitosa.postValue(false)
            escuchadorExcepciones.postValue(UsuarioNoEstaRegistradoExcepcion())
            return false
        }
        return true
    }

    private fun lasCredencialesSonCorrectas() : Boolean{
        val jacEntity = jacDao.encontrarRegistroPorCorreoYContrasenia(email = usuarioInicioSesionModel.correo!!, contrasenia = usuarioInicioSesionModel.contrasenia!!)
        if (jacEntity == null) {
            inicioSesionExitosa.postValue(false)
            escuchadorExcepciones.postValue(RevisaCredencialesExcepcion())
            return false
        }
        return true
    }
    //endregion

}