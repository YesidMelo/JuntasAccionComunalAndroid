package com.hefesto.juntasaccioncomunal.logica.excepciones

import androidx.annotation.StringRes
import com.hefesto.juntasaccioncomunal.R

open class LogicaExcepcion(
    val mensaje : String = "Surgio un problema, intentalo mas tarde",
    @StringRes val stringResMensaje : Int = R.string.surgio_un_problema,
    @StringRes val stringResTitulo : Int = R.string.error_app,
    val tipoExcepcion : TiposExcepciones = TiposExcepciones.GENERADO_SISTEMA
) : Exception(mensaje)