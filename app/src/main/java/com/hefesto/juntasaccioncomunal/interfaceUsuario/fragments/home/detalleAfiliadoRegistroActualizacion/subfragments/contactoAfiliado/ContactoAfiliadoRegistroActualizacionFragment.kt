package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.detalleAfiliadoRegistroActualizacion.subfragments.contactoAfiliado

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import com.hefesto.juntasaccioncomunal.databinding.FragmentContactoAfiliadoHomeBinding
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.detalleAfiliadoRegistroActualizacion.DetalleAfiliadoRegistroActualizacionFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.detalleAfiliadoRegistroActualizacion.helpers.HelperSpinnerTiposTelefonoRegistroAfiliadoHome
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.detalleAfiliadoRegistroActualizacion.subfragments.ConfigurarInformacionParaCrearModeloRegistro
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores.NodosNavegacionFragments
import com.hefesto.juntasaccioncomunal.logica.modelos.home.DatosBasicosAfiliadoActualizarRegistrarInformacionModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.DatosContactoAfiliadoModel
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
        precargarContacto()
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

                pasarContactoAViewModel()
            }
    }
    //region precarga contacto
    private fun precargarContacto() {
        traerViewModel()
            .traerContactoParaRegistrarModel()
            .observe(viewLifecycleOwner) {
                if (it == null) return@observe
                cargarTipoTelefonoEnSpinner(datosContactoAfiliadoModel = it)
                cargarCorreo(datosContactoAfiliadoModel = it)
                cargarDireccion(datosContactoAfiliadoModel = it)
                cargarTelefono(datosContactoAfiliadoModel = it)
            }
    }

    private fun cargarTipoTelefonoEnSpinner(datosContactoAfiliadoModel: DatosContactoAfiliadoModel) {
        helperSpinnerTiposTelefonoRegistroAfiliadoHome
            .conTipoTelefonoSeleccionado(tipoTelefonoSeleccionado = datosContactoAfiliadoModel.tipoTelefono)
    }

    private fun cargarCorreo(datosContactoAfiliadoModel: DatosContactoAfiliadoModel) {
        binding.editTextContactoAfiliadoHomeCorreo.setText(datosContactoAfiliadoModel.correo)
    }

    private fun cargarDireccion(datosContactoAfiliadoModel: DatosContactoAfiliadoModel) {
        binding.editTextContactoAfiliadoDireccion.setText(datosContactoAfiliadoModel.direccion)
    }

    private fun cargarTelefono(datosContactoAfiliadoModel: DatosContactoAfiliadoModel) {
        binding.editTextContactoAfiliadoTelefono.setText(datosContactoAfiliadoModel.telefono)
    }
    //endregion

    private fun pasarContactoAViewModel() {
        val usuario = (arguments?.get(DetalleAfiliadoRegistroActualizacionFragment.DETALLE_AFILIADO_ACTUALIZACION) as? DatosBasicosAfiliadoActualizarRegistrarInformacionModel)?:return
        val contacto = usuario.datosContactoAfiliadoModel?:return
        traerViewModel().conDatosContactoAfiliadoModel(datosContactoAfiliadoModel = contacto)
    }
    //endregion
    //endregion

}