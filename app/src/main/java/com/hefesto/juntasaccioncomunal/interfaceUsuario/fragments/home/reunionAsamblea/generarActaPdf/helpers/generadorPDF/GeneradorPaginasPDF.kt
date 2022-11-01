package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.generarActaPdf.helpers.generadorPDF

import android.graphics.Typeface
import android.graphics.pdf.PdfDocument
import android.text.TextPaint
import kotlinx.coroutines.delay

class GeneradorPaginasPDF {

    //region variables
    private lateinit var configuracionDocumentoPDF: ConfiguracionDocumentoPDF
    private var listaItems = emptyList<DetalleItemPdf>().toMutableList()
    private lateinit var pdfDocument: PdfDocument
    private var posicionYPagina = 0f
    private var paginaActual : PdfDocument.Page? = null
    private var contadorPagina = 1
    //endregion

    //region metodos publicos

    fun conConfiguracionDocumentoPDF(configuracionDocumentoPDF: ConfiguracionDocumentoPDF) : GeneradorPaginasPDF {
        this.configuracionDocumentoPDF = configuracionDocumentoPDF
        return this
    }

    fun conListaItems(listaItems : MutableList<DetalleItemPdf>) : GeneradorPaginasPDF {
        this.listaItems = listaItems
        return this
    }

    fun conPdfDocument(pdfDocument: PdfDocument) : GeneradorPaginasPDF {
        this.pdfDocument = pdfDocument
        return this
    }

    suspend fun llenarPDF() {
        for (item in listaItems) {
            generarLineas(detalle = item)
            delay(200)
        }
        pdfDocument.finishPage(paginaActual!!)
    }

    //endregion

    //region metodos privados


    //region generar lineas
    private suspend fun generarLineas(detalle: DetalleItemPdf) {
        for (item in generarSplitString(detalle = detalle)) {
            generarLinea(texto = item, detalle= detalle)
        }
    }

    private fun generarSplitString(detalle: DetalleItemPdf) : List<String> {
        val lista = emptyList<String>().toMutableList()
        val ancho = configuracionDocumentoPDF.traerMargenIzquierda() + (detalle.detalle.length * (detalle.tamanioLetra/2))
        if (ancho < configuracionDocumentoPDF.traerAnchoPagina() - configuracionDocumentoPDF.traerMargenDerecha()) {
            lista.add(detalle.detalle)
            return lista
        }

        var itemLista = ""
        for (caracter in detalle.detalle) {
            itemLista += caracter
            val anchoFor = configuracionDocumentoPDF.traerMargenIzquierda() + (itemLista.length * (detalle.tamanioLetra/2))

            if (anchoFor < configuracionDocumentoPDF.traerAnchoPagina() - configuracionDocumentoPDF.traerMargenDerecha()) continue
            itemLista.toMutableList().removeLast()
            lista.add(itemLista)
            itemLista = caracter.toString()

        }
        return lista
    }


    private suspend fun generarPagina() {
        if (paginaActual == null) {
            paginaActual = pdfDocument.startPage(configuracionDocumentoPDF.traerPageInfo(numeroPagina = contadorPagina))
            posicionYPagina = configuracionDocumentoPDF.traerMargenAlto()
            return
        }

        val margenInferior = configuracionDocumentoPDF.traerAltoPagina() - configuracionDocumentoPDF.traerMargenAbajo()
        if (posicionYPagina < margenInferior ) return

        pdfDocument.finishPage(paginaActual!!)
        contadorPagina++
        paginaActual = pdfDocument.startPage(configuracionDocumentoPDF.traerPageInfo(numeroPagina = contadorPagina))
        posicionYPagina = configuracionDocumentoPDF.traerMargenAlto()
    }

    private suspend fun generarLinea(texto : String, detalle: DetalleItemPdf) {
        generarPagina()
        paginaActual!!.canvas.drawText(
            texto,
            configuracionDocumentoPDF.traerMargenIzquierda(),
            posicionYPagina,
            when(detalle.tipo) {
                TipoAAplicar.NORMAL -> formatoDetalle(textSize = detalle.tamanioLetra)
                TipoAAplicar.RESALTADO -> formatoResalto(textSize = detalle.tamanioLetra)
            }
        )
        posicionYPagina += detalle.tamanioLetra
        generarPagina()
    }

    //endregion

    //region formatos
    private fun formatoResalto(textSize: Float = 20f) : TextPaint {
        val titulo = TextPaint()
        titulo.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        titulo.textSize = textSize
        return titulo
    }

    private fun formatoDetalle(textSize: Float = 20f) : TextPaint {
        val titulo = TextPaint()
        titulo.typeface = Typeface.defaultFromStyle(Typeface.NORMAL)
        titulo.textSize = textSize
        return titulo
    }
    //endregion

    //endregion

}