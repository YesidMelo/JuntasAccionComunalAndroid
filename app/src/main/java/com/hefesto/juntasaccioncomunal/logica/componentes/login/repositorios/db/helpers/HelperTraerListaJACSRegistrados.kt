package com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.helpers

import androidx.lifecycle.MutableLiveData
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.JacDao
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.mapper.convertirAListaJacDisponibleParaAfiliacion
import com.hefesto.juntasaccioncomunal.logica.excepciones.LogicaExcepcion
import com.hefesto.juntasaccioncomunal.logica.modelos.login.registrarAfiliado.JACDisponibleParaAfiliadoModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class HelperTraerListaJACSRegistrados constructor(
    @JvmField @Inject var jacDao: JacDao,
) {

    //region variables
    private lateinit var listaJacsRegistradas : MutableLiveData<List<JACDisponibleParaAfiliadoModel>?>
    private lateinit var escuchadorExcepciones: MutableLiveData<LogicaExcepcion?>
    //endregion

    fun traerLista() {
        GlobalScope.launch {
            listaJacsRegistradas.postValue(null)
            delay(5000)
            cargoListaDesdeDBLocal()
        }
    }

    fun conListaJacsRegistradas(listaJacsRegistradas : MutableLiveData<List<JACDisponibleParaAfiliadoModel>?>) : HelperTraerListaJACSRegistrados {
        this.listaJacsRegistradas = listaJacsRegistradas
        return this
    }

    fun conEscuchadorExcepciones(escuchadorExcepciones: MutableLiveData<LogicaExcepcion?>) : HelperTraerListaJACSRegistrados {
        this.escuchadorExcepciones = escuchadorExcepciones
        return this
    }


    //region metodos privados
    private fun cargoListaDesdeDBLocal(): Boolean {
        val lista = jacDao.traerListaTodosLosRegistros()
        listaJacsRegistradas.postValue(lista.convertirAListaJacDisponibleParaAfiliacion())
        return true
    }
    //endregion
}