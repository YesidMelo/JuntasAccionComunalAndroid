package com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.registroAfiliado.helpers

import com.hefesto.juntasaccioncomunal.logica.excepciones.ApellidoAfiliadoNoValidoHomeExcepcion
import com.hefesto.juntasaccioncomunal.logica.excepciones.NoHaCreadoModeloDeDatosBasicosParaRegistroExcepcion
import com.hefesto.juntasaccioncomunal.logica.excepciones.NoHaIngresadoApellidoRegistroHomeExcepcion
import com.hefesto.juntasaccioncomunal.logica.excepciones.NoHaIngresadoNombreRegistroHomeExcepcion
import com.hefesto.juntasaccioncomunal.logica.excepciones.NombreAfiliadoNoValidoHomeExcepcion
import com.hefesto.juntasaccioncomunal.logica.modelos.home.registroAfiliado.DatosBasicosParaRegistrarModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.RegexEnum
import com.hefesto.juntasaccioncomunal.logica.utilidades.validarConRegex

class HelperValidacionDatosBasicos {

    fun validar(datosBasicosParaRegistrarModel: DatosBasicosParaRegistrarModel?) {
        val datos = datosBasicosParaRegistrarModel?: throw NoHaCreadoModeloDeDatosBasicosParaRegistroExcepcion()
        validarNombre(datosBasicosParaRegistrarModel = datos)
        validarApellido(datosBasicosParaRegistrarModel = datos)
    }

    //region metodos privados
    private fun validarNombre(datosBasicosParaRegistrarModel: DatosBasicosParaRegistrarModel) {
        if (datosBasicosParaRegistrarModel.nombres.isNullOrEmpty()) throw NoHaIngresadoNombreRegistroHomeExcepcion()
        if (!validarConRegex(string = datosBasicosParaRegistrarModel.nombres!!, RegexEnum.NOMBRE_AFILIADO)) throw NombreAfiliadoNoValidoHomeExcepcion()
    }

    private fun validarApellido(datosBasicosParaRegistrarModel: DatosBasicosParaRegistrarModel) {
        if (datosBasicosParaRegistrarModel.apellidos.isNullOrEmpty()) throw NoHaIngresadoApellidoRegistroHomeExcepcion()
        if (!validarConRegex(string = datosBasicosParaRegistrarModel.apellidos!!, RegexEnum.APELLIDO_AFILIADO)) throw ApellidoAfiliadoNoValidoHomeExcepcion()
    }

    //endregion
}