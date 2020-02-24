package world.gregs.hestia.cache.definition

import com.displee.cache.index.Index
import world.gregs.hestia.io.BufferReader
import java.util.concurrent.ConcurrentHashMap

interface DefinitionReader<T : Definition> {

    val index: Index

    val cache: ConcurrentHashMap<Int, T>

    open val size: Int
        get() {
            val lastArchiveId = index.last()?.id ?: 0
            return lastArchiveId * 256 + (index.archive(lastArchiveId)?.fileIds()?.size ?: 0)
        }

    fun get(id: Int, member: Boolean = true): T = cache.getOrPut(id) { create(id, member) }

    fun create(id: Int, member: Boolean): T

    fun T.readData(archive: Int, id: Int, member: Boolean = true) {
        val data = index.archive(archive)?.file(id)?.data
        if (data != null) {
            readValueLoop(BufferReader(data), member)
        }
    }

    fun clear() {
        cache.clear()
    }
}