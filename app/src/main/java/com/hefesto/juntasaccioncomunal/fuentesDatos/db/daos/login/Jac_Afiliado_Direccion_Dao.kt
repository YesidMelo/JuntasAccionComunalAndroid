package com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login

import androidx.room.Dao
import androidx.room.Query
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.BaseDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.Jac_Afiliado_Direccion_Entity

@Dao
interface Jac_Afiliado_Direccion_Dao : BaseDao<Jac_Afiliado_Direccion_Entity> {

    @Query("SELECT t.registroId FROM Jac_Afiliado_Direccion_Entity t WHERE t.jacId = :jacId AND t.direccionId = :direccionId AND t.afiliadoId = :afiliadoId")
    fun traerNumeroRegistro(jacId: Int, direccionId: Int, afiliadoId : Int) : Int?
}