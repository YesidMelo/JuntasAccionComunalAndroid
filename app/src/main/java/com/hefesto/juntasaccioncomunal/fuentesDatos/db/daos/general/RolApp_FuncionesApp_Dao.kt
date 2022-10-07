package com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.general

import androidx.room.Dao
import androidx.room.Query
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.BaseDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.general.RolApp_FuncionesApp_Entity

@Dao
interface RolApp_FuncionesApp_Dao : BaseDao<RolApp_FuncionesApp_Entity> {

    @Query("SELECT rfe.registro FROM RolApp_FuncionesApp_Entity rfe WHERE  rfe.funcionAppId = :funcionAppId AND  rfe.rolAppId = :rolAppId")
    fun traerRegistroIdPorRolAppIdYFuncionAppId(rolAppId: Int, funcionAppId: Int) : Int?

    @Query("SELECT rfe.funcionAppId FROM RolApp_FuncionesApp_Entity rfe WHERE rfe.rolAppId = :rolAppId")
    fun traerListaFuncionesAppPorRol(rolAppId: Int): List<Int>
}