package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.Afiliado.detalleAfiliadoActualizacion.subfragments.seguridadAfiliado

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hefesto.juntasaccioncomunal.databinding.FragmentSeguridadAfiliadoHomeBinding
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.Afiliado.detalleAfiliadoActualizacion.DetalleAfiliadoRegistroActualizacionFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.Afiliado.detalleAfiliadoActualizacion.subfragments.ConfigurarInformacionParaCrearModeloRegistro
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores.NodosNavegacionFragments
import com.hefesto.juntasaccioncomunal.logica.modelos.home.DatosBasicosAfiliadoActualizarRegistrarInformacionModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.registroAfiliado.SeguridadParaRegistroModel
import javax.inject.Inject

class SeguridadAfiliadoFragment :
    BaseFragment<SeguridadAfiliadoViewModel>(),
    ConfigurarInformacionParaCrearModeloRegistro<SeguridadParaRegistroModel>
{

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
        cargarCredencialesId()
        return binding.root
    }

    override fun armarModelo(): SeguridadParaRegistroModel {
        return traerViewModel()
            .conContrasenia(contrasenia = binding.editTextSeguridadAfiliadoContrasenia.text.toString())
            .conRepetirContrasenia(repetirContrasenia = binding.editTextSeguridadAfiliadoRepetirContrasenia.text.toString())
            .traerSeguridadParaRegistroModel()
    }

    //region metodos privados
    private fun cargarCredencialesId() {
        val usuario = (arguments?.get(DetalleAfiliadoRegistroActualizacionFragment.DETALLE_AFILIADO_ACTUALIZACION) as? DatosBasicosAfiliadoActualizarRegistrarInformacionModel)?: return
        traerViewModel()
            .conCredencialesSesionId(credencialesSesionId = usuario.credencialesSesion)
    }
    //endregion

}