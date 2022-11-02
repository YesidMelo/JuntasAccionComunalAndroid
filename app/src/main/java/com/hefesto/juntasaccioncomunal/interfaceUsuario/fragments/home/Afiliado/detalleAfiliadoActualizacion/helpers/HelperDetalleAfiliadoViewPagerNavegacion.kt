package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.Afiliado.detalleAfiliadoActualizacion.helpers

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.hefesto.juntasaccioncomunal.R
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.Afiliado.detalleAfiliadoActualizacion.DetalleAfiliadoRegistroActualizacionFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.Afiliado.detalleAfiliadoActualizacion.adapters.ViewPagerRegistroAfiliadoAdapter
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.Afiliado.detalleAfiliadoActualizacion.subfragments.ConfigurarInformacionParaCrearModeloRegistro
import com.hefesto.juntasaccioncomunal.logica.modelos.home.registroAfiliado.CompiladoInformacionAfiliadoParaRegistroModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.registroAfiliado.ContactoParaRegistrarModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.registroAfiliado.DatosBasicosParaRegistrarModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.registroAfiliado.DetalleEnJACParaRegistroModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.registroAfiliado.SeguridadParaRegistroModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HelperDetalleAfiliadoViewPagerNavegacion {

    //region
    private lateinit var tabLayout : TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var mapFragments: Map<Fragment, Int>
    private lateinit var detalleAfiliadoRegistroActualizacionFragment: DetalleAfiliadoRegistroActualizacionFragment
    private lateinit var viewPagerRegistroAfiliadoAdapter : ViewPagerRegistroAfiliadoAdapter
    private lateinit var botonSiguiente : Button
    private lateinit var botonVolver : Button
    private lateinit var escuchadorFinalizarRegistro : (CompiladoInformacionAfiliadoParaRegistroModel) -> Unit
    private var compiladoInformacionAfiliadoParaRegistroModel = CompiladoInformacionAfiliadoParaRegistroModel()

    private var bundle: Bundle? = null
    private val finalizoLaCarga = MutableLiveData<Boolean>()

    //endregion

    fun conTabLayout(tabLayout: TabLayout) : HelperDetalleAfiliadoViewPagerNavegacion {
        this.tabLayout = tabLayout
        return this
    }

    fun conViewPager(viewPager: ViewPager2) : HelperDetalleAfiliadoViewPagerNavegacion {
        this.viewPager = viewPager
        return this
    }

    fun conMapaFragmentos(mapFragments: Map<Fragment, Int>) : HelperDetalleAfiliadoViewPagerNavegacion {
        this.mapFragments = mapFragments
        return this
    }

    fun conDetalleAfiliadoRegistroActializacionFragment(detalleAfiliadoRegistroActualizacionFragment: DetalleAfiliadoRegistroActualizacionFragment) : HelperDetalleAfiliadoViewPagerNavegacion {
        this.detalleAfiliadoRegistroActualizacionFragment = detalleAfiliadoRegistroActualizacionFragment
        return this
    }

    fun conBundle(bundle: Bundle?) : HelperDetalleAfiliadoViewPagerNavegacion {
        this.bundle = bundle
        return this
    }

    fun conBotonSiguiente(botonSiguiente: Button) : HelperDetalleAfiliadoViewPagerNavegacion{
        this.botonSiguiente = botonSiguiente
        return this
    }

    fun conBotonVolver(botonVolver : Button) : HelperDetalleAfiliadoViewPagerNavegacion {
        this.botonVolver = botonVolver
        return this
    }

    fun conEscuchadorFinalizacionPaginas(escuchadorFinalizarRegistro : (CompiladoInformacionAfiliadoParaRegistroModel) -> Unit) : HelperDetalleAfiliadoViewPagerNavegacion {
        this.escuchadorFinalizarRegistro = escuchadorFinalizarRegistro
        return this
    }

    fun configurarPaginas() : MutableLiveData<Boolean> {
        configurarVisibilidaBotonVolver()
        GlobalScope.launch {
            finalizoLaCarga.postValue(false)
            configurarViewPager()
            configurarTabLayout()
            precargarPaginas()
            finalizoLaCarga.postValue(true)

        }
        return finalizoLaCarga
    }

    fun siguiente() {
        configurarAccionSiguiente()
        configurarVisibilidaBotonVolver()
        configurarTextoBotonSiguiente()
    }

    fun volver() {
        configurarAccionVolver()
        configurarVisibilidaBotonVolver()
        configurarTextoBotonSiguiente()
    }

    //region metodos privados

    //region tablayout
    private fun configurarTabLayout() {
        viewPager.post {
            TabLayoutMediator(tabLayout, viewPager){
                    tab, position ->
                tab.setText(mapFragments.values.toList()[position])
                tab.view.isClickable = false
            }.attach()
        }
    }
    //endregion

    //region viewPager
    private fun configurarViewPager() {
        viewPager.post {
            viewPagerRegistroAfiliadoAdapter = ViewPagerRegistroAfiliadoAdapter(
                fragment = detalleAfiliadoRegistroActualizacionFragment,
                listaPasosRegistroFragment = mapFragments.keys.toList(),
                bundle = bundle
            )
            viewPager.adapter = viewPagerRegistroAfiliadoAdapter
        }
    }

    //endregion

    //region botones

    //region boton siguiente
    private fun configurarAccionSiguiente() {
        if (viewPager.currentItem == mapFragments.size - 1) {
            generarModelosParaCompilado()
            escuchadorFinalizarRegistro.invoke(compiladoInformacionAfiliadoParaRegistroModel)
            return
        }
        viewPager.currentItem = viewPager.currentItem + 1
        limpiarObjetoParaCompilado()
    }

    private fun configurarTextoBotonSiguiente() {
        if (viewPager.currentItem == mapFragments.size - 1) {
            botonSiguiente.setText(R.string.finalizar_registro_afiliado)
            return
        }
        botonSiguiente.setText(R.string.siguiente)
    }
    //endregion

    //region boton volver
    private fun configurarAccionVolver() {
        if(viewPager.currentItem == 0) return
        viewPager.currentItem = viewPager.currentItem - 1
    }

    private fun configurarVisibilidaBotonVolver() {
        if (viewPager.currentItem == 0) {
            botonVolver.visibility = View.GONE
            return
        }
        botonVolver.visibility = View.VISIBLE
    }
    //endregion

    //endregion

    //region compilar modelo general
    private fun generarModelosParaCompilado() {
        compiladoInformacionAfiliadoParaRegistroModel = CompiladoInformacionAfiliadoParaRegistroModel(
            datosBasicosParaRegistrarModel = (mapFragments.keys.toList()[0] as ConfigurarInformacionParaCrearModeloRegistro<DatosBasicosParaRegistrarModel>).armarModelo(),
            contactoParaRegistrarModel = (mapFragments.keys.toList()[1] as ConfigurarInformacionParaCrearModeloRegistro<ContactoParaRegistrarModel>).armarModelo(),
            detalleEnJACParaRegistroModel = (mapFragments.keys.toList()[2] as ConfigurarInformacionParaCrearModeloRegistro<DetalleEnJACParaRegistroModel>).armarModelo(),
            seguridadParaRegistroModel = (mapFragments.keys.toList()[3] as ConfigurarInformacionParaCrearModeloRegistro<SeguridadParaRegistroModel>).armarModelo(),
        )
    }

    private fun limpiarObjetoParaCompilado() {
        compiladoInformacionAfiliadoParaRegistroModel = CompiladoInformacionAfiliadoParaRegistroModel()
    }

    private suspend fun precargarPaginas() {
        delay(1000)
        for (contador in 0 until mapFragments.size) {
            viewPager.post { viewPager.currentItem = contador }
            delay(1000)
        }
        viewPager.post { viewPager.currentItem = 0 }
        delay(1000)
    }
    //endregion

    //endregion
}