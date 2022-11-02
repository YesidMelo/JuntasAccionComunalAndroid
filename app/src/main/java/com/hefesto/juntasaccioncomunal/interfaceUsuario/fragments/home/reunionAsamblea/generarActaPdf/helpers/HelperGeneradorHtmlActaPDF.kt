package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.generarActaPdf.helpers

import android.webkit.WebView
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.actasParaPDF.PuntoReunionParaGenerarPDFModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.actasParaPDF.ReunionParaGenerarPDFModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.FormatosFecha
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.TipoReunion
import com.hefesto.juntasaccioncomunal.logica.utilidades.extenciones.convertirAFormato

class HelperGeneradorHtmlActaPDF {

    //region variables
    private lateinit var reunionParaGenerarPDFModel: ReunionParaGenerarPDFModel
    private lateinit var webView: WebView
    private var actaHtml : String = ""
    //endregion

    fun conReunionParaGenerarPDFModel(reunionParaGenerarPDFModel: ReunionParaGenerarPDFModel) : HelperGeneradorHtmlActaPDF {
        this.reunionParaGenerarPDFModel = reunionParaGenerarPDFModel
        return this
    }

    fun conWebView(webView: WebView) : HelperGeneradorHtmlActaPDF {
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
         var detalleCuerpo= ""
         detalleCuerpo += "${generarNumeroActa()}</br></br>"
         detalleCuerpo += "${generarFecha()}</br></br>"
         detalleCuerpo += "${generarLugar()}</br></br>"
         detalleCuerpo += "${generarTipoActa()}</br></br>"
         detalleCuerpo += "${generarPresidente()}</br></br>"
         detalleCuerpo += "${generarSecretario()}</br></br>"
         detalleCuerpo += "${generarQuorum()}</br></br>"
         detalleCuerpo += "${generarConvocantes()}</br></br>"
         detalleCuerpo += "${generarOrdenDia()}</br></br>"
         detalleCuerpo += "${generarDesarrollo()}</br></br>"
         return detalleCuerpo
     }

    private fun generarNumeroActa() : String {
        return "<b>Numero acta:</b> ${reunionParaGenerarPDFModel.numeroActa?:""}"
    }

    private fun generarFecha() : String {
        return "<b>fecha:</b> ${reunionParaGenerarPDFModel.fechaRegistro!!.convertirAFormato(formato = FormatosFecha.ANIO_MES_DIA_GIONES)}"
    }

    private fun generarLugar() : String {
        return "<b>Lugar:</b> ${reunionParaGenerarPDFModel.sitio?:""}"
    }

    private fun generarTipoActa() :String {
        return "<b>Tipo Acta:</b> ${webView.context.getString(reunionParaGenerarPDFModel.tipoReunion!!.traerStringRes())}"
    }

    private fun generarPresidente() :String {
        return "<b>Presidente:</b> ${reunionParaGenerarPDFModel.presidente?.nombres?:""} ${reunionParaGenerarPDFModel.presidente?.apellidos?:""}"
    }

    private fun generarSecretario() :String {
        return "<b>Secretario:</b> ${reunionParaGenerarPDFModel.secretario?.nombres?:""} ${reunionParaGenerarPDFModel.secretario?.apellidos?:""}"
    }

    private fun generarQuorum() :String {
        val numeroAsistentes = reunionParaGenerarPDFModel.numeroAsistentes?:0
        val numeroAfiliadosActivos = reunionParaGenerarPDFModel.numeroAfiliadosActivos?:1
        val porcentaje = (numeroAsistentes/numeroAfiliadosActivos)*100
        return "<b>Quorum:</b> $porcentaje %"
    }

    private fun generarConvocantes() :String {
        if (reunionParaGenerarPDFModel.listaConvocantes.isNullOrEmpty()) return ""
        var convocantes = "<b>Convocantes:</b>"
        convocantes += "<ol>"
        for (item in reunionParaGenerarPDFModel.listaConvocantes!!) {
            convocantes += "<li>${item.nombres} ${item.apellidos}</li>"
        }
        convocantes += "</ol>"
        return convocantes
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