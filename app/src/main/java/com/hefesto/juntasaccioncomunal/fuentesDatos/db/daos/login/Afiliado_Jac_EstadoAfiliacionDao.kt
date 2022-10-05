package com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login

import androidx.room.Dao
import androidx.room.Query
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.BaseDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.Afiliado_Jac_EstadoAfiliacionEntity

@Dao
interface Afiliado_Jac_EstadoAfiliacionDao : BaseDao<Afiliado_Jac_EstadoAfiliacionEntity>{

    @Query("SELECT t.registro FROM Afiliado_Jac_EstadoAfiliacionEntity t WHERE t.afiliadoId = :afiliadoId AND t.jacId = :jacId")
    fun traerIdRegistroConAfiliadoIdYJACID(afiliadoId: Int, jacId: Int) : Int?
}