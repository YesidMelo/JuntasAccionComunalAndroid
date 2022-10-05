package com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login

import androidx.room.Dao
import androidx.room.Query
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.BaseDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.Afiliado_Direccion_Entity

@Dao
interface Afiliado_Direccion_Dao : BaseDao<Afiliado_Direccion_Entity> {

    @Query("SELECT t.registro FROM Afiliado_Direccion_Entity t WHERE t.afiliadoId = :afiliadoId AND t.direccionId = :direccionId")
    fun traerNumeroRegistro(afiliadoId: Int, direccionId: Int) : Int?
}