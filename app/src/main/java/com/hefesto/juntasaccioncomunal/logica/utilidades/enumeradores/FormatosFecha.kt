package com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores

enum class FormatosFecha (private val formato: String){
    ISO_8610("yyyy-MM-dd'T'HH:mm:ss.SSSZ"),
    SLASH_1(" yyyy / MM / dd "),
    ;

    fun traerFormatoFecha() = formato
}