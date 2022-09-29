package com.hefesto.juntasaccioncomunal.ui.activities.splash

import androidx.lifecycle.ViewModel
import com.hefesto.juntasaccioncomunal.fuentesDatos.cache.MemoriaCache
import javax.inject.Inject

class SplashActivityViewModel  constructor(
    @Inject @JvmField var cache : MemoriaCache
) : ViewModel() {
}