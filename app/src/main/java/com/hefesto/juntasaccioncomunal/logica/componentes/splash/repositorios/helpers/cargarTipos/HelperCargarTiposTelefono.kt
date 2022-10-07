package com.hefesto.juntasaccioncomunal.logica.componentes.splash.repositorios.helpers.cargarTipos

import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.general.TipoTelefonoDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.general.TipoTelefonoEntity
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.TipoTelefono

class HelperCargarTiposTelefono constructor(
    var tipoTelefonoDao: TipoTelefonoDao
) {

    suspend fun cargar() {
        val lista = emptyList<TipoTelefonoEntity>().toMutableList()
        lista.add(TipoTelefonoEntity(tipoTelefonoId = TipoTelefono.FIJO.traerId(), "Telefono fijo"))
        lista.add(TipoTelefonoEntity(tipoTelefonoId = TipoTelefono.MOVIL.traerId(), "Telefono movil"))
        tipoTelefonoDao.insertarElementos(lista)
    }
}