package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.configuracionAfiliadoEnDirectiva

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hefesto.juntasaccioncomunal.databinding.FragmentConfiguracionAfiliadoModificacionDirectivaBinding
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores.AccionesNavGrap
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores.NodosNavegacionFragments
import javax.inject.Inject

class ConfiguracionAfiliadoEnDirectivaFragment : BaseFragment<ConfiguracionAfiliadoEnDirectivaViewModel>() {

    //region variables
    @Inject
    lateinit var configuracionAfiliadoEnDirectivaViewModel: ConfiguracionAfiliadoEnDirectivaViewModel
    lateinit var binding: FragmentConfiguracionAfiliadoModificacionDirectivaBinding
    //endregion

    override fun traerViewModel(): ConfiguracionAfiliadoEnDirectivaViewModel = configuracionAfiliadoEnDirectivaViewModel

    override fun traerNodoNavegacion(): NodosNavegacionFragments = NodosNavegacionFragments.CONFIGURACION_AFILIADO_EN_DIRECTIVA

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentConfiguracionAfiliadoModificacionDirectivaBinding.inflate(inflater)
        val afiliado = arguments?.get(DETALLE_AFILIADO_EN_DIRECTIVA)
        configuracionBotonAtras()
        return binding.root
    }

    //region metodos privados
    private fun configuracionBotonAtras() {
        conEscuchadorAccionBotonAtras {
            navegacionAplicacion.navegar(
                a = NodosNavegacionFragments.LISTA_AFILIADOS_MODIFICACION_DIRECTIVAS,
                de = NodosNavegacionFragments.CONFIGURACION_AFILIADO_EN_DIRECTIVA,
                accion = AccionesNavGrap.CONFIGURACION_AFILIADO_EN_DIRECTIVA_A_LISTA_AFILIADOS_MODIFICACION_DIRECTIVA
            )
        }
    }
    //enbregion

    companion object {
        const val DETALLE_AFILIADO_EN_DIRECTIVA = "Detalle afiliado en directiva"
    }
}