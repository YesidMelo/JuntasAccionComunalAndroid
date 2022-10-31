package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.crearActa

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hefesto.juntasaccioncomunal.R
import com.hefesto.juntasaccioncomunal.databinding.FragmentCrearactaBinding
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.dialogo.DialogoInformativo
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.crearActa.helper.HelperViewPagerFormulariosCompletarActas
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores.NodosNavegacionFragments
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.crearActa.ReunionAsambleaCreacionActaModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.FormatosFecha
import com.hefesto.juntasaccioncomunal.logica.utilidades.extenciones.convertirAFormato
import javax.inject.Inject

class CrearActaFragment  : BaseFragment<CrearActaViewModel>(){

    //region variables
    @Inject
    lateinit var crearActaViewModel: CrearActaViewModel

    @Inject
    lateinit var helperViewPagerFormulariosCompletarActas: HelperViewPagerFormulariosCompletarActas

    private lateinit var binding: FragmentCrearactaBinding
    //endregion

    override fun traerViewModel(): CrearActaViewModel = crearActaViewModel

    override fun traerNodoNavegacion(): NodosNavegacionFragments = NodosNavegacionFragments.CREAR_ACTA_REUNION_ASAMBLEA

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCrearactaBinding.inflate(inflater)
        configurarBotones()
        precargarVista()
        return binding.root
    }

    //region metodos privados

    //region botones
    private fun configurarBotones() {
        configurarBotonAtras()
        configurarGuardarActa()
    }

    private fun configurarBotonAtras(){
        conEscuchadorAccionBotonAtras { navegarAtras() }
    }


    //region guardar acta
    private fun configurarGuardarActa() {
        binding.buttonCrearActaEjecutarCreacionActa.setOnClickListener {
            funcionSegura(
                funcion = {
                    deshabilitarBotones()
                    mostrarMensajeAdvertenciaGuardarActa()
                },
                aceptarFallo = ::habiliarBotones
            )

        }
    }

    private fun mostrarMensajeAdvertenciaGuardarActa() {
        mostrarDialogo(
            tipoDialogo = DialogoInformativo.TipoDialogo.ADVERTENCIA,
            titulo = R.string.crear_acta_reunion,
            mensaje = R.string.deseas_continuar_con_la_creacion_del_acta,
            accionAceptar = traerViewModel()::guardarActa,
            accionCancelar = ::habiliarBotones
        )
    }

    private fun habiliarBotones() {
        binding.buttonCrearActaEjecutarCreacionActa.isEnabled = true
    }

    private fun deshabilitarBotones() {
        binding.buttonCrearActaEjecutarCreacionActa.isEnabled = false
    }

    //endregion

    //endregion

    //region precarga

    private fun precargarVista() {
        traerViewModel().cargo(value = false)
        precargarViewPager()
        precargarLiveData()
        detalleInfoAViewModel()
    }

    private fun precargarViewPager() {
        helperViewPagerFormulariosCompletarActas
            .conCrearActaFragment(crearActaFragment = this)
            .conCrearActaViewModel(crearActaViewModel = traerViewModel())
            .conViewPager(viewPager = binding.viewPager2FormulariosActa)
            .conTabLayout(tabLayout = binding.tablayoutFormulariosFinalizacion)
            .configurarPaginas()
    }

    //region livedata
    private fun precargarLiveData() {
        precargarReunionAsambleaLiveData()
        precargarCargaVistaLiveData()
        precargarGuardadoExitoso()
    }

    private fun precargarReunionAsambleaLiveData() {
        traerViewModel()
            .traerDetalleReunionLiveData()
            .observe(viewLifecycleOwner) {
                val detalle = it?:return@observe
                cargarReunion(detalle = detalle)
            }
    }

    private fun cargarReunion(detalle: ReunionAsambleaCreacionActaModel) {
        binding.textViewCrearActaDetalleasunto.text = detalle.asuntoReunion
        binding.textViewCrearActaDetallefechaconvocatoria.text = detalle.fechaYHoraProgramacionReunion?.convertirAFormato(formato = FormatosFecha.ANIO_MES_DIA_GIONES)
        binding.textViewCrearActaDetalleHoraConvocatoria.text = detalle.fechaYHoraProgramacionReunion?.convertirAFormato(formato = FormatosFecha.HORA_MINUTO_12H)
        binding.textViewCrearActaDetalleTipoReunion.setText(detalle.tipoReunion!!.traerStringRes())
    }

    private fun precargarCargaVistaLiveData() {
        traerViewModel()
            .traerHaCargadoLiveData()
            .observe(viewLifecycleOwner) {
                if (!it) {
                    mostrarLoading()
                    return@observe
                }
                ocultarLoading()
            }
    }

    private fun precargarGuardadoExitoso() {
        traerViewModel()
            .traerActaCreadaLiveData()
            .observe(viewLifecycleOwner) {
                if (!it) return@observe
                mostrarDialogo(
                    tipoDialogo = DialogoInformativo.TipoDialogo.INFORMATIVO,
                    titulo = R.string.crear_acta_reunion,
                    mensaje = R.string.se_ha_creado_el_acta_exitosamente,
                    accionAceptar = ::navegarAtras
                )
            }
    }

    //endregion

    private fun detalleInfoAViewModel() {
        val detalle = arguments?.get(REUNION_PENDIENTE_ACTA) as? ReunionAsambleaCreacionActaModel
        traerViewModel().conReunionAModificar(detalle = detalle)
    }

    //endregion

    //endregion

    companion object {
        const val REUNION_PENDIENTE_ACTA = "ReunionPendienteActa"
    }
}