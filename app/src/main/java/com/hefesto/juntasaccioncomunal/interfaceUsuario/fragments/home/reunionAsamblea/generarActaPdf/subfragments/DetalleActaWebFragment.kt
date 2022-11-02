package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.generarActaPdf.subfragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hefesto.juntasaccioncomunal.R
import com.hefesto.juntasaccioncomunal.databinding.SubfragmentDetalleactaWebBinding
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.dialogo.DialogoInformativo
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.generarActaPdf.GenerarActaPdfViewModel
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.generarActaPdf.helpers.HelperGeneradorHtmlActaPDF
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.generarActaPdf.helpers.generadorPDF.HelperGeneradorPDFActa
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores.NodosNavegacionFragments
import com.hefesto.juntasaccioncomunal.interfaceUsuario.utilidades.gestorPermisos.PermisosAplicacionEnum
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.actasParaPDF.ReunionParaGenerarPDFModel
import javax.inject.Inject

class DetalleActaWebFragment :  BaseFragment<GenerarActaPdfViewModel>() {

    //region variable
    @Inject
    lateinit var generarActaPdfViewModel: GenerarActaPdfViewModel

    @Inject
    lateinit var helperGeneradorHtmlActaPDF: HelperGeneradorHtmlActaPDF

    @Inject
    lateinit var helperGeneradorPDFActa: HelperGeneradorPDFActa

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
        precargarFinalizoPDFLiveData()
        configuracionBotonGenerarPDF()
    }

    //region configurar botones

    private fun configurarBotones() {
        configurarBotonAtras()
    }

    private fun configurarBotonAtras() {
        conEscuchadorAccionBotonAtras { navegarAtras() }
    }

    private fun configuracionBotonGenerarPDF() {
        binding.buttonGenerarActaPdf.setOnClickListener {
            funcionSeguraConPermisos(
                PermisosAplicacionEnum.LECTURA_DOCUMENTO, PermisosAplicacionEnum.ESCRITURA_DOCUMENTOS,
                accionTieneTodosLosPermisos = {
                    val modelo = (arguments?.getSerializable(DETALLE_ACTA) as? ReunionParaGenerarPDFModel)?:return@funcionSeguraConPermisos
                    helperGeneradorPDFActa
                        .conContext(context = it.context)
                        .conReunionParaGenerarPDFModel(reunionParaGenerarPDFModel = modelo)
                        .conMostrarLoading (::mostrarLoading)
                        .conOcultarLoading (::ocultarLoading)
                        .conEscuchadorExcepcion {
                            funcionSegura(funcion = {
                                throw it
                            })
                        }
                        .crearPDF()
                }
            )
        }
    }
    //endregion

    //region cargarActa
    private fun precargarDetalleActa() {
        val modelo = (arguments?.getSerializable(DETALLE_ACTA) as? ReunionParaGenerarPDFModel)?:return
        helperGeneradorHtmlActaPDF
            .conWebView(webView = binding.webviewPrevisualizacion)
            .conReunionParaGenerarPDFModel(reunionParaGenerarPDFModel = modelo)
            .generarActaHtml()
    }

    private fun precargarFinalizoPDFLiveData() {
        helperGeneradorPDFActa
            .traerCreoPDFLiveData()
            .observe(viewLifecycleOwner) {
                if(!it) return@observe
                mostrarDialogo(
                    tipoDialogo = DialogoInformativo.TipoDialogo.INFORMATIVO,
                    titulo = R.string.crear_acta_reunion,
                    mensaje = R.string.se_ha_creado_el_pdf_correctamente,
                    accionAceptar = {
                        binding.buttonGenerarActaPdf.isEnabled = false
                        navegarAtras()
                    }
                )
            }
    }
    //endregion

    //endregion

    //endregion

    companion object {
        const val DETALLE_ACTA = "detalle acta"
    }

}