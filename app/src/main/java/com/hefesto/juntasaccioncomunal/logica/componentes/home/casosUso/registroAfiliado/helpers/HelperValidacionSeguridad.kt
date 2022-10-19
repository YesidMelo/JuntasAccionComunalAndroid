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
        if (seguridad.credencialesSesionId == null) {
            nuevoRegistro(seguridad = seguridad)
            return
        }
        actualizarRegistro(seguridad = seguridad)
    }

    //region metodos privados
    //region nuevo registro
    private fun nuevoRegistro(seguridad: SeguridadParaRegistroModel) {
        validarContraseniaNuevoRegistro(seguridadParaRegistroModel = seguridad)
        validarRepetirContraseniaNuevoRegistro(seguridadParaRegistroModel = seguridad)
        validarCoincidirContraseniaNuevoRegistro(seguridadParaRegistroModel = seguridad)
    }

    private fun validarContraseniaNuevoRegistro(seguridadParaRegistroModel: SeguridadParaRegistroModel) {
        if (seguridadParaRegistroModel.contrasenia.isNullOrEmpty()) throw ContraseniaVaciaRegistroHomeAfiliadoExcepcion()
        if(!validarConRegex(string = seguridadParaRegistroModel.contrasenia!!, regex = RegexEnum.CONSTRASENIA)) throw ContraseniaInvalidaRegistrarAfiliadoHomeExcepcion()
    }

    private fun validarRepetirContraseniaNuevoRegistro(seguridadParaRegistroModel: SeguridadParaRegistroModel) {
        if (seguridadParaRegistroModel.repetirContrasenia.isNullOrEmpty()) throw RepetirContraseniaRegistroAfiliadoHomeVacioExcepcion()
        if(!validarConRegex(string = seguridadParaRegistroModel.repetirContrasenia!!, regex = RegexEnum.CONSTRASENIA)) throw RepetirContraseniaRegistrarAfiliadoHomeNoEsValidoException()
    }

    private fun validarCoincidirContraseniaNuevoRegistro(seguridadParaRegistroModel: SeguridadParaRegistroModel) {
        if(seguridadParaRegistroModel.contrasenia != seguridadParaRegistroModel.repetirContrasenia) throw ElCampoContraseniaYRepetirContraseniaNoCoincidenRegistrarAfiliadoHomeException()
    }
    //endregion

    //region actualizacion
    private fun actualizarRegistro(seguridad: SeguridadParaRegistroModel) {
        if(seguridad.contrasenia.isNullOrEmpty() && seguridad.repetirContrasenia.isNullOrEmpty()) return
        actualizarContraseniaNuevoRegistro(seguridadParaRegistroModel = seguridad)
        actualizarRepetirContraseniaNuevoRegistro(seguridadParaRegistroModel = seguridad)
        actualizarCoincidirContraseniaNuevoRegistro(seguridadParaRegistroModel = seguridad)
    }

    private fun actualizarContraseniaNuevoRegistro(seguridadParaRegistroModel: SeguridadParaRegistroModel) {
        if (seguridadParaRegistroModel.contrasenia.isNullOrEmpty()) throw ContraseniaVaciaRegistroHomeAfiliadoExcepcion()
        if(!validarConRegex(string = seguridadParaRegistroModel.contrasenia!!, regex = RegexEnum.CONSTRASENIA)) throw ContraseniaInvalidaRegistrarAfiliadoHomeExcepcion()
    }

    private fun actualizarRepetirContraseniaNuevoRegistro(seguridadParaRegistroModel: SeguridadParaRegistroModel) {
        if (seguridadParaRegistroModel.repetirContrasenia.isNullOrEmpty()) throw RepetirContraseniaRegistroAfiliadoHomeVacioExcepcion()
        if(!validarConRegex(string = seguridadParaRegistroModel.repetirContrasenia!!, regex = RegexEnum.CONSTRASENIA)) throw RepetirContraseniaRegistrarAfiliadoHomeNoEsValidoException()
    }

    private fun actualizarCoincidirContraseniaNuevoRegistro(seguridadParaRegistroModel: SeguridadParaRegistroModel) {
        if(seguridadParaRegistroModel.contrasenia != seguridadParaRegistroModel.repetirContrasenia) throw ElCampoContraseniaYRepetirContraseniaNoCoincidenRegistrarAfiliadoHomeException()
    }
    //endregion
    //endregion
}