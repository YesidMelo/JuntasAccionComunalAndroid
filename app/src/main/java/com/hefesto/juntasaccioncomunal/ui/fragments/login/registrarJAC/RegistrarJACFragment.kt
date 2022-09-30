package com.hefesto.juntasaccioncomunal.ui.fragments.login.registrarJAC

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hefesto.juntasaccioncomunal.databinding.FragmentRegistrarJacBinding
import com.hefesto.juntasaccioncomunal.ui.base.BaseFragment
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
        return binding.root
    }

    override fun traerViewModel(): RegistrarJACFragmentViewModel = viewModelFragment
    override fun traerNodoNavegacion(): NodosNavegacionFragments = NodosNavegacionFragments.REGISTRAR_JAC
}