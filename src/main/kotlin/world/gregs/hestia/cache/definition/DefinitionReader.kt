package world.gregs.hestia.cache.definition

import org.displee.cache.index.Index
import world.gregs.hestia.io.BufferReader
import java.util.concurrent.ConcurrentHashMap

interface DefinitionReader<T : Definition> {

    val index: Index

    val cache: ConcurrentHashMap<Int, T>

    open val size: Int
        get() {
            val lastArchiveId = index.lastArchive.id
            return lastArchiveId * 256 + (index.getArchive(lastArchiveId).fileIds.size)
        }

    fun get(id: Int, member: Boolean = true): T = cache.getOrPut(id) { create(id, member) }

    fun create(id: Int, member: Boolean): T

    fun T.readData(archive: Int, id: Int, member: Boolean = true) {
        val data = index.getArchive(archive).getFile(id).data
        if (data != null) {
            readValueLoop(BufferReader(data), member)
        }
    }

    fun clear() {
        cache.clear()
    }
}