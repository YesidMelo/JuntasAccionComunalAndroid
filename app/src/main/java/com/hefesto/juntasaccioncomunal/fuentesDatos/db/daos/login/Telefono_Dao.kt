package com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login

import androidx.room.Dao
import androidx.room.Query
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.BaseDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.TelefonosEntity

@Dao
interface Telefono_Dao : BaseDao<TelefonosEntity>{

    @Query("SELECT TelefonosEntity.telefonoId FROM TelefonosEntity WHERE TelefonosEntity.tipoTelefonoId = :tipoTelefono AND TelefonosEntity.telefono = :numeroTelefono")
    fun traerIdPorTipoTelefonoYNumero(tipoTelefono: Int, numeroTelefono: String): Int?

}