package entities

data class ComplexNumber(val re: Double, val im: Double = 0.0)

fun ComplexNumber.toText() = "${this.re.format(5)} ${ if (this.im != 0.0) this.im.format(5, "+") + "i" else "" }"

fun Double.format(digits: Int, sign: String = "") = "%$sign.${digits}g".format(this)