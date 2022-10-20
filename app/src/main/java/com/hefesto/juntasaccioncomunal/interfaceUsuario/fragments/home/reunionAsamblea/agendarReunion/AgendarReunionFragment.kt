package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.agendarReunion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hefesto.juntasaccioncomunal.databinding.FragmentAgendarReunionAsambleaBinding
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores.NodosNavegacionFragments
import javax.inject.Inject

class AgendarReunionFragment : BaseFragment<AgendarReunionViewModel> (){

    //region variables
    @Inject
    lateinit var agendarReunionViewModel: AgendarReunionViewModel
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
        configurarBotonAtras()
        return binding.root
    }

    //region metodos privados
    //region botones
    private fun configurarBotonAtras() {
        conEscuchadorAccionBotonAtras { navegarAtras() }
    }
    //endregion
    //endregion
}