package com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores

import androidx.annotation.StringRes
import com.hefesto.juntasaccioncomunal.R

enum class TipoReunion constructor(
    private val id: Int,
    private val nombre: String,
    @StringRes private var stringRes: Int,
    private val minimoDiasAgendarReunion : Int = 0,
) {
    ASAMBLEA_INFORMATIVA(id = 1, nombre =  "Asamblea informativa", stringRes = R.string.asamblea_informativa, minimoDiasAgendarReunion = 15),
    ASAMBLEA_DECISORIA(id = 2, nombre =  "Asamblea decisoria", stringRes = R.string.asamblea_decisoria, minimoDiasAgendarReunion = 15),
    REUNION_DIRECTIVA_INFORMATIVA(id = 3, nombre =  "Reunion Directiva informativa", stringRes = R.string.reunion_directiva_informativa, minimoDiasAgendarReunion = 3),
    REUNION_DIRECTIVA_DECISORIA(id = 4, nombre =  "Reunion Directiva decisoria", stringRes = R.string.reunion_directiva_decisoria, minimoDiasAgendarReunion = 3),
    ;

    fun traerId() = id
    fun traerNombre() = nombre
    fun traerStringRes() = stringRes
    fun traerMinimoDiasAgendarReunion() = minimoDiasAgendarReunion

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