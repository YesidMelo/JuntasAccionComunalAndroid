package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.login.registrarAfiliado

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.MutableLiveData
import com.hefesto.juntasaccioncomunal.R
import com.hefesto.juntasaccioncomunal.databinding.FragmentRegistroAfiliadoBinding
import com.hefesto.juntasaccioncomunal.logica.modelos.login.registrarAfiliado.AfiliadoARegistrarModel
import com.hefesto.juntasaccioncomunal.logica.modelos.login.registrarAfiliado.JACDisponibleParaAfiliadoModel
import com.hefesto.juntasaccioncomunal.logica.modelos.login.registrarAfiliado.TipoDocumentoModel
import com.hefesto.juntasaccioncomunal.logica.modelos.login.registrarAfiliado.TipoTelefonoModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.constantes.ConstantesFecha.EDAD_MINIMA_INSCRIPCION_JAC
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.FormatosFecha
import com.hefesto.juntasaccioncomunal.logica.utilidades.extenciones.convertirAFormato
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.dialogo.DialogoInformativo
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores.AccionesNavGrap
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores.NodosNavegacionFragments
import java.util.*
import javax.inject.Inject

class RegistrarAfiliadoFragment : BaseFragment<RegistrarAfiliadoFragmentViewModel>() {

    //region variables
    @Inject
    lateinit var viewModelFragment: RegistrarAfiliadoFragmentViewModel

    private lateinit var binding : FragmentRegistroAfiliadoBinding
    private val fechaNacimientoLiveData = MutableLiveData<Date?>()
    private var jacSeleccionada: JACDisponibleParaAfiliadoModel? = null
    private var tipoDocumentoModel: TipoDocumentoModel? = null
    private var tipoTelefonoModel: TipoTelefonoModel? = null
    //endregion

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegistroAfiliadoBinding.inflate(inflater)
        precargarElementosEnUI()
        ponerEscuchadorBotones()
        return binding.root
    }

    override fun traerViewModel(): RegistrarAfiliadoFragmentViewModel = viewModelFragment

    override fun traerNodoNavegacion(): NodosNavegacionFragments = NodosNavegacionFragments.REGISTRAR_AFILIADO

    //region metodos privados
    //region escuchadorBotones
    private fun ponerEscuchadorBotones() {
        ponerEscuchadorBotonVolver()
        ponerEscuchadorBotonRegistrarAfiliado()
    }

    private fun ponerEscuchadorBotonVolver() {
        binding.buttonRegistroAfiliadosVolver.setOnClickListener {
            navegacionAplicacion
                .navegar(
                    de = NodosNavegacionFragments.REGISTRAR_AFILIADO,
                    a = NodosNavegacionFragments.INICIAR_SESION,
                    accion = AccionesNavGrap.REGISTRAR_AFILIADO_A_INICIAR_SESION
                )
        }
    }

    private fun ponerEscuchadorBotonRegistrarAfiliado() {
        binding.buttonRegistrarAfiliado.setOnClickListener {
            mostrarAdvertenciaPreviaRegistro {
                funcionSegura(funcion = ::registrarAfiliado, aceptarFallo = ::habilitarBotones)
            }
        }
    }

    private fun registrarAfiliado() {
        binding.buttonRegistrarAfiliado.isEnabled = false
        binding.buttonRegistroAfiliadosVolver.isEnabled = false
        traerViewModel()
            .registrarAfiliado(traerInformacionAfiliadoDeLaVista())
            .observe(viewLifecycleOwner) {
                if(it == null) {
                    mostrarLoading()
                    return@observe
                }
                ocultarLoading()
                if (!it) {
                    habilitarBotones()
                    return@observe
                }
                mostrarDialogoRegistroExitoso()
            }
    }

    private fun habilitarBotones() {
        binding.buttonRegistrarAfiliado.isEnabled = true
        binding.buttonRegistroAfiliadosVolver.isEnabled = true
    }

    private fun traerInformacionAfiliadoDeLaVista() : AfiliadoARegistrarModel {
        return AfiliadoARegistrarModel(
            apellidos = binding.editTextInputRegistroApellidos.text?.toString(),
            jacSeleccionado = jacSeleccionada,
            contrasenia = binding.editTextContrasenia.text?.toString(),
            correo = binding.editTextCorreo.text?.toString(),
            direccion = binding.editTextDireccion.text?.toString(),
            fechaInscripcion = Date(),
            fechaNacimiento = fechaNacimientoLiveData.value,
            nombres = binding.editTextRegistroNombreAfiliado.text?.toString(),
            numeroDocumento = binding.editTextRegistroNumeroDocumento.text?.toString(),
            repetirContrasenia = binding.editTextRepetirContrasenia.text?.toString(),
            telefono = binding.editTextTelefono.text?.toString(),
            tipoDocumento = tipoDocumentoModel,
            tipoTelefono = tipoTelefonoModel
        )
    }


    //endregion

    //region precargaUI
    private fun precargarElementosEnUI() {
        limpiaCacheViewModel()
        ponerEscuchadorLoading()
        precargaJacsDisponibles()
        precargarTiposDocumento()
        precargarTiposTelefono()
        precargarFecha()
        ponerEscuchadorFecha()
    }

    private fun limpiaCacheViewModel() {
        funcionSegura(funcion = { traerViewModel().inicializarMapaDeCarga()})
    }

    private fun ponerEscuchadorLoading() {
        traerViewModel().cargaCompleta.observe(viewLifecycleOwner) {
            if (!it) {
                mostrarLoading()
                return@observe
            }
            ocultarLoading()
        }
    }


    private fun precargaJacsDisponibles() {
        funcionSegura ( funcion = {
            traerViewModel()
                .traerListaJacsRegistradas()
                .observe(viewLifecycleOwner) {
                    if(it == null) return@observe
                    val adapter = ArrayAdapter(context!!, android.R.layout.simple_list_item_1, it)
                    binding.autoCompleteTextViewJacsDisponibles.setAdapter(adapter)
                    traerViewModel().cargo(elementoCarga = RegistrarAfiliadoFragmentViewModel.ElementosCarga.JACS_DISPONIBLES)

                    binding.autoCompleteTextViewJacsDisponibles.setOnItemClickListener {
                        _, _, indexSelected, _ ->
                        jacSeleccionada = adapter.getItem(indexSelected)
                    }
                }
        })
    }

    private fun precargarTiposDocumento() {
        funcionSegura(funcion = {
            traerViewModel()
                .traerTiposDocumento()
                .observe(viewLifecycleOwner) {
                    val adapter = ArrayAdapter(context!!, android.R.layout.simple_list_item_1, it)
                    binding.spinnerRegistroTiposDocumeto.adapter = adapter
                    traerViewModel().cargo(elementoCarga = RegistrarAfiliadoFragmentViewModel.ElementosCarga.TIPOS_DOCUMENTO)
                    tipoDocumentoModel =  binding.spinnerRegistroTiposDocumeto.selectedItem as TipoDocumentoModel
                }
        })
    }

    private fun precargarTiposTelefono() {
        funcionSegura (funcion = {
            traerViewModel()
                .traerTiposTelefono()
                .observe(viewLifecycleOwner) {
                    val adapter = ArrayAdapter(context!!, android.R.layout.simple_list_item_1, it)
                    binding.spinnerRegistroTipoTelefono.adapter = adapter
                    traerViewModel().cargo(elementoCarga = RegistrarAfiliadoFragmentViewModel.ElementosCarga.TIPOS_TELEFONO)
                    tipoTelefonoModel = binding.spinnerRegistroTipoTelefono.selectedItem as TipoTelefonoModel
                }
        })
    }

    private fun precargarFecha() {
        fechaNacimientoLiveData.observe(viewLifecycleOwner) {
            if (it == null) return@observe
            binding.textViewRegistroAfiliadosSeleccionarFecha.text = it.convertirAFormato(FormatosFecha.SLASH_1)
        }
    }

    private fun ponerEscuchadorFecha() {
        binding.textViewRegistroAfiliadosSeleccionarFecha.setOnClickListener{
            val fechaMaximaSeleccion = Calendar.getInstance()
            fechaMaximaSeleccion.add(Calendar.YEAR, -EDAD_MINIMA_INSCRIPCION_JAC)
            mostrarDialogoCalendario(
                accionFechaSeleccionada = { fechaNacimientoLiveData.value = it },
                calendarFechaMaximaSeleccion = fechaMaximaSeleccion
            )
        }
    }

    //endregion

    //region dialogos
    private fun mostrarDialogoRegistroExitoso() {
        mostrarDialogo(
            tipoDialogo = DialogoInformativo.TipoDialogo.INFORMATIVO,
            titulo = R.string.registro_afiliado_jac,
            mensaje = R.string.registro_afiliado_exitoso,
            accionAceptar = {
                navegacionAplicacion.navegar(
                    de = NodosNavegacionFragments.REGISTRAR_AFILIADO,
                    a = NodosNavegacionFragments.INICIAR_SESION,
                    accion = AccionesNavGrap.REGISTRAR_AFILIADO_A_INICIAR_SESION
                )
            }
        )
    }

    private fun mostrarAdvertenciaPreviaRegistro(funcion : ()->Unit) {
        mostrarDialogo(
            tipoDialogo = DialogoInformativo.TipoDialogo.ADVERTENCIA,
            titulo = R.string.registro_afiliado_jac,
            mensaje = R.string.deseas_continuar_con_el_registro,
            accionAceptar = funcion,
            accionCancelar = ::habilitarBotones
        )
    }
    //endregion

    //endregion
}