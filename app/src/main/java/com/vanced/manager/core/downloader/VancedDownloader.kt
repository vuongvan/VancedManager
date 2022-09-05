package com.vanced.manager.core.downloader

import android.content.Context
import com.vanced.manager.R
import com.vanced.manager.utils.*
import com.vanced.manager.utils.DownloadHelper.download
import com.vanced.manager.utils.PackageHelper.install

object MicrogDownloader {

    private const val fileName = "youtube.apk"
    private const val folderName = "youtube"

    fun downloadMicrog(context: Context) {
        val url = microg.value?.string("url") ?: ""
        download(url, "$baseInstallUrl/", folderName, fileName, context, onDownloadComplete = {
            startMicrogInstall(context)
        }, onError = {
            downloadingFile.postValue(context.getString(R.string.error_downloading, fileName))
        })

    }

    fun startMicrogInstall(context: Context) {
        installing.postValue(true)
        postReset()
        install("${context.getExternalFilesDir(folderName)}/$fileName", context)
    }
}
