package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.generarConvocatoriaReunionPdf

import android.graphics.Typeface
import android.graphics.pdf.PdfDocument
import android.os.Environment
import android.text.TextPaint
import android.util.Log
import com.hefesto.juntasaccioncomunal.interfaceUsuario.utilidades.generadorPDF.AuxiliaresGeneracionPDF
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.reunionParaConvocatoriaPDF.ReunionParaGenerarConvocatoriaPDFModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.FormatosFecha
import com.hefesto.juntasaccioncomunal.logica.utilidades.extenciones.convertirAFormato
import java.io.File
import java.io.FileOutputStream

class HelperGenerarConvocatoriaPDF {

    //region variables
    private lateinit var convocatoria: ReunionParaGenerarConvocatoriaPDFModel
    //endregion

    fun conConvocatoria(convocatoria: ReunionParaGenerarConvocatoriaPDFModel) : HelperGenerarConvocatoriaPDF {
        this.convocatoria = convocatoria
        return this
    }

    fun generarPDF() {
        val pdfDocument = PdfDocument()
        crearPagina(pdfDocument = pdfDocument)
        generarArchivo(pdfDocument = pdfDocument)
        pdfDocument.close()
    }

    //region metodos privados

    //region configuracion pagina
    private fun configurarPaginas(numeroPagina : Int) : PdfDocument.PageInfo {
        return  PdfDocument.PageInfo.Builder(842, 595,  numeroPagina).create()
    }
    //endregion

    //region crear pagina
    private fun crearPagina(pdfDocument: PdfDocument) {
        val pagina1 : PdfDocument.Page = pdfDocument.startPage(configurarPaginas(numeroPagina = 1))
        var posicionY = 70f
        posicionY = encabezado(pagina1 = pagina1, posicionY = posicionY)
        posicionY = ponerAsunto(pagina1 = pagina1, posicionY = posicionY)
        posicionY = ponerSitioFechaHora(pagina1 = pagina1, posicionY = posicionY)
        pdfDocument.finishPage(pagina1)
    }

    //region encabezado
    private fun encabezado(pagina1: PdfDocument.Page, posicionY : Float) : Float {
        val tamanioLetra = 20f
        var posicionYSalida = posicionY
        pagina1.canvas.drawText(
            "La junta de accion comunal convocan a la comunidad a la asamblea :",
            40f,
            posicionYSalida,
            formatoDetalle(textSize = tamanioLetra)
        )
        return posicionYSalida + tamanioLetra
    }
    //endregion

    //region asunto
    private fun ponerAsunto(pagina1: PdfDocument.Page, posicionY : Float) : Float {
        val tamanio = 40f
        val encabezado = "Asunto:"
        pagina1.canvas.drawText(encabezado, 40f, posicionY, formatoResalto(textSize = tamanio))
        pagina1.canvas.drawText(
            " ${convocatoria.asuntoReunion?:""} ",
            encabezado.length * AuxiliaresGeneracionPDF.traerAnchoM(tamanio = tamanio),
            posicionY,
            formatoDetalle(textSize = tamanio)
        )
        return posicionY + 50
    }


    //endregion

    //region Sitio, fecha Hora de la reunion
    private fun ponerSitioFechaHora(pagina1: PdfDocument.Page, posicionY : Float) : Float {
        val tamanioLetra = 20f
        var posicionYSalida = posicionY

        fun llenarDetalle(encabezado : String, detalle: String) {
            pagina1.canvas.drawText(encabezado, 40f, posicionYSalida, formatoResalto(textSize = tamanioLetra))
            val posicionXSitio = encabezado.length * AuxiliaresGeneracionPDF.traerAnchoM(tamanio = tamanioLetra)
            pagina1.canvas.drawText(detalle,posicionXSitio, posicionYSalida,formatoDetalle(textSize = tamanioLetra))
            posicionYSalida += tamanioLetra
        }

        llenarDetalle(encabezado = "Sitio:", detalle = " ${convocatoria.sitio?:""} ")
        llenarDetalle(encabezado = "Fecha:", detalle = " ${convocatoria.fechaYHoraProgramacionReunion?.convertirAFormato(formato = FormatosFecha.ANIO_MES_DIA_GIONES)?:""} ")
        llenarDetalle(encabezado = "Hora:", detalle = " ${convocatoria.fechaYHoraProgramacionReunion?.convertirAFormato(formato = FormatosFecha.HORA_MINUTO_12H)?:""} ")

        return posicionYSalida
    }
    //endregion

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

    //region generar documento pdf
    fun generarArchivo(pdfDocument: PdfDocument) {
        val file = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "Convocatoria.pdf")
        try {
            pdfDocument.writeTo(FileOutputStream(file))
            Log.e("Err", "Se ha generado el documento")
        }catch (e :Exception) {
            Log.e("Err", "surgio un problema", e)
        }
    }
    //endregion

    //endregion

}