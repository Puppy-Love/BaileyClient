package com.min.bailey.client.app.service

import android.app.IntentService
import android.content.Intent
import com.jess.arms.utils.ArmsUtils
import com.min.bailey.client.app.data.api.Constant
import com.min.bailey.client.app.data.api.service.GankService
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import okhttp3.ResponseBody
import timber.log.Timber
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream


class DownloadService : IntentService("YangYanDownload") {
    lateinit var mInputSteam: InputStream
    lateinit var mOutputStream: OutputStream
    override fun onHandleIntent(intent: Intent) {
        val fileName = intent.getStringExtra("name") ?: "${System.currentTimeMillis()}.jpg"
        val downloadUrl = intent.getStringExtra("downloadUrl")
        if (downloadUrl.isNullOrEmpty()) {
            throw RuntimeException("连接地址不能为空")
        }
        val appComment = ArmsUtils.obtainAppComponentFromContext(applicationContext)
        appComment.repositoryManager().obtainRetrofitService(GankService::class.java)
                .download(downloadUrl)
                .subscribe(object : ErrorHandleSubscriber<ResponseBody>(appComment.rxErrorHandler()) {
                    override fun onNext(t: ResponseBody) {
                        //保存文件到sd卡
                        try {
                            val filePath = Constant.SPLASH_IMAGE_URL
                            val dir = File(filePath)
                            if (!dir.exists()) {
                                dir.mkdirs()
                            }
                            val file = File(filePath, fileName)
                            val byteArray = ByteArray(2048)
                            //val fileSize = t.contentLength()
                            mInputSteam = t.byteStream()
                            mOutputStream = FileOutputStream(file)
                            while (true) {
                                val read = mInputSteam.read(byteArray)
                                if (read == -1) break
                                mOutputStream.write(byteArray, 0, read)
                            }

                            mOutputStream.flush()


                        } catch (e: Exception) {
                            Timber.e(e)
                            throw RuntimeException(e.toString())
                        }
                    }

                })
    }

    override fun onDestroy() {
        super.onDestroy()
        mInputSteam.close()
        mOutputStream.close()
    }
}