package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.detalleAfiliadoRegistroActualizacion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hefesto.juntasaccioncomunal.databinding.FragmentRegistroactualizacionAfiliadohomeBinding
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores.AccionesNavGrap
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores.NodosNavegacionFragments
import javax.inject.Inject

class DetalleAfiliadoRegistroActualizacionFragment : BaseFragment<DetalleAfiliadoRegistroActualizacionViewModel>() {

    //region variables
    @Inject
    lateinit var detalleAfiliadoRegistroActualizacionViewModel : DetalleAfiliadoRegistroActualizacionViewModel

    lateinit var binding: FragmentRegistroactualizacionAfiliadohomeBinding
    //endregion

    override fun traerViewModel(): DetalleAfiliadoRegistroActualizacionViewModel = detalleAfiliadoRegistroActualizacionViewModel

    override fun traerNodoNavegacion(): NodosNavegacionFragments = NodosNavegacionFragments.DETALLE_AFILIADO_REGISTRO_HOME

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegistroactualizacionAfiliadohomeBinding.inflate(inflater)
        configurarBotones()
        return binding.root
    }

    //region metodos privados
    //region configuracion botones
    private fun configurarBotones() {
        conEscuchadorAccionBotonAtras { navegarAtras() }
    }

    private fun navegarAtras() {
        navegacionAplicacion
            .navegar(
                de = traerNodoNavegacion(),
                a = NodosNavegacionFragments.REGISTRAR_AFILIADO_HOME,
                accion = AccionesNavGrap.DETALLE_AFILIADO_HOME_A_REGISTRAR_AFILIADO_HOME
            )
    }
    //endregion
    //endregion


}