package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.generarActaPdf.helpers.generadorPDF

import android.content.Context
import android.graphics.pdf.PdfDocument
import android.os.Environment
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.hefesto.juntasaccioncomunal.R
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.actasParaPDF.ReunionParaGenerarPDFModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.FormatosFecha
import com.hefesto.juntasaccioncomunal.logica.utilidades.extenciones.convertirAFormato
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream

class HelperGeneradorPDFActa {

    //region variables
    private lateinit var reunionParaGenerarPDFModel: ReunionParaGenerarPDFModel
    private lateinit var mostrarLoading : ()->Unit
    private lateinit var ocultarLoading : ()->Unit
    private lateinit var context: Context
    private var tamanioLetra : Float = 11f
    private val creoPDFLiveData = MutableLiveData<Boolean>()
    private val configuracionDocumentoPDF = ConfiguracionDocumentoPDF()
    private val generadorPaginasPDF = GeneradorPaginasPDF()
    //endregion

    fun conContext(context: Context) : HelperGeneradorPDFActa {
        this.context = context
        return this
    }

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
            val listaItems = generarListaItems()
            generadorPaginasPDF
                .conConfiguracionDocumentoPDF(configuracionDocumentoPDF = configuracionDocumentoPDF)
                .conListaItems(listaItems = listaItems)
                .conPdfDocument(pdfDocument = pdfDocument)
                .conMarcaDeAgua(marcaAguaBase64 = reunionParaGenerarPDFModel.marcaAgua)
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
        lista.add(generarNumeroActa())
        lista.add(generarLugar())
        lista.add(generarFecha())
        lista.add(generarConvocantes())
        lista.add(generarNumeroAsistentes())
        lista.add(generarQuorum())
        lista.add(generarPresidente())
        lista.add(generarSecretario())
        lista.add(generarOrdenDelDia())
        lista.add(generarDesarrolloOrdenDelDia())
        lista.add(generarFirmaSecretario())
        lista.add(generarFirmaPresidente())
        return lista
    }

    private fun generarNumeroActa() : DetalleItemPdf {
        return DetalleItemPdf().apply {
            this.detalle = "${context.getString(R.string.numero_acta)}: ${reunionParaGenerarPDFModel.numeroActa?:1}"
            this.tamanioLetra = this@HelperGeneradorPDFActa.tamanioLetra
            this.tipo = TipoAAplicar.NORMAL
        }
    }

    private fun generarLugar() : DetalleItemPdf {
        return DetalleItemPdf().apply {
            this.detalle = "${context.getString(R.string.lugar)}: ${reunionParaGenerarPDFModel.sitio?:""}"
            this.tamanioLetra = this@HelperGeneradorPDFActa.tamanioLetra
            this.tipo = TipoAAplicar.NORMAL
        }
    }

    private fun generarFecha() : DetalleItemPdf {
        return DetalleItemPdf().apply {
            this.detalle = "${context.getString(R.string.fecha)}: ${reunionParaGenerarPDFModel.fechaYHoraProgramacionReunion?.convertirAFormato(formato = FormatosFecha.ANIO_MES_DIA_GIONES)?:""}"
            this.tamanioLetra = this@HelperGeneradorPDFActa.tamanioLetra
            this.tipo = TipoAAplicar.NORMAL
        }
    }

    private fun generarConvocantes() : DetalleItemPdf {
        return DetalleItemPdf().apply {
            this.detalle = "\n${context.getString(R.string.convoca)}:\n\n"
            this.tamanioLetra = this@HelperGeneradorPDFActa.tamanioLetra
            this.tipo = TipoAAplicar.NORMAL

            if (reunionParaGenerarPDFModel.listaConvocantes == null) return@apply

            for (contador in 0 until reunionParaGenerarPDFModel.listaConvocantes!!.size) {
                this.detalle+= "\t${contador+1}. ${reunionParaGenerarPDFModel.listaConvocantes!![contador].nombres} ${reunionParaGenerarPDFModel.listaConvocantes!![contador].apellidos}"
            }
            this.detalle += "\n\n"
        }
    }

    private fun generarNumeroAsistentes() : DetalleItemPdf {
        return DetalleItemPdf().apply {
            this.detalle = "${context.getString(R.string.numero_asistentes)}: ${reunionParaGenerarPDFModel.numeroAsistentes?:0}"
            this.tamanioLetra = this@HelperGeneradorPDFActa.tamanioLetra
            this.tipo = TipoAAplicar.NORMAL
        }
    }

    private fun generarQuorum() : DetalleItemPdf {
        return DetalleItemPdf().apply {
            val numeroAsistentes = reunionParaGenerarPDFModel.numeroAsistentes?:0
            val afiliadosActivos = reunionParaGenerarPDFModel.numeroAfiliadosActivos?:1
            val quorum = (numeroAsistentes.toFloat()/afiliadosActivos)
            this.detalle = "${context.getString(R.string.quorum)}: $quorum"
            this.tamanioLetra = this@HelperGeneradorPDFActa.tamanioLetra
            this.tipo = TipoAAplicar.NORMAL
        }
    }

    private fun generarPresidente() : DetalleItemPdf {
        return DetalleItemPdf().apply {
            this.detalle = "${context.getString(R.string.presidente)}: ${reunionParaGenerarPDFModel.presidente?.nombres?:""} ${reunionParaGenerarPDFModel.presidente?.apellidos?:""}"
            this.tamanioLetra = this@HelperGeneradorPDFActa.tamanioLetra
            this.tipo = TipoAAplicar.NORMAL
        }
    }

    private fun generarSecretario() : DetalleItemPdf {
        return DetalleItemPdf().apply {
            this.detalle = "${context.getString(R.string.secretario)}: ${reunionParaGenerarPDFModel.secretario?.nombres?:""} ${reunionParaGenerarPDFModel.secretario?.apellidos?:""}\n"
            this.tamanioLetra = this@HelperGeneradorPDFActa.tamanioLetra
            this.tipo = TipoAAplicar.NORMAL
        }
    }

    private fun generarOrdenDelDia() : DetalleItemPdf {
        return DetalleItemPdf().apply {
            this.tamanioLetra = this@HelperGeneradorPDFActa.tamanioLetra
            this.tipo = TipoAAplicar.NORMAL
            this.detalle = "${context.getString(R.string.orden_del_dia)}:\n\n"
            for (contador in 0 until reunionParaGenerarPDFModel.listaPuntos!!.size) {
                detalle += "\t${contador + 1}. ${reunionParaGenerarPDFModel.listaPuntos!![contador].tituloPunto?:""}\n"
            }
        }
    }

    private fun generarDesarrolloOrdenDelDia() : DetalleItemPdf {
        return DetalleItemPdf().apply {
            this.tamanioLetra = this@HelperGeneradorPDFActa.tamanioLetra
            this.tipo = TipoAAplicar.NORMAL
            this.detalle = "${context.getString(R.string.desarrollo_orden_del_dia)}:\n\n"
            for (contador in 0 until reunionParaGenerarPDFModel.listaPuntos!!.size) {
                detalle += "${contador + 1}. ${reunionParaGenerarPDFModel.listaPuntos!![contador].tituloPunto?:""}:\n"
                detalle += "${reunionParaGenerarPDFModel.listaPuntos!![contador].detallePunto?:""}."
                if (!reunionParaGenerarPDFModel.listaPuntos!![contador].tieneVotacion) {
                    detalle += "\n\n"
                    continue
                }
                detalle += "\n\n${context.getString(R.string.votos_a_favor)}: ${reunionParaGenerarPDFModel.listaPuntos!![contador].votosAFavor?:0} "
                detalle += "${context.getString(R.string.votos_en_contra)}: ${reunionParaGenerarPDFModel.listaPuntos!![contador].votosEnContra?:0}\n\n"
            }
        }
    }

    private fun generarFirmaSecretario() : DetalleItemPdf {
        return DetalleItemPdf().apply {
            this.detalle = "\n\n\n${context.getString(R.string.secretario)}: ${reunionParaGenerarPDFModel.secretario?.nombres?:""} ${reunionParaGenerarPDFModel.secretario?.apellidos?:""}\n\n\n"
            this.tamanioLetra = this@HelperGeneradorPDFActa.tamanioLetra
            this.tipo = TipoAAplicar.NORMAL
            this.esFirma = true
        }
    }
    private fun generarFirmaPresidente() : DetalleItemPdf {
        return DetalleItemPdf().apply {
            this.detalle = "${context.getString(R.string.presidente)}: ${reunionParaGenerarPDFModel.presidente?.nombres?:""} ${reunionParaGenerarPDFModel.presidente?.apellidos?:""}"
            this.tamanioLetra = this@HelperGeneradorPDFActa.tamanioLetra
            this.tipo = TipoAAplicar.NORMAL
            this.esFirma = true
        }
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