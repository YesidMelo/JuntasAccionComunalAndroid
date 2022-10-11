package com.hefesto.juntasaccioncomunal.logica.utilidades.extenciones

import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.FormatosFecha
import java.text.SimpleDateFormat
import java.util.*

fun String.convertirADate(formatoEntrada: FormatosFecha) : Date? {
    val dateFormat= SimpleDateFormat(formatoEntrada.traerFormatoFecha())
    return dateFormat.parse(this)
}