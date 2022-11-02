package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.generarActaPdf.helpers.generadorPDF

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.pdf.PdfDocument
import android.text.TextPaint
import android.util.Base64
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.delay

class GeneradorPaginasPDF {

    //region variables
    private lateinit var configuracionDocumentoPDF: ConfiguracionDocumentoPDF
    private var listaItems = emptyList<DetalleItemPdf>().toMutableList()
    private lateinit var pdfDocument: PdfDocument
    private var posicionYPagina = 0f
    private var paginaActual : PdfDocument.Page? = null
    private var contadorPagina = 1
    private var marcaAgua: String? = null
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

    fun conMarcaDeAgua(marcaAguaBase64: String?) : GeneradorPaginasPDF {
        this.marcaAgua = marcaAguaBase64
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

    //region generar pagina
    private fun generarPagina() {
        if (paginaActual == null) {
            paginaActual = pdfDocument.startPage(configuracionDocumentoPDF.traerPageInfo(numeroPagina = contadorPagina))
            posicionYPagina = configuracionDocumentoPDF.traerMargenAlto()
            ponerMarcaAgua(pagina = paginaActual!!)
            return
        }

        val margenInferior = configuracionDocumentoPDF.traerAltoPagina() - configuracionDocumentoPDF.traerMargenAbajo()
        if (posicionYPagina < margenInferior ) return

        pdfDocument.finishPage(paginaActual!!)
        contadorPagina++
        paginaActual = pdfDocument.startPage(configuracionDocumentoPDF.traerPageInfo(numeroPagina = contadorPagina))
        posicionYPagina = configuracionDocumentoPDF.traerMargenAlto()
        ponerMarcaAgua(pagina = paginaActual!!)
    }

    private fun ponerMarcaAgua(pagina: PdfDocument.Page) {
        val logo = marcaAgua?:return
        if (logo.isEmpty()) return
        val detalleImagen = Base64.decode(logo, Base64.DEFAULT)
        val anchoImagenEscalada = 350
        val altoImagenEscalada = 500
        val bitmap = BitmapFactory.decodeByteArray(detalleImagen, 0, detalleImagen.size)
        val bitmapEscalada = Bitmap.createScaledBitmap(bitmap, anchoImagenEscalada, altoImagenEscalada, false)

        val posicionX = (configuracionDocumentoPDF.traerAnchoPagina().toFloat()/2) - (anchoImagenEscalada.toFloat()/2)
        val posicionY = (configuracionDocumentoPDF.traerAltoPagina().toFloat()/2) - (altoImagenEscalada.toFloat()/2)
        pagina.canvas.drawBitmap(bitmapEscalada,posicionX,posicionY, null)
    }

    //endregion

    //region generar lineas
    private suspend fun generarLineas(detalle: DetalleItemPdf) {
        for (item in generarSplitString(detalle = detalle)) {
            generarLinea(texto = item, detalle= detalle)
        }
    }

    //region generar splits en string
    private fun generarSplitString(detalle: DetalleItemPdf) : List<String> {
        val lista = emptyList<String>().toMutableList()

        lista.addAll(
            if (!detalle.detalle.contains("\n"))
                generarSplitConDetalleSinSaltosDeLinea(detalle = detalle)
            else
                generarSplitConSaltosDeLinea(detalle = detalle)
        )
        return lista
    }

    private fun generarSplitConSaltosDeLinea(detalle: DetalleItemPdf) : List<String> {
        val lista = emptyList<String>().toMutableList()
        val detalleSplit = detalle.detalle.split("\n")
        for (item in detalleSplit){
            lista.addAll(generarSplitConDetalleSinSaltosDeLinea(detalle = detalle, stringARevisar = item))
        }
        return lista
    }

    private fun generarSplitConDetalleSinSaltosDeLinea(detalle: DetalleItemPdf, stringARevisar: String?= null): List<String> {
        val lista = emptyList<String>().toMutableList()
        val string = stringARevisar?:detalle.detalle
        val ancho = configuracionDocumentoPDF.traerMargenIzquierda() + (string.length * (detalle.tamanioLetra/2))
        if (ancho < configuracionDocumentoPDF.traerAnchoPagina() - configuracionDocumentoPDF.traerMargenDerecha()) {
            lista.add(string)
            return lista
        }

        var itemLista = ""
        for (caracter in string) {
            itemLista += caracter
            val anchoFor = configuracionDocumentoPDF.traerMargenIzquierda() + (itemLista.length * (detalle.tamanioLetra/2))

            if (anchoFor < configuracionDocumentoPDF.traerAnchoPagina() - configuracionDocumentoPDF.traerMargenDerecha()) continue
            itemLista.toMutableList().removeLast()
            lista.add(itemLista)
            itemLista = caracter.toString()

        }
        return lista
    }
    //endregion

    private fun generarLinea(texto : String, detalle: DetalleItemPdf) {
        generarPagina()
        ponerGionFirma(texto = texto, detalle = detalle)
        ponerTextoNormal(texto = texto, detalle= detalle)
        ponerTextoEncabezado(texto = texto, detalle = detalle)
        generarPagina()
    }

    private fun ponerGionFirma(texto : String, detalle: DetalleItemPdf) {
        if (detalle.configuracionDetalleItem != ConfiguracionDetalleItem.FIRMA) return
        if (texto.isEmpty()) return
        val puntoInicial = Pair(first = configuracionDocumentoPDF.traerMargenIzquierda(), second = posicionYPagina)
        val puntoFinal = Pair(first = configuracionDocumentoPDF.traerMargenIzquierda() + 250f, second = posicionYPagina)
        paginaActual!!.canvas.drawLine(puntoInicial.first, puntoInicial.second, puntoFinal.first, puntoFinal.second, Paint())
        posicionYPagina += detalle.tamanioLetra
    }

    private fun ponerTextoNormal(texto : String, detalle: DetalleItemPdf) {
        if(detalle.configuracionDetalleItem == ConfiguracionDetalleItem.ENCABEZADO) return

        val posicionX = if (!texto.contains("\t")) configuracionDocumentoPDF.traerMargenIzquierda() else configuracionDocumentoPDF.traerMargenIzquierda()+ configuracionDocumentoPDF.traerAnchoTab()
        paginaActual!!.canvas.drawText(
            texto,
            posicionX,
            posicionYPagina,
            when(detalle.tipo) {
                TipoAAplicar.NORMAL -> formatoDetalle(textSize = detalle.tamanioLetra)
                TipoAAplicar.RESALTADO -> formatoResalto(textSize = detalle.tamanioLetra)
            }
        )
        posicionYPagina += detalle.tamanioLetra
    }

    private fun ponerTextoEncabezado(texto : String, detalle: DetalleItemPdf) {
        if(detalle.configuracionDetalleItem != ConfiguracionDetalleItem.ENCABEZADO) return
        val anchoTabs = texto.filter { return@filter it.toString() == "\t" }.count()
        val posicionX = configuracionDocumentoPDF.traerMargenIzquierda() + anchoTabs
        paginaActual!!.canvas.drawText(
            texto,
            posicionX,
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