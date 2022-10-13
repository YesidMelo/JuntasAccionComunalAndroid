package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.detalleAfiliadoRegistroActualizacion.subfragments.datosBasicosAfiliado

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hefesto.juntasaccioncomunal.databinding.FragmentDatosRegistroactualizacionHomeBinding
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores.NodosNavegacionFragments
import javax.inject.Inject

class DatosRegistroActualizacionFragment : BaseFragment<DatosRegistroActualizacionViewModel>() {

    //region variables
    @Inject
    lateinit var datosRegistroActualizacionViewModel: DatosRegistroActualizacionViewModel
    private lateinit var binding: FragmentDatosRegistroactualizacionHomeBinding
    //endregion

    override fun traerViewModel(): DatosRegistroActualizacionViewModel = datosRegistroActualizacionViewModel

    override fun traerNodoNavegacion(): NodosNavegacionFragments = NodosNavegacionFragments.DATOS_AFILIADO_HOME

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDatosRegistroactualizacionHomeBinding.inflate(inflater)
        return binding.root
    }

}