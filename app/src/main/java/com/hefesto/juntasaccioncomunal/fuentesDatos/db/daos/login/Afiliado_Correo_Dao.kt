package com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login

import androidx.room.Dao
import androidx.room.Query
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.BaseDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.Afiliado_Correo_Entity

@Dao
interface Afiliado_Correo_Dao : BaseDao<Afiliado_Correo_Entity> {

    @Query("SELECT t.registro FROM Afiliado_Correo_Entity t WHERE t.afiliadoId=:afiliadoId AND t.correoId = :correoId ")
    fun traerRegistroId(afiliadoId: Int, correoId: Int) : Int?
}