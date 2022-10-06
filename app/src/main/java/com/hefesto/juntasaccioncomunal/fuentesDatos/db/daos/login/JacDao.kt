package com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login

import androidx.room.Dao
import androidx.room.Query
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.BaseDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.JACEntity

@Dao
interface JacDao : BaseDao<JACEntity> {

    @Query("SELECT * FROM JACEntity")
    fun traerListaTodosLosRegistros(): List<JACEntity>

    @Query("SELECT * FROM JACEntity WHERE JACEntity.codigoJAC = :codigoJAC")
    fun encontrarRegistroPorCorreoYCodigoJAC(codigoJAC: String) : JACEntity?

}