package com.example.musicplayerclone.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.android.synthetic.*
import com.example.musicplayerclone.R
import com.example.musicplayerclone.exoplayer.MusicServiceConnection
import dagger.Provides
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponentManager::class)
object AppModule {

    @Singleton
    @Provides
    fun providesMusicServiceConnection(
            @ApplicationContext context: Context,
    )  = MusicServiceConnection(context)


    @Singleton
    @Provides
    fun provideGlideInstance (
            @ApplicationContext context: Context
    ) = Glide.with(context).setDefaultRequestOptions(
        RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
    )


}