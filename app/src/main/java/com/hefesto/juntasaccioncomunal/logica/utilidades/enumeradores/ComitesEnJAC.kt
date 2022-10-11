package com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores

import androidx.annotation.StringRes
import com.hefesto.juntasaccioncomunal.R

enum class ComitesEnJAC (
    private val id: Int,
    private val nombre: String,
    @StringRes private val stringRes: Int
) {
    TRABAJO(id = 1, nombre = "trabajo", stringRes = R.string.comite_trabajo),
    SALUD(id = 2, nombre = "Salud", stringRes = R.string.comite_salud),
    DEPORTES(id = 3, nombre = "Deportes", stringRes = R.string.comite_deportes),
    AMBIENTE(id = 4, nombre = "Ambiente", stringRes = R.string.comite_ambiente),
    ;
    fun traerId() = id
    fun traerNombre() = nombre
    fun traerStringRes() = stringRes
}