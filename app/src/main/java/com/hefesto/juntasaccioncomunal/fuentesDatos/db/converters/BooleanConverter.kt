package com.hefesto.juntasaccioncomunal.fuentesDatos.db.converters

import androidx.room.TypeConverter

class BooleanConverter {

    @TypeConverter
    fun deBooleano(value: Boolean?): Int? {
        val valor = value?:return 0
        return if (valor) 1 else 0
    }

    @TypeConverter
    fun deEntero(value: Int?) : Boolean {
        val valor = value?:return false
        return valor == 1
    }
}