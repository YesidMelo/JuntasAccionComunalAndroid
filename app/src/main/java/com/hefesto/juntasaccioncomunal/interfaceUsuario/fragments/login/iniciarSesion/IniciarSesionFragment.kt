package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.login.iniciarSesion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hefesto.juntasaccioncomunal.BuildConfig
import com.hefesto.juntasaccioncomunal.R
import com.hefesto.juntasaccioncomunal.databinding.FragmentIniciarSesionBinding
import com.hefesto.juntasaccioncomunal.logica.modelos.login.iniciarSesion.UsuarioInicioSesionModel
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.AccionesNavGrap
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.NodosNavegacionActividades
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.NodosNavegacionFragments
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
        ponerEscuchadorBotonRegistrarAfiliado()
    }

    private fun ponerEscuchadorBotonIniciarSesion() {
        binding.buttonIniciarSesion.setOnClickListener {
            funcionSegura( funcion =  {
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
                    ocultarLoading()
                    if (!it) return@observe
                    navegacionAplicacion.navegar(
                        de = NodosNavegacionActividades.LOGIN_ACTIVITY,
                        a = NodosNavegacionActividades.HOME_ACTIVITY
                    )
                }
            })
        }
    }

    private fun ponerEscuchadorBotonRegistrarJAC() {
        binding.textViewRegistrarJac.setOnClickListener{
            navegacionAplicacion.navegar(
                de = NodosNavegacionFragments.INICIAR_SESION,
                a= NodosNavegacionFragments.REGISTRAR_JAC,
                accion = AccionesNavGrap.INICIAR_SESION_A_REGISTRAR_JAC
            )
        }
    }

    private fun ponerEscuchadorBotonRegistrarAfiliado() {
        binding.textViewRegistrarAfiliado.setOnClickListener{
            navegacionAplicacion.navegar(
                de = NodosNavegacionFragments.INICIAR_SESION,
                a= NodosNavegacionFragments.REGISTRAR_AFILIADO,
                accion = AccionesNavGrap.INICIAR_SESION_A_REGISTRAR_AFILIADO
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