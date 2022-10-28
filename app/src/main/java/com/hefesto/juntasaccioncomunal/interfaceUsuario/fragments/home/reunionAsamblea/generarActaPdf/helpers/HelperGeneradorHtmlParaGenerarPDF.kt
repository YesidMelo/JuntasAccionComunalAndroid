package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.generarActaPdf.helpers

import android.webkit.WebView
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.actasParaPDF.PuntoReunionParaGenerarPDFModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.actasParaPDF.ReunionParaGenerarPDFModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.FormatosFecha
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.TipoReunion
import com.hefesto.juntasaccioncomunal.logica.utilidades.extenciones.convertirAFormato

class HelperGeneradorHtmlParaGenerarPDF {

    //region variables
    private lateinit var reunionParaGenerarPDFModel: ReunionParaGenerarPDFModel
    private lateinit var webView: WebView
    private var actaHtml : String = ""
    //endregion

    fun conReunionParaGenerarPDFModel(reunionParaGenerarPDFModel: ReunionParaGenerarPDFModel) : HelperGeneradorHtmlParaGenerarPDF {
        this.reunionParaGenerarPDFModel = reunionParaGenerarPDFModel
        return this
    }

    fun conWebView(webView: WebView) : HelperGeneradorHtmlParaGenerarPDF {
        this.webView = webView
        return this
    }

    fun traerActaHtml() = actaHtml

    fun generarActaHtml() {
        generarHtml()
    }

    //region metodos privados
    private fun generarHtml() {
        actaHtml = "<html><body>${cuerpoActa()}</body></html>"
        webView.settings.javaScriptEnabled = true
        webView.loadDataWithBaseURL(null, actaHtml, "text/html", "utf-8", null)
    }

     private fun cuerpoActa() : String {
         var detalleCuerpo= "${generarFecha()}</br></br>"
         detalleCuerpo += "${generarTipoActa()}</br></br>"
         detalleCuerpo += "${generarOrdenDia()}</br></br>"
         detalleCuerpo += "${generarDesarrollo()}</br></br>"
         return detalleCuerpo
     }

    private fun generarFecha() : String {
        return "Bogotá D.C. ${reunionParaGenerarPDFModel.fechaRegistro!!.convertirAFormato(formato = FormatosFecha.ANIO_MES_DIA_GIONES)}"
    }

    private fun generarTipoActa() :String {
        return "<b>Tipo Acta:</b> ${webView.context.getString(reunionParaGenerarPDFModel.tipoReunion!!.traerStringRes())}"
    }

    private fun generarOrdenDia() : String {
        var ordenDia = "<b>Orden del dia</b></br>"
        ordenDia+="<ol>"
        for (punto in reunionParaGenerarPDFModel.listaPuntos!!) {
            ordenDia+="<li>${punto.tituloPunto!!}</li>"
        }
        ordenDia+="</ol>"
        return ordenDia
    }

    //region desarrollo
    private fun generarDesarrollo() : String {
        var detalleDesarrollo = "<b>Desarrollo:</b></br>"
        detalleDesarrollo+="<ol>"
        for (punto in reunionParaGenerarPDFModel.listaPuntos!!) {
            detalleDesarrollo+="<li>"
            detalleDesarrollo+="<b>${punto.tituloPunto}:</b> ${generarContadorVotos(punto = punto)}</br>"
            detalleDesarrollo+="${punto.detallePunto}</br></br>"
            detalleDesarrollo+="</li>"
        }
        detalleDesarrollo+="</ol>"
        return detalleDesarrollo
    }

    private fun generarContadorVotos(punto: PuntoReunionParaGenerarPDFModel) : String {
        if (punto.votosAFavor == null) return ""
        if (punto.votosEnContra == null) return ""
        if (reunionParaGenerarPDFModel.tipoReunion == TipoReunion.ASAMBLEA_INFORMATIVA) return ""
        if (reunionParaGenerarPDFModel.tipoReunion == TipoReunion.REUNION_DIRECTIVA_INFORMATIVA) return ""
        return "<b>Votos a favor:</b> ${punto.votosAFavor} <b>Votos en contra:</b>${punto.votosEnContra}"
    }
    //endregion


    //endregion

}