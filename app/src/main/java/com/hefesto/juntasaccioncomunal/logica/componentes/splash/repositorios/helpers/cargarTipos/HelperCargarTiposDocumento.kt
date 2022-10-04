package com.hefesto.juntasaccioncomunal.logica.componentes.splash.repositorios.helpers.cargarTipos

import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.general.TipoDocumentoDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.general.TipoDocumentoEntity
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.TipoDocumento

class HelperCargarTiposDocumento constructor(
    var tipoDocumentoDao: TipoDocumentoDao
) {

    suspend fun cargar() {
        val lista = emptyList<TipoDocumentoEntity>().toMutableList()
        lista.add(TipoDocumentoEntity(tipoDocumentoId = TipoDocumento.CEDULA_CIUDADANIA.traerId(), "Cedula ciudadania"))
        lista.add(TipoDocumentoEntity(tipoDocumentoId = TipoDocumento.CEDULA_EXTRANJERIA.traerId(), "Cedula Extranjeria"))
        lista.add(TipoDocumentoEntity(tipoDocumentoId = TipoDocumento.REGISTRO_CIVIL.traerId(), "Registro civil"))
        lista.add(TipoDocumentoEntity(tipoDocumentoId = TipoDocumento.TARJETA_IDENTIDAD.traerId(), "Tarjeta identidad"))
        tipoDocumentoDao.insertarElementos(lista)
    }
}