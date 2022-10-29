package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.generarConvocatoriaReunionPdf

import android.webkit.WebView
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.reunionParaConvocatoriaPDF.ReunionParaGenerarConvocatoriaPDFModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.FormatosFecha
import com.hefesto.juntasaccioncomunal.logica.utilidades.extenciones.convertirAFormato

class HelperConfigurarConvocatoriaHtml {

    //region variables
    private lateinit var webView: WebView
    private lateinit var reunionParaGenerarConvocatoriaPDFModel: ReunionParaGenerarConvocatoriaPDFModel
    private var convocatoriaHtml = ""
    //endregion

    fun conWebView(webView: WebView) : HelperConfigurarConvocatoriaHtml {
        this.webView = webView
        return this
    }

    fun conReunion(reunionParaGenerarConvocatoriaPDFModel: ReunionParaGenerarConvocatoriaPDFModel) : HelperConfigurarConvocatoriaHtml {
        this.reunionParaGenerarConvocatoriaPDFModel = reunionParaGenerarConvocatoriaPDFModel
        return this
    }

    fun cargarConvocatoria() {
        convocatoriaHtml = "<html><body width>${cuerpoConvocatoria()}</body></html>"
        webView.settings.javaScriptEnabled = true
        webView.loadDataWithBaseURL(null, convocatoriaHtml, "text/html", "utf-8", null)
    }

    //region metodos privados
    private fun cuerpoConvocatoria() : String {
        var cuerpo = "${asunto()} "
        cuerpo+= "${sitio()} "
        cuerpo+= "${fecha()} "
        cuerpo+= "${hora()} "
        cuerpo+= "${ordenDia()} "
        cuerpo+= "${convocantes()} "
        return cuerpo
    }

    private fun asunto() : String {
        return "<b>Asunto:</b> ${reunionParaGenerarConvocatoriaPDFModel.asuntoReunion?:""}</br>"
    }

    private fun sitio() : String {
        return "<b>Sitio:</b> ${reunionParaGenerarConvocatoriaPDFModel.sitio}</br>"
    }

    private fun fecha() : String {
        return "<b>Fecha:</b> ${reunionParaGenerarConvocatoriaPDFModel.fechaYHoraProgramacionReunion?.convertirAFormato(formato = FormatosFecha.ANIO_MES_DIA_GIONES)?:""}</br>"
    }

    private fun hora() : String {
        return "<b>Hora:</b> ${reunionParaGenerarConvocatoriaPDFModel.fechaYHoraProgramacionReunion?.convertirAFormato(formato = FormatosFecha.HORA_MINUTO_12H)?:""}</br>"
    }

    private fun ordenDia() : String {
        var ordenDia = "<b>Orden dia:</b>"
        ordenDia+="<ol>"
        for (punto in reunionParaGenerarConvocatoriaPDFModel.listaPuntos!!) {
            ordenDia+="<li>${punto.tituloPunto}</li>"
        }
        ordenDia+="</ol>"
        return ordenDia
    }

    private fun convocantes() :String {
        var convocan = "<b>Convocan:</b>"
        convocan+="<ol>"
        for (item in reunionParaGenerarConvocatoriaPDFModel.listaConvocantes!!) {
            convocan += "<li>${item.nombres?:""} ${item.apellidos?:""}</li>"
        }
        convocan+="</ol>"
        return convocan
    }
    //endregion
}