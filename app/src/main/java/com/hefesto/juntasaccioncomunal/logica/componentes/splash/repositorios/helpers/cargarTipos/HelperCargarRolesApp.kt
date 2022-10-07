package com.hefesto.juntasaccioncomunal.logica.componentes.splash.repositorios.helpers.cargarTipos

import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.general.RolesAppDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.general.RolesAppEntity
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.RolesEnApp
import javax.inject.Inject

class HelperCargarRolesApp constructor(
    @JvmField @Inject var rolesAppDao: RolesAppDao
) {

    suspend fun cargar() {
        val lista = emptyList<RolesAppEntity>().toMutableList()
        RolesEnApp.values().forEach { lista.add(RolesAppEntity(rolid = it.traerId(), nombre = it.traerNombre())) }
        rolesAppDao.insertarElementos(lista)
    }
}