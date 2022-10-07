package com.hefesto.juntasaccioncomunal.logica.componentes.splash.repositorios.helpers.cargarTipos

import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.general.FuncionesRolAppDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.general.FuncionesRolAppEntity
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.FuncionesRolApp
import javax.inject.Inject

class HelperCargarFuncionesApp constructor(
    @JvmField @Inject var funcionesRolAppDao: FuncionesRolAppDao
) {

    suspend fun cargar() {
        val lista = emptyList<FuncionesRolAppEntity>().toMutableList()
        FuncionesRolApp.values().forEach {
            lista.add(FuncionesRolAppEntity(funcionId = it.traerId(), nombre = it.traerNombre()))
        }
        funcionesRolAppDao.insertarElementos(lista)
    }
}