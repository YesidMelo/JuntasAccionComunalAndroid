package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.generarConvocatoriaReunionPdf

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hefesto.juntasaccioncomunal.databinding.FragmentDetalleconvocatoriaBinding
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.listaConvocatoriasReuniones.ListaConvocatoriasViewModel
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores.NodosNavegacionFragments
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.reunionParaConvocatoriaPDF.ReunionParaGenerarConvocatoriaPDFModel
import javax.inject.Inject

class DetalleConvocatoriaFragment : BaseFragment<ListaConvocatoriasViewModel>() {

    @Inject
    lateinit var listaConvocatoriasViewModel : ListaConvocatoriasViewModel
    @Inject
    lateinit var helperConfigurarConvocatoriaHtml: HelperConfigurarConvocatoriaHtml

    lateinit var binding: FragmentDetalleconvocatoriaBinding

    override fun traerViewModel(): ListaConvocatoriasViewModel = listaConvocatoriasViewModel

    override fun traerNodoNavegacion(): NodosNavegacionFragments = NodosNavegacionFragments.DETALLE_GENERAR_CONVOCATORIA_PDF

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetalleconvocatoriaBinding.inflate(inflater)
        llenarWebView()
        return binding.root
    }

    private fun llenarWebView() {
        val convocatoria = (arguments?.getSerializable(DETALLE_CONVOCATORIA) as? ReunionParaGenerarConvocatoriaPDFModel)?:return
        helperConfigurarConvocatoriaHtml
            .conWebView(webView = binding.webViewDetalleConvocatoria)
            .conReunion(reunionParaGenerarConvocatoriaPDFModel = convocatoria)
            .cargarConvocatoria()
    }

    companion object {
        const val DETALLE_CONVOCATORIA = "convocatoria"
    }

}