package com.jqorz.test.library.coil

import android.webkit.MimeTypeMap
import coil.bitmap.BitmapPool
import coil.decode.DataSource
import coil.decode.Options
import coil.fetch.FetchResult
import coil.fetch.Fetcher
import coil.fetch.SourceResult
import coil.size.Size
import okio.buffer
import okio.source
import java.io.File

/**
 * @author  jqorz
 * @since  2021/2/25
 */
class FileFetcher(private val addLastModifiedToFileCacheKey: Boolean) : Fetcher<File> {

    override fun key(data: File): String {
        return if (addLastModifiedToFileCacheKey) "${data.path}:${data.lastModified()}" else data.path
    }

    override suspend fun fetch(
            pool: BitmapPool,
            data: File,
            size: Size,
            options: Options
    ): FetchResult {
        return SourceResult(
                source = data.source().buffer(),
                mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(data.extension),
                dataSource = DataSource.DISK
        )
    }
}