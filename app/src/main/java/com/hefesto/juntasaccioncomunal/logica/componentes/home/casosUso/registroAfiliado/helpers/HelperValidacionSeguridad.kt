package com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.registroAfiliado.helpers

import com.hefesto.juntasaccioncomunal.logica.excepciones.ContraseniaInvalidaRegistrarAfiliadoHomeExcepcion
import com.hefesto.juntasaccioncomunal.logica.excepciones.ContraseniaVaciaRegistroHomeAfiliadoExcepcion
import com.hefesto.juntasaccioncomunal.logica.excepciones.ElCampoContraseniaYRepetirContraseniaNoCoincidenRegistrarAfiliadoHomeException
import com.hefesto.juntasaccioncomunal.logica.excepciones.NoHaCreadoModeloSeguridadAfiliadoParaRegistroExcepcion
import com.hefesto.juntasaccioncomunal.logica.excepciones.RepetirContraseniaRegistrarAfiliadoHomeNoEsValidoException
import com.hefesto.juntasaccioncomunal.logica.excepciones.RepetirContraseniaRegistroAfiliadoHomeVacioExcepcion
import com.hefesto.juntasaccioncomunal.logica.modelos.home.registroAfiliado.SeguridadParaRegistroModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.RegexEnum
import com.hefesto.juntasaccioncomunal.logica.utilidades.validarConRegex

class HelperValidacionSeguridad {

    fun validar(seguridadParaRegistroModel: SeguridadParaRegistroModel?) {
        val seguridad = seguridadParaRegistroModel?: throw NoHaCreadoModeloSeguridadAfiliadoParaRegistroExcepcion()
        validarContrasenia(seguridadParaRegistroModel = seguridad)
        validarRepetirContrasenia(seguridadParaRegistroModel = seguridad)
        validarCoincidirContrasenia(seguridadParaRegistroModel = seguridad)
    }

    //region metodos privados
    private fun validarContrasenia(seguridadParaRegistroModel: SeguridadParaRegistroModel) {
        if (seguridadParaRegistroModel.contrasenia.isNullOrEmpty()) throw ContraseniaVaciaRegistroHomeAfiliadoExcepcion()
        if(!validarConRegex(string = seguridadParaRegistroModel.contrasenia!!, regex = RegexEnum.CONSTRASENIA)) throw ContraseniaInvalidaRegistrarAfiliadoHomeExcepcion()
    }

    private fun validarRepetirContrasenia(seguridadParaRegistroModel: SeguridadParaRegistroModel) {
        if (seguridadParaRegistroModel.repetirContrasenia.isNullOrEmpty()) throw RepetirContraseniaRegistroAfiliadoHomeVacioExcepcion()
        if(!validarConRegex(string = seguridadParaRegistroModel.repetirContrasenia!!, regex = RegexEnum.CONSTRASENIA)) throw RepetirContraseniaRegistrarAfiliadoHomeNoEsValidoException()
    }

    private fun validarCoincidirContrasenia(seguridadParaRegistroModel: SeguridadParaRegistroModel) {
        if(seguridadParaRegistroModel.contrasenia != seguridadParaRegistroModel.repetirContrasenia) throw ElCampoContraseniaYRepetirContraseniaNoCoincidenRegistrarAfiliadoHomeException()
    }

    //endregion
}