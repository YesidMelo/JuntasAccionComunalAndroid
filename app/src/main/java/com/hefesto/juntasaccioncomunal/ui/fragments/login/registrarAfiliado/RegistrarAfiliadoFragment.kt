package com.hefesto.juntasaccioncomunal.ui.fragments.login.registrarAfiliado

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.hefesto.juntasaccioncomunal.R
import com.hefesto.juntasaccioncomunal.databinding.FragmentRegistroAfiliadoBinding
import com.hefesto.juntasaccioncomunal.logica.modelos.login.registrarAfiliado.AfiliadoARegistrarModel
import com.hefesto.juntasaccioncomunal.logica.modelos.login.registrarAfiliado.JACDisponibleParaAfiliadoModel
import com.hefesto.juntasaccioncomunal.ui.base.BaseFragment
import com.hefesto.juntasaccioncomunal.ui.navegacion.AccionesNavGrap
import com.hefesto.juntasaccioncomunal.ui.navegacion.NodosNavegacionFragments
import javax.inject.Inject

class RegistrarAfiliadoFragment : BaseFragment<RegistrarAfiliadoFragmentViewModel>() {

    //region variables
    @Inject
    lateinit var viewModelFragment: RegistrarAfiliadoFragmentViewModel

    private lateinit var binding : FragmentRegistroAfiliadoBinding
    //endregion

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegistroAfiliadoBinding.inflate(inflater)
        navegacionAplicacion.conIdNavGraph(R.id.nav_host_fragment_content_main)
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
            funcionSegura {
                traerViewModel()
                    .registrarAfiliado(traerInformacionAfiliadoDeLaVista())
                    .observe(viewLifecycleOwner) {
                        if(it == null) {
                            mostrarLoading()
                            return@observe
                        }
                        ocultarLoading()
                        if (!it) return@observe
                        Log.e("Error", "Se registro el afiliado")
                    }
            }
        }
    }

    private fun traerInformacionAfiliadoDeLaVista() : AfiliadoARegistrarModel {
        return AfiliadoARegistrarModel()
    }

    //endregion

    //region precargaUI
    private fun precargarElementosEnUI() {
        limpiaCacheViewModel()
        ponerEscuchadorLoading()
        precargaJacsDisponibles()
        precargarTiposDocumento()
        precargarTiposTelefono()
    }

    private fun limpiaCacheViewModel() {
        funcionSegura { traerViewModel().inicializarMapaDeCarga()}
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
        funcionSegura {
            traerViewModel()
                .traerListaJacsRegistradas()
                .observe(viewLifecycleOwner) {
                    if(it == null) return@observe
                    val adapter = ArrayAdapter(context!!, android.R.layout.simple_list_item_1, it)
                    binding.autoCompleteTextViewJacsDisponibles.setAdapter(adapter)
                    traerViewModel().cargo(elementoCarga = RegistrarAfiliadoFragmentViewModel.ElementosCarga.JACS_DISPONIBLES)
                }
        }
    }

    private fun precargarTiposDocumento() {
        funcionSegura {
            traerViewModel()
                .traerTiposDocumento()
                .observe(viewLifecycleOwner) {
                    val adapter = ArrayAdapter(context!!, android.R.layout.simple_list_item_1, it)
                    binding.spinnerRegistroTiposDocumeto.adapter = adapter
                    traerViewModel().cargo(elementoCarga = RegistrarAfiliadoFragmentViewModel.ElementosCarga.TIPOS_DOCUMENTO)
                }
        }
    }

    private fun precargarTiposTelefono() {
        funcionSegura {
            traerViewModel()
                .traerTiposTelefono()
                .observe(viewLifecycleOwner) {
                    val adapter = ArrayAdapter(context!!, android.R.layout.simple_list_item_1, it)
                    binding.spinnerRegistroTipoTelefono.adapter = adapter
                    traerViewModel().cargo(elementoCarga = RegistrarAfiliadoFragmentViewModel.ElementosCarga.TIPOS_TELEFONO)
                }
        }
    }

    //endregion
    //endregion
}