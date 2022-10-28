package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.generarActaPdf.subfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hefesto.juntasaccioncomunal.databinding.SubfragmentDetalleactaWebBinding
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.generarActaPdf.GenerarActaPdfViewModel
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.generarActaPdf.helpers.HelperGeneradorHtmlParaGenerarPDF
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores.NodosNavegacionFragments
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.actasParaPDF.ReunionParaGenerarPDFModel
import javax.inject.Inject

class DetalleActaWebFragment :  BaseFragment<GenerarActaPdfViewModel>() {

    //region variable
    @Inject
    lateinit var generarActaPdfViewModel: GenerarActaPdfViewModel

    @Inject
    lateinit var helperGeneradorHtmlParaGenerarPDF: HelperGeneradorHtmlParaGenerarPDF

    private lateinit var binding: SubfragmentDetalleactaWebBinding
    //endregion


    override fun traerViewModel(): GenerarActaPdfViewModel = generarActaPdfViewModel

    override fun traerNodoNavegacion(): NodosNavegacionFragments= NodosNavegacionFragments.DETALLE_PUNTO_REUNION

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SubfragmentDetalleactaWebBinding.inflate(inflater)
        precargarVista()
        return binding.root
    }

    //region metodos privados

    //region precargar vista
    private fun precargarVista() {
        configurarBotones()
        precargarDetalleActa()
    }

    //region configurar botones

    private fun configurarBotones() {
        configurarBotonAtras()
    }

    private fun configurarBotonAtras() {
        conEscuchadorAccionBotonAtras { navegarAtras() }
    }
    //endregion

    //region cargarActa
    private fun precargarDetalleActa() {
        val modelo = (arguments?.getSerializable(DETALLE_ACTA) as? ReunionParaGenerarPDFModel)?:return
        helperGeneradorHtmlParaGenerarPDF
            .conWebView(webView = binding.webviewPrevisualizacion)
            .conReunionParaGenerarPDFModel(reunionParaGenerarPDFModel = modelo)
            .generarActaHtml()
    }
    //endregion

    //endregion

    //endregion

    companion object {
        const val DETALLE_ACTA = "detalle acta"
    }

}