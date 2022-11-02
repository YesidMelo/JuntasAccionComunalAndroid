package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.Afiliado.configuracionAfiliadoEnDirectiva

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hefesto.juntasaccioncomunal.R
import com.hefesto.juntasaccioncomunal.databinding.FragmentConfiguracionAfiliadoModificacionDirectivaBinding
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.dialogo.DialogoInformativo
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.Afiliado.configuracionAfiliadoEnDirectiva.helpers.HelperSpinnerEstadosAfiliacionConfiguracionDirectivas
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.Afiliado.configuracionAfiliadoEnDirectiva.helpers.HelperSpinnerRolesAfiliacionConfiguracionDirectiva
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores.NodosNavegacionFragments
import com.hefesto.juntasaccioncomunal.logica.modelos.home.AfiliadoEnDirectivaModificadoModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.AfiliadoParaModificacionDirectivaModel
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

        configuracionBotones()
        configurarEscuchadorCarga()
        precargarDetalleAfiliado();
        return binding.root
    }

    //region metodos privados

    //region configuracion botones

    private fun configuracionBotones() {
        configuracionBotonAtras()
        configuracionBotonRegistrar()
    }

    private fun configuracionBotonAtras() {
        conEscuchadorAccionBotonAtras {
            navegarAtras()
        }
    }

    private fun configuracionBotonRegistrar() {
        binding.buttonActualizarRolDirectivas.setOnClickListener {
            mostrarDialogoConfirmacionRegistro()
        }
    }

    private fun mostrarDialogoConfirmacionRegistro() {
        mostrarDialogo(
            tipoDialogo = DialogoInformativo.TipoDialogo.ADVERTENCIA,
            titulo = R.string.actualizar_rol_en_directiva,
            mensaje = R.string.deseas_continuar_con_la_actualizacion_del_afiliado,
            accionAceptar =  ::registrarModificacion,
            accionCancelar = ::habilitarVistas
        )
    }

    private fun mostrarDialogoFinalizacionRegistro() {
        mostrarDialogo(
            tipoDialogo = DialogoInformativo.TipoDialogo.INFORMATIVO,
            titulo = R.string.actualizar_rol_en_directiva,
            mensaje = R.string.actualizacion_afiliado_exitosa,
            accionAceptar = ::navegarAtras
        )
    }

    private fun registrarModificacion() {
        val afiliado  = (arguments?.get(DETALLE_AFILIADO_EN_DIRECTIVA) as? AfiliadoParaModificacionDirectivaModel)?:return
        mostrarLoading()
        deshabilitarVistas()
        funcionSegura(funcion = {
            traerViewModel()
                .actualizarAfiliadoEnDirectiva(afiliadoEnDirectivaModificadoModel = crearAfiliadoModificado(afiliado = afiliado))
                .observe(viewLifecycleOwner) {
                    if (it == null) return@observe
                    ocultarLoading()
                    if(!it) return@observe
                    mostrarDialogoFinalizacionRegistro()
                }
        })
    }

    private fun crearAfiliadoModificado(afiliado: AfiliadoParaModificacionDirectivaModel) : AfiliadoEnDirectivaModificadoModel {
        return AfiliadoEnDirectivaModificadoModel(
            afiliadoId = afiliado.afiliadoId,
            rolApp = helperSpinnerRolesAfiliacionConfiguracionDirectiva.traerItemSeleccionado().rolesEnApp,
            estadoAfiliacion = helperSpinnerEstadosAfiliacionConfiguracionDirectivas.traerEstadoAfiliacionSeleccionado().estadoAfiliacion
        )
    }

    //endregion

    //region navegacion


    private fun habilitarVistas() {
        binding.buttonActualizarRolDirectivas.isEnabled = true
        binding.spinnerConfiguracionAfiliadoDirectivasRolesApp.isEnabled = true
        binding.spinnerConfiguracionAfiliadoDirectivasEstadosAfiliacion.isEnabled = true
    }

    private fun deshabilitarVistas() {
        binding.buttonActualizarRolDirectivas.isEnabled = false
        binding.spinnerConfiguracionAfiliadoDirectivasRolesApp.isEnabled = false
        binding.spinnerConfiguracionAfiliadoDirectivasEstadosAfiliacion.isEnabled = false
    }
    //endregion

    //region precarga
    private fun precargarDetalleAfiliado() {
        val afiliado  = (arguments?.get(DETALLE_AFILIADO_EN_DIRECTIVA) as? AfiliadoParaModificacionDirectivaModel)?:return
        precargarNombre(afiliado = afiliado)
        precargarEstadosAfiliadoEnDirectiva(afiliado = afiliado)
        precargarRolesApp(afiliado = afiliado)
    }

    private fun precargarNombre(afiliado: AfiliadoParaModificacionDirectivaModel) {
        binding.textViewNombresAfiliadoEnDirectivas.text = "${afiliado.nombres} ${afiliado.apellidos}"
    }

    private fun precargarEstadosAfiliadoEnDirectiva(afiliado: AfiliadoParaModificacionDirectivaModel) {
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

    private fun precargarRolesApp(afiliado: AfiliadoParaModificacionDirectivaModel) {
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

    //endregion

    //endregion

    companion object {
        const val DETALLE_AFILIADO_EN_DIRECTIVA = "Detalle afiliado en directiva"
    }
}