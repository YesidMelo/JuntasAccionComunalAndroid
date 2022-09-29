package com.hefesto.juntasaccioncomunal.ui.fragments.login.iniciarSesion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hefesto.juntasaccioncomunal.databinding.FragmentIniciarSesionBinding
import com.hefesto.juntasaccioncomunal.ui.base.BaseFragment
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
        inflater: LayoutInflater?,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIniciarSesionBinding.inflate(inflater!!)
        return binding.root
    }

    override fun traerNodoNavegacion(): NodosNavegacionFragments = NodosNavegacionFragments.INICIAR_SESION

}