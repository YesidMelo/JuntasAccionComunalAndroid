package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.detalleAfiliadoRegistroActualizacion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hefesto.juntasaccioncomunal.R
import com.hefesto.juntasaccioncomunal.databinding.FragmentRegistroactualizacionAfiliadohomeBinding
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.detalleAfiliadoRegistroActualizacion.helpers.HelperDetalleAfiliadoViewPagerNavegacion
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.detalleAfiliadoRegistroActualizacion.subfragments.contactoAfiliado.ContactoAfiliadoRegistroActualizacionFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.detalleAfiliadoRegistroActualizacion.subfragments.datosBasicosAfiliado.DatosRegistroActualizacionFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.detalleAfiliadoRegistroActualizacion.subfragments.detalleEnJACAfiliado.DetalleEnJacFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.detalleAfiliadoRegistroActualizacion.subfragments.seguridadAfiliado.SeguridadAfiliadoFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores.AccionesNavGrap
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores.NodosNavegacionFragments
import com.hefesto.juntasaccioncomunal.logica.modelos.home.DatosBasicosAfiliadoActualizarRegistrarInformacionModel
import javax.inject.Inject

class DetalleAfiliadoRegistroActualizacionFragment : BaseFragment<DetalleAfiliadoRegistroActualizacionViewModel>() {

    //region variables
    @Inject
    lateinit var detalleAfiliadoRegistroActualizacionViewModel : DetalleAfiliadoRegistroActualizacionViewModel

    @Inject
    lateinit var helperDetalleAfiliadoViewPagerNavegacion : HelperDetalleAfiliadoViewPagerNavegacion

    lateinit var binding: FragmentRegistroactualizacionAfiliadohomeBinding
    //endregion

    override fun traerViewModel(): DetalleAfiliadoRegistroActualizacionViewModel = detalleAfiliadoRegistroActualizacionViewModel

    override fun traerNodoNavegacion(): NodosNavegacionFragments = NodosNavegacionFragments.DETALLE_AFILIADO_REGISTRO_HOME

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegistroactualizacionAfiliadohomeBinding.inflate(inflater)
        configurarBotones()
        configurarPaginas()
        return binding.root
    }

    //region metodos privados
    //region configuracion botones
    private fun configurarBotones() {
        conEscuchadorAccionBotonAtras { navegarAtras() }
        configuracionEscuchadorBotonSiguiente()
        configuracionEscuchadorBotonVolver()
    }

    private fun configuracionEscuchadorBotonSiguiente() {
        binding.buttonRegistroAfiliadosSiguiente.setOnClickListener {
            helperDetalleAfiliadoViewPagerNavegacion
                .siguiente()
        }
    }

    private fun configuracionEscuchadorBotonVolver() {
        binding.buttonRegistroAfiliadoVolver.setOnClickListener {
            helperDetalleAfiliadoViewPagerNavegacion
                .volver()
        }
    }

    //endregion
    //region configuracion fragments
    private fun configurarPaginas() {

        helperDetalleAfiliadoViewPagerNavegacion
            .conTabLayout(tabLayout = binding.tabLayoutRegistroAfiliadoVistasDisponibles)
            .conViewPager(viewPager = binding.viewPagerRegistroActualizacionAfiliadoFormularios)
            .conDetalleAfiliadoRegistroActializacionFragment(detalleAfiliadoRegistroActualizacionFragment = this)
            .conMapaFragmentos(mapFragments = mapOf(
                Pair(first = DatosRegistroActualizacionFragment(), second = R.string.datos_afiliado),
                Pair(first = ContactoAfiliadoRegistroActualizacionFragment(), second = R.string.contacto_afiliado),
                Pair(first = DetalleEnJacFragment(), second = R.string.detalle_en_jac),
                Pair(first = SeguridadAfiliadoFragment(), second = R.string.seguridad_afiliado),
            ))
            .conBundle(bundle = configurarBundle())
            .conBotonSiguiente(botonSiguiente = binding.buttonRegistroAfiliadosSiguiente)
            .conBotonVolver(botonVolver = binding.buttonRegistroAfiliadoVolver)
            .configurarPaginas()
            .observe(viewLifecycleOwner) {
                if (!it) {
                    mostrarLoading()
                    return@observe
                }
                ocultarLoading()
            }
    }

    private fun configurarBundle() : Bundle? {
        val objeto = (arguments?.get(DETALLE_AFILIADO_ACTUALIZACION) as? DatosBasicosAfiliadoActualizarRegistrarInformacionModel)?:return null
        return Bundle().apply {
            putSerializable(DETALLE_AFILIADO_ACTUALIZACION, objeto)
        }
    }
    //endregion
    //endregion

    companion object {
        const val DETALLE_AFILIADO_ACTUALIZACION = "DetalleAfiliadoActualizacion"
    }
}