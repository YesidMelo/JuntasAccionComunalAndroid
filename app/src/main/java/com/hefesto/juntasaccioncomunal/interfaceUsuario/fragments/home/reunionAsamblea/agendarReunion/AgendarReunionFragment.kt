package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.agendarReunion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hefesto.juntasaccioncomunal.databinding.FragmentAgendarReunionAsambleaBinding
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.agendarReunion.helpers.HelperSpinnerTiposReunion
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores.NodosNavegacionFragments
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.FormatosFecha
import com.hefesto.juntasaccioncomunal.logica.utilidades.extenciones.convertirAFormato
import java.util.*
import javax.inject.Inject

class AgendarReunionFragment : BaseFragment<AgendarReunionViewModel> (){

    //region variables
    @Inject
    lateinit var agendarReunionViewModel: AgendarReunionViewModel
    @Inject
    lateinit var helperSpinnerTiposReunion : HelperSpinnerTiposReunion

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
        configurarBotonAtras()
        configurarSeleccionFecha()
        configurarSeleccionHora()
        configurarAdicionarPunto()
        configurarBotonGuardar()
        configurarBotonCancelar()
    }

    private fun configurarBotonAtras() {
        conEscuchadorAccionBotonAtras { navegarAtras() }
    }

    private fun configurarSeleccionFecha() {
        binding.textViewAgendarReunionAsambleaFechaReunion.setOnClickListener {
            val fechaMinima = Calendar.getInstance()
            fechaMinima.add(Calendar.DAY_OF_MONTH, 1)
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
            traerViewModel().adicionarPuntoAReunion(adicionar = false)
        }
    }

    private fun configurarBotonCancelar() {
        binding.buttonAgendarReunionAsambleaCancelar.setOnClickListener {
            traerViewModel().adicionarPuntoAReunion(adicionar = false)
        }
    }
    //endregion

    //region precarga
    private fun precargarVista() {
        precargarSpinnerTipoReunion()
        precargarFechaReunionAsamblea()
        precargarHoraReunionAsamblea()
        precargarMostrarFormularioNuevoPunto()
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
    //endregion

    //endregion
}