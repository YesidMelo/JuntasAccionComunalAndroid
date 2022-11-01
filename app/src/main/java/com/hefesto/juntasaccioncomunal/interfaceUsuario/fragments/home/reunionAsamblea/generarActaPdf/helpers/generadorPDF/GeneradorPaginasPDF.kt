package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.generarActaPdf.helpers.generadorPDF

import android.graphics.Typeface
import android.graphics.pdf.PdfDocument
import android.text.TextPaint
import com.hefesto.juntasaccioncomunal.interfaceUsuario.utilidades.generadorPDF.AuxiliaresGeneracionPDF

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

    fun llenarPDF() {
        for (item in listaItems) {
            generarLineas(detalle = item)
        }
        pdfDocument.finishPage(paginaActual!!)
    }

    //endregion

    //region metodos privados


    //region generar lineas
    private fun generarLineas(detalle: DetalleItemPdf) {
        generarPagina()
        val pagina = paginaActual?: return

        for (item in generarSplitString(detalle = detalle)) {
            generarLinea(texto = item, detalle= detalle, pagina = pagina)
        }
    }

    private fun generarSplitString(detalle: DetalleItemPdf) : List<String> {
        val lista = emptyList<String>().toMutableList()
        var itemLista = ""
        for (caracter in detalle.detalle) {
            itemLista += caracter
            val ancho = configuracionDocumentoPDF.traerMargenIzquierda() + (itemLista.length * (detalle.tamanioLetra/2))

            if (ancho < configuracionDocumentoPDF.traerAnchoPagina() - configuracionDocumentoPDF.traerMargenDerecha()) continue
            itemLista.toMutableList().removeLast()
            lista.add(itemLista)
            itemLista = caracter.toString()

        }
        return lista
    }


    private fun generarPagina() {
        if (paginaActual == null) {
            paginaActual = pdfDocument.startPage(configuracionDocumentoPDF.traerPageInfo(numeroPagina = contadorPagina))
            posicionYPagina = configuracionDocumentoPDF.traerMargenAlto()
            return
        }

        val margenInferior = configuracionDocumentoPDF.traerAltoPagina() - configuracionDocumentoPDF.traerMargenAbajo()
        if (posicionYPagina < margenInferior ) return

        pdfDocument.finishPage(paginaActual!!)
        contadorPagina++
        pdfDocument.startPage(configuracionDocumentoPDF.traerPageInfo(numeroPagina = contadorPagina))
        posicionYPagina = configuracionDocumentoPDF.traerMargenAlto()
    }

    private fun generarLinea(texto : String, detalle: DetalleItemPdf, pagina: PdfDocument.Page) {
        pagina.canvas.drawText(
            texto,
            configuracionDocumentoPDF.traerMargenIzquierda(),
            posicionYPagina,
            when(detalle.tipo) {
                TipoAAplicar.NORMAL -> formatoDetalle(textSize = detalle.tamanioLetra)
                TipoAAplicar.RESALTADO -> formatoResalto(textSize = detalle.tamanioLetra)
            }
        )
        posicionYPagina += detalle.tamanioLetra
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