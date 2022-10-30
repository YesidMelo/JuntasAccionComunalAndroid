package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.generarConvocatoriaReunionPdf

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.pdf.PdfDocument
import android.os.Environment
import android.text.TextPaint
import android.util.Base64
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.hefesto.juntasaccioncomunal.R
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.generarActaPdf.helpers.HelperGeneradorHtmlParaGenerarPDF
import com.hefesto.juntasaccioncomunal.interfaceUsuario.utilidades.generadorPDF.AuxiliaresGeneracionPDF
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.reunionParaConvocatoriaPDF.ReunionParaGenerarConvocatoriaPDFModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.FormatosFecha
import com.hefesto.juntasaccioncomunal.logica.utilidades.extenciones.convertirAFormato
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream

class HelperGenerarConvocatoriaPDF {

    //region variables
    private lateinit var convocatoria: ReunionParaGenerarConvocatoriaPDFModel
    private lateinit var mostrarLoading: ()->Unit
    private lateinit var ocultarLoading: ()->Unit
    private lateinit var context: Context
    private val margenIzquierda = 40f
    private val seGeneroPDF = MutableLiveData<Boolean>()
    //endregion

    fun conMostrarLoading(mostrarLoading: ()->Unit) : HelperGenerarConvocatoriaPDF {
        this.mostrarLoading = mostrarLoading
        return this
    }

    fun conOcultarLoading(ocultarLoading: ()->Unit) : HelperGenerarConvocatoriaPDF {
        this.ocultarLoading = ocultarLoading
        return this
    }

    fun conContext(context: Context) : HelperGenerarConvocatoriaPDF{
        this.context = context
        return this
    }

    fun conConvocatoria(convocatoria: ReunionParaGenerarConvocatoriaPDFModel) : HelperGenerarConvocatoriaPDF {
        this.convocatoria = convocatoria
        return this
    }

    fun traerSeGeneroPDFLiveData() = seGeneroPDF

    fun generarPDF() {
        GlobalScope.launch {
            mostrarLoading.invoke()
            seGeneroPDF.postValue(false)
            val pdfDocument = PdfDocument()
            crearPagina(pdfDocument = pdfDocument)
            generarArchivo(pdfDocument = pdfDocument)
            pdfDocument.close()
            ocultarLoading.invoke()
            seGeneroPDF.postValue(true)
        }
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
        ponerMarcaAgua(pagina1 = pagina1, posicionY = posicionY)
        posicionY = ponerAsunto(pagina1 = pagina1, posicionY = posicionY)
        posicionY = ponerSitioFechaHora(pagina1 = pagina1, posicionY = posicionY)
        posicionY = ordenDelDiaYConvocantes(pagina1 = pagina1, posicionY = posicionY + 50)
        posicionY = pie(pagina1 = pagina1, posicionY = posicionY + 50)
        pdfDocument.finishPage(pagina1)
    }

    //region asunto

    private fun ponerMarcaAgua(pagina1: PdfDocument.Page, posicionY : Float) {
        val logo = convocatoria.logoJAC
        val detalleImagen = Base64.decode(logo, Base64.DEFAULT)
        val bitmap = BitmapFactory.decodeByteArray(detalleImagen, 0, detalleImagen.size)
        val bitmapEscalada = Bitmap.createScaledBitmap(bitmap, 350, 500, false)
        pagina1.canvas.drawBitmap(bitmapEscalada,250f,50f, null)
    }

    private fun ponerAsunto(pagina1: PdfDocument.Page, posicionY : Float) : Float {
        val tamanioEncabezado = 25f
        val tamanioDetalleEncabezado = 30f
        val encabezado = "${context.getString(R.string.asunto)}:"
        pagina1.canvas.drawText(encabezado , margenIzquierda, posicionY, formatoResalto(textSize = tamanioEncabezado))
        pagina1.canvas.drawText("${convocatoria.asuntoReunion?:""} " , margenIzquierda + 100f, posicionY, formatoDetalle(textSize = tamanioEncabezado))
        return posicionY + tamanioEncabezado + tamanioDetalleEncabezado
    }


    //endregion

    //region Sitio, fecha Hora de la reunion
    private fun ponerSitioFechaHora(pagina1: PdfDocument.Page, posicionY : Float) : Float {
        val tamanioLetra = 20f
        val posicionYSalida = posicionY

        ponerSitio(pagina1 = pagina1, posicionY = posicionY, tamanioLetra = tamanioLetra)
        ponerFecha(pagina1 = pagina1, posicionY = posicionY, tamanioLetra = tamanioLetra)
        ponerHora(pagina1 = pagina1, posicionY = posicionY, tamanioLetra = tamanioLetra)
        return posicionYSalida + tamanioLetra * 2
    }

    private fun ponerSitio(pagina1: PdfDocument.Page, posicionY : Float, tamanioLetra : Float) {
        val posicionPrincipal = margenIzquierda
        val encabezado = "${context.getString(R.string.sitio)}:"
        pagina1.canvas.drawText(encabezado, posicionPrincipal, posicionY, formatoResalto(textSize = tamanioLetra))
        val posicionX = 11 + posicionPrincipal + (encabezado.length * AuxiliaresGeneracionPDF.traerAnchoM(tamanio = tamanioLetra))/2
        pagina1.canvas.drawText(" ${convocatoria.sitio?:""} ", posicionX, posicionY, formatoDetalle(textSize = tamanioLetra))
    }

    private fun ponerFecha(pagina1: PdfDocument.Page, posicionY : Float, tamanioLetra : Float) {
        val posicionPrincipal = margenIzquierda + 160f
        val encabezado = "${context.getString(R.string.fecha)}:"
        pagina1.canvas.drawText(encabezado, posicionPrincipal, posicionY, formatoResalto(textSize = tamanioLetra))
        val posicionX = 20 + posicionPrincipal + (encabezado.length * AuxiliaresGeneracionPDF.traerAnchoM(tamanio = tamanioLetra))/2
        val fecha = convocatoria.fechaYHoraProgramacionReunion?.convertirAFormato(formato = FormatosFecha.ANIO_MES_DIA_GIONES)
        pagina1.canvas.drawText(" ${fecha?:""} ", posicionX, posicionY, formatoDetalle(textSize = tamanioLetra))
    }

    private fun ponerHora(pagina1: PdfDocument.Page, posicionY : Float, tamanioLetra : Float) {
        val posicionPrincipal = margenIzquierda + 360f
        val encabezado = "${context.getString(R.string.hora_reunion)}:"
        pagina1.canvas.drawText(encabezado, posicionPrincipal, posicionY, formatoResalto(textSize = tamanioLetra))
        val posicionX = 40 + posicionPrincipal + (encabezado.length * AuxiliaresGeneracionPDF.traerAnchoM(tamanio = tamanioLetra))/2
        val hora = convocatoria.fechaYHoraProgramacionReunion?.convertirAFormato(formato = FormatosFecha.HORA_MINUTO_12H)
        pagina1.canvas.drawText(" ${hora?:""} ", posicionX, posicionY, formatoDetalle(textSize = tamanioLetra))
    }
    //endregion

    //region orden del dia y Convocantes

    private fun ordenDelDiaYConvocantes(pagina1: PdfDocument.Page, posicionY : Float) : Float {
        val posicicionYOrdenDia = ordenDelDia(pagina1 = pagina1, posicionY = posicionY)
        val posicionYConvocantes = convocantes(pagina1 = pagina1, posicionY = posicionY)
        return if (posicicionYOrdenDia>posicionYConvocantes) posicicionYOrdenDia else posicionYConvocantes
    }

    private fun ordenDelDia(pagina1: PdfDocument.Page, posicionY : Float) : Float{
        val tamanioLetra = 20f
        val margenIzquierda = margenIzquierda
        var posicionYFinal = posicionY + tamanioLetra

        pagina1.canvas.drawText("${context.getString(R.string.orden_del_dia)}:",margenIzquierda,posicionY,formatoResalto(tamanioLetra))
        posicionYFinal += tamanioLetra

        fun crearPuntos(punto: String, posicion: Int) {
            pagina1.canvas.drawText("${posicion+1}. $punto",margenIzquierda * 2,posicionYFinal,formatoDetalle(tamanioLetra))
            posicionYFinal += tamanioLetra
        }

        for (item in 0 until convocatoria.listaPuntos!!.size) {
            crearPuntos(punto = convocatoria.listaPuntos!![item].tituloPunto!!, posicion = item)
        }

        return posicionYFinal
    }

    private fun convocantes(pagina1: PdfDocument.Page, posicionY : Float) : Float {
        val tamanioLetra = 20f
        var posicionFinalY = posicionY
        val posicionX = margenIzquierda + 460f
        val separacionX = 40f

        pagina1.canvas.drawText("${context.getString(R.string.convoca)}: ", posicionX, posicionFinalY, formatoResalto(textSize = tamanioLetra))
        posicionFinalY += tamanioLetra * 2
        val numeroConvocantes = if (convocatoria.listaConvocantes!!.size < 6 ) convocatoria.listaConvocantes!!.size else 6

        fun detalleConvocante(nombres: String, posicion : Int) {
            pagina1.canvas.drawText("${posicion + 1}. $nombres", posicionX + separacionX, posicionFinalY, formatoDetalle(textSize = tamanioLetra))
            posicionFinalY += tamanioLetra
        }

        for (item in 0 until numeroConvocantes) {
            detalleConvocante(nombres = "${convocatoria.listaConvocantes!![item].nombres} ${convocatoria.listaConvocantes!![item].nombres}", posicion = item)
        }

        return posicionY
    }
    //endregion

    //region pie
    private fun pie(pagina1: PdfDocument.Page, posicionY : Float) : Float{
        var posicionYFinal = posicionY
        posicionYFinal = detallePie(pagina1 = pagina1, posicionY = posicionYFinal)
        posicionYFinal = firmas(pagina1 = pagina1, posicionY = posicionYFinal)
        return posicionYFinal
    }

    private fun detallePie(pagina1: PdfDocument.Page, posicionY : Float) : Float {
        val tamanioLetra = 15f
        var posicionYFinal = posicionY
        var posicionX = margenIzquierda
        val stringArray = context.resources.getStringArray(R.array.la_junta_accion_comunal_convoca)

        stringArray.forEach {
            pagina1.canvas.drawText(it, posicionX, posicionYFinal, formatoDetalle(textSize = tamanioLetra))
            posicionYFinal += tamanioLetra + 2
        }

        return posicionYFinal
    }

    //region  firma
    private fun firmas(pagina1: PdfDocument.Page, posicionY : Float) :Float {
        var posicionYFinal = posicionY
        posicionYFinal = lineasFirma(pagina1 = pagina1, posicionYFinal)
        detalleFirma(pagina1 = pagina1, posicionYFinal)
        detalleFechaPublicacion(pagina1 = pagina1, posicionYFinal)
        return posicionYFinal
    }

    private fun lineasFirma(pagina1: PdfDocument.Page, posicionY : Float) : Float {
        val posicionYFinal = posicionY
        val desplazamientoEnY = 50
        val anchoLinea = 200f
        val separacion = 50f
        val puntoInicialLinea1 = Pair(first = margenIzquierda, second = posicionYFinal + desplazamientoEnY)
        val puntoFinalLinea1 = Pair(first = margenIzquierda + anchoLinea, second = posicionYFinal + desplazamientoEnY)
        val puntoInicialLinea2 = Pair(first = separacion + margenIzquierda + anchoLinea, second = posicionYFinal + desplazamientoEnY)
        val puntoFinalLinea2 = Pair(first = separacion + margenIzquierda +(anchoLinea*2) , second = posicionYFinal + desplazamientoEnY)
        val paint = Paint()
        pagina1.canvas.drawLine( puntoInicialLinea1.first, puntoInicialLinea1.second, puntoFinalLinea1.first , puntoFinalLinea1.second ,paint)
        pagina1.canvas.drawLine( puntoInicialLinea2.first, puntoInicialLinea2.second, puntoFinalLinea2.first , puntoFinalLinea2.second ,paint)
        return posicionYFinal + desplazamientoEnY
    }

    private fun detalleFirma(pagina1: PdfDocument.Page, posicionY : Float) : Float {
        val tamanioLetra = 20f
        val posicionYFinal = posicionY + tamanioLetra
        pagina1.canvas.drawText("${context.getString(R.string.firma_secretario)} ", margenIzquierda, posicionYFinal, formatoDetalle(textSize = tamanioLetra))
        return posicionYFinal
    }

    private fun detalleFechaPublicacion(pagina1: PdfDocument.Page, posicionY : Float) {
        val tamanioLetra = 20f
        val posicionYFinal = posicionY + tamanioLetra
        val anchoLinea = 200f
        val separacion = 50f
        val posicionX = margenIzquierda + anchoLinea + separacion
        pagina1.canvas.drawText("${context.getString(R.string.fecha_de_publicacion)} ", posicionX, posicionYFinal, formatoDetalle(textSize = tamanioLetra))
    }
    //endregion

    //endregion

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