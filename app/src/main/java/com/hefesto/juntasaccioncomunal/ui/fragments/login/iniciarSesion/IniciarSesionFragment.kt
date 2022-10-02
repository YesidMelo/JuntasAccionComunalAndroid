package com.hefesto.juntasaccioncomunal.ui.fragments.login.iniciarSesion

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hefesto.juntasaccioncomunal.BuildConfig
import com.hefesto.juntasaccioncomunal.R
import com.hefesto.juntasaccioncomunal.databinding.FragmentIniciarSesionBinding
import com.hefesto.juntasaccioncomunal.logica.modelos.login.iniciarSesion.UsuarioInicioSesionModel
import com.hefesto.juntasaccioncomunal.ui.base.BaseFragment
import com.hefesto.juntasaccioncomunal.ui.navegacion.AccionesNavGrap
import com.hefesto.juntasaccioncomunal.ui.navegacion.NodosNavegacionFragments
import javax.inject.Inject

class IniciarSesionFragment : BaseFragment<IniciarSesionFragmentViewModel>() {

    //region variables

    //region inyecciones
    @Inject
    lateinit var viewModelFragment : IniciarSesionFragmentViewModel
    //endregion

    private lateinit var binding: FragmentIniciarSesionBinding

    //endregion

    override fun traerViewModel(): IniciarSesionFragmentViewModel = viewModelFragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIniciarSesionBinding.inflate(inflater)
        navegacionAplicacion.conIdNavGraph(R.id.nav_host_fragment_content_main)
        ponerEscuchadoresBotones()
        ponerDefaultsDesarrollo()
        return binding.root
    }

    override fun traerNodoNavegacion(): NodosNavegacionFragments = NodosNavegacionFragments.INICIAR_SESION

    //region metodos privados
    private fun ponerEscuchadoresBotones() {
        ponerEscuchadorBotonIniciarSesion()
        ponerEscuchadorBotonRegistrarJAC()
    }

    private fun ponerEscuchadorBotonIniciarSesion() {
        binding.buttonIniciarSesion.setOnClickListener {
            funcionSegura {
                viewModelFragment.iniciarSesion(
                    UsuarioInicioSesionModel(
                        correo = binding.textInputEmail.text?.toString(),
                        contrasenia = binding.textInputEdittextPassword.text?.toString()
                    )
                ).observe(viewLifecycleOwner) {
                    if(it == null) {
                        mostrarLoading()
                        return@observe
                    }
                    ocultarProgress()
                    if (!it) return@observe
                    Log.e("Error", "Inicio sesion")
                }
            }
        }
    }

    private fun ponerEscuchadorBotonRegistrarJAC() {
        binding.textViewRegistrarse.setOnClickListener{
            navegacionAplicacion.navegar(
                de = NodosNavegacionFragments.INICIAR_SESION,
                a= NodosNavegacionFragments.REGISTRAR_JAC,
                accion = AccionesNavGrap.INICIAR_SESION_A_REGISTRAR_JAC
            )
        }
    }

    private fun ponerDefaultsDesarrollo() {
        if(!BuildConfig.PROBANDO_INICIO_SESION) return
        binding.textInputEmail.setText(BuildConfig.CORREO_PRUEBAS)
        binding.textInputEdittextPassword.setText(BuildConfig.CONTRASENIA_PRUEBAS)
    }
    //endregion
}