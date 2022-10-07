package com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login

import androidx.room.Dao
import androidx.room.Query
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.BaseDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.JACEntity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.views.login.JACEnSesionView

@Dao
interface JacDao : BaseDao<JACEntity> {

    @Query("SELECT * FROM JACEntity")
    fun traerListaTodosLosRegistros(): List<JACEntity>

    @Query("SELECT * FROM JACEntity WHERE JACEntity.codigoJAC = :codigoJAC")
    fun encontrarRegistroPorCorreoYCodigoJAC(codigoJAC: String) : JACEntity?

    @Query("SELECT * FROM JACEnSesionView jac WHERE jac.correo = :correo")
    fun traerJAcEnSesion(correo: String): JACEnSesionView?
}