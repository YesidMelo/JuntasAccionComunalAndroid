package com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login

import androidx.room.Dao
import androidx.room.Query
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.BaseDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.DireccionesEntity

@Dao
interface DireccionDao : BaseDao<DireccionesEntity> {
    @Query("SELECT DireccionesEntity.direccionId  FROM DireccionesEntity WHERE DireccionesEntity.direccion = :direccion")
    fun traerIdPorDireccion(direccion: String): Int?
}