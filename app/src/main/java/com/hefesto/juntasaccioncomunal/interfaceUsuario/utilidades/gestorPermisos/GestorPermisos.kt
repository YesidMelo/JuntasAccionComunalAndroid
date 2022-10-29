package com.hefesto.juntasaccioncomunal.interfaceUsuario.utilidades.gestorPermisos

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class GestorPermisos {

    //region variables
    private lateinit var activity : AppCompatActivity
    private val RequestCode = 200
    private lateinit var accionTieneTodosLosPermisos: ()-> Unit
    private lateinit var accionNoTieneTodosLosPermisos: ()-> Unit

    //endregion

    //region metodos publicos
    fun conActivity(activity : AppCompatActivity) : GestorPermisos {
        this.activity = activity
        return this
    }

    fun ejecutarAccionBasadoEnPermisos(
        vararg permiso: PermisosAplicacionEnum,
        accionTieneTodosLosPermisos: ()-> Unit,
        accionNoTieneTodosLosPermisos: ()-> Unit
    ) {
        this.accionTieneTodosLosPermisos = accionTieneTodosLosPermisos
        this.accionNoTieneTodosLosPermisos = accionNoTieneTodosLosPermisos
        val listaPermisosAutorizados = permiso.filter { return@filter noTienePermisos(permisoARevisar = it) }
        if (listaPermisosAutorizados.isEmpty()) {
            accionTieneTodosLosPermisos.invoke()
            return
        }
        solicitarPermisos(permiso = listaPermisosAutorizados.toTypedArray())
    }

    fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode != RequestCode) return
        if (grantResults.isEmpty()) return
        val listaPermisosDenegados = grantResults.filter { return@filter it == PackageManager.PERMISSION_DENIED }

        if (listaPermisosDenegados.isNotEmpty()) {
            accionNoTieneTodosLosPermisos.invoke()
            return
        }
        accionTieneTodosLosPermisos.invoke()
    }

    //endregion

    //region metodos privados

    private fun noTienePermisos(permisoARevisar: PermisosAplicacionEnum) : Boolean {
        val permisoInt = ContextCompat.checkSelfPermission(activity.applicationContext, permisoARevisar.traerIdentificadorManifiest())
        return permisoInt == PackageManager.PERMISSION_DENIED
    }

    private fun solicitarPermisos(vararg permiso: PermisosAplicacionEnum) {
        val listaPermisos = emptyList<String>().toMutableList()
        permiso.forEach { listaPermisos.add(it.traerIdentificadorManifiest()) }
        ActivityCompat.requestPermissions(activity, listaPermisos.toTypedArray(),RequestCode)
    }

    //endregion
}