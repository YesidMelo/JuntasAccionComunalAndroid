package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.detalleAfiliadoRegistroActualizacion.subfragments.contactoAfiliado

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hefesto.juntasaccioncomunal.databinding.FragmentContactoAfiliadoHomeBinding
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores.NodosNavegacionFragments
import javax.inject.Inject

class ContactoAfiliadoRegistroActualizacionFragment : BaseFragment<ContactoAfiliadoRegistroActualizacionViewModel>() {

    //region variables
    @Inject
    lateinit var contactoAfiliadoRegistroActualizacionViewModel : ContactoAfiliadoRegistroActualizacionViewModel

    private lateinit var binding: FragmentContactoAfiliadoHomeBinding
    //endregion

    override fun traerViewModel(): ContactoAfiliadoRegistroActualizacionViewModel = contactoAfiliadoRegistroActualizacionViewModel

    override fun traerNodoNavegacion(): NodosNavegacionFragments = NodosNavegacionFragments.CONTACTO_AFILIADO_HOME

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContactoAfiliadoHomeBinding.inflate(inflater)
        return binding.root
    }

}