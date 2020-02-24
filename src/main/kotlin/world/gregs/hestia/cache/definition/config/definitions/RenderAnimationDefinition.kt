package world.gregs.hestia.cache.definition.config.definitions

import world.gregs.hestia.cache.definition.Definition
import world.gregs.hestia.io.Reader

class RenderAnimationDefinition : Definition {
    var anIntArrayArray3249: Array<IntArray?>? = null
    var anInt3250: Int = 0
    var run: Int = -1
    var anInt3253: Int = -1
    var anIntArray3255: IntArray? = null
    var anInt3256: Int = -1
    var anInt3258 = 0
    var primaryIdle: Int = -1
    var anInt3260: Int = -1
    var anInt3261 = 0
    var anInt3262: Int = -1
    var anInt3263: Int = 0
    var anInt3266: Int = 0
    var aBoolean3267: Boolean = true
    var anInt3269: Int = -1
    var anInt3270: Int = -1
    var anInt3271 = -1
    var anInt3272: Int = 0
    var anIntArrayArray3273: Array<IntArray?>? = null
    var secondaryWalk: Int = -1
    var anInt3275: Int = -1
    var anIntArray3276: IntArray? = null
    var primaryWalk: Int = -1
    var anInt3278 = 0
    var anInt3281: Int = 0
    var anInt3282: Int = -1
    var anInt3283: Int = 0
    var anInt3284: Int = 0
    var anInt3285: Int = 0
    var walkBackwards: Int = -1
    var sideStepRight: Int = -1
    var anInt3289: Int = 0
    var anInt3290: Int = -1
    var anInt3291: Int = 0
    var anInt3292: Int = -1
    var anInt3293: Int = -1
    var anIntArray3294: IntArray? = null
    var anInt3297: Int = -1
    var anInt3298: Int = -1
    var turning: Int = -1
    var sideStepLeft: Int = -1
    var anIntArray3302: IntArray? = null
    var anInt3303: Int = -1
    var anInt3304: Int = -1
    var anInt3305: Int = -1

    override fun readValues(opcode: Int, buffer: Reader, member: Boolean) {
        when (opcode) {
            1 -> {
                primaryIdle = buffer.readShort()
                primaryWalk = buffer.readShort()
                if (primaryIdle == 65535) {
                    primaryIdle = -1
                }
                if (primaryWalk == 65535) {
                    primaryWalk = -1
                }
            }
            2 -> anInt3262 = buffer.readShort()
            3 -> anInt3297 = buffer.readShort()
            4 -> anInt3269 = buffer.readShort()
            5 -> anInt3304 = buffer.readShort()
            6 -> run = buffer.readShort()
            7 -> anInt3271 = buffer.readShort()
            8 -> anInt3270 = buffer.readShort()
            9 -> anInt3293 = buffer.readShort()
            26 -> {
                anInt3261 = (4 * buffer.readUnsignedByte()).toShort().toInt()
                anInt3266 = (4 * buffer.readUnsignedByte()).toShort().toInt()
            }
            27 -> {
                if (anIntArrayArray3273 == null) {
                    anIntArrayArray3273 = arrayOfNulls(defaultsSize)
                }
                val length = buffer.readUnsignedByte()
                anIntArrayArray3273!![length] = IntArray(6)
                repeat(5) { index ->
                    anIntArrayArray3273!![length]!![index] = buffer.readUnsignedShort()
                }
            }
            28 -> {
                val length = buffer.readUnsignedByte()
                anIntArray3276 = IntArray(length)
                repeat(length) { count ->
                    anIntArray3276!![count] = buffer.readUnsignedByte()
                    if (anIntArray3276!![count] == 255) {
                        anIntArray3276!![count] = -1
                    }
                }
            }
            29 -> anInt3258 = buffer.readUnsignedByte()
            30 -> anInt3283 = buffer.readShort()
            31 -> anInt3278 = buffer.readUnsignedByte()
            32 -> anInt3284 = buffer.readShort()
            33 -> anInt3250 = buffer.readUnsignedShort()
            34 -> anInt3272 = buffer.readUnsignedByte()
            35 -> anInt3289 = buffer.readShort()
            36 -> anInt3285 = buffer.readUnsignedShort()
            37 -> anInt3256 = buffer.readUnsignedByte()
            38 -> turning = buffer.readShort()
            39 -> secondaryWalk = buffer.readShort()
            40 -> walkBackwards = buffer.readShort()
            41 -> sideStepLeft = buffer.readShort()
            42 -> sideStepRight = buffer.readShort()
            43 -> anInt3290 = buffer.readShort()
            44 -> anInt3292 = buffer.readShort()
            45 -> anInt3303 = buffer.readShort()
            46 -> anInt3275 = buffer.readShort()
            47 -> anInt3260 = buffer.readShort()
            48 -> anInt3282 = buffer.readShort()
            49 -> anInt3253 = buffer.readShort()
            50 -> anInt3298 = buffer.readShort()
            51 -> anInt3305 = buffer.readShort()
            52 -> {
                val length = buffer.readUnsignedByte()
                anIntArray3294 = IntArray(length)
                anIntArray3302 = IntArray(length)
                repeat(length) { index ->
                    anIntArray3294!![index] = buffer.readShort()
                    val value = buffer.readUnsignedByte()
                    anIntArray3302!![index] = value
                    anInt3281 += value
                }
            }
            53 -> aBoolean3267 = false
            54 -> {
                anInt3263 = buffer.readUnsignedByte() shl 6
                anInt3291 = buffer.readUnsignedByte() shl 6
            }
            55 -> {
                if (anIntArray3255 == null) {
                    anIntArray3255 = IntArray(defaultsSize)
                }
                val index = buffer.readUnsignedByte()
                anIntArray3255!![index] = buffer.readShort()
            }
            56 -> {
                if (anIntArrayArray3249 == null) {
                    anIntArrayArray3249 = arrayOfNulls(defaultsSize)
                }
                val length = buffer.readUnsignedByte()
                anIntArrayArray3249!![length] = IntArray(3)
                repeat(2) { index ->
                    anIntArrayArray3249!![length]!![index] = buffer.readUnsignedShort()
                }
            }
        }
    }
    companion object {
        const val defaultsSize = 15//aClass281_3265.aClass363_3578.anIntArray4508.length
    }
}