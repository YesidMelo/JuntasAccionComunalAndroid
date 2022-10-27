package com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.reunionAsamblea

import androidx.room.Dao
import androidx.room.Query
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.BaseDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.reunionAsamblea.ReunionAsambleaEntity

@Dao
interface ReunionAsambleaDao : BaseDao<ReunionAsambleaEntity> {

    @Query("SELECT * from ReunionAsambleaEntity t where t.creoActa = 0 and t.jacId = :jacId")
    fun traerListaReunionesParaCreacionActas(jacId: Int) : List<ReunionAsambleaEntity>

    @Query("SELECT * from ReunionAsambleaEntity t where t.creoActa = 1 and t.jacId = :jacId")
    fun traerListaReunionesParaGeneracionPDF(jacId: Int) : List<ReunionAsambleaEntity>

}