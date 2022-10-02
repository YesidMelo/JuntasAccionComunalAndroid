package com.hefesto.juntasaccioncomunal.logica.componentes.login.casosUso

import androidx.lifecycle.MutableLiveData
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.LoginRepositorio
import com.hefesto.juntasaccioncomunal.logica.excepciones.ContraseniaInvalidaExcepcion
import com.hefesto.juntasaccioncomunal.logica.excepciones.ContraseniaVaciaExcepcion
import com.hefesto.juntasaccioncomunal.logica.excepciones.CorreoInvalidoLoginExcepcion
import com.hefesto.juntasaccioncomunal.logica.excepciones.SinCorreoLoginExcepcion
import com.hefesto.juntasaccioncomunal.logica.modelos.login.iniciarSesion.UsuarioInicioSesionModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.RegexEnum
import com.hefesto.juntasaccioncomunal.logica.utilidades.correoValido
import com.hefesto.juntasaccioncomunal.logica.utilidades.validarConRegex
import javax.inject.Inject

interface IniciarSesionCasoUso {
    fun invoke(usuarioInicioSesionModel: UsuarioInicioSesionModel) : MutableLiveData<Boolean?>
}

class IniciarSesionCasoUsoImpl (
    @JvmField @Inject var loginRepositorio: LoginRepositorio
) : IniciarSesionCasoUso {

    override fun invoke(usuarioInicioSesionModel: UsuarioInicioSesionModel): MutableLiveData<Boolean?> {
        validacionCorreo(usuarioInicioSesionModel = usuarioInicioSesionModel)
        validaContrasenia(usuarioInicioSesionModel = usuarioInicioSesionModel)
        return loginRepositorio.iniciarSesion(usuarioInicioSesionModel = usuarioInicioSesionModel)
    }

    //region metodos privados
    private fun validacionCorreo(usuarioInicioSesionModel: UsuarioInicioSesionModel) {
        if (usuarioInicioSesionModel.correo == null) throw  SinCorreoLoginExcepcion()
        if (usuarioInicioSesionModel.correo.isEmpty()) throw  SinCorreoLoginExcepcion()
        if (usuarioInicioSesionModel.correo.isBlank()) throw  SinCorreoLoginExcepcion()
        if(!correoValido(correo = usuarioInicioSesionModel.correo)) throw CorreoInvalidoLoginExcepcion()
    }

    private fun validaContrasenia(usuarioInicioSesionModel: UsuarioInicioSesionModel) {
        if (usuarioInicioSesionModel.contrasenia == null) throw ContraseniaVaciaExcepcion()
        if (usuarioInicioSesionModel.contrasenia.isEmpty()) throw ContraseniaVaciaExcepcion()
        if (usuarioInicioSesionModel.contrasenia.isBlank()) throw ContraseniaVaciaExcepcion()
        if(!validarConRegex(string = usuarioInicioSesionModel.contrasenia, regex = RegexEnum.CONSTRASENIA)) throw ContraseniaInvalidaExcepcion()

    }
    //endregion
}