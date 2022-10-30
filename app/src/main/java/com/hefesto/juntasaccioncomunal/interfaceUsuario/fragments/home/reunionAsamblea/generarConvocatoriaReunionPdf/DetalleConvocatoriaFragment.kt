package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.generarConvocatoriaReunionPdf

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hefesto.juntasaccioncomunal.R
import com.hefesto.juntasaccioncomunal.databinding.FragmentDetalleconvocatoriaBinding
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.dialogo.DialogoInformativo
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.listaConvocatoriasReuniones.ListaConvocatoriasViewModel
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores.NodosNavegacionFragments
import com.hefesto.juntasaccioncomunal.interfaceUsuario.utilidades.gestorPermisos.PermisosAplicacionEnum
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.reunionParaConvocatoriaPDF.ReunionParaGenerarConvocatoriaPDFModel
import javax.inject.Inject

class DetalleConvocatoriaFragment : BaseFragment<ListaConvocatoriasViewModel>() {

    @Inject
    lateinit var listaConvocatoriasViewModel : ListaConvocatoriasViewModel
    @Inject
    lateinit var helperConfigurarConvocatoriaHtml: HelperConfigurarConvocatoriaHtml
    @Inject
    lateinit var helperGenerarConvocatoriaPDF: HelperGenerarConvocatoriaPDF

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
        confugurarBotonGenerarPDF()
        configurarBotonAtras()
        configurarLivedataFinalizoGeneracionPDF()
        return binding.root
    }


    private fun llenarWebView() {
        val convocatoria = (arguments?.getSerializable(DETALLE_CONVOCATORIA) as? ReunionParaGenerarConvocatoriaPDFModel)?:return
        helperConfigurarConvocatoriaHtml
            .conWebView(webView = binding.webViewDetalleConvocatoria)
            .conReunion(reunionParaGenerarConvocatoriaPDFModel = convocatoria)
            .cargarConvocatoria()
    }

    private fun confugurarBotonGenerarPDF() {
        val convocatoria = (arguments?.getSerializable(DETALLE_CONVOCATORIA) as? ReunionParaGenerarConvocatoriaPDFModel)?:return
        binding.buttonGenerarConvocatoriaPdf.setOnClickListener {
            funcionSeguraConPermisos(
                PermisosAplicacionEnum.ESCRITURA_DOCUMENTOS, PermisosAplicacionEnum.LECTURA_DOCUMENTO,
                accionTieneTodosLosPermisos = {
                    helperGenerarConvocatoriaPDF
                        .conContext(context = binding.webViewDetalleConvocatoria.context)
                        .conConvocatoria(convocatoria = convocatoria)
                        .conMostrarLoading(mostrarLoading = ::mostrarLoading)
                        .conOcultarLoading(ocultarLoading = ::ocultarLoading)
                        .generarPDF()
                }
            )
        }
    }

    private fun configurarBotonAtras() {
        conEscuchadorAccionBotonAtras { navegarAtras() }
    }

    private fun configurarLivedataFinalizoGeneracionPDF() {
        helperGenerarConvocatoriaPDF
            .traerSeGeneroPDFLiveData()
            .observe(viewLifecycleOwner) {
                if (!it)return@observe
                mostrarDialogo(
                    tipoDialogo = DialogoInformativo.TipoDialogo.INFORMATIVO,
                    titulo = R.string.generar_convocatoria_reunionPDF,
                    mensaje = R.string.se_ha_generado_el_pdf_correctamente,
                    accionAceptar = ::navegarAtras
                )
            }
    }

    companion object {
        const val DETALLE_CONVOCATORIA = "convocatoria"
    }

}