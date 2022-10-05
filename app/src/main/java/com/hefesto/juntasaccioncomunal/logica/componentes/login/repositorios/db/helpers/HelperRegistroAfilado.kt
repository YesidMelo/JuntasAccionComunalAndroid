package com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.helpers

import androidx.lifecycle.MutableLiveData
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.AfiliadoDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.Afiliado_Correo_Dao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.Afiliado_Jac_EstadoAfiliacionDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.CorreoDao
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.helpers.registroAfiliado.HelperRegistroAfiliadoEntity
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.helpers.registroAfiliado.HelperRegistroCorreoAfiliadoEntity
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.helpers.registroAfiliado.HelperRegistroDireccionAfiliadoEntity
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

            helperRegistroAfiliadoEntity.conAfiliadoARegistrarModel(afiliadoARegistrarModel = afiliadoARegistrarModel).guardarAfiliado()
            helperRegistroCorreoAfiliadoEntity.conAfiliadoARegistrarModel(afiliadoARegistrarModel = afiliadoARegistrarModel).guardarCorreo()
            helperRegistroDireccionAfiliadoEntity.conAfiliadoARegistrarModel(afiliadoARegistrarModel = afiliadoARegistrarModel).guardarDireccion()
            delay(5000)
            escuchadorRegistroAfiliadoExitoso.postValue(true)
        }
    }
}