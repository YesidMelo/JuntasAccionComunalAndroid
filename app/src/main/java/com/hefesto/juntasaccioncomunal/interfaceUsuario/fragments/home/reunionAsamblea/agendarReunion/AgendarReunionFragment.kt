package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.agendarReunion

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hefesto.juntasaccioncomunal.R
import com.hefesto.juntasaccioncomunal.databinding.FragmentAgendarReunionAsambleaBinding
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.dialogo.DialogoInformativo
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.agendarReunion.helpers.HelperAutocompleteConvocantesAgendarReunion
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.agendarReunion.helpers.HelperRecyclerViewAgendarReunionListaPuntos
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.agendarReunion.helpers.HelperSpinnerTiposReunion
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores.NodosNavegacionFragments
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.agendarReunion.PuntoReunionAgendarReunionAsambleaModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.FormatosFecha
import com.hefesto.juntasaccioncomunal.logica.utilidades.extenciones.convertirAFormato
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class AgendarReunionFragment : BaseFragment<AgendarReunionViewModel> (){

    //region variables
    @Inject
    lateinit var agendarReunionViewModel: AgendarReunionViewModel
    @Inject
    lateinit var helperSpinnerTiposReunion : HelperSpinnerTiposReunion
    @Inject
    lateinit var helperRecyclerViewAgendarReunionListaPuntos: HelperRecyclerViewAgendarReunionListaPuntos
    @Inject
    lateinit var helperAutocompleteConvocantesAgendarReunion: HelperAutocompleteConvocantesAgendarReunion

    private lateinit var binding: FragmentAgendarReunionAsambleaBinding
    //endregion

    override fun traerViewModel(): AgendarReunionViewModel = agendarReunionViewModel

    override fun traerNodoNavegacion(): NodosNavegacionFragments = NodosNavegacionFragments.AGENDAR_REUNION_ASAMBLEA

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAgendarReunionAsambleaBinding.inflate(inflater)
        configurarBotones()
        precargarVista()
        return binding.root
    }

    //region metodos privados

    //region botones
    private fun configurarBotones() {
        GlobalScope.launch {
            configurarBotonAtras()
            configurarSeleccionFecha()
            configurarSeleccionHora()
            configurarAdicionarPunto()
            configurarBotonGuardar()
            configurarBotonCancelar()
            configurarBotonAgendarReunion()
        }
    }

    private fun configurarBotonAtras() {
        conEscuchadorAccionBotonAtras { navegarAtras() }
    }

    private fun configurarSeleccionFecha() {
        binding.textViewAgendarReunionAsambleaFechaReunion.setOnClickListener {
            val fechaMinima = Calendar.getInstance()
            fechaMinima.add(Calendar.DAY_OF_MONTH, helperSpinnerTiposReunion.traerSeleccionado().tipoReunion.traerMinimoDiasAgendarReunion())
            mostrarDialogoCalendario(
                accionFechaSeleccionada = traerViewModel()::adicionarFechaSeleccionada,
                calendarFechaMinimaSeleccion = fechaMinima
            )
        }
    }

    private fun configurarSeleccionHora() {
        binding.textViewAgendarReunionAsambleaHoraReunion.setOnClickListener {
            mostrarDialogoHora {
                traerViewModel()
                    .adicionarHoraSeleccionada(hora = it)
            }
        }
    }

    private fun configurarAdicionarPunto() {
        binding.imageViewAgendarReunionAsambleaAdicionarPunto.setOnClickListener {
            traerViewModel().adicionarPuntoAReunion(adicionar = true)
        }
    }

    private fun configurarBotonGuardar() {
        binding.buttonAgendarReunionAsambleaGuardar.setOnClickListener {
            val punto = PuntoReunionAgendarReunionAsambleaModel(tituloPunto = binding.edittextAgendarReunionAsambleaTituloPunto.text.toString())
            helperRecyclerViewAgendarReunionListaPuntos
                .adicionarPunto(punto = punto)
            traerViewModel().adicionarPuntoAReunion(adicionar = false)
        }
    }

    private fun configurarBotonCancelar() {
        binding.buttonAgendarReunionAsambleaCancelar.setOnClickListener {
            traerViewModel().adicionarPuntoAReunion(adicionar = false)
        }
    }

    private fun configurarBotonAgendarReunion() {
        binding.buttonAgendarReunion.setOnClickListener {
            funcionSegura(
                aceptarFallo = {
                    habilitarBotones()
               },
                funcion = {
                    traerViewModel()
                        .agendarReunion(
                            listaPuntosReunion = helperRecyclerViewAgendarReunionListaPuntos.traerListaPuntos(),
                            tituloReunion = binding.editTextAgendarReunionAsambleaAsunto.text.toString(),
                            tipoReunion = helperSpinnerTiposReunion.traerSeleccionado().tipoReunion,
                            listaConvocantes = helperAutocompleteConvocantesAgendarReunion.traerListaConvocantes()
                        )
                }
            )
        }
    }
    //endregion

    //region precarga
    private fun precargarVista() {
        precargarConvocantesDisponibles()
        precargarSpinnerTipoReunion()
        precargarFechaReunionAsamblea()
        precargarHoraReunionAsamblea()
        precargarMostrarFormularioNuevoPunto()
        precargarReciclerView()
        precargarCargaFragment()
        precargarReunionAgendada()
    }

    private fun precargarConvocantesDisponibles() {
        traerViewModel()
            .traerAfiliadosConvocantes()
            .observe(viewLifecycleOwner){
                helperAutocompleteConvocantesAgendarReunion
                    .conListaConvocantesDiponibles(listaConvocantes = it)
                    .conAutocompleteView(autoCompleteTextView = binding.autocompleteAgendarReunionAsambleaFiltroConvocante)
                    .conRecyclerView(recyclerView = binding.recyclerviewAgendarReunionAsambleaConvocantes)
                    .conRadioButtonGroup(radioGroup = binding.radioButtonGroupSelectorFiltro)
                    .conRadioButtonDocumento(radioButtonDocumento = binding.radioButtonAgendarReunionAsambleaNumerodocumento)
                    .conRadioButtonNombres(radioButtonNombre = binding.radioButtonAgendarReunionAsambleaNombres)
                    .conMostrarLoading(mostrarLoading = ::mostrarLoading)
                    .conOcultarLoading(ocultarLoading = ::ocultarLoading)
                    .configurarAutocompleteRecycler()
            }
    }

    private fun precargarSpinnerTipoReunion() {
        traerViewModel()
            .traerTiposReunionLiveData()
            .observe(viewLifecycleOwner) {
                helperSpinnerTiposReunion
                    .conSpinner(spinner = binding.spinnerAgendarReunionAsambleaTipoReunion)
                    .conListaTiposReunion(lista = it)
                    .cargarSpinner()
            }
    }

    private fun precargarFechaReunionAsamblea() {
        traerViewModel()
            .traerfechaReunionLiveData()
            .observe(viewLifecycleOwner) {
                if (it == null) return@observe
                binding.textViewAgendarReunionAsambleaFechaReunion.setText(it.convertirAFormato(formato = FormatosFecha.SLASH_1))
            }
    }

    private fun precargarHoraReunionAsamblea() {
        traerViewModel()
            .traerHoraReunionLiveData()
            .observe(viewLifecycleOwner){
                if(it == null) return@observe
                binding.textViewAgendarReunionAsambleaHoraReunion.text = it.convertirAFormato(formato = FormatosFecha.HORA_MINUTO_12H)
            }
    }

    private fun precargarMostrarFormularioNuevoPunto() {
        traerViewModel()
            .traerAdicionarPuntoLiveData()
            .observe(viewLifecycleOwner) {
                binding.constraintlayoutAgendarReunionAsambleaFormularioPunto.visibility = if(it) View.VISIBLE else View.GONE
                binding.imageViewAgendarReunionAsambleaAdicionarPunto.visibility = if(!it) View.VISIBLE else View.GONE
                binding.edittextAgendarReunionAsambleaTituloPunto.setText("")
            }
    }

    private fun precargarReciclerView() {
        helperRecyclerViewAgendarReunionListaPuntos
            .conRecyclerView(recyclerView = binding.reciclerViewListaPuntosReunion)
            .cargarRecycler()
    }

    private fun precargarCargaFragment() {
        traerViewModel()
            .traerTerminoCargaLiveData()
            .observe(viewLifecycleOwner) {
                if (!it) {
                    deshabilitarBotones()
                    mostrarLoading()
                    return@observe
                }
                habilitarBotones()
                ocultarLoading()
            }
    }

    private fun habilitarBotones() {
        binding.buttonAgendarReunion.isEnabled = true
        binding.buttonAgendarReunionAsambleaCancelar.isEnabled = true
        binding.buttonAgendarReunionAsambleaGuardar.isEnabled = true
    }

    private fun deshabilitarBotones() {
        binding.buttonAgendarReunion.isEnabled = false
        binding.buttonAgendarReunionAsambleaCancelar.isEnabled = false
        binding.buttonAgendarReunionAsambleaGuardar.isEnabled = false
    }

    //region reunion agendada

    private fun precargarReunionAgendada() {
        traerViewModel()
            .traerReunionAgendadaLiveData()
            .observe(viewLifecycleOwner) {
                if (!it) return@observe
                deshabilitarBotones()
                mostrarNotificacionAgendamientoExitoso()
            }
    }

    private fun mostrarNotificacionAgendamientoExitoso() {
        mostrarDialogo(
            tipoDialogo = DialogoInformativo.TipoDialogo.INFORMATIVO,
            titulo = R.string.agendar_reunion,
            mensaje = R.string.se_agendo_exitosamente_la_reunion,
            accionAceptar = ::navegarAtras
        )
    }

    //endregion

    //endregion

    //endregion
}