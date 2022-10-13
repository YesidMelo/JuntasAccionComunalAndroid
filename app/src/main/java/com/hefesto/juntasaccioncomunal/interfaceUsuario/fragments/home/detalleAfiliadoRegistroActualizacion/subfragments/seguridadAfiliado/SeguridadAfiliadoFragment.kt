package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.detalleAfiliadoRegistroActualizacion.subfragments.seguridadAfiliado

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hefesto.juntasaccioncomunal.databinding.FragmentSeguridadAfiliadoHomeBinding
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores.NodosNavegacionFragments
import javax.inject.Inject

class SeguridadAfiliadoFragment : BaseFragment<SeguridadAfiliadoViewModel>() {

    //region variables
    @Inject
    lateinit var seguridadAfiliadoViewModel : SeguridadAfiliadoViewModel
    private lateinit var binding: FragmentSeguridadAfiliadoHomeBinding
    //endregion

    override fun traerViewModel(): SeguridadAfiliadoViewModel = seguridadAfiliadoViewModel

    override fun traerNodoNavegacion(): NodosNavegacionFragments = NodosNavegacionFragments.SEGURIDAD_AFILIADO_HOME

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSeguridadAfiliadoHomeBinding.inflate(inflater)
        return binding.root
    }
}