package com.vanced.manager.core.downloader

import android.content.Context
import com.vanced.manager.R
import com.vanced.manager.utils.*
import com.vanced.manager.utils.DownloadHelper.download
import com.vanced.manager.utils.PackageHelper.install

object MusicDownloader {

    private const val fileName = "youtubervx.apk"
    private const val folderName = "youtubervx"

    fun downloadMusic(context: Context) {
        val url = music.value?.string("url") ?: ""
        download(url, "$baseInstallUrl/", folderName, fileName, context, onDownloadComplete = {
            startMusicInstall(context)
        }, onError = {
            downloadingFile.postValue(context.getString(R.string.error_downloading, fileName))
        })

    }

    fun startMusicInstall(context: Context) {
        installing.postValue(true)
        postReset()
        install("${context.getExternalFilesDir(folderName)}/$fileName", context)
    }
}
