package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.crearActa.subfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hefesto.juntasaccioncomunal.databinding.SubfragmentDetallepuntoreunionBinding
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.crearActa.CrearActaViewModel
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.crearActa.helper.HelperViewPagerPuntosCrearActa
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores.NodosNavegacionFragments
import javax.inject.Inject

class DetallePuntoSubfragment : BaseFragment<CrearActaViewModel>() {

    //region variables
    lateinit var crearActaViewModel: CrearActaViewModel
    private lateinit var binding: SubfragmentDetallepuntoreunionBinding
    //endregion

    override fun traerViewModel(): CrearActaViewModel = crearActaViewModel

    override fun traerNodoNavegacion(): NodosNavegacionFragments = NodosNavegacionFragments.DETALLE_PUNTO_REUNION

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SubfragmentDetallepuntoreunionBinding.inflate(inflater)
        return binding.root
    }
}