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

fun Date.esMenorAFecha(fecha: Date) : Boolean {
    if (this.before(fecha) || this.equals(fecha)) return true
    return false
}

fun Date.esMenorAFechaConMinimo(fecha: Date, identificador : Int, tiempo: Int) : Boolean {
    val calendar = GregorianCalendar.getInstance()
    calendar.time = this
    calendar.add(identificador, tiempo)
    val tiempoMinimo = Date(calendar.timeInMillis)
    if (tiempoMinimo.before(fecha) || tiempoMinimo.equals(fecha) ) return true
    return false
}

fun Date.convertirAFormato(formato: FormatosFecha) : String {
    val dateFormat= SimpleDateFormat(formato.traerFormatoFecha())
    return dateFormat.format(this)
}