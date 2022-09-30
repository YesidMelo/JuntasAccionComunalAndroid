package com.hefesto.juntasaccioncomunal.ui.navegacion

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import com.hefesto.juntasaccioncomunal.MiAplicacion
import com.hefesto.juntasaccioncomunal.ui.base.BaseActivity
import java.io.Serializable

class HelperActivities constructor(
    private val activity: BaseActivity<*>,
    private val de: NodosNavegacionActividades,
    private val a: NodosNavegacionActividades,
    private val listaPutExtra: List<Pair<String, Any?>>,
    private val parVistaTrancicion: Pair<String, View>? = null
) {

    //region variables
    private var intent: Intent? = null
    private var activityOptionsCompat : ActivityOptionsCompat? = null
    //endregion

    //region metodos publicos
    fun crearIntent() : HelperActivities {
        if (a.traerClaseActivity() == null) return this
        intent = Intent(activity as Context, a.traerClaseActivity())
        return this
    }

    fun crearPutExtras() : HelperActivities {
        if (listaPutExtra.isEmpty()) return this
        if(intent == null) return this
        for (actual in listaPutExtra) {
            if (actual.second  == null) continue
            if (actual.second is Int) { intent?.putExtra(actual.first, actual.second as Int); continue; }
            if (actual.second is IntArray) { intent?.putExtra(actual.first, actual.second as IntArray); continue; }
            if (actual.second is Double) { intent?.putExtra(actual.first, actual.second as Double); continue; }
            if (actual.second is DoubleArray) { intent?.putExtra(actual.first, actual.second as DoubleArray); continue; }
            if (actual.second is Boolean) { intent?.putExtra(actual.first, actual.second as Boolean); continue; }
            if (actual.second is BooleanArray) { intent?.putExtra(actual.first, actual.second as BooleanArray); continue; }
            if (actual.second is Char) { intent?.putExtra(actual.first, actual.second as Char); continue; }
            if (actual.second is CharArray) { intent?.putExtra(actual.first, actual.second as CharArray); continue; }
            if (actual.second is Float) { intent?.putExtra(actual.first, actual.second as Float); continue; }
            if (actual.second is FloatArray) { intent?.putExtra(actual.first, actual.second as FloatArray); continue; }
            if (actual.second is Long) { intent?.putExtra(actual.first, actual.second as Long); continue; }
            if (actual.second is LongArray) { intent?.putExtra(actual.first, actual.second as LongArray); continue; }
            if (actual.second is Short) { intent?.putExtra(actual.first, actual.second as Short); continue; }
            if (actual.second is ShortArray) { intent?.putExtra(actual.first, actual.second as ShortArray); continue; }
            if (actual.second is String) { intent?.putExtra(actual.first, actual.second as String); continue; }
            if (actual.second is Bundle) { intent?.putExtra(actual.first, actual.second as Bundle); continue; }
            if (actual.second is Parcelable) { intent?.putExtra(actual.first, actual.second as Parcelable); continue; }
            if (actual.second is Serializable) { intent?.putExtra(actual.first, actual.second as Serializable); continue; }
            if (actual.second is Array<*>) { intent?.putExtra(actual.first, actual.second as Array<*>); continue; }
        }
        return this
    }

    fun crearActivityOptionsCompat() : HelperActivities {
        if(parVistaTrancicion == null) return this
        activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, parVistaTrancicion.second, parVistaTrancicion.first)
        return this
    }

    fun navegarAActivity(){
        if(NavegacionNormal()) return
        activity.finish()
    }

    //endregion

    //region metodos privados
    private fun NavegacionNormal()  : Boolean {
        if (a.traerClaseActivity() == null) return false
        if(intent == null) return false
        if(activityOptionsCompat == null) {
            activity.startActivity(intent)
            return true
        }
        activity.startActivity(intent, activityOptionsCompat!!.toBundle())
        return true
    }
    //endregion

}