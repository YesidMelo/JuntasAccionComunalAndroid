package com.hefesto.juntasaccioncomunal.logica.componentes.splash.repositorios.helpers.cargarTipos

import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.RolAfiliacionDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.RolAfiliacionEntity
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.RolDeAfiliacion
import javax.inject.Inject

class HelperCargarRolAfiliacion constructor(
    @JvmField @Inject var rolAfiliacionDao: RolAfiliacionDao
) {

    suspend fun cargar() {
        val lista = emptyList<RolAfiliacionEntity>().toMutableList()
        lista.add(RolAfiliacionEntity(rolAfiliacionId = RolDeAfiliacion.ARRENDATARIO.traerId(), nombre = "Arrendatario"))
        lista.add(RolAfiliacionEntity(rolAfiliacionId = RolDeAfiliacion.DUENIO_CASA.traerId(), nombre = "Dueño casa"))
        lista.add(RolAfiliacionEntity(rolAfiliacionId = RolDeAfiliacion.DUENIO_NEGOCIO.traerId(), nombre = "Dueño Negocio"))
        lista.add(RolAfiliacionEntity(rolAfiliacionId = RolDeAfiliacion.HIJO_ARRENDATARIO.traerId(), nombre = "Hijo arrendatario"))
        lista.add(RolAfiliacionEntity(rolAfiliacionId = RolDeAfiliacion.HIJO_DUENIO_CASA.traerId(), nombre = "Hijo dueño casa"))
        rolAfiliacionDao.insertarElementos(lista)
    }

}