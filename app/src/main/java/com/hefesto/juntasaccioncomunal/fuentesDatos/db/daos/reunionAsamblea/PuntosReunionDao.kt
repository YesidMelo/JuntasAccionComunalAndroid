package com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.reunionAsamblea

import androidx.room.Dao
import androidx.room.Query
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.BaseDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.reunionAsamblea.PuntosReunionEntity

@Dao
interface PuntosReunionDao : BaseDao<PuntosReunionEntity> {

    @Query("SELECT * from PuntosReunionEntity t where t.reunionId = :reunionId")
    fun traerListaPuntosDeLaReunionPorReunionId(reunionId: Int): List<PuntosReunionEntity>

}