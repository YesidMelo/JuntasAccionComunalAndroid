package com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores

import androidx.annotation.StringRes
import com.hefesto.juntasaccioncomunal.R

enum class TipoTelefono constructor(
    private val id: Int,
    @StringRes private val stringRes: Int
) {
    FIJO(id = 1, stringRes = R.string.telefono_fijo),
    MOVIL(id = 2, stringRes = R.string.telefono_movil),
    ;

    fun traerId() = id
    fun traerStringRes() = stringRes

    companion object {
        fun buscarTipoTelefonoPorId(id: Int?) : TipoTelefono {
            val idFinal = id?:return FIJO
            for (tipo in values()) {
                if (idFinal != tipo.id) continue
                return tipo
            }
            return FIJO
        }
    }
}