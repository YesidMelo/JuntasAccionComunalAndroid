package com.hefesto.juntasaccioncomunal.logica.componentes.login.casosUso

import androidx.lifecycle.MutableLiveData
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.LoginRepositorio
import com.hefesto.juntasaccioncomunal.logica.excepciones.*
import com.hefesto.juntasaccioncomunal.logica.modelos.login.registrarJAC.JACRegistroModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.RegexEnum
import com.hefesto.juntasaccioncomunal.logica.utilidades.correoValido
import com.hefesto.juntasaccioncomunal.logica.utilidades.validarConRegex
import javax.inject.Inject

interface RegistrarJACCasoUso {
    fun invoke(jacRegistroModel: JACRegistroModel) : MutableLiveData<Boolean?>
}

class RegistrarJACCasoUsoImpl constructor(
    @JvmField @Inject var loginRepositorio: LoginRepositorio
) : RegistrarJACCasoUso {

    override fun invoke(jacRegistroModel: JACRegistroModel): MutableLiveData<Boolean?> {
        validarNombreJAC(jacRegistroModel = jacRegistroModel)
        validarCodigoJAC(jacRegistroModel = jacRegistroModel)
        validarCorreo(jacRegistroModel = jacRegistroModel)
        validarContrasenia(jacRegistroModel = jacRegistroModel)
        validarRepetirContrasenia(jacRegistroModel = jacRegistroModel)
        return loginRepositorio.registrarJAC(jacRegistroModel = jacRegistroModel)
    }

    //region metodos privados
    private fun validarNombreJAC(jacRegistroModel: JACRegistroModel) {
        if (jacRegistroModel.NombreJAC.isNullOrEmpty()) throw NombreJACNoValidoExcepcion()
        if (jacRegistroModel.NombreJAC.isNullOrBlank()) throw NombreJACNoValidoExcepcion()
        if (!validarConRegex(string = jacRegistroModel.NombreJAC!!, regex = RegexEnum.NOMBRE_JAC)) throw NombreJACNoValidoExcepcion()
    }

    private fun validarCodigoJAC(jacRegistroModel: JACRegistroModel) {
        if(jacRegistroModel.CodigoJAC.isNullOrEmpty()) throw CodigoJACNoValidoExcepcion()
        if(jacRegistroModel.CodigoJAC.isNullOrBlank()) throw CodigoJACNoValidoExcepcion()
        if(!validarConRegex(string = jacRegistroModel.CodigoJAC!!, regex = RegexEnum.CODIGO_JAC)) throw CodigoJACNoValidoExcepcion()
    }

    private fun validarCorreo(jacRegistroModel: JACRegistroModel) {
        if(jacRegistroModel.Correo.isNullOrEmpty()) throw SinCorreoLoginExcepcion()
        if(jacRegistroModel.Correo.isNullOrBlank()) throw SinCorreoLoginExcepcion()
        if(!correoValido(correo = jacRegistroModel.Correo!!)) throw CorreoInvalidoLoginExcepcion()
    }

    private fun validarContrasenia(jacRegistroModel: JACRegistroModel) {
        if(jacRegistroModel.Contrasenia.isNullOrEmpty()) throw RepetirContraseniaJACVacioExcepcion()
        if(jacRegistroModel.Contrasenia.isNullOrBlank()) throw RepetirContraseniaJACVacioExcepcion()
        if(!validarConRegex(string = jacRegistroModel.Contrasenia!!, regex = RegexEnum.CONSTRASENIA)) throw RepetirContraseniaJACNoEsValidoException()
    }

    private fun validarRepetirContrasenia(jacRegistroModel: JACRegistroModel) {
        if(jacRegistroModel.RepetirContrasenia.isNullOrEmpty()) throw RepetirContraseniaJACVacioExcepcion()
        if(jacRegistroModel.RepetirContrasenia.isNullOrBlank()) throw RepetirContraseniaJACVacioExcepcion()
        if(!validarConRegex(string = jacRegistroModel.RepetirContrasenia!!, regex = RegexEnum.CONSTRASENIA)) throw RepetirContraseniaJACNoEsValidoException()
        if(jacRegistroModel.Contrasenia != jacRegistroModel.RepetirContrasenia) throw ElCampoContraseniaYRepetirContraseniaNoCoincidenException()
    }
    //endregion
}