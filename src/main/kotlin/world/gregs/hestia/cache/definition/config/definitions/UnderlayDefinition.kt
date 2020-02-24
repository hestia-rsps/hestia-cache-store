package world.gregs.hestia.cache.definition.config.definitions

import world.gregs.hestia.cache.definition.Definition
import world.gregs.hestia.io.Reader

class UnderlayDefinition : Definition {
    var hue = 0
    var chroma = 0
    var texture = -1
    var aBoolean2892 = true
    var colour = 0
    var scale = 512
    var blockShadow = true
    var saturation = 0
    var lightness = 0

    override fun readValues(opcode: Int, buffer: Reader, member: Boolean) {
        when (opcode) {
            1 -> {
                colour = buffer.readMedium()
                computeHsl(colour)
            }
            2 -> {
                texture = buffer.readShort()
                if (texture == 65535) {
                    texture = -1
                }
            }
            3 -> scale = buffer.readShort() shl 2
            4 -> blockShadow = false
            5 -> aBoolean2892 = false
        }
    }

    private fun computeHsl(i: Int) {
        val r = (0xffab7a and i shr 16).toDouble() / 256.0
        val g = (i and 0xff74 shr 8).toDouble() / 256.0
        val b = (0xff and i).toDouble() / 256.0
        var maximum = r
        if (maximum > g) {
            maximum = g
        }
        if (maximum > b) {
            maximum = b
        }
        var minimum = r
        if (g > minimum) {
            minimum = g
        }
        if (b > minimum) {
            minimum = b
        }
        var h = 0.0
        var s = 0.0
        val l = (maximum + minimum) / 2.0
        if (maximum != minimum) {
            if (l < 0.5) {
                s = (minimum - maximum) / (maximum + minimum)
            }
            h = when {
                r == minimum -> (-b + g) / (-maximum + minimum)
                minimum == g -> (-r + b) / (minimum - maximum) + 2.0
                minimum == b -> 4.0 + (r - g) / (-maximum + minimum)
                else -> h
            }
            if (l >= 0.5) {
                s = (-maximum + minimum) / (2.0 - minimum - maximum)
            }
        }
        saturation = (256.0 * s).toInt()
        lightness = (l * 256.0).toInt()
        h /= 6.0
        chroma = if (l > 0.5) {
            (512.0 * ((1.0 - l) * s)).toInt()
        } else {
            (512.0 * (l * s)).toInt()
        }
        if (lightness < 0) {
            lightness = 0
        } else if (lightness > 255) {
            lightness = 255
        }
        if (saturation < 0) {
            saturation = 0
        } else if (saturation > 255) {
            saturation = 255
        }
        if (chroma < 1) {
            chroma = 1
        }
        hue = (h * chroma.toDouble()).toInt()
    }
}