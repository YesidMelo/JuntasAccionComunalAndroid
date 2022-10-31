package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.crearActa.subfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.hefesto.juntasaccioncomunal.databinding.SubfragmentComplemetoinformacionactaBinding
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.crearActa.CrearActaViewModel
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores.NodosNavegacionFragments
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.FormatosFecha
import com.hefesto.juntasaccioncomunal.logica.utilidades.extenciones.convertirAFormato

class ComplementoActaFragment : BaseFragment<CrearActaViewModel>() {

    //region variables
    var crearActaViewModel: CrearActaViewModel? = null
    lateinit var binding: SubfragmentComplemetoinformacionactaBinding
    //endregion

    override fun traerViewModel(): CrearActaViewModel = crearActaViewModel!!

    override fun traerNodoNavegacion(): NodosNavegacionFragments = NodosNavegacionFragments.COMPLETAR_INFORMACION_ACTA

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SubfragmentComplemetoinformacionactaBinding.inflate(inflater)
        configuracionBotones()
        configuracionEdittext()
        return binding.root
    }

    //region metodos privados

    //region botones
    private fun configuracionBotones() {
        configurarBotonHoraInicial()
        configuracionHoraFinal()
    }

    private fun configurarBotonHoraInicial() {
        binding.textViewComplementoActaDetalleHoraInicio.setOnClickListener {
            mostrarDialogoHora {
                binding.textViewComplementoActaDetalleHoraInicio.text = it.convertirAFormato(formato = FormatosFecha.HORA_MINUTO_12H)
                traerViewModel().traerDetalleReunionLiveData().value?.horaInicio = it
            }
        }
    }

    private fun configuracionHoraFinal() {
        binding.textViewComplementoActaDetalleHoraFinal.setOnClickListener {
            mostrarDialogoHora {
                binding.textViewComplementoActaDetalleHoraFinal.text = it.convertirAFormato(formato = FormatosFecha.HORA_MINUTO_12H)
                traerViewModel().traerDetalleReunionLiveData().value?.horaFin = it
            }
        }
    }
    //endregion

    //region edittext
    private fun configuracionEdittext() {
        binding.editTextNumberNumeroActa.addTextChangedListener {
            traerViewModel().traerDetalleReunionLiveData().value?.numeroActa = it?.toString()?.toIntOrNull()
        }
    }
    //endregion

    //endregion
}