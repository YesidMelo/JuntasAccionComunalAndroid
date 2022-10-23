package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.generarActa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hefesto.juntasaccioncomunal.databinding.FragmentCrearactareunionBinding
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores.NodosNavegacionFragments
import javax.inject.Inject

class CrearActaReunionFragment :  BaseFragment<CrearActaReunionViewModel>() {

    //region variables
    @Inject
    lateinit var crearActaReunionViewModel : CrearActaReunionViewModel
    private lateinit var binding : FragmentCrearactareunionBinding
    //endregion

    override fun traerViewModel(): CrearActaReunionViewModel = crearActaReunionViewModel

    override fun traerNodoNavegacion(): NodosNavegacionFragments = NodosNavegacionFragments.CREAR_ACTA_REUNION_ASAMBLEA

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCrearactareunionBinding.inflate(inflater)
        configurarBotones()
        return binding.root
    }


    //region metodos privados

    //region botones
    private fun configurarBotones() {
        configurarBotonAtras()
    }

    private fun configurarBotonAtras() {
        conEscuchadorAccionBotonAtras { navegarAtras() }
    }
    //endregion
    //endregion

}