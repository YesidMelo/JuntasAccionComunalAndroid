package com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores

import androidx.annotation.StringRes
import com.hefesto.juntasaccioncomunal.R

enum class TipoReunion constructor(
    private val id: Int,
    private val nombre: String,
    @StringRes private var stringRes: Int
) {
    ASAMBLEA_INFORMATIVA(id = 1, nombre =  "Asamblea informativa", stringRes = R.string.asamblea_informativa),
    ASAMBLEA_DECISORIA(id = 2, nombre =  "Asamblea decisoria", stringRes = R.string.asamblea_decisoria),
    REUNION_DIRECTIVA_INFORMATIVA(id = 3, nombre =  "Reunion Directiva informativa", stringRes = R.string.reunion_directiva_informativa),
    REUNION_DIRECTIVA_DECISORIA(id = 4, nombre =  "Reunion Directiva decisoria", stringRes = R.string.reunion_directiva_decisoria),
    ;

    fun traerId() = id
    fun traerNombre() = nombre
    fun traerStringRes() = stringRes

    companion object {
        fun buscarTipoReunionPorId(id: Int?) : TipoReunion {
            val idfinal = id?: return REUNION_DIRECTIVA_INFORMATIVA
            for (item in values()) {
                if (item.id != idfinal) continue
                return item
            }
            return REUNION_DIRECTIVA_INFORMATIVA
        }
    }
}