package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.login.registrarJAC

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hefesto.juntasaccioncomunal.BuildConfig
import com.hefesto.juntasaccioncomunal.R
import com.hefesto.juntasaccioncomunal.databinding.FragmentRegistrarJacBinding
import com.hefesto.juntasaccioncomunal.logica.modelos.login.registrarJAC.JACRegistroModel
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.dialogo.DialogoInformativo
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores.AccionesNavGrap
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores.NodosNavegacionFragments
import javax.inject.Inject

class RegistrarJACFragment : BaseFragment<RegistrarJACFragmentViewModel>() {

    //region variables
    //region Inyeccion
    @Inject lateinit var viewModelFragment : RegistrarJACFragmentViewModel
    //endregion

    //region bindings
    private lateinit var binding: FragmentRegistrarJacBinding
    //endregion
    //endregion

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegistrarJacBinding.inflate(inflater)
        ponerEscuchadoresBotones()
        ponerDefaultsDesarrollo()
        return binding.root
    }

    override fun traerViewModel(): RegistrarJACFragmentViewModel = viewModelFragment
    override fun traerNodoNavegacion(): NodosNavegacionFragments = NodosNavegacionFragments.REGISTRAR_JAC

    //region metodos privados
    private fun ponerEscuchadoresBotones() {
        ponerEscuchadoresBotonVolver()
        ponerEscuchadorBotonRegistrar()
    }

    private fun ponerEscuchadoresBotonVolver() {
        binding.buttonVolver.setOnClickListener { navegarAIniciarSesion()        }
    }

    private fun navegarAIniciarSesion() {
        navegacionAplicacion.navegar(
            de = NodosNavegacionFragments.REGISTRAR_JAC,
            a = NodosNavegacionFragments.INICIAR_SESION,
            accion = AccionesNavGrap.REGISTRAR_JAC_A_INICIAR_SESION
        )
    }

    private fun ponerEscuchadorBotonRegistrar() {
        binding.buttonRegistrar.setOnClickListener {
            mostrarAdvertenciaPreviaRegistro {
                binding.buttonRegistrar.isEnabled = false
                binding.buttonVolver.isEnabled = false
                funcionSegura( funcion = ::realizarRegistro, aceptarFallo = ::habilitarBotones )
            }
        }
    }

    private fun realizarRegistro() {
        traerViewModel()
            .registrarJAC(JACRegistroModel(
                NombreJAC = binding.editTextNombreJAC.text?.toString(),
                CodigoJAC = binding.editTextCodigoJAC.text?.toString(),
                Correo = binding.editTextCorreo.text?.toString(),
                Contrasenia = binding.editTextContrasenia.text?.toString(),
                RepetirContrasenia = binding.editTextRepetirContrasenia.text?.toString()
            ))
            .observe(viewLifecycleOwner) {
                if (it == null) { mostrarLoading(); return@observe; }
                ocultarLoading()
                if (!it) {
                    habilitarBotones()
                    return@observe
                }
                notificacionRegistroExitoso()
            }
    }

    private fun habilitarBotones() {
        binding.buttonRegistrar.isEnabled = true
        binding.buttonVolver.isEnabled = true
    }

    private fun notificacionRegistroExitoso() {
        mostrarDialogo(
            tipoDialogo = DialogoInformativo.TipoDialogo.INFORMATIVO,
            titulo = R.string.registro_jac,
            mensaje = R.string.registro_exitoso_jac,
            accionAceptar = ::navegarAIniciarSesion
        )
    }

    private fun mostrarAdvertenciaPreviaRegistro(funcion: ()->Unit) {
        mostrarDialogo(
            tipoDialogo = DialogoInformativo.TipoDialogo.ADVERTENCIA,
            titulo = R.string.registrar_jac,
            mensaje = R.string.deseas_continuar_con_el_registro,
            accionAceptar = funcion,
            accionCancelar = {
                binding.buttonRegistrar.isEnabled = true
                binding.buttonVolver.isEnabled = true
            }
        )
    }

    private fun ponerDefaultsDesarrollo() {
        if(!BuildConfig.PROBANDO_REGISTRO_JAC) return
        binding.editTextCorreo.setText(BuildConfig.CORREO_PRUEBAS)
        binding.editTextNombreJAC.setText(BuildConfig.NOMBRE_JAC)
        binding.editTextCodigoJAC.setText(BuildConfig.CODIGO_JAC)
        binding.editTextContrasenia.setText(BuildConfig.CONTRASENIA_PRUEBAS)
        binding.editTextRepetirContrasenia.setText(BuildConfig.REPETIR_CONSTRASENIA_JAC)
    }
    //endregion
}