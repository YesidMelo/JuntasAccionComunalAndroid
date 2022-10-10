package com.hefesto.juntasaccioncomunal.logica.componentes.splash.repositorios.helpers.cargarTipos

import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.general.RolApp_FuncionesApp_Dao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.general.RolApp_FuncionesApp_Entity
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.FuncionesRolApp
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.RolesEnApp
import javax.inject.Inject

class HelperCargarRelacionRolFunciones (
    @JvmField @Inject var rolappFuncionesappDao: RolApp_FuncionesApp_Dao
) {

    suspend fun cargarRelacion() {
        val lista = emptyList<RolApp_FuncionesApp_Entity>().toMutableList()
        for (funcionRolapp in FuncionesRolApp.values()) {
            inspeccionarFuncionesYRoles(funcionesRolApp = funcionRolapp, lista = lista)
        }
        if (lista.isEmpty()) return
        rolappFuncionesappDao.insertarElementos(elementos = lista)
    }

    //region metodos privados


    private fun inspeccionarFuncionesYRoles(
        funcionesRolApp: FuncionesRolApp,
        lista: MutableList<RolApp_FuncionesApp_Entity>
    ) {
        for ( rol in funcionesRolApp.traerRolesEncargados()) {
            if (traerRegistroId(funcionesRolApp = funcionesRolApp, rol = rol) != null) continue
            lista.add(RolApp_FuncionesApp_Entity(
                rolAppId = rol.traerId(),
                funcionAppId = funcionesRolApp.traerId()
            ))
        }
    }

    private fun traerRegistroId(funcionesRolApp: FuncionesRolApp, rol: RolesEnApp) : Int? {
        return rolappFuncionesappDao.traerRegistroIdPorRolAppIdYFuncionAppId(
            rolAppId = rol.traerId(),
            funcionAppId = funcionesRolApp.traerId()
        )
    }


    //endregion
}