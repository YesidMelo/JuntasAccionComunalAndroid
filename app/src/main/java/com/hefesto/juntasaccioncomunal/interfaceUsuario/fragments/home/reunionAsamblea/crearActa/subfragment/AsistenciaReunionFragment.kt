package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.crearActa.subfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hefesto.juntasaccioncomunal.databinding.SubfragmentModificarlistaasistenciaBinding
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.crearActa.CrearActaViewModel
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores.NodosNavegacionFragments

class AsistenciaReunionFragment : BaseFragment<CrearActaViewModel>() {

    //region variables
    lateinit var crearActaViewModel: CrearActaViewModel
    private lateinit var binding : SubfragmentModificarlistaasistenciaBinding
    //endregion


    override fun traerViewModel(): CrearActaViewModel = crearActaViewModel

    override fun traerNodoNavegacion(): NodosNavegacionFragments = NodosNavegacionFragments.LISTA_ASISTENCIA_REUNION

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SubfragmentModificarlistaasistenciaBinding.inflate(inflater)
        return binding.root
    }
}