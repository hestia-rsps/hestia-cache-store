package world.gregs.hestia.cache.definition.readers

import com.displee.cache.CacheLibrary
import world.gregs.hestia.cache.Indices.QUICK_CHAT_MENUS
import world.gregs.hestia.cache.Indices.QUICK_CHAT_MESSAGES
import world.gregs.hestia.cache.definition.DefinitionReader
import world.gregs.hestia.cache.definition.definitions.QuickChatOptionDefinition
import world.gregs.hestia.io.BufferReader
import java.util.concurrent.ConcurrentHashMap

class QuickChatOptionDefinitionReader(cacheStore: CacheLibrary) : DefinitionReader<QuickChatOptionDefinition> {

    override val index = cacheStore.index(QUICK_CHAT_MESSAGES)

    val secondIndex = cacheStore.index(QUICK_CHAT_MENUS)

    override val size: Int
        get() = ((index.last()?.id ?: 0) * 256 + (index.last()?.fileIds()?.size ?: 0)) + ((secondIndex.last()?.id ?: 0) * 256 + (secondIndex.last()?.fileIds()?.size ?: 0))

    override val cache = ConcurrentHashMap<Int, QuickChatOptionDefinition>()

    override fun create(id: Int, member: Boolean) = QuickChatOptionDefinition().apply {
        val data = if(id < 32768) {
            index.archive(0)?.file(id)?.data
        } else {
            secondIndex.archive(0)?.file(id and 0x7fff)?.data
        }

        if(data != null) {
            readValueLoop(BufferReader(data), member)
        }

        if(id >= 32768) {
            changeValues()
        }
    }
}