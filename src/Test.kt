// 2023.12.10 at 02:33:45 GMT
import java.io.BufferedInputStream
import java.io.File
import java.io.PrintWriter
import kotlin.system.measureTimeMillis
import java.util.TreeMap
import java.util.TreeSet
import kotlin.math.absoluteValue
import kotlin.random.Random
import kotlin.random.nextInt

// 1. Modded
fun Int.adjust(): Int {
    if (this >= p) {
        return this - p
    } else if (this < 0) {
        return this + p
    };return this
}

fun Int.snap(): Int {
    if (this >= p) {
        return this - p
    } else return this
}

infix fun Int.mm(b: Int): Int {
    return ((this.toLong() * b) % p).toInt()
}

infix fun Int.mp(b: Int): Int {
    val ans = this + b;return if (ans >= p) ans - p else ans
}

infix fun Int.ms(b: Int): Int {
    val ans = this - b;return if (ans < 0) ans + p else ans
}

fun Long.modded(): Int = (this % p).toInt().adjust()
fun Int.inverse(): Int = intPow(this, p - 2)
fun Int.additiveInverse(): Int = if (this == 0) 0 else p - this
infix fun Int.modDivide(b: Int): Int {
    return this mm (b.inverse())
}

fun intPow(x: Int, e: Int): Int {
    var x = x;
    var e = e;
    var ret = 1
    while (e > 0) {
        if (e and 1 == 1) ret = ret mm x
        x = x mm x
        e = e shr 1
    }
    return ret
}

// 2. DP initial values
const val plarge = 1_000_000_727
const val nlarge = -plarge
const val phuge = 2_727_000_000_000_000_000L
const val nhuge = -phuge

// 3. convenience conversions
val Boolean.chi: Int get() = if (this) 1 else 0 //characteristic function
val BooleanArray.chiarray: IntArray get() = IntArray(this.size) { this[it].chi }
val Char.code: Int get() = this.toInt() - 'a'.toInt()

//3. hard to write stuff
fun IntArray.put(i: Int, v: Int) {
    this[i] = (this[i] + v).adjust()
}

val mint: MutableList<Int> get() = mutableListOf<Int>()
val mong: MutableList<Long> get() = mutableListOf<Long>()
val mchar: MutableList<Char> get() = mutableListOf()
fun IntArray.minindex(): Int = this.indexOf(this.minOrNull()!!)
fun IntArray.maxindex(): Int = this.indexOf(this.maxOrNull()!!)

//4. more outputs
fun List<Char>.conca(): String = this.joinToString("")
val CharArray.conca: String get() = this.concatToString()
val IntArray.conca: String get() = this.joinToString(" ")

@JvmName("concaInt")
fun List<Int>.conca(): String = this.joinToString(" ")
val LongArray.conca: String get() = this.joinToString(" ")

@JvmName("concaLong")
fun List<Long>.conca(): String = this.joinToString(" ")

@JvmName("concaString")
fun List<String>.conca(): String = this.joinToString("")
//5. Pair of ints
typealias pii = Pair<Int, Int>
typealias pll = Pair<Long, Long> //Nb perm is the worst

fun order(a: Int, b: Int): Pair<Int, Int> = Pair(minOf(a, b), maxOf(a, b))

//6. strings
val String.size get() = this.length
const val randCount = 100

//7. bits
fun Int.has(i: Int): Boolean = (this and (1 shl i) != 0)
fun Long.has(i: Int): Boolean = (this and (1L shl i) != 0L)

//8 TIME
inline fun TIME(f: () -> Unit) {
    val t = measureTimeMillis() { f() }
    println("$t ms")
}

//9 rand
fun rand(x: Int) = Random.nextInt(x)
fun rand(x: IntRange) = Random.nextInt(x)
fun dist(a: Int, b: Int): Int = (a - b).absoluteValue
fun dist(a: Long, b: Long): Long = (a - b).absoluteValue
//10 typing issues, rename
typealias ints = IntArray
typealias longs = LongArray
typealias bools = BooleanArray

inline fun assert(x: Boolean, act: () -> Any = {}) {
    if (!x) error(act())
}

const val interactive = false

//Cannot line space beacuse IDE
object Reader {
    private const val BS = 1 shl 16
    private const val NC = 0.toChar()
    private val buf = ByteArray(BS)
    private var bId = 0
    private var size = 0
    private var c = NC
    var warningActive = true
    var fakein = StringBuilder()
    private var IN: BufferedInputStream = BufferedInputStream(System.`in`, BS)
    val OUT: PrintWriter = PrintWriter(System.out)
    private val char: Char
        get() {
            if (interactive) {
                return System.`in`.read().toChar()
            }
            while (bId == size) {
                size = IN.read(buf) // no need for checked exceptions
                if (size == -1) return NC
                bId = 0
            }
            return buf[bId++].toChar()
        }

    fun nextInt(): Int {
        var neg = false
        if (c == NC) c = char
        while (c < '0' || c > '9') {
            if (c == '-') neg = true
            c = char
        }
        var res = 0
        while (c in '0'..'9') {
            res = (res shl 3) + (res shl 1) + (c - '0')
            c = char
        }
        return if (neg) -res else res
    }

    fun nextLong(): Long {
        var neg = false
        if (c == NC) c = char
        while (c < '0' || c > '9') {
            if (c == '-') neg = true
            c = char
        }
        var res = 0L
        while (c in '0'..'9') {
            res = (res shl 3) + (res shl 1) + (c - '0')
            c = char
        }
        return if (neg) -res else res
    }

    fun nextString(): String {
        val ret = StringBuilder()
        while (true) {
            c = char
            if (!isWhitespace(c)) {
                break
            }
        }
        ret.append(c)
        while (true) {
            c = char
            if (isWhitespace(c)) {
                break
            }
            ret.append(c)
        }
        return ret.toString()
    }

    fun isWhitespace(c: Char): Boolean {
        return c == ' ' || c == '\n' || c == '\r' || c == '\t'
    }

    fun rerouteInput() {
        if (warningActive) {
            put("Custom test enabled")
            println("Custom test enabled")
            warningActive = false
        }
        val S = fakein.toString()
        println("New Case ")
        println(S.take(80))
        println("...")
        fakein.clear()
        IN = BufferedInputStream(S.byteInputStream(), BS)
    }

    fun flush() {
        OUT.flush()
    }

    fun takeFile(name: String) {
        IN = BufferedInputStream(File(name).inputStream(), BS)
    }
}

fun eat() {
    val st1 = TreeSet<Int>();
    val st2 = TreeMap<Int, Int>()
}

fun put(aa: Any) {
    Reader.OUT.println(aa)
    if (interactive) {
        Reader.flush()
    }
}

fun put(vararg x: Any) {
    for (c in x) {
        Reader.OUT.print(c)
        Reader.OUT.print(" ")
    }
    Reader.OUT.print("\n")
    if (interactive) {
        Reader.flush()
    }
}

fun done() {
    Reader.OUT.close()
}

fun share(aa: Any) {
    Reader.fakein.append(format(aa))
    Reader.fakein.append("\n")
}

val getintfast: Int get() = Reader.nextInt()
val getint: Int
    get() {
        val ans = getlong; if (ans > Int.MAX_VALUE) IntArray(1000000000); return ans.toInt()
    }
val getlong: Long get() = Reader.nextLong()
val getstr: String get() = Reader.nextString()
fun getline(n: Int): IntArray = IntArray(n) { getint }
fun getlineL(n: Int): LongArray = LongArray(n) { getlong }
fun subformat(a: Any?): String { // for not a collection
    return if (a == null) "null"
    else if (a is Iterable<*>) a.joinToString(" ")
    else if (a is BooleanArray) a.joinToString("") { if (it) "1" else "0" }
    else if (a is IntArray) a.joinToString(" ")
    else if (a is LongArray) a.joinToString(" ")
    else a.toString()
}

fun format(a: Any?): String {
    if (a == null) {
        return "null"
    } else if (a is BooleanArray) {
        return a.joinToString("") { if (it) "1" else "0" }
    } else if (a is Array<*>) {
        return "\n" + a.joinToString("\n") { subformat(it) }
    } else {
        return subformat(a)
    }
}

var dmark = -1
infix fun Any.dei(a: Any?) {
    dmark++; debug()
    println("<${dmark}>   ${this} : ${format(a)}")
}

const val just = " "

enum class solveMode {
    real, rand, tc
}

object solve {
    var mode: solveMode = solveMode.real
    var tcNum: Int = 0
    var rand: () -> Unit = {}
    var TC: MutableMap<Int, () -> Unit> = mutableMapOf()
    var tn: Long = 0
    fun cases(onecase: () -> Unit) {
        val t = if (mode == solveMode.real) {
            if (singleCase) 1 else getint
        } else if (mode == solveMode.tc) {
            1
        } else randCount
        if (p != 998_244_353 && p != 1_000_000_007) {
            throw AssertionError("Not usual primes!")
        }
        if (t == 1 && mode != solveMode.real) {
            tn = System.currentTimeMillis()
        }
        repeat(t) {
            if (mode == solveMode.tc) {
                TC[tcNum]?.let { it() }
                Reader.rerouteInput()
            } else if (mode == solveMode.rand) {
                rand()
                Reader.rerouteInput()
            }
            onecase()
        }
        if (t == 1 && mode != solveMode.real) {
            val dt = System.currentTimeMillis() - tn
            println("Time $dt ms ")
        }
    }

    fun rand(a: () -> Unit) {
        this.rand = a
    }

    fun tc(id: Int = 0, a: () -> Unit) {
        TC[id] = a
    }

    fun usetc(a: Int = 0) {
        this.tcNum = a
        this.mode = solveMode.tc
    }

    fun userand() {
        this.mode = solveMode.rand
    }
}

class rsq(arr: IntArray) {
    val n = arr.size
    val ps = LongArray(arr.size + 1)

    constructor(arr: List<Int>) : this(arr.toIntArray())

    init {
        for (i in 0 until arr.size) ps[i + 1] = ps[i] + arr[i]
    }

    fun sumQuery(l: Int, r: Int): Long {
        if (l > r || l >= n || r < 0) return 0L
        val ll = maxOf(l, 0)
        val rr = minOf(n - 1, r) + 1
        return ps[rr] - ps[ll]
    }
}

class rsqlong(arr: LongArray) {
    val n = arr.size
    val ps = LongArray(arr.size + 1)

    constructor(arr: List<Long>) : this(arr.toLongArray())

    init {
        for (i in 0 until arr.size) ps[i + 1] = ps[i] + arr[i]
    }

    fun sumQuery(l: Int, r: Int): Long {
        if (l > r || l >= n || r < 0) return 0L
        val ll = maxOf(l, 0)
        val rr = minOf(n - 1, r) + 1
        return ps[rr] - ps[ll]
    }
}

fun IntArray.torsq(): rsq = rsq(this)
fun LongArray.torsq(): rsqlong = rsqlong(this)
fun List<Int>.torsq(): rsq = rsq(this)
fun List<Long>.torsq(): rsqlong = rsqlong(this)
class rsqArrModded(val arr: IntArray) {
    val ps = IntArray(arr.size + 1)

    init {
        for (i in 0 until arr.size) {
            ps[i + 1] = ps[i] mp arr[i]
        }
    }

    fun sumQuery(l: Int, r: Int): Int {
        val ll = maxOf(l, 0)
        val rr = minOf(arr.lastIndex, r) + 1
        return ps[rr] ms ps[ll]
    }
}


inline fun BinarySearchFirstTrue(l: Int, r: Int, isTrue: (Int) -> Boolean): Int? {
    var L = l
    var R = r + 1
    while (L < R) {
        val m = (L + R) shr 1
        if (m == r + 1 || isTrue(m)) {
            R = m
        } else {
            L = m + 1
        }
    }
    return if (L == r + 1) null else L
}

inline fun BinarySearchLastTrue(l: Int, r: Int, isTrue: (Int) -> Boolean): Int? {
    var L = l - 1
    var R = r
    while (L < R) {
        val m = ((L + R) shr 1) + 1
        if (m == l - 1 || isTrue(m)) {
            L = m
        } else {
            R = m - 1
        }
    }
    return if (L == l - 1) null else L
}

inline fun BinarySearchFirstTrue(l: Long, r: Long, isTrue: (Long) -> Boolean): Long? {
    var L = l
    var R = r + 1
    while (L < R) {
        val m = (L + R) shr 1
        if (m == r + 1 || isTrue(m)) {
            R = m
        } else {
            L = m + 1
        }
    }
    return if (L == r + 1) null else L
}

inline fun BinarySearchLastTrue(l: Long, r: Long, isTrue: (Long) -> Boolean): Long? {
    var L = l - 1
    var R = r
    while (L < R) {
        val m = ((L + R) shr 1) + 1
        if (m == l - 1 || isTrue(m)) {
            L = m
        } else {
            R = m - 1
        }
    }
    return if (L == l - 1) null else L
}


fun debug() {}
class Solution2nd {
    fun countSubarrays(nums: IntArray, k: Int): Long {
        val max = nums.maxOrNull()!!
        val n = nums.size
        val that = ints(n) { (nums[it] == max).chi }

        val Q = that.torsq()
        var ret = 0L
        for (l in 0 until n) {
            val first = BinarySearchFirstTrue(l + 1, n) {
                Q.sumQuery(l, it - 1) >= k
            } ?: continue
            ret += n - first + 1
        }
        return ret
    }
}

const val p = 1_000_000_007
const val singleCase = true
fun main() {
    solve.cases {


    }
    done()
}

