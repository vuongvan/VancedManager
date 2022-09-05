package com.vanced.manager.core.downloader

import android.content.Context
import com.vanced.manager.R
import com.vanced.manager.utils.*
import com.vanced.manager.utils.DownloadHelper.download
import com.vanced.manager.utils.PackageHelper.install

object VancedDownloader {

    private const val fileName = "youtube.apk"
    private const val folderName = "youtube"

    fun downloadVanced(context: Context) {
        val url = vanced.value?.string("url") ?: ""
        download(url, "$baseInstallUrl/", folderName, fileName, context, onDownloadComplete = {
            startVancedInstall(context)
        }, onError = {
            downloadingFile.postValue(context.getString(R.string.error_downloading, fileName))
        })

    }

    fun startVancedInstall(context: Context) {
        installing.postValue(true)
        postReset()
        install("${context.getExternalFilesDir(folderName)}/$fileName", context)
    }
}
