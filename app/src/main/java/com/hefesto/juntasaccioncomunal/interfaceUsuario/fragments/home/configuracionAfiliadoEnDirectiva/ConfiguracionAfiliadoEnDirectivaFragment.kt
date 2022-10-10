package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.configuracionAfiliadoEnDirectiva

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hefesto.juntasaccioncomunal.databinding.FragmentConfiguracionAfiliadoModificacionDirectivaBinding
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.configuracionAfiliadoEnDirectiva.helpers.HelperSpinnerEstadosAfiliacionConfiguracionDirectivas
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.configuracionAfiliadoEnDirectiva.helpers.HelperSpinnerRolesAfiliacionConfiguracionDirectiva
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores.AccionesNavGrap
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores.NodosNavegacionFragments
import com.hefesto.juntasaccioncomunal.logica.modelos.home.AfiliadoModificacionDirectivaModel
import javax.inject.Inject

class ConfiguracionAfiliadoEnDirectivaFragment : BaseFragment<ConfiguracionAfiliadoEnDirectivaViewModel>() {

    //region variables
    @Inject
    lateinit var configuracionAfiliadoEnDirectivaViewModel: ConfiguracionAfiliadoEnDirectivaViewModel
    @Inject
    lateinit var helperSpinnerEstadosAfiliacionConfiguracionDirectivas: HelperSpinnerEstadosAfiliacionConfiguracionDirectivas
    @Inject
    lateinit var helperSpinnerRolesAfiliacionConfiguracionDirectiva : HelperSpinnerRolesAfiliacionConfiguracionDirectiva

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
        configuracionBotonAtras()
        configurarEscuchadorCarga()
        precargarDetalleAfiliado();
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

    private fun precargarDetalleAfiliado() {
        val afiliado  = (arguments?.get(DETALLE_AFILIADO_EN_DIRECTIVA) as? AfiliadoModificacionDirectivaModel)?:return
        precargarNombre(afiliado = afiliado)
        precargarEstadosAfiliadoEnDirectiva(afiliado = afiliado)
        precargarRolesApp(afiliado = afiliado)
    }

    private fun precargarNombre(afiliado: AfiliadoModificacionDirectivaModel) {
        binding.textViewNombresAfiliadoEnDirectivas.text = "${afiliado.nombres} ${afiliado.apellidos}"
    }

    private fun precargarEstadosAfiliadoEnDirectiva(afiliado: AfiliadoModificacionDirectivaModel) {
        configuracionAfiliadoEnDirectivaViewModel
            .traerListaEstadosAfiliado()
            .observe(viewLifecycleOwner) {
                helperSpinnerEstadosAfiliacionConfiguracionDirectivas
                    .conViewModel(viewModel = traerViewModel())
                    .conSpinner(spinner = binding.spinnerConfiguracionAfiliadoDirectivasEstadosAfiliacion)
                    .conListaEstadosAfiliadoEnDirectiva(listaEstadosAfiliado = it)
                    .conAfiliadoModificacionDirectivaModel(afiliado = afiliado)
                    .configurarSpinner()
            }
    }

    private fun precargarRolesApp(afiliado: AfiliadoModificacionDirectivaModel) {
        configuracionAfiliadoEnDirectivaViewModel
            .traerListaRolesApp()
            .observe(viewLifecycleOwner) {
                if (it == null) return@observe
                helperSpinnerRolesAfiliacionConfiguracionDirectiva
                    .conViewModel(viewModel = traerViewModel())
                    .conSpinner(spinner = binding.spinnerConfiguracionAfiliadoDirectivasRolesApp)
                    .conListaRolAppModel(it)
                    .conAfiliado(afiliado = afiliado)
                    .cargarSpinner()
            }
    }

    private fun configurarEscuchadorCarga() {
        traerViewModel()
            .cargo()
            .observe(viewLifecycleOwner) {
                if (!it) {
                    mostrarLoading()
                    return@observe
                }
                ocultarLoading()
            }
    }

    //enbregion

    companion object {
        const val DETALLE_AFILIADO_EN_DIRECTIVA = "Detalle afiliado en directiva"
    }
}