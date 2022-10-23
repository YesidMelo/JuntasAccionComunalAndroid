package com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos

import androidx.room.*
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.BaseEntity

@Dao
interface BaseDao<T : BaseEntity> {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertarElemento(elemento: T) : Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertarElementos(elementos: List<T>)

    @Update
    fun actualizarElemento(elemento: T)

    @Update
    fun actualizarElementos(elementos: List<T>)

    @Delete
    fun eliminarElemento(elemento: T)

    @Delete
    fun eliminarListaElementos(elementos: List<T>)

}