package com.hefesto.juntasaccioncomunal.logica.componentes.splash.repositorios.helpers.cargarTipos

import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.general.TipoReunionDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.general.TipoReunionEntity
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.TipoReunion
import javax.inject.Inject

interface HelperCargarTiposReunion {
    suspend fun cargarTiposReunion()
}

class HelperCargarTiposReunionImpl constructor(
    @JvmField @Inject var tipoReunionDao: TipoReunionDao
) : HelperCargarTiposReunion {

    override suspend fun cargarTiposReunion() {
        val lista = emptyList<TipoReunionEntity>().toMutableList()
        TipoReunion.values().forEach {
            lista.add(TipoReunionEntity(tipoReunionId = it.traerId(), nombre = it.traerNombre()))
        }
        tipoReunionDao.insertarElementos(elementos = lista)
    }

}