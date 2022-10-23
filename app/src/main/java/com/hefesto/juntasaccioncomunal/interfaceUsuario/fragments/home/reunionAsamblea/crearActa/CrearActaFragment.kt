package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.crearActa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hefesto.juntasaccioncomunal.databinding.FragmentCrearactaBinding
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores.NodosNavegacionFragments
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.crearActa.ReunionAsambleaCreacionActaModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.FormatosFecha
import com.hefesto.juntasaccioncomunal.logica.utilidades.extenciones.convertirAFormato
import javax.inject.Inject

class CrearActaFragment  : BaseFragment<CrearActaViewModel>(){

    //region variables
    @Inject
    lateinit var crearActaViewModel: CrearActaViewModel

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
    }

    private fun configurarBotonAtras(){
        conEscuchadorAccionBotonAtras { navegarAtras() }
    }
    //endregion

    //region precarga

    private fun precargarVista() {
        precargarLiveData()
        detalleInfoAViewModel()
    }

    //region livedata
    private fun precargarLiveData() {
        precargarReunionAsambleaLiveData()
        precargarCargaVistaLiveData()
    }

    private fun precargarReunionAsambleaLiveData() {
        traerViewModel()
            .traerDetalleReunionLiveData()
            .observe(viewLifecycleOwner) {
                traerViewModel().cargo(cargo = true)
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

    //endregion

    private fun detalleInfoAViewModel() {

        val detalle = arguments?.get(REUNION_PENDIENTE_ACTA) as? ReunionAsambleaCreacionActaModel
        traerViewModel().cargo(cargo = false)
        traerViewModel().conReunionAModificar(detalle = detalle)
    }

    //endregion

    //endregion

    companion object {
        const val REUNION_PENDIENTE_ACTA = "ReunionPendienteActa"
    }
}