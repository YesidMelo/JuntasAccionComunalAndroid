package com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.reunionAsamblea

import androidx.room.Dao
import androidx.room.Query
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.BaseDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.reunionAsamblea.ConvocantesEntity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.views.home.ConvocantesDisponiblesView

@Dao
interface ConvocatesDao : BaseDao<ConvocantesEntity> {

    @Query("Select t.* FROM ConvocantesDisponiblesView t WHERE t.jacId = :jacId")
    fun traerListaConvocantesPorJacId(jacId:Int): List<ConvocantesDisponiblesView>

}