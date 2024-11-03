package org.capstone_project.travelku.core.data.utils

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherProvider {
    val io: CoroutineDispatcher
    val main: CoroutineDispatcher
}

expect fun provideDispatcher(): DispatcherProvider