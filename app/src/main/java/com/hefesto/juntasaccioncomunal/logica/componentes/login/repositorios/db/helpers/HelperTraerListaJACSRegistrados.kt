package com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.helpers

import androidx.lifecycle.MutableLiveData
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.JacDao
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.mapper.convertirAListaJacDisponibleParaAfiliacion
import com.hefesto.juntasaccioncomunal.logica.excepciones.LogicaExcepcion
import com.hefesto.juntasaccioncomunal.logica.modelos.login.registrarAfiliado.JACDisponibleParaAfiliadoModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HelperTraerListaJACSRegistrados constructor(
    private val listaJacsRegistradas : MutableLiveData<List<JACDisponibleParaAfiliadoModel>?>,
    private val jacDao: JacDao,
    private val escuchadorExcepciones: MutableLiveData<LogicaExcepcion?>
) {

    fun traerLista() {
        GlobalScope.launch {
            listaJacsRegistradas.postValue(null)
            delay(5000)
            cargoListaDesdeDBLocal()
        }
    }

    //region metodos privados
    private fun cargoListaDesdeDBLocal(): Boolean {
        val lista = jacDao.traerListaTodosLosRegistros()
        listaJacsRegistradas.postValue(lista.convertirAListaJacDisponibleParaAfiliacion())
        return true
    }
    //endregion
}