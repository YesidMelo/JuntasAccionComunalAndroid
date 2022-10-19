package com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.listaAfiliadosRegistroActualizacion

import com.hefesto.juntasaccioncomunal.fuentesDatos.cache.MemoriaCache
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.AfiliadoDao
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.mappers.convertirADatosJACModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.DatosBasicosAfiliadoActualizarRegistrarInformacionModel
import javax.inject.Inject

interface HelperListaAfiliadosCargarDetalleJAC {
    suspend fun cargarDetalleJAC(lista: List<DatosBasicosAfiliadoActualizarRegistrarInformacionModel>)
}

class HelperListaAfiliadosCargarDetalleJACImpl constructor(
    @JvmField @Inject var afiliadoDao: AfiliadoDao
): HelperListaAfiliadosCargarDetalleJAC {

    override suspend fun cargarDetalleJAC(lista: List<DatosBasicosAfiliadoActualizarRegistrarInformacionModel>) {
        for (item in lista) {
            val afiliadoId = item.afiliadoId?:continue
            val jacId = item.jacId?:continue
            val detalleJAC = afiliadoDao.traerAfiliadoDetalleJAC(afiliadoId = afiliadoId, jacId = jacId)?:continue
            item.datosJACModel = detalleJAC.convertirADatosJACModel()
        }
    }

}