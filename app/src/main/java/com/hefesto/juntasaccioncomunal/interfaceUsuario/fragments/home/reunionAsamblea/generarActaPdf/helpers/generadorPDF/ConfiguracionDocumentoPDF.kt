package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.generarActaPdf.helpers.generadorPDF

import android.graphics.pdf.PdfDocument

class ConfiguracionDocumentoPDF {
    //region variables
    private val altoPagina = 842
    private val anchoPagina = 395
    private val margenAlto = 50f
    private val margenBajo = 50f
    private val margenIzquierda = 50f
    private val margenDerecha = 50f
    //region

    fun traerAltoPagina() = altoPagina
    fun traerAnchoPagina() = anchoPagina
    fun traerMargenAlto() = margenAlto
    fun traerMargenAbajo() = margenBajo
    fun traerMargenIzquierda() = margenIzquierda
    fun traerMargenDerecha() = margenDerecha

    fun traerPageInfo(numeroPagina : Int) : PdfDocument.PageInfo
    = PdfDocument.PageInfo.Builder(anchoPagina, altoPagina, numeroPagina).create()
}