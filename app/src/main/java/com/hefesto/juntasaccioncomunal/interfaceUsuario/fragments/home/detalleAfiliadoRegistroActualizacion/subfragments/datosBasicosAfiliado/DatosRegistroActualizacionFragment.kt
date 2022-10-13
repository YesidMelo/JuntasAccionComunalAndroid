package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.detalleAfiliadoRegistroActualizacion.subfragments.datosBasicosAfiliado

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hefesto.juntasaccioncomunal.databinding.FragmentDatosRegistroactualizacionHomeBinding
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.detalleAfiliadoRegistroActualizacion.DetalleAfiliadoRegistroActualizacionFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.detalleAfiliadoRegistroActualizacion.helpers.HelperSpinnerTiposDocumentoRegistroAfiliadoHome
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores.NodosNavegacionFragments
import com.hefesto.juntasaccioncomunal.logica.modelos.home.DatosBasicosAfiliadoActualizarRegistrarInformacionModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.FormatosFecha
import com.hefesto.juntasaccioncomunal.logica.utilidades.extenciones.convertirAFormato
import javax.inject.Inject

class DatosRegistroActualizacionFragment : BaseFragment<DatosRegistroActualizacionViewModel>() {

    //region variables
    @Inject
    lateinit var datosRegistroActualizacionViewModel: DatosRegistroActualizacionViewModel
    @Inject
    lateinit var helperSpinnerTiposDocumentoRegistroAfiliadoHome: HelperSpinnerTiposDocumentoRegistroAfiliadoHome
    private lateinit var binding: FragmentDatosRegistroactualizacionHomeBinding
    //endregion

    override fun traerViewModel(): DatosRegistroActualizacionViewModel = datosRegistroActualizacionViewModel

    override fun traerNodoNavegacion(): NodosNavegacionFragments = NodosNavegacionFragments.DATOS_AFILIADO_HOME

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDatosRegistroactualizacionHomeBinding.inflate(inflater)
        precargarElementosVista()
        mostrarInformacionAfiliado()
        return binding.root
    }

    //region metodos privados

    //region precargar vista
    private fun precargarElementosVista() {
        precargarSpinnerTipoDocumento()
    }

    private fun precargarSpinnerTipoDocumento() {
        traerViewModel()
            .traerTiposDocumento()
            .observe(viewLifecycleOwner) {
                if (it == null) return@observe
                helperSpinnerTiposDocumentoRegistroAfiliadoHome
                    .conSpinner(spinner = binding.spinnerRegistroAfiliadoHomeTiposDocumento)
                    .conListaTiposDocumento(listaTiposDocumento = it)
                    .cargarSpinner()
            }
    }
    //endregion

    //region precarga info afiliado
    private fun mostrarInformacionAfiliado() {
        val usuario = (arguments?.get(DetalleAfiliadoRegistroActualizacionFragment.DETALLE_AFILIADO_ACTUALIZACION) as? DatosBasicosAfiliadoActualizarRegistrarInformacionModel)?:return
        ponerNombres(usuario = usuario)
        ponerApellidos(usuario = usuario)
        ponerFechaNacimiento(usuario = usuario)
        ponerTipoDocumento(usuario= usuario)
        ponerDocumento(usuario= usuario)
    }

    private fun ponerApellidos(usuario: DatosBasicosAfiliadoActualizarRegistrarInformacionModel) = binding.editTextDatosAfiliadoHomeApellidos.setText(usuario.apellidos)

    private fun ponerDocumento(usuario: DatosBasicosAfiliadoActualizarRegistrarInformacionModel) = binding.editTextDatosAfiliadosHomeNumeroDocumento.setText(usuario.documento)

    private fun ponerFechaNacimiento(usuario: DatosBasicosAfiliadoActualizarRegistrarInformacionModel) {
        binding.textViewDatosAfiliadoHomeFechaNacimiento.text = usuario.fechaNacimiento?.convertirAFormato(formato = FormatosFecha.SLASH_1)
    }

    private fun ponerNombres(usuario: DatosBasicosAfiliadoActualizarRegistrarInformacionModel) = binding.editTextDatosAfiliadoHomeNombre.setText(usuario.nombres)

    private fun ponerTipoDocumento(usuario: DatosBasicosAfiliadoActualizarRegistrarInformacionModel) {}
    //endregion

    //endregion

}