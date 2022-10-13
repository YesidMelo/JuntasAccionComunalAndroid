package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.detalleAfiliadoRegistroActualizacion.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerRegistroAfiliadoAdapter constructor(
    fragment: Fragment,
    val listaPasosRegistroFragment : List<Fragment>,
    val bundle: Bundle?= null
) : FragmentStateAdapter(fragment)
{

    override fun getItemCount(): Int = listaPasosRegistroFragment.size

    override fun createFragment(position: Int): Fragment {
        val fragmentSeleccionado = listaPasosRegistroFragment[position]
        fragmentSeleccionado.arguments = bundle
        return fragmentSeleccionado
    }

}