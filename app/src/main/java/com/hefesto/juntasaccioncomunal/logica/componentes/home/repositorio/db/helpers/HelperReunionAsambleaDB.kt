package com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers

import android.util.Log
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.reunionAsamblea.HelperAgendarReunionDB
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.DetalleReunionAAgendarModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface HelperReunionAsambleaDB {
    fun agendarReunion(detalleReunionAAgendarModel: DetalleReunionAAgendarModel) : Flow<Boolean>
}

class HelperReunionAsambleaDBImpl constructor(
    @JvmField @Inject var helperAgendarReunionDB : HelperAgendarReunionDB
) : HelperReunionAsambleaDB {

    override fun agendarReunion(detalleReunionAAgendarModel: DetalleReunionAAgendarModel): Flow<Boolean> = flow {
        Log.e("Error", "agendando reunion")
        delay(5000)
        emit(true)
    }

}