package com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login

import androidx.room.Dao
import androidx.room.Query
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.BaseDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.CredencialesSesionEntity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.views.login.CredencialesSesionView

@Dao
interface CredencialesSesionDao : BaseDao<CredencialesSesionEntity> {

    @Query("SELECT cre.registro FROM CredencialesSesionView cre WHERE cre.correo = :correo")
    fun traerRegistroId(correo: String) : Int?

    @Query("SELECT * FROM CredencialesSesionView cre WHERE cre.correo = :correo AND cre.contrasenia = :contrasenia")
    fun traerCredencialesSesionView(correo: String, contrasenia: String) : CredencialesSesionView?

    @Query("SELECT * FROM CredencialesSesionEntity t WHERE t.registro = :credencialesId")
    fun traerCredencialesPorCredencialesId(credencialesId: Int): CredencialesSesionEntity

}