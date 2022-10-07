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
        for (funcionRolapp in FuncionesRolApp.values()) {
            if (traerRegistroId(funcionesRolApp = funcionRolapp) != null) continue
            lista.add(RolApp_FuncionesApp_Entity(
                rolAppId = funcionRolapp.traerRolEncargado().traerId(),
                funcionAppId = funcionRolapp.traerId()
            ))
        }
        if (lista.isEmpty()) return
        rolappFuncionesappDao.insertarElementos(elementos = lista)
    }

    //region metodos privados
    private fun traerRegistroId(funcionesRolApp: FuncionesRolApp) : Int? {
        return rolappFuncionesappDao.traerRegistroIdPorRolAppIdYFuncionAppId(
            rolAppId = funcionesRolApp.traerRolEncargado().traerId(),
            funcionAppId = funcionesRolApp.traerId()
        )
    }
    //endregion
}