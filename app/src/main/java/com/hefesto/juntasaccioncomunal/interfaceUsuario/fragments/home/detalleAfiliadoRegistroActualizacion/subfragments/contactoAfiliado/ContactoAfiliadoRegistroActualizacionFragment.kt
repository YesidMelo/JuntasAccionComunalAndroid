package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.detalleAfiliadoRegistroActualizacion.subfragments.contactoAfiliado

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hefesto.juntasaccioncomunal.databinding.FragmentContactoAfiliadoHomeBinding
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.detalleAfiliadoRegistroActualizacion.helpers.HelperSpinnerTiposTelefonoRegistroAfiliadoHome
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.detalleAfiliadoRegistroActualizacion.subfragments.ConfigurarInformacionParaCrearModeloRegistro
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores.NodosNavegacionFragments
import com.hefesto.juntasaccioncomunal.logica.modelos.home.registroAfiliado.ContactoParaRegistrarModel
import javax.inject.Inject

class ContactoAfiliadoRegistroActualizacionFragment :
    BaseFragment<ContactoAfiliadoRegistroActualizacionViewModel>(),
    ConfigurarInformacionParaCrearModeloRegistro<ContactoParaRegistrarModel>
{

    //region variables
    @Inject
    lateinit var contactoAfiliadoRegistroActualizacionViewModel : ContactoAfiliadoRegistroActualizacionViewModel

    @Inject
    lateinit var helperSpinnerTiposTelefonoRegistroAfiliadoHome: HelperSpinnerTiposTelefonoRegistroAfiliadoHome

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
        precargaVista()
        return binding.root
    }

    override fun armarModelo(): ContactoParaRegistrarModel {
        return traerViewModel()
            .conCorreo(correo = binding.editTextContactoAfiliadoHomeCorreo.text.toString())
            .conDireccion(direccion = binding.editTextContactoAfiliadoDireccion.text.toString())
            .conTelefono(telefono = binding.editTextContactoAfiliadoTelefono.text.toString())
            .conTipoTelefono(tipoTelefono = helperSpinnerTiposTelefonoRegistroAfiliadoHome.traerTipoTelefono())
            .traerObjetoArmado()
    }

    //region metodos privados
    //region precarga
    private fun precargaVista() {
        precargarSpinnerTiposTelefono()
    }

    private fun precargarSpinnerTiposTelefono() {
        traerViewModel()
            .traerListaTiposTelefono()
            .observe(viewLifecycleOwner){
                if (it == null) return@observe
                helperSpinnerTiposTelefonoRegistroAfiliadoHome
                    .conSpinner(spinner = binding.spinnerContactoAfiliadoTipoTelefono)
                    .conListaTiposTelefono(listaTiposTelefono = it)
                    .cargarSpinner()
            }
    }
    //endregion
    //endregion

}