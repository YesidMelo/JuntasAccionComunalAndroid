package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.detalleAfiliadoRegistroActualizacion.subfragments.detalleEnJACAfiliado

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hefesto.juntasaccioncomunal.databinding.FragmentDetalleEnJacHomeBinding
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.detalleAfiliadoRegistroActualizacion.subfragments.ConfigurarInformacionParaCrearModeloRegistro
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores.NodosNavegacionFragments
import com.hefesto.juntasaccioncomunal.logica.modelos.home.registroAfiliado.DetalleEnJACParaRegistroModel
import javax.inject.Inject

class DetalleEnJacFragment :
    BaseFragment<DetalleEnJacViewModel>(),
    ConfigurarInformacionParaCrearModeloRegistro<DetalleEnJACParaRegistroModel>
{

    //region variables
    @Inject
    lateinit var detalleEnJacViewModel: DetalleEnJacViewModel
    private lateinit var binding : FragmentDetalleEnJacHomeBinding
    //endregion

    override fun traerViewModel(): DetalleEnJacViewModel = detalleEnJacViewModel

    override fun traerNodoNavegacion(): NodosNavegacionFragments = NodosNavegacionFragments.DETALLE_JAC_AFILIADO_HOME

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetalleEnJacHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun armarModelo(): DetalleEnJACParaRegistroModel {
        return DetalleEnJACParaRegistroModel()
    }

}