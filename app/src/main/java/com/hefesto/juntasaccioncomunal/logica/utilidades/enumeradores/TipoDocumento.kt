package com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores

import androidx.annotation.StringRes
import com.hefesto.juntasaccioncomunal.R

enum class TipoDocumento constructor(
    private val id: Int,
    @StringRes private val stringRes: Int
) {
    REGISTRO_CIVIL(id = 1, stringRes = R.string.registro_civil),
    TARJETA_IDENTIDAD(id = 2, stringRes = R.string.tarjeta_identidad),
    CEDULA_CIUDADANIA(id = 3, stringRes = R.string.cedula_ciudadania),
    CEDULA_EXTRANJERIA(id = 4, stringRes = R.string.cedula_extranjeria),
    ;

    fun traerId() = id

    fun traerStringRes() = stringRes
}