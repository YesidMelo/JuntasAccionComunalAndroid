package com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores

enum class FormatosFecha (private val formato: String){
    ISO_8610("yyyy-MM-dd'T'HH:mm:ss"),
    SLASH_1(" yyyy / MM / dd "),
    HORA_MINUTO_24H("HH:mm"),
    HORA_MINUTO_12H("hh:mm a"),
    ANIO_MES_DIA_GIONES("yyyy-MM-dd")
    ;

    fun traerFormatoFecha() = formato
}