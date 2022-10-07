package com.hefesto.juntasaccioncomunal.logica.componentes.splash.repositorios.helpers.cargarTipos

import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.EstadoAfiliacionDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.EstadoAfiliacionEntity
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.EstadoAfiliacion
import javax.inject.Inject

class HelperCargarEstadoAfiliacion constructor(
    @JvmField @Inject var estadoAfiliacionDao: EstadoAfiliacionDao
){

    suspend fun cargar() {
        val lista = emptyList<EstadoAfiliacionEntity>().toMutableList()
        lista.add(EstadoAfiliacionEntity(estadoAfiliadoId = EstadoAfiliacion.ACTIVO.traerId(), nombre = "Activo"))
        lista.add(EstadoAfiliacionEntity(estadoAfiliadoId = EstadoAfiliacion.PRE_AFILIADO.traerId(), nombre = "Preafiliado"))
        lista.add(EstadoAfiliacionEntity(estadoAfiliadoId = EstadoAfiliacion.DESAFILIADO.traerId(), nombre = "Desafiliado"))
        lista.add(EstadoAfiliacionEntity(estadoAfiliadoId = EstadoAfiliacion.EXPULSADO.traerId(), nombre = "Expulsado"))
        lista.add(EstadoAfiliacionEntity(estadoAfiliadoId = EstadoAfiliacion.FALLECIDO.traerId(), nombre = "Fallecido"))
        lista.add(EstadoAfiliacionEntity(estadoAfiliadoId = EstadoAfiliacion.INACTIVO.traerId(), nombre = "Inactivo"))
        estadoAfiliacionDao.insertarElementos(lista)
    }
}