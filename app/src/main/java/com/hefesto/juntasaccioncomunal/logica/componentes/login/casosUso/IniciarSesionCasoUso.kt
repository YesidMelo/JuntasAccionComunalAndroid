package com.hefesto.juntasaccioncomunal.logica.componentes.login.casosUso

import androidx.lifecycle.MutableLiveData
import com.hefesto.juntasaccioncomunal.logica.excepciones.SinCorreoLoginExcepcion
import com.hefesto.juntasaccioncomunal.logica.modelos.login.iniciarSesion.UsuarioInicioSesionModel

interface IniciarSesionCasoUso {
    fun invoke(usuarioInicioSesionModel: UsuarioInicioSesionModel) : MutableLiveData<Boolean>
}

class IniciarSesionCasoUsoImpl : IniciarSesionCasoUso {

    override fun invoke(usuarioInicioSesionModel: UsuarioInicioSesionModel): MutableLiveData<Boolean> {
        correoValido(usuarioInicioSesionModel = usuarioInicioSesionModel)
        val liveData = MutableLiveData<Boolean>()
        liveData.value = true
        return liveData
    }

    //region metodos privados
    private fun correoValido(usuarioInicioSesionModel: UsuarioInicioSesionModel) {
        if (usuarioInicioSesionModel.correo == null) throw  SinCorreoLoginExcepcion()
        if (usuarioInicioSesionModel.correo.isEmpty()) throw  SinCorreoLoginExcepcion()
        if (usuarioInicioSesionModel.correo.isBlank()) throw  SinCorreoLoginExcepcion()

    }
    //endregion
}