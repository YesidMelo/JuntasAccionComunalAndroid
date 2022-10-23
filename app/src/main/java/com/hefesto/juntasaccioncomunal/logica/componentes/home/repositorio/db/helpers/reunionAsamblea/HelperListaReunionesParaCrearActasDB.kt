package com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.reunionAsamblea

import android.util.Log
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.reunionAsamblea.PuntosReunionDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.reunionAsamblea.ReunionAsambleaDao
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.mappers.convertirAListaReunionAsambleaCreacionActaModel
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.mappers.convertirAPuntoReunionParaCreacionActaModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.crearActa.ReunionAsambleaCreacionActaModel
import kotlinx.coroutines.delay
import javax.inject.Inject

interface HelperListaReunionesParaCrearActasDB {
    suspend fun traerListaReunionesParaCrearActa() : List<ReunionAsambleaCreacionActaModel>
}

class HelperListaReunionesParaCrearActasDBImpl constructor(
    @JvmField @Inject var reunionAsambleaDao: ReunionAsambleaDao,
    @JvmField @Inject var puntosReunionDao: PuntosReunionDao
) : HelperListaReunionesParaCrearActasDB {

    override suspend fun traerListaReunionesParaCrearActa(): List<ReunionAsambleaCreacionActaModel> {
        delay(5000)
        val lista = emptyList<ReunionAsambleaCreacionActaModel>().toMutableList()
        cargarListaActas(listaReuniones = lista)
        cargarPuntosReunion(listaReuniones = lista)
        Log.e("Err","ml")
        return lista
    }

    //region metodos privados
    private fun cargarListaActas(listaReuniones: MutableList<ReunionAsambleaCreacionActaModel>) {
        val listaEntity = reunionAsambleaDao.traerListaReunionesParaCreacionActas()
        val listaModels = listaEntity.convertirAListaReunionAsambleaCreacionActaModel()
        listaModels.forEach { listaReuniones.add(it) }
    }

    private fun cargarPuntosReunion(listaReuniones: MutableList<ReunionAsambleaCreacionActaModel>) {
        for(item in listaReuniones) {
            if (item.reunionAsambleaId == null) continue
            val listaPuntosEntity = puntosReunionDao.traerListaPuntosDeLaReunionPorReunionId(reunionId = item.reunionAsambleaId!!)
            item.listaPuntos = listaPuntosEntity.convertirAPuntoReunionParaCreacionActaModel()
        }
    }
    //endregion

}