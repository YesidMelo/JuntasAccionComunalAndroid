package com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.reunionAsamblea

import androidx.room.Dao
import androidx.room.Query
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.BaseDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.reunionAsamblea.ReunionAsambleaEntity

@Dao
interface ReunionAsambleaDao : BaseDao<ReunionAsambleaEntity> {

    @Query("SELECT * from ReunionAsambleaEntity t where t.creoActa = 0")
    fun traerListaReunionesParaCreacionActas() : List<ReunionAsambleaEntity>

}