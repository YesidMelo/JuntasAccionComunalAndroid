package com.hefesto.juntasaccioncomunal.logica.componentes.splash.repositorios.helpers.cargarTipos

import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.general.RolApp_FuncionesApp_Dao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.general.RolApp_FuncionesApp_Entity
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.FuncionesRolApp
import javax.inject.Inject

class HelperCargarRelacionRolFunciones (
    @JvmField @Inject var rolappFuncionesappDao: RolApp_FuncionesApp_Dao
) {

    suspend fun cargarRelacion() {
        val lista = emptyList<RolApp_FuncionesApp_Entity>().toMutableList()
        FuncionesRolApp.values().forEach {
            lista.add(RolApp_FuncionesApp_Entity(
                rolAppId = it.traerRolEncargado().traerId(),
                funcionAppId = it.traerId()
            ))
        }

        rolappFuncionesappDao.insertarElementos(elementos = lista)
    }
}