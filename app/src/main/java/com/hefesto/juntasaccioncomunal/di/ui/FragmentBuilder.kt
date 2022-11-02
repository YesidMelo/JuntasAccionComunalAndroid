package com.hefesto.juntasaccioncomunal.di.ui

import com.hefesto.juntasaccioncomunal.di.ui.modules.HomeActivityModule
import com.hefesto.juntasaccioncomunal.di.ui.modules.LoginActivityModule
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.Afiliado.configuracionAfiliadoEnDirectiva.ConfiguracionAfiliadoEnDirectivaFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.Afiliado.detalleAfiliadoActualizacion.DetalleAfiliadoRegistroActualizacionFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.Afiliado.detalleAfiliadoActualizacion.subfragments.contactoAfiliado.ContactoAfiliadoRegistroActualizacionFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.Afiliado.detalleAfiliadoActualizacion.subfragments.datosBasicosAfiliado.DatosRegistroActualizacionFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.Afiliado.detalleAfiliadoActualizacion.subfragments.detalleEnJACAfiliado.DetalleEnJacFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.Afiliado.detalleAfiliadoActualizacion.subfragments.seguridadAfiliado.SeguridadAfiliadoFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.Afiliado.listaAfiliadosModificacionDirectivas.ListaAfiliadosModificacionDirectivasFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.panelControl.PanelControlFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.registrarAfiliado.RegistrarAfiliadoHomeFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.agendarReunion.AgendarReunionFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.crearActa.CrearActaFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.crearActa.subfragment.AsistenciaReunionFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.crearActa.subfragment.ComplementoActaFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.crearActa.subfragment.DetallePuntoSubfragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.crearActa.subfragment.PuntosSubfragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.generarActaPdf.ListaGenerarActaPdfFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.generarActaPdf.subfragments.DetalleActaWebFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.generarConvocatoriaReunionPdf.DetalleConvocatoriaFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.listaConvocatoriasReuniones.ListaConvocatoriasFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.listaReunionesCreacionActa.ListaReunionesCreacionActaFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.login.iniciarSesion.IniciarSesionFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.login.registrarAfiliado.RegistrarAfiliadoFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.login.registrarJAC.RegistrarJACFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {

    //region home

    //region afiliados
    @ContributesAndroidInjector(modules = [HomeActivityModule::class])
    abstract fun providesConfiguracionAfiliadoEnDirectivaFragment() : ConfiguracionAfiliadoEnDirectivaFragment

    @ContributesAndroidInjector(modules = [HomeActivityModule::class])
    abstract fun providesContactoAfiliadoRegistroActualizacionFragment() : ContactoAfiliadoRegistroActualizacionFragment

    @ContributesAndroidInjector(modules = [HomeActivityModule::class])
    abstract fun providesDatosRegistroActualizacionFragment() : DatosRegistroActualizacionFragment

    @ContributesAndroidInjector(modules = [HomeActivityModule::class])
    abstract fun providesDetalleAfiliadoRegistroActualizacionFragment() : DetalleAfiliadoRegistroActualizacionFragment

    @ContributesAndroidInjector(modules = [HomeActivityModule::class])
    abstract fun providesDetalleEnJacFragment() : DetalleEnJacFragment

    @ContributesAndroidInjector(modules = [HomeActivityModule::class])
    abstract fun providesListaAfiliadosModificacionDirectivaFragment() : ListaAfiliadosModificacionDirectivasFragment

    @ContributesAndroidInjector(modules = [HomeActivityModule::class])
    abstract fun providesRegistrarAfiliadoHomeFragment() : RegistrarAfiliadoHomeFragment

    @ContributesAndroidInjector(modules = [HomeActivityModule::class])
    abstract fun providesSeguridadAfiliadoFragment() : SeguridadAfiliadoFragment

    //endregion

    //region reuniones/asambleas
    @ContributesAndroidInjector(modules = [HomeActivityModule::class])
    abstract fun providesAgendarReunionFragment() : AgendarReunionFragment

    @ContributesAndroidInjector(modules = [HomeActivityModule::class])
    abstract fun providesListaReunionesCreacionActaFragment() : ListaReunionesCreacionActaFragment

    @ContributesAndroidInjector(modules = [HomeActivityModule::class])
    abstract fun providesCrearActaFragment() : CrearActaFragment

    @ContributesAndroidInjector(modules = [HomeActivityModule::class])
    abstract fun providesPuntoSubfragment() : PuntosSubfragment

    @ContributesAndroidInjector(modules = [HomeActivityModule::class])
    abstract fun providesDetallePuntoSubfragment() : DetallePuntoSubfragment

    @ContributesAndroidInjector(modules = [HomeActivityModule::class])
    abstract fun providesAsistenciaReunionFragment() : AsistenciaReunionFragment

    @ContributesAndroidInjector(modules = [HomeActivityModule::class])
    abstract fun providesListaGenerarActaPdfFragment() : ListaGenerarActaPdfFragment

    @ContributesAndroidInjector(modules = [HomeActivityModule::class])
    abstract fun providesDetalleActaWebFragment() : DetalleActaWebFragment

    @ContributesAndroidInjector(modules = [HomeActivityModule::class])
    abstract fun providesListaConvocatoriasFragment() : ListaConvocatoriasFragment

    @ContributesAndroidInjector(modules = [HomeActivityModule::class])
    abstract fun providesDetalleConvocatoriaFragment() : DetalleConvocatoriaFragment

    @ContributesAndroidInjector(modules = [HomeActivityModule::class])
    abstract fun providesComplementoActaFragment() : ComplementoActaFragment


    //endregion

    @ContributesAndroidInjector(modules = [HomeActivityModule::class])
    abstract fun providesPanelControlFragment() : PanelControlFragment

    //endregion

    //region login
    @ContributesAndroidInjector(modules = [LoginActivityModule::class])
    abstract fun contributeIniciarSesionFragment() : IniciarSesionFragment

    @ContributesAndroidInjector(modules = [LoginActivityModule::class])
    abstract fun contributeRegistrarJACFragment() : RegistrarJACFragment

    @ContributesAndroidInjector(modules = [LoginActivityModule::class])
    abstract fun contributeRegistrarAfiliadoFragment() : RegistrarAfiliadoFragment
    //endregion
}