package com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login

import androidx.room.Dao
import androidx.room.Query
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.BaseDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.CredencialesSesionEntity

@Dao
interface CredencialesSesionDao : BaseDao<CredencialesSesionEntity> {

    @Query("SELECT cre.registro FROM CredencialesSesionView cre WHERE cre.correo = :correo")
    fun traerRegistroId(correo: String) : Int?

}