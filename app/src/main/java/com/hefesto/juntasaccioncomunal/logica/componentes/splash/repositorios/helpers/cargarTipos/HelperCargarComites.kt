package com.hefesto.juntasaccioncomunal.logica.componentes.splash.repositorios.helpers.cargarTipos

import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.general.ComitesDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.general.ComitesEntity
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.ComitesEnJAC
import javax.inject.Inject

class HelperCargarComites constructor(
    @JvmField @Inject var comitesDao: ComitesDao
) {

    suspend fun cargarComites() {
        val lista = emptyList<ComitesEntity>().toMutableList()
        for(comite in ComitesEnJAC.values()) {
            lista.add(ComitesEntity(comiteId = comite.traerId(), nombre = comite.traerNombre()))
        }
        comitesDao.insertarElementos(elementos = lista)
    }

}