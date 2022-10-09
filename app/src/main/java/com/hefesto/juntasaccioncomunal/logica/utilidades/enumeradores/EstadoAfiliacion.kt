package com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores

import androidx.annotation.StringRes
import com.hefesto.juntasaccioncomunal.R

enum class EstadoAfiliacion (
    private val id: Int,
    @StringRes private val stringRes: Int
) {
    PRE_AFILIADO(id = 1, stringRes = R.string.pre_afiliado),
    ACTIVO(id = 2, stringRes = R.string.activo),
    INACTIVO(id = 3, stringRes = R.string.inactivo),
    DESAFILIADO(id = 4, stringRes = R.string.desafiliado),
    EXPULSADO(id = 5, stringRes = R.string.expulsado),
    FALLECIDO(id = 6, stringRes = R.string.fallecido),
    ;

    fun traerId() = id
    fun traerStringRes() = stringRes

    companion object {
        fun traerEstadoAfiliacionPorId(id : Int) : EstadoAfiliacion {
            for(estadoAfiliacion in values()) {
                if (estadoAfiliacion.traerId() != id) continue
                return estadoAfiliacion
            }
            return EstadoAfiliacion.PRE_AFILIADO
        }
    }
}