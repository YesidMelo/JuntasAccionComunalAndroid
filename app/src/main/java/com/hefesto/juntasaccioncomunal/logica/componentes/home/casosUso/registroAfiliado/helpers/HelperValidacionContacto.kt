package com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.registroAfiliado.helpers

import com.hefesto.juntasaccioncomunal.logica.excepciones.CampoTelefonoVacioRegistroAfiliadosHomeExcepcion
import com.hefesto.juntasaccioncomunal.logica.excepciones.CorreoInvalidoLoginHomeExcepcion
import com.hefesto.juntasaccioncomunal.logica.excepciones.DireccinoInvalidoRegistroAfiliadoHomeExcepcion
import com.hefesto.juntasaccioncomunal.logica.excepciones.ElTelefonoNoEsValidoRegistroAfiliadoHomeExcepcion
import com.hefesto.juntasaccioncomunal.logica.excepciones.NoHaCreadoModeloDeContactoAfiliadoParaRegistroExcepcion
import com.hefesto.juntasaccioncomunal.logica.excepciones.NoHaIngresadoUnaDireccionAfiliadoHomeExcepcion
import com.hefesto.juntasaccioncomunal.logica.excepciones.SinCorreoRegistrarAfiliadHomeoExcepcion
import com.hefesto.juntasaccioncomunal.logica.modelos.home.registroAfiliado.ContactoParaRegistrarModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.correoValido
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.RegexEnum
import com.hefesto.juntasaccioncomunal.logica.utilidades.validarConRegex

class HelperValidacionContacto {

    fun validar(contactoParaRegistrarModel: ContactoParaRegistrarModel?) {
        val contacto = contactoParaRegistrarModel?: throw NoHaCreadoModeloDeContactoAfiliadoParaRegistroExcepcion()
        validarcionCorreoElectronico(contactoParaRegistrarModel = contacto)
        validarDireccion(contactoParaRegistrarModel = contacto)
        validarTelefono(contactoParaRegistrarModel = contacto)
    }

    //region metodos privados
    private fun validarcionCorreoElectronico(contactoParaRegistrarModel: ContactoParaRegistrarModel) {
        if(contactoParaRegistrarModel.correo.isNullOrEmpty()) throw SinCorreoRegistrarAfiliadHomeoExcepcion()
        if(!correoValido(correo = contactoParaRegistrarModel.correo!!)) throw CorreoInvalidoLoginHomeExcepcion()
    }

    private fun validarDireccion(contactoParaRegistrarModel: ContactoParaRegistrarModel) {
        if(contactoParaRegistrarModel.direccion.isNullOrEmpty()) throw NoHaIngresadoUnaDireccionAfiliadoHomeExcepcion()
        if(!validarConRegex(string = contactoParaRegistrarModel.direccion!!, regex = RegexEnum.DIRECCION)) throw DireccinoInvalidoRegistroAfiliadoHomeExcepcion()
    }

    private fun validarTelefono(contactoParaRegistrarModel: ContactoParaRegistrarModel) {
        if(contactoParaRegistrarModel.telefono.isNullOrEmpty()) throw CampoTelefonoVacioRegistroAfiliadosHomeExcepcion()
        val telefonoValido = validarConRegex(string = contactoParaRegistrarModel.telefono!!, regex = RegexEnum.TELEFONO_FIJO) || validarConRegex(string = contactoParaRegistrarModel.telefono!!, regex = RegexEnum.TELEFONO_MOVIL)
        if(!telefonoValido) throw ElTelefonoNoEsValidoRegistroAfiliadoHomeExcepcion()
    }
    //end region
}