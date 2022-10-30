package com.hefesto.juntasaccioncomunal.interfaceUsuario.utilidades.generadorPDF

object AuxiliaresGeneracionPDF {

    fun traerAnchoM(tamanio: Float = 20f) : Float {
        val anchoM = 13f
        val tamanioReferencia = 20f
        return (tamanio * anchoM)/tamanioReferencia
    }
}