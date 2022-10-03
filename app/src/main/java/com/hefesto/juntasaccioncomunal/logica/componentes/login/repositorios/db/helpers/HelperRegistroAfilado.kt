package com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.helpers

import androidx.lifecycle.MutableLiveData
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.AfiliadoDao
import com.hefesto.juntasaccioncomunal.logica.excepciones.LogicaExcepcion
import com.hefesto.juntasaccioncomunal.logica.modelos.login.registrarAfiliado.AfiliadoARegistrarModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HelperRegistroAfilado constructor(
    private val afiliadoARegistrarModel: AfiliadoARegistrarModel,
    private val escuchadorExcepciones: MutableLiveData<LogicaExcepcion?>,
    private val escuchadorRegistroAfiliadoExitoso: MutableLiveData<Boolean?>,
    private val afiliadoDao: AfiliadoDao
) {

    fun registrar() {
        GlobalScope.launch {
            escuchadorRegistroAfiliadoExitoso.postValue(null)
            delay(5000)
            escuchadorRegistroAfiliadoExitoso.postValue(true)
        }
    }
}