package com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.reunionAsamblea

import androidx.room.Dao
import androidx.room.Query
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.BaseDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.reunionAsamblea.ListaAsistenciaEntity

@Dao
interface ListaAsistenciaDao : BaseDao<ListaAsistenciaEntity> {

    @Query("SELECT count(DISTINCT t.afiliadoId) as asistentes from ListaAsistenciaEntity t where t.reunionasistenciaid = :reunionId")
    fun traerNumeroAsistentesPorReunionId(reunionId: Int): Int
}