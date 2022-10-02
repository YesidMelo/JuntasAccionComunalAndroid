package com.hefesto.juntasaccioncomunal.ui.fragments.login.registrarJAC

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hefesto.juntasaccioncomunal.R
import com.hefesto.juntasaccioncomunal.databinding.FragmentRegistrarJacBinding
import com.hefesto.juntasaccioncomunal.logica.modelos.login.registrarJAC.JACRegistroModel
import com.hefesto.juntasaccioncomunal.ui.base.BaseFragment
import com.hefesto.juntasaccioncomunal.ui.navegacion.AccionesNavGrap
import com.hefesto.juntasaccioncomunal.ui.navegacion.NodosNavegacionFragments
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
        navegacionAplicacion.conIdNavGraph(R.id.nav_host_fragment_content_main)
        ponerEscuchadoresBotones()
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
        binding.buttonVolver.setOnClickListener {
            navegacionAplicacion.navegar(
                de = NodosNavegacionFragments.REGISTRAR_JAC,
                a = NodosNavegacionFragments.INICIAR_SESION,
                accion = AccionesNavGrap.REGISTRAR_JAC_A_INICIAR_SESION
            )
        }
    }
    private fun ponerEscuchadorBotonRegistrar() {
        binding.buttonRegistrar.setOnClickListener {
            funcionSegura {
                traerViewModel()
                    .registrarJAC(JACRegistroModel(
                        NombreJAC = binding.editTextNombreJAC.text?.toString(),
                        CodigoJAC = binding.editTextCodigoJAC.text?.toString(),
                        Correo = binding.editTextCorreo.text?.toString(),
                        Contrasenia = binding.editTextContrasenia.text?.toString(),
                        RepetirContrasenia = binding.editTextRepetirContrasenia.text?.toString()
                    ))
                    .observe(viewLifecycleOwner) {
                        if (it == null) {
                            mostrarLoading()
                            return@observe
                        }
                        ocultarProgress()
                        if (!it) return@observe
                        Log.e("Error", "Se ha registrado")
                    }
            }
        }
    }
    //endregion
}