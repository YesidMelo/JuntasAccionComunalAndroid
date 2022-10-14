package com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.registroAfiliado.helpers

import com.hefesto.juntasaccioncomunal.logica.excepciones.ApellidoAfiliadoNoValidoHomeExcepcion
import com.hefesto.juntasaccioncomunal.logica.excepciones.FechaNacimientoAfiliadoNoIngresadoHomeExcepcion
import com.hefesto.juntasaccioncomunal.logica.excepciones.FechaNacimientoAfiliadoNoValidoHomeExcepcion
import com.hefesto.juntasaccioncomunal.logica.excepciones.NoHaCreadoModeloDeDatosBasicosParaRegistroExcepcion
import com.hefesto.juntasaccioncomunal.logica.excepciones.NoHaIngresadoApellidoRegistroHomeExcepcion
import com.hefesto.juntasaccioncomunal.logica.excepciones.NoHaIngresadoNombreRegistroHomeExcepcion
import com.hefesto.juntasaccioncomunal.logica.excepciones.NoHaIngresadoNumeroDocumentoHomeExcepcion
import com.hefesto.juntasaccioncomunal.logica.excepciones.NoHaIngresadoTipoDocumentoHomeExcepcion
import com.hefesto.juntasaccioncomunal.logica.excepciones.NoHaNumeroDocumentoInvalidoHomeExcepcion
import com.hefesto.juntasaccioncomunal.logica.excepciones.NombreAfiliadoNoValidoHomeExcepcion
import com.hefesto.juntasaccioncomunal.logica.modelos.home.registroAfiliado.DatosBasicosParaRegistrarModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.constantes.ConstantesFecha
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.RegexEnum
import com.hefesto.juntasaccioncomunal.logica.utilidades.extenciones.convertirAFormato
import com.hefesto.juntasaccioncomunal.logica.utilidades.extenciones.esMenorAFechaActualMenosAnios
import com.hefesto.juntasaccioncomunal.logica.utilidades.validarConRegex

class HelperValidacionDatosBasicos {

    fun validar(datosBasicosParaRegistrarModel: DatosBasicosParaRegistrarModel?) {
        val datos = datosBasicosParaRegistrarModel?: throw NoHaCreadoModeloDeDatosBasicosParaRegistroExcepcion()
        validarNombre(datosBasicosParaRegistrarModel = datos)
        validarApellido(datosBasicosParaRegistrarModel = datos)
        validarFechaNacimiento(datosBasicosParaRegistrarModel = datos)
        validarTipoDocumento(datosBasicosParaRegistrarModel = datos)
        validarNumeroDocumento(datosBasicosParaRegistrarModel = datos)
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

    private fun validarFechaNacimiento(datosBasicosParaRegistrarModel: DatosBasicosParaRegistrarModel) {
        val fecha = datosBasicosParaRegistrarModel.fechaNacimiento?:throw FechaNacimientoAfiliadoNoIngresadoHomeExcepcion()
        if (!fecha.esMenorAFechaActualMenosAnios(anios = -ConstantesFecha.EDAD_MINIMA_INSCRIPCION_JAC)) throw FechaNacimientoAfiliadoNoValidoHomeExcepcion()
    }

    private fun validarTipoDocumento(datosBasicosParaRegistrarModel: DatosBasicosParaRegistrarModel)  {
        val tipo = datosBasicosParaRegistrarModel.tipoDocumento?:throw NoHaIngresadoTipoDocumentoHomeExcepcion()
    }

    private fun validarNumeroDocumento(datosBasicosParaRegistrarModel: DatosBasicosParaRegistrarModel) {
        if (datosBasicosParaRegistrarModel.numeroDocumento.isNullOrEmpty()) throw NoHaIngresadoNumeroDocumentoHomeExcepcion()
        if(!validarConRegex(datosBasicosParaRegistrarModel.numeroDocumento!!, regex = RegexEnum.DOCUMENTO_IDENTIDAD)) throw NoHaNumeroDocumentoInvalidoHomeExcepcion()
    }

    //endregion
}