package com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.helpers

import androidx.lifecycle.MutableLiveData
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.AfiliadoDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.CorreoDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.JacDao
import com.hefesto.juntasaccioncomunal.logica.excepciones.LogicaExcepcion
import com.hefesto.juntasaccioncomunal.logica.excepciones.RevisaCredencialesExcepcion
import com.hefesto.juntasaccioncomunal.logica.excepciones.UsuarioNoEstaRegistradoExcepcion
import com.hefesto.juntasaccioncomunal.logica.modelos.login.iniciarSesion.UsuarioInicioSesionModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class HelperIniciarSesion constructor(
    @JvmField @Inject var jacDao: JacDao,
    @JvmField @Inject var correoDao: CorreoDao
) {

    //region variables
    private lateinit var inicioSesionExitosa : MutableLiveData<Boolean?>
    private lateinit var usuarioInicioSesionModel: UsuarioInicioSesionModel
    private lateinit var escuchadorExcepciones: MutableLiveData<LogicaExcepcion?>
    //endregion

    fun iniciarSesion() {
        GlobalScope.launch {
            inicioSesionExitosa.postValue(null)
            delay(5000)
            if(!elCorreoExiste()) return@launch
            if(!lasCredencialesSonCorrectas()) return@launch
            inicioSesionExitosa.postValue(true)
        }
    }

    fun conInicioSesionExitosa(inicioSesionExitosa : MutableLiveData<Boolean?>) : HelperIniciarSesion {
        this.inicioSesionExitosa = inicioSesionExitosa
        return this
    }

    fun conUsuarioInicioSesionModel(usuarioInicioSesionModel: UsuarioInicioSesionModel) : HelperIniciarSesion {
        this.usuarioInicioSesionModel = usuarioInicioSesionModel
        return this
    }

    fun conEscuchadorExcepciones(escuchadorExcepciones: MutableLiveData<LogicaExcepcion?>) : HelperIniciarSesion {
        this.escuchadorExcepciones = escuchadorExcepciones
        return this
    }


    //region metodos privados
    private fun elCorreoExiste() : Boolean {
        val jacEntity = jacDao.encontrarRegistroPorCorreo(email = usuarioInicioSesionModel.correo!!)
        val correoId = correoDao.traerId(correo = usuarioInicioSesionModel.correo!!)
        if (jacEntity == null && correoId == null) {
            inicioSesionExitosa.postValue(false)
            escuchadorExcepciones.postValue(UsuarioNoEstaRegistradoExcepcion())
            return false
        }
        return true
    }

    private fun lasCredencialesSonCorrectas() : Boolean{
        val jacEntity = jacDao.encontrarRegistroPorCorreoYContrasenia(
            email = usuarioInicioSesionModel.correo!!,
            contrasenia = usuarioInicioSesionModel.contrasenia!!
        )

        if (jacEntity == null) {
            inicioSesionExitosa.postValue(false)
            escuchadorExcepciones.postValue(RevisaCredencialesExcepcion())
            return false
        }
        return true
    }
    //endregion

}