package com.hefesto.juntasaccioncomunal.logica.componentes.login.casosUso

import androidx.lifecycle.MutableLiveData
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.LoginRepositorio
import com.hefesto.juntasaccioncomunal.logica.excepciones.*
import com.hefesto.juntasaccioncomunal.logica.modelos.login.registrarJAC.JACRegistroModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.RegexEnum
import com.hefesto.juntasaccioncomunal.logica.utilidades.correoValido
import com.hefesto.juntasaccioncomunal.logica.utilidades.validarConRegex
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface RegistrarJACCasoUso {
    fun invoke(jacRegistroModel: JACRegistroModel) : Flow<Boolean?>
}

class RegistrarJACCasoUsoImpl constructor(
    @JvmField @Inject var loginRepositorio: LoginRepositorio
) : RegistrarJACCasoUso {

    override fun invoke(jacRegistroModel: JACRegistroModel): Flow<Boolean?> = flow {
        validarNombreJAC(jacRegistroModel = jacRegistroModel)
        validarCodigoJAC(jacRegistroModel = jacRegistroModel)
        validarCorreo(jacRegistroModel = jacRegistroModel)
        validarContrasenia(jacRegistroModel = jacRegistroModel)
        validarRepetirContrasenia(jacRegistroModel = jacRegistroModel)
        validarNit(jacRegistroModel = jacRegistroModel)
        validarPJ(jacRegistroModel = jacRegistroModel)

        loginRepositorio
            .registrarJAC(jacRegistroModel = jacRegistroModel)
            .collect{ emit(it)}
    }

    //region metodos privados
    private fun validarNombreJAC(jacRegistroModel: JACRegistroModel) {
        if (jacRegistroModel.NombreJAC.isNullOrEmpty()) throw NombreJACNoValidoExcepcion()
        if (jacRegistroModel.NombreJAC.isNullOrBlank()) throw NombreJACNoValidoExcepcion()
        if (!validarConRegex(string = jacRegistroModel.NombreJAC!!, regex = RegexEnum.NOMBRE_JAC)) throw NombreJACNoValidoExcepcion()
    }

    private fun validarCodigoJAC(jacRegistroModel: JACRegistroModel) {
        if(jacRegistroModel.CodigoJAC.isNullOrEmpty()) throw CodigoJACNoValidoRegistroJACExcepcion()
        if(jacRegistroModel.CodigoJAC.isNullOrBlank()) throw CodigoJACNoValidoRegistroJACExcepcion()
        if(!validarConRegex(string = jacRegistroModel.CodigoJAC!!, regex = RegexEnum.CODIGO_JAC)) throw CodigoJACNoValidoRegistroJACExcepcion()
    }

    private fun validarCorreo(jacRegistroModel: JACRegistroModel) {
        if(jacRegistroModel.Correo.isNullOrEmpty()) throw SinCorreoRegistrarJACExcepcion()
        if(jacRegistroModel.Correo.isNullOrBlank()) throw SinCorreoRegistrarJACExcepcion()
        if(!correoValido(correo = jacRegistroModel.Correo!!)) throw CorreoInvalidoRegistroJacExcepcion()
    }

    private fun validarContrasenia(jacRegistroModel: JACRegistroModel) {
        if(jacRegistroModel.Contrasenia.isNullOrEmpty()) throw RepetirContraseniaRegistroJACVacioExcepcion()
        if(jacRegistroModel.Contrasenia.isNullOrBlank()) throw RepetirContraseniaRegistroJACVacioExcepcion()
        if(!validarConRegex(string = jacRegistroModel.Contrasenia!!, regex = RegexEnum.CONSTRASENIA)) throw RepetirContraseniaRegistrarJACNoEsValidoException()
    }

    private fun validarRepetirContrasenia(jacRegistroModel: JACRegistroModel) {
        if(jacRegistroModel.RepetirContrasenia.isNullOrEmpty()) throw RepetirContraseniaRegistroJACVacioExcepcion()
        if(jacRegistroModel.RepetirContrasenia.isNullOrBlank()) throw RepetirContraseniaRegistroJACVacioExcepcion()
        if(!validarConRegex(string = jacRegistroModel.RepetirContrasenia!!, regex = RegexEnum.CONSTRASENIA)) throw RepetirContraseniaRegistrarJACNoEsValidoException()
        if(jacRegistroModel.Contrasenia != jacRegistroModel.RepetirContrasenia) throw ElCampoContraseniaYRepetirContraseniaNoCoincidenRegistrarJacException()
    }

    private fun validarNit(jacRegistroModel: JACRegistroModel) {
        if(jacRegistroModel.Nit.isNullOrEmpty()) throw NoHaIngresadoNitJuntaExcepcion()
        if(jacRegistroModel.Nit.isNullOrBlank()) throw NoHaIngresadoNitJuntaExcepcion()
    }

    private fun validarPJ(jacRegistroModel: JACRegistroModel) {
        if(jacRegistroModel.PJ.isNullOrEmpty()) throw NoHaIngresadoPJJuntaExcepcion()
        if(jacRegistroModel.PJ.isNullOrBlank()) throw NoHaIngresadoPJJuntaExcepcion()
    }
    //endregion
}