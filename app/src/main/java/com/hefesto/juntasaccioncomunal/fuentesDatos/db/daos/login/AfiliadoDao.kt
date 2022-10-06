package com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login

import androidx.room.Dao
import androidx.room.Query
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.BaseDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.AfiliadoEntity

@Dao
interface AfiliadoDao : BaseDao<AfiliadoEntity> {

    @Query("SELECT t.afiliadoId FROM AfiliadoEntity t WHERE t.tipoDocumento = :tipoDocumento AND t.documento = :documento")
    fun traerIdPorTipoDocumentoYDocumento(tipoDocumento: Int, documento: String) : Int?

}