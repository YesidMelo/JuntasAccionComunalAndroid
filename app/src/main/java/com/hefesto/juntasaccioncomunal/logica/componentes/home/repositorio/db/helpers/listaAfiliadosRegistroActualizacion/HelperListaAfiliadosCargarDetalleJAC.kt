package com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.listaAfiliadosRegistroActualizacion

import android.util.Log
import com.hefesto.juntasaccioncomunal.fuentesDatos.cache.MemoriaCache
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.AfiliadoDao
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.mappers.convertirADatosJACModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.DatosBasicosAfiliadoActualizarRegistrarInformacionModel
import kotlinx.coroutines.delay
import javax.inject.Inject

interface HelperListaAfiliadosCargarDetalleJAC {
    suspend fun cargarDetalleJAC(lista: List<DatosBasicosAfiliadoActualizarRegistrarInformacionModel>)
}

class HelperListaAfiliadosCargarDetalleJACImpl constructor(
    @JvmField @Inject var afiliadoDao: AfiliadoDao
): HelperListaAfiliadosCargarDetalleJAC {

    override suspend fun cargarDetalleJAC(lista: List<DatosBasicosAfiliadoActualizarRegistrarInformacionModel>) {
        for(contador in lista.indices) {
            val afiliadoId = lista[contador].afiliadoId?:continue
            val jacId = lista[contador].jacId?:continue
            val detalleJAC = afiliadoDao.traerAfiliadoDetalleJAC(afiliadoId = afiliadoId, jacId = jacId)?:continue
            lista[contador].datosJACModel = detalleJAC.convertirADatosJACModel()
        }
    }

}