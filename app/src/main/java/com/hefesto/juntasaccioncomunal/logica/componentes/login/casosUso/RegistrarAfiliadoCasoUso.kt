package com.hefesto.juntasaccioncomunal.logica.componentes.login.casosUso

import androidx.lifecycle.MutableLiveData
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.LoginRepositorio
import com.hefesto.juntasaccioncomunal.logica.excepciones.ApellidoAfiliadoNoValidoExcepcion
import com.hefesto.juntasaccioncomunal.logica.excepciones.CampoApellidosVacioRegistroAfiliadosExcepcion
import com.hefesto.juntasaccioncomunal.logica.excepciones.CampoNombresVacioRegistroAfiliadosExcepcion
import com.hefesto.juntasaccioncomunal.logica.excepciones.CampoTelefonoVacionRegistroAfiliadosExcepcion
import com.hefesto.juntasaccioncomunal.logica.excepciones.ContraseniaInvalidaRegistrarAfiliadoExcepcion
import com.hefesto.juntasaccioncomunal.logica.excepciones.ContraseniaVaciaRegistroAfiliadoExcepcion
import com.hefesto.juntasaccioncomunal.logica.excepciones.CorreoInvalidoRegistroAfiliadoExcepcion
import com.hefesto.juntasaccioncomunal.logica.excepciones.DireccinoInvalidoRegistroAfiliadoExcepcion
import com.hefesto.juntasaccioncomunal.logica.excepciones.ElCampoContraseniaYRepetirContraseniaNoCoincidenRegistrarAfiliadoException
import com.hefesto.juntasaccioncomunal.logica.excepciones.ElTelefonoNoEsValidoRegistroAfiliadoExcepcion
import com.hefesto.juntasaccioncomunal.logica.excepciones.NoCumpleConLaFechaMinimaParaLaInscripcionExcepcion
import com.hefesto.juntasaccioncomunal.logica.excepciones.NoHaIngresadoUnNumeroDeDocumentoValidoAfiliadoExcepcion
import com.hefesto.juntasaccioncomunal.logica.excepciones.NoHaIngresadoUnaDireccionAfiliadoExcepcion
import com.hefesto.juntasaccioncomunal.logica.excepciones.NoHaIngresadoUnaFechaDeNacimientoExcepcion
import com.hefesto.juntasaccioncomunal.logica.excepciones.NoHaIngresadoUnaJacRegistradaExcepcion
import com.hefesto.juntasaccioncomunal.logica.excepciones.NoHaSeleccionadoUnTipoDeDocumentoExcepcion
import com.hefesto.juntasaccioncomunal.logica.excepciones.NoHaSeleccionadoUnTipoDeTelefonoExcepcion
import com.hefesto.juntasaccioncomunal.logica.excepciones.NombreAfiliadoNoValidoExcepcion
import com.hefesto.juntasaccioncomunal.logica.excepciones.NumeroDocumentoInvalidoAfiliadoExcepcion
import com.hefesto.juntasaccioncomunal.logica.excepciones.RepetirContraseniaRegistrarAfiliadoNoEsValidoException
import com.hefesto.juntasaccioncomunal.logica.excepciones.RepetirContraseniaRegistroAfiliadoVacioExcepcion
import com.hefesto.juntasaccioncomunal.logica.excepciones.SinCorreoRegistrarAfiliadoExcepcion
import com.hefesto.juntasaccioncomunal.logica.modelos.login.registrarAfiliado.AfiliadoARegistrarModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.constantes.ConstantesFecha.EDAD_MINIMA_INSCRIPCION_JAC
import com.hefesto.juntasaccioncomunal.logica.utilidades.correoValido
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.RegexEnum
import com.hefesto.juntasaccioncomunal.logica.utilidades.extenciones.esMenorAFechaActualMenosAnios
import com.hefesto.juntasaccioncomunal.logica.utilidades.validarConRegex
import javax.inject.Inject

interface RegistrarAfiliadoCasoUso {
    fun invoke(afiliadoARegistrarModel: AfiliadoARegistrarModel) : MutableLiveData<Boolean?>
}

class RegistrarAfiliadoCasoUsoImpl constructor(
    @JvmField @Inject var loginRepositorio: LoginRepositorio
) : RegistrarAfiliadoCasoUso {

    override fun invoke(afiliadoARegistrarModel: AfiliadoARegistrarModel): MutableLiveData<Boolean?> {
        validarJAC(afiliadoARegistrarModel = afiliadoARegistrarModel)
        validarNombres(afiliadoARegistrarModel = afiliadoARegistrarModel)
        validarApellidos(afiliadoARegistrarModel = afiliadoARegistrarModel)
        validarFechaNacimiento(afiliadoARegistrarModel = afiliadoARegistrarModel)
        validarTipoDocumento(afiliadoARegistrarModel= afiliadoARegistrarModel)
        validarDocumento(afiliadoARegistrarModel= afiliadoARegistrarModel)
        validarCorreo(afiliadoARegistrarModel= afiliadoARegistrarModel)
        validarDireccion(afiliadoARegistrarModel = afiliadoARegistrarModel)
        validarTipoTelefono(afiliadoARegistrarModel = afiliadoARegistrarModel)
        validarTelefono(afiliadoARegistrarModel = afiliadoARegistrarModel)
        validarContrasenia(afiliadoARegistrarModel = afiliadoARegistrarModel)
        validarRepetirContrasenia(afiliadoARegistrarModel = afiliadoARegistrarModel)
        return loginRepositorio.registrarAfiliado(afiliadoARegistrarModel = afiliadoARegistrarModel)
    }
    //region metodos privados
    private fun validarJAC(afiliadoARegistrarModel: AfiliadoARegistrarModel) {
        if (afiliadoARegistrarModel.jacSeleccionado == null) throw NoHaIngresadoUnaJacRegistradaExcepcion()
    }

    private fun validarNombres(afiliadoARegistrarModel: AfiliadoARegistrarModel) {
        if (afiliadoARegistrarModel.nombres.isNullOrEmpty()) throw CampoNombresVacioRegistroAfiliadosExcepcion()
        if (afiliadoARegistrarModel.nombres.isNullOrBlank()) throw CampoNombresVacioRegistroAfiliadosExcepcion()
        if (!validarConRegex(string = afiliadoARegistrarModel.nombres!!, regex = RegexEnum.NOMBRE_AFILIADO)) throw NombreAfiliadoNoValidoExcepcion()
    }

    private fun validarApellidos(afiliadoARegistrarModel: AfiliadoARegistrarModel) {
        if (afiliadoARegistrarModel.apellidos.isNullOrEmpty()) throw CampoApellidosVacioRegistroAfiliadosExcepcion()
        if (afiliadoARegistrarModel.apellidos.isNullOrBlank()) throw CampoApellidosVacioRegistroAfiliadosExcepcion()
        if (!validarConRegex(string = afiliadoARegistrarModel.apellidos!!, regex = RegexEnum.APELLIDO_AFILIADO)) throw ApellidoAfiliadoNoValidoExcepcion()
    }

    private fun validarFechaNacimiento(afiliadoARegistrarModel: AfiliadoARegistrarModel) {
        if (afiliadoARegistrarModel.fechaNacimiento == null) throw NoHaIngresadoUnaFechaDeNacimientoExcepcion()
        if (!afiliadoARegistrarModel.fechaNacimiento!!.esMenorAFechaActualMenosAnios(-EDAD_MINIMA_INSCRIPCION_JAC)) throw NoCumpleConLaFechaMinimaParaLaInscripcionExcepcion()
    }

    private fun validarTipoDocumento(afiliadoARegistrarModel: AfiliadoARegistrarModel) {
        if (afiliadoARegistrarModel.tipoDocumento == null) throw NoHaSeleccionadoUnTipoDeDocumentoExcepcion()
    }

    private fun validarDocumento(afiliadoARegistrarModel: AfiliadoARegistrarModel) {
        if (afiliadoARegistrarModel.numeroDocumento.isNullOrEmpty()) throw NoHaIngresadoUnNumeroDeDocumentoValidoAfiliadoExcepcion()
        if (afiliadoARegistrarModel.numeroDocumento.isNullOrBlank()) throw NoHaIngresadoUnNumeroDeDocumentoValidoAfiliadoExcepcion()
        if (!validarConRegex(string = afiliadoARegistrarModel.numeroDocumento!!, regex = RegexEnum.DOCUMENTO_IDENTIDAD)) throw NumeroDocumentoInvalidoAfiliadoExcepcion()
    }

    private fun validarCorreo(afiliadoARegistrarModel: AfiliadoARegistrarModel) {
        if (afiliadoARegistrarModel.correo.isNullOrEmpty()) throw SinCorreoRegistrarAfiliadoExcepcion()
        if (afiliadoARegistrarModel.correo.isNullOrBlank()) throw SinCorreoRegistrarAfiliadoExcepcion()
        if (!correoValido(correo = afiliadoARegistrarModel.correo!!)) throw CorreoInvalidoRegistroAfiliadoExcepcion()
    }

    private fun validarDireccion(afiliadoARegistrarModel: AfiliadoARegistrarModel) {
        if(afiliadoARegistrarModel.direccion.isNullOrBlank()) throw NoHaIngresadoUnaDireccionAfiliadoExcepcion()
        if(afiliadoARegistrarModel.direccion.isNullOrEmpty()) throw NoHaIngresadoUnaDireccionAfiliadoExcepcion()
        if (!validarConRegex(string = afiliadoARegistrarModel.direccion!!, regex = RegexEnum.DIRECCION)) throw DireccinoInvalidoRegistroAfiliadoExcepcion()
    }

    private fun validarTipoTelefono(afiliadoARegistrarModel: AfiliadoARegistrarModel) {
        if(afiliadoARegistrarModel.tipoTelefono == null) throw NoHaSeleccionadoUnTipoDeTelefonoExcepcion()
    }

    private fun validarTelefono(afiliadoARegistrarModel : AfiliadoARegistrarModel) {
        if (afiliadoARegistrarModel.telefono.isNullOrBlank()) throw CampoTelefonoVacionRegistroAfiliadosExcepcion()
        if (afiliadoARegistrarModel.telefono.isNullOrEmpty()) throw CampoTelefonoVacionRegistroAfiliadosExcepcion()
        val telefonoFijoInvalido = !validarConRegex(string = afiliadoARegistrarModel.telefono!!, regex = RegexEnum.TELEFONO_FIJO)
        val telefonoMovilnvalido = !validarConRegex(string = afiliadoARegistrarModel.telefono!!, regex = RegexEnum.TELEFONO_MOVIL)
        if (telefonoFijoInvalido && telefonoMovilnvalido) throw ElTelefonoNoEsValidoRegistroAfiliadoExcepcion()
    }

    private fun validarContrasenia(afiliadoARegistrarModel: AfiliadoARegistrarModel) {
        if (afiliadoARegistrarModel.contrasenia.isNullOrBlank()) throw ContraseniaVaciaRegistroAfiliadoExcepcion()
        if (afiliadoARegistrarModel.contrasenia.isNullOrEmpty()) throw ContraseniaVaciaRegistroAfiliadoExcepcion()
        if (!validarConRegex(string = afiliadoARegistrarModel.contrasenia!!, regex = RegexEnum.CONSTRASENIA)) throw ContraseniaInvalidaRegistrarAfiliadoExcepcion()
    }

    private fun validarRepetirContrasenia(afiliadoARegistrarModel: AfiliadoARegistrarModel) {
        if (afiliadoARegistrarModel.repetirContrasenia.isNullOrBlank()) throw RepetirContraseniaRegistroAfiliadoVacioExcepcion()
        if (afiliadoARegistrarModel.repetirContrasenia.isNullOrEmpty()) throw RepetirContraseniaRegistroAfiliadoVacioExcepcion()
        if (!validarConRegex(string = afiliadoARegistrarModel.repetirContrasenia!!, regex = RegexEnum.CONSTRASENIA)) throw RepetirContraseniaRegistrarAfiliadoNoEsValidoException()
        if (afiliadoARegistrarModel.contrasenia != afiliadoARegistrarModel.repetirContrasenia) throw ElCampoContraseniaYRepetirContraseniaNoCoincidenRegistrarAfiliadoException()
    }

    //endregion

}