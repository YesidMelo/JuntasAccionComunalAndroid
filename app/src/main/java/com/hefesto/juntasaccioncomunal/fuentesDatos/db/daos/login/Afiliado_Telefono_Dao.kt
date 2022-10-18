package com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login

import androidx.room.Dao
import androidx.room.Query
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.BaseDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.Afiliado_Telefono_Entity

@Dao
interface Afiliado_Telefono_Dao : BaseDao<Afiliado_Telefono_Entity> {

    @Query("SELECT t.registroID FROM Afiliado_Telefono_Entity t WHERE t.afiliadoId = :afiliadoId AND t.telefonoId = :telefonoId")
    fun traerRegistroId(afiliadoId: Int, telefonoId: Int): Int?
}