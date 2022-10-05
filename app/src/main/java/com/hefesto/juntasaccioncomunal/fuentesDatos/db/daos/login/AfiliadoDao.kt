package com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login

import androidx.room.Dao
import androidx.room.Query
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.BaseDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.AfiliadoEntity

@Dao
interface AfiliadoDao : BaseDao<AfiliadoEntity> {

    @Query("SELECT AfiliadoEntity.afiliadoId FROM AfiliadoEntity WHERE AfiliadoEntity.afiliadoId = :tipoDocumento AND AfiliadoEntity.documento = :documento")
    fun traerId(tipoDocumento: Int, documento: String) : Int?

}