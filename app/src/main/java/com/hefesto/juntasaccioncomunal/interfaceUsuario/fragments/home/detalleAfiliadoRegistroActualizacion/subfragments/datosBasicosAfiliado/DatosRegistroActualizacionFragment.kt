package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.detalleAfiliadoRegistroActualizacion.subfragments.datosBasicosAfiliado

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hefesto.juntasaccioncomunal.databinding.FragmentDatosRegistroactualizacionHomeBinding
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.detalleAfiliadoRegistroActualizacion.DetalleAfiliadoRegistroActualizacionFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.detalleAfiliadoRegistroActualizacion.helpers.HelperSpinnerTiposDocumentoRegistroAfiliadoHome
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.detalleAfiliadoRegistroActualizacion.subfragments.ConfigurarInformacionParaCrearModeloRegistro
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores.NodosNavegacionFragments
import com.hefesto.juntasaccioncomunal.logica.modelos.home.DatosBasicosAfiliadoActualizarRegistrarInformacionModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.registroAfiliado.DatosBasicosParaRegistrarModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.constantes.ConstantesFecha
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.FormatosFecha
import com.hefesto.juntasaccioncomunal.logica.utilidades.extenciones.convertirAFormato
import java.util.*
import javax.inject.Inject

class DatosRegistroActualizacionFragment : BaseFragment<DatosRegistroActualizacionViewModel>() ,
    ConfigurarInformacionParaCrearModeloRegistro<DatosBasicosParaRegistrarModel> {

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
        configurarBotones()
        return binding.root
    }

    override fun armarModelo(): DatosBasicosParaRegistrarModel
        = datosRegistroActualizacionViewModel
        .conNombres(nombres = binding.editTextDatosAfiliadoHomeNombre.text.toString())
        .conApellidos(apellidos = binding.editTextDatosAfiliadoHomeApellidos.text.toString())
        .conNumeroDocumento(numeroDocumento = binding.editTextDatosAfiliadosHomeNumeroDocumento.text.toString())
        .conTipoDocumento(tipoDocumento = helperSpinnerTiposDocumentoRegistroAfiliadoHome.traerTipoDocumentoSeleccionado())
        .crearModeloParaActualizarDatosBasicos()


    //region metodos privados

    //region precargar vista
    private fun precargarElementosVista() {
        val usuario = (arguments?.get(DetalleAfiliadoRegistroActualizacionFragment.DETALLE_AFILIADO_ACTUALIZACION) as? DatosBasicosAfiliadoActualizarRegistrarInformacionModel)
        precargarSpinnerTipoDocumento(afiliado = usuario)
        precargarInformacionAfiliado(afiliado= usuario)
    }

    private fun precargarSpinnerTipoDocumento(afiliado: DatosBasicosAfiliadoActualizarRegistrarInformacionModel?) {
        traerViewModel()
            .traerTiposDocumento()
            .observe(viewLifecycleOwner) {
                if (it == null) return@observe
                helperSpinnerTiposDocumentoRegistroAfiliadoHome
                    .conSpinner(spinner = binding.spinnerRegistroAfiliadoHomeTiposDocumento)
                    .conListaTiposDocumento(listaTiposDocumento = it)
                    .conDatosBasicosAfiliadoActualizarRegistroInformacionModel(datosBasicosAfiliadoActualizarRegistrarInformacionModel = afiliado)
                    .cargarSpinner()
            }
    }

    private fun precargarInformacionAfiliado(afiliado: DatosBasicosAfiliadoActualizarRegistrarInformacionModel?) {
        val usuario = afiliado?:return
        ponerNombres(afiliado = usuario)
        ponerApellidos(afiliado = usuario)
        ponerFechaNacimiento(afiliado = usuario)
        ponerDocumento(afiliado= usuario)
    }

    private fun ponerApellidos(afiliado: DatosBasicosAfiliadoActualizarRegistrarInformacionModel) = binding.editTextDatosAfiliadoHomeApellidos.setText(afiliado.apellidos)

    private fun ponerDocumento(afiliado: DatosBasicosAfiliadoActualizarRegistrarInformacionModel) = binding.editTextDatosAfiliadosHomeNumeroDocumento.setText(afiliado.documento)

    private fun ponerFechaNacimiento(afiliado: DatosBasicosAfiliadoActualizarRegistrarInformacionModel) {
        binding.textViewDatosAfiliadoHomeFechaNacimiento.text = afiliado.fechaNacimiento?.convertirAFormato(formato = FormatosFecha.SLASH_1)
    }

    private fun ponerNombres(afiliado: DatosBasicosAfiliadoActualizarRegistrarInformacionModel) = binding.editTextDatosAfiliadoHomeNombre.setText(afiliado.nombres)

    //endregion

    private fun configurarBotones() {
        binding
            .textViewDatosAfiliadoHomeFechaNacimiento
            .setOnClickListener {
                val fechaMaximaSeleccion = Calendar.getInstance()
                fechaMaximaSeleccion.add(Calendar.YEAR, -ConstantesFecha.EDAD_MINIMA_INSCRIPCION_JAC)
                mostrarDialogoCalendario(
                    accionFechaSeleccionada = {
                        traerViewModel().conFechaNacimiento(fechaNacimiento = it)
                        binding.textViewDatosAfiliadoHomeFechaNacimiento.text = it.convertirAFormato(formato = FormatosFecha.SLASH_1)
                    },
                    calendarFechaMaximaSeleccion = fechaMaximaSeleccion
                )
            }
    }

    //endregion

}