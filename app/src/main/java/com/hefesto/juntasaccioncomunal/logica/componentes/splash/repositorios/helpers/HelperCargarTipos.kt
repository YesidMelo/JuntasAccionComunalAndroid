package com.hefesto.juntasaccioncomunal.logica.componentes.splash.repositorios.helpers

import com.hefesto.juntasaccioncomunal.logica.componentes.splash.repositorios.helpers.cargarTipos.HelperCargarTiposDocumento
import com.hefesto.juntasaccioncomunal.logica.componentes.splash.repositorios.helpers.cargarTipos.HelperCargarTiposTelefono
import com.hefesto.juntasaccioncomunal.logica.componentes.splash.repositorios.helpers.cargarTipos.HelperCargarEstadoAfiliacion
import com.hefesto.juntasaccioncomunal.logica.componentes.splash.repositorios.helpers.cargarTipos.HelperCargarFuncionesApp
import com.hefesto.juntasaccioncomunal.logica.componentes.splash.repositorios.helpers.cargarTipos.HelperCargarRolAfiliacion
import com.hefesto.juntasaccioncomunal.logica.componentes.splash.repositorios.helpers.cargarTipos.HelperCargarRolesApp
import javax.inject.Inject

class HelperCargarTipos constructor(
    @JvmField @Inject var helperCargarEstadoAfiliacion : HelperCargarEstadoAfiliacion,
    @JvmField @Inject var helperCargarFuncionesApp: HelperCargarFuncionesApp,
    @JvmField @Inject var helperCargarRolAfiliacion: HelperCargarRolAfiliacion,
    @JvmField @Inject var helperCargarRolesApp: HelperCargarRolesApp,
    @JvmField @Inject var helperCargarTiposDocumento: HelperCargarTiposDocumento,
    @JvmField @Inject var helperCargarTiposTelefono: HelperCargarTiposTelefono
) {

    suspend fun cargarEstadosAfiliacion() = helperCargarEstadoAfiliacion.cargar()
    suspend fun cargarFuncionesApp() = helperCargarFuncionesApp.cargar()
    suspend fun cargarRolAfiliacion() = helperCargarRolAfiliacion.cargar()
    suspend fun cargarRolesApp() = helperCargarRolesApp.cargar()
    suspend fun cargarTiposDocumento() = helperCargarTiposDocumento.cargar()
    suspend fun cargarTiposTelefono() = helperCargarTiposTelefono.cargar()
}