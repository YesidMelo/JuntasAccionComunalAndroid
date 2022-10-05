package com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login

import androidx.room.Dao
import androidx.room.Query
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.BaseDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.CorreosEntity

@Dao
interface CorreoDao : BaseDao<CorreosEntity>{

    @Query("SELECT CorreosEntity.registro FROM CorreosEntity WHERE CorreosEntity.correo = :correo")
    fun traerId(correo : String): Int?
}