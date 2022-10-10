package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.registrarAfiliado

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hefesto.juntasaccioncomunal.databinding.FragmentRegistrarAfiliadoHomeBinding
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores.AccionesNavGrap
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores.NodosNavegacionFragments
import javax.inject.Inject

class RegistrarAfiliadoHomeFragment : BaseFragment<RegistrarAfiliadoHomeViewModel>() {

    //region variables
    @Inject
    lateinit var registrarAfiliadoFragmentViewModel: RegistrarAfiliadoHomeViewModel

    private lateinit var binding: FragmentRegistrarAfiliadoHomeBinding
    //endregion

    override fun traerViewModel(): RegistrarAfiliadoHomeViewModel = registrarAfiliadoFragmentViewModel

    override fun traerNodoNavegacion(): NodosNavegacionFragments= NodosNavegacionFragments.REGISTRAR_AFILIADO_HOME

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegistrarAfiliadoHomeBinding.inflate(inflater)
        configurarBotones()
        return binding.root
    }

    //region metodos privados

    //region botones
    private fun configurarBotones() {
        conEscuchadorAccionBotonAtras { navegarAtras() }
    }

    private fun navegarAtras() {
        navegacionAplicacion
            .navegar(
                de = traerNodoNavegacion(),
                a = NodosNavegacionFragments.PANEL_CONTROL,
                accion = AccionesNavGrap.REGISTRAR_AFILIADO_HOME_A_PANEL_CONTROL
            )
    }
    //endregion

    //region precarga
    //endregion

    //endregion
}