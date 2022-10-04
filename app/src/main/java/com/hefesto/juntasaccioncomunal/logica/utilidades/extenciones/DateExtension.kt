package com.hefesto.juntasaccioncomunal.logica.utilidades.extenciones

import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.FormatosFecha
import java.text.SimpleDateFormat
import java.util.*

fun Date.esMenorAFechaActualMenosAnios(anios : Int) : Boolean {
    val calendar = GregorianCalendar.getInstance()
    calendar.add(Calendar.YEAR, anios)
    val fechaMinima = Date(calendar.timeInMillis)
    if (this.before(fechaMinima) || this.equals(fechaMinima)) return true
    return false
}

fun Date.convertirAFormato(formato: FormatosFecha) : String {
    val dateFormat= SimpleDateFormat(formato.traerFormatoFecha())
    return dateFormat.format(this)
}