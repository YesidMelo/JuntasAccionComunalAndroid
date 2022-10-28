package com.hefesto.juntasaccioncomunal.interfaceUsuario.utilidades.generadorPDF

interface ConfiguracionPagina {

    enum class Orientacion { HORIZONTAL, VERTICAL }

    enum class TamanioPagina constructor (
        private val ancho: Int,
        private val alto: Int,
    ) {
        CARTA(ancho = 595, alto = 842)
        ;
        fun traerAncho() = ancho
        fun traerAlto() = alto
    }

    fun traerMarcaAgua(): String?
    fun traerMargenDerecha() : Int
    fun traerMargenInferior() : Int
    fun traerMargenIzquierda() : Int
    fun traerMargenSuperior() : Int
    fun traerNumeroPaginas() : Int
    fun traerTamanioPagina(): TamanioPagina = TamanioPagina.CARTA
    fun traerOrientacion() : Orientacion

}