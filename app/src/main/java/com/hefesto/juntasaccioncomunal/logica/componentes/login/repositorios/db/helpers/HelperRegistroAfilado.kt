package com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.helpers

import androidx.lifecycle.MutableLiveData
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.helpers.registroAfiliado.HelperRegistroAfiliadoEntity
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.helpers.registroAfiliado.HelperRegistroCorreoAfiliadoEntity
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.helpers.registroAfiliado.HelperRegistroCredencialesSesionAfiliado
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.helpers.registroAfiliado.HelperRegistroDireccionAfiliadoEntity
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.helpers.registroAfiliado.HelperRegistroTelefono
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.helpers.registroAfiliado.HelperValidarExisteUsuarioEnJAC
import com.hefesto.juntasaccioncomunal.logica.excepciones.LogicaExcepcion
import com.hefesto.juntasaccioncomunal.logica.modelos.login.registrarAfiliado.AfiliadoARegistrarModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class HelperRegistroAfilado constructor(
    @JvmField @Inject var helperRegistroAfiliadoEntity : HelperRegistroAfiliadoEntity,
    @JvmField @Inject var helperRegistroCorreoAfiliadoEntity : HelperRegistroCorreoAfiliadoEntity,
    @JvmField @Inject var helperRegistroDireccionAfiliadoEntity : HelperRegistroDireccionAfiliadoEntity,
    @JvmField @Inject var helperRegistroTelefonoEntity : HelperRegistroTelefono,
    @JvmField @Inject var helperValidarExisteUsuarioEnJAC: HelperValidarExisteUsuarioEnJAC,
    @JvmField @Inject var helperRegistroCredencialesSesionAfiliado : HelperRegistroCredencialesSesionAfiliado
) {

    //variables variables
    private lateinit var afiliadoARegistrarModel: AfiliadoARegistrarModel
    private lateinit var escuchadorRegistroAfiliadoExitoso: MutableLiveData<Boolean?>
    private lateinit var escuchadorExcepciones: MutableLiveData<LogicaExcepcion?>
    //enregion

    fun conAfiliadoARegistrarModel(afiliadoARegistrarModel: AfiliadoARegistrarModel) : HelperRegistroAfilado {
        this.afiliadoARegistrarModel = afiliadoARegistrarModel
        return this
    }

    fun conEscuchadorRegistroAfiliadoExitoso(escuchadorRegistroAfiliadoExitoso: MutableLiveData<Boolean?>) : HelperRegistroAfilado {
        this.escuchadorRegistroAfiliadoExitoso = escuchadorRegistroAfiliadoExitoso
        return this
    }

    fun conEscuchadorExcepciones(escuchadorExcepciones: MutableLiveData<LogicaExcepcion?>) : HelperRegistroAfilado {
        this.escuchadorExcepciones = escuchadorExcepciones
        return this
    }

    fun registrar() {
        GlobalScope.launch {
            escuchadorRegistroAfiliadoExitoso.postValue(null)
            if(
                helperValidarExisteUsuarioEnJAC
                    .conAfiliadoARegistrarModel(afiliadoARegistrarModel = afiliadoARegistrarModel)
                    .conEscuchadorExcepciones(escuchadorExcepciones = escuchadorExcepciones)
                    .existeUsuario()
            ) {
                escuchadorRegistroAfiliadoExitoso.postValue(false)
                return@launch
            }
            registrarAfiliadoADB()
            delay(5000)
            escuchadorRegistroAfiliadoExitoso.postValue(true)
        }
    }

    //region metodos privados
    private suspend fun registrarAfiliadoADB() {
        helperRegistroAfiliadoEntity.conAfiliadoARegistrarModel(afiliadoARegistrarModel = afiliadoARegistrarModel).guardarAfiliado()
        helperRegistroCorreoAfiliadoEntity.conAfiliadoARegistrarModel(afiliadoARegistrarModel = afiliadoARegistrarModel).guardarCorreo()
        helperRegistroDireccionAfiliadoEntity.conAfiliadoARegistrarModel(afiliadoARegistrarModel = afiliadoARegistrarModel).guardarDireccion()
        helperRegistroTelefonoEntity.conAfiliadoARegistrar(afiliadoARegistrarModel = afiliadoARegistrarModel).guardarTelefono()
        helperRegistroCredencialesSesionAfiliado.conAfiliadoARegistrarModel(afiliadoARegistrarModel = afiliadoARegistrarModel).guardarCredencialesSesion()
    }

    //endregion
}