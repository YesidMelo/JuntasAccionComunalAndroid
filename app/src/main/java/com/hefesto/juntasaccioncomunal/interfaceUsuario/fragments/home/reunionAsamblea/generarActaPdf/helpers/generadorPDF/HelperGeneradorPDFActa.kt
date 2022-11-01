package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.generarActaPdf.helpers.generadorPDF

import android.graphics.pdf.PdfDocument
import android.os.Environment
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.actasParaPDF.ReunionParaGenerarPDFModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream

class HelperGeneradorPDFActa {

    //region variables
    private lateinit var reunionParaGenerarPDFModel: ReunionParaGenerarPDFModel
    private lateinit var mostrarLoading : ()->Unit
    private lateinit var ocultarLoading : ()->Unit
    private val creoPDFLiveData = MutableLiveData<Boolean>()
    private val configuracionDocumentoPDF = ConfiguracionDocumentoPDF()
    private val generadorPaginasPDF = GeneradorPaginasPDF()
    //endregion

    fun conReunionParaGenerarPDFModel(reunionParaGenerarPDFModel: ReunionParaGenerarPDFModel) : HelperGeneradorPDFActa {
        this.reunionParaGenerarPDFModel = reunionParaGenerarPDFModel
        return this
    }

    fun conMostrarLoading(mostrarLoading : ()->Unit) : HelperGeneradorPDFActa {
        this.mostrarLoading = mostrarLoading
        return this
    }

    fun conOcultarLoading(ocultarLoading : ()->Unit) : HelperGeneradorPDFActa {
        this.ocultarLoading = ocultarLoading
        return this
    }

    fun traerCreoPDFLiveData() = creoPDFLiveData

    fun crearPDF() {
        GlobalScope.launch {
            creoPDFLiveData.postValue(false)
            mostrarLoading.invoke()

            val pdfDocument = PdfDocument()

            generadorPaginasPDF
                .conConfiguracionDocumentoPDF(configuracionDocumentoPDF = configuracionDocumentoPDF)
                .conListaItems(listaItems = generarListaItems())
                .conPdfDocument(pdfDocument = pdfDocument)
                .llenarPDF()

            generarArchivo(pdfDocument = pdfDocument)

            pdfDocument.close()
            ocultarLoading.invoke()
            creoPDFLiveData.postValue(true)
        }
    }

    //region metodos privados
    private fun generarListaItems() : MutableList<DetalleItemPdf> {
        val lista = emptyList<DetalleItemPdf>().toMutableList()
        val detalle = DetalleItemPdf().apply {
            this.tamanioLetra = 20f
            this.detalle= "ñiashflalkjfgajgfjshfdlakshdfñkjashfkahsñdkfhasñkdfasñovhahvkjahñfkhasñkdfhañkshdfkajsdhfñkasdfñiagsdivblkj" +
                    "vblajkbvljahbjasgljahsgfdlaigfiaiyfalj,hdbfakjhfljhkasdfjhsjfkhgaskjdfgaksjhfdgkjhasgf"+
                    "vblajkbvljahbjasgljahsgfdlaigfiaiyfalj,hdbfakjhfljhkasdfjhsjfkhgaskjdfgaksjhfdgkjhasgf"+
                    "vblajkbvljahbjasgljahsgfdlaigfiaiyfalj,hdbfakjhfljhkasdfjhsjfkhgaskjdfgaksjhfdgkjhasgf"+
                    "vblajkbvljahbjasgljahsgfdlaigfiaiyfalj,hdbfakjhfljhkasdfjhsjfkhgaskjdfgaksjhfdgkjhasgf" +
                    "vblajkbvljahbjasgljahsgfdlaigfiaiyfalj,hdbfakjhfljhkasdfjhsjfkhgaskjdfgaksjhfdgkjhasgf" +
                    "vblajkbvljahbjasgljahsgfdlaigfiaiyfalj,hdbfakjhfljhkasdfjhsjfkhgaskjdfgaksjhfdgkjhasgf" +
                    "vblajkbvljahbjasgljahsgfdlaigfiaiyfalj,hdbfakjhfljhkasdfjhsjfkhgaskjdfgaksjhfdgkjhasgf" +
                    "vblajkbvljahbjasgljahsgfdlaigfiaiyfalj,hdbfakjhfljhkasdfjhsjfkhgaskjdfgaksjhfdgkjhasgf" +
                    "vblajkbvljahbjasgljahsgfdlaigfiaiyfalj,hdbfakjhfljhkasdfjhsjfkhgaskjdfgaksjhfdgkjhasgf" +
                    "vblajkbvljahbjasgljahsgfdlaigfiaiyfalj,hdbfakjhfljhkasdfjhsjfkhgaskjdfgaksjhfdgkjhasgf" +
                    "vblajkbvljahbjasgljahsgfdlaigfiaiyfalj,hdbfakjhfljhkasdfjhsjfkhgaskjdfgaksjhfdgkjhasgf" +
                    "vblajkbvljahbjasgljahsgfdlaigfiaiyfalj,hdbfakjhfljhkasdfjhsjfkhgaskjdfgaksjhfdgkjhasgf" +
                    "vblajkbvljahbjasgljahsgfdlaigfiaiyfalj,hdbfakjhfljhkasdfjhsjfkhgaskjdfgaksjhfdgkjhasgf" +
                    "vblajkbvljahbjasgljahsgfdlaigfiaiyfalj,hdbfakjhfljhkasdfjhsjfkhgaskjdfgaksjhfdgkjhasgf" +
                    "vblajkbvljahbjasgljahsgfdlaigfiaiyfalj,hdbfakjhfljhkasdfjhsjfkhgaskjdfgaksjhfdgkjhasgf" +
                    "vblajkbvljahbjasgljahsgfdlaigfiaiyfalj,hdbfakjhfljhkasdfjhsjfkhgaskjdfgaksjhfdgkjhasgf" +
                    "vblajkbvljahbjasgljahsgfdlaigfiaiyfalj,hdbfakjhfljhkasdfjhsjfkhgaskjdfgaksjhfdgkjhasgf" +
                    "vblajkbvljahbjasgljahsgfdlaigfiaiyfalj,hdbfakjhfljhkasdfjhsjfkhgaskjdfgaksjhfdgkjhasgf" +
                    "vblajkbvljahbjasgljahsgfdlaigfiaiyfalj,hdbfakjhfljhkasdfjhsjfkhgaskjdfgaksjhfdgkjhasgf" +
                    "vblajkbvljahbjasgljahsgfdlaigfiaiyfalj,hdbfakjhfljhkasdfjhsjfkhgaskjdfgaksjhfdgkjhasgf" +
                    "vblajkbvljahbjasgljahsgfdlaigfiaiyfalj,hdbfakjhfljhkasdfjhsjfkhgaskjdfgaksjhfdgkjhasgf" +
                    "vblajkbvljahbjasgljahsgfdlaigfiaiyfalj,hdbfakjhfljhkasdfjhsjfkhgaskjdfgaksjhfdgkjhasgf" +
                    "vblajkbvljahbjasgljahsgfdlaigfiaiyfalj,hdbfakjhfljhkasdfjhsjfkhgaskjdfgaksjhfdgkjhasgf"
            this.tipo = TipoAAplicar.NORMAL
        }
        lista.add(detalle)
        return lista
    }

    //region generar documento pdf
    fun generarArchivo(pdfDocument: PdfDocument) {
        val file = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "Acta.pdf")
        try {
            pdfDocument.writeTo(FileOutputStream(file))
        }catch (e :Exception) {
            Log.e("Err", "surgio un problema", e)
        }
    }
    //endregion
    //endregion
}