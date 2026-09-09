import kotlin.text.Regex

// A simple User-Agent parser for demonstration purposes.
// In a real-world scenario, you'd use a more robust library.
object UserAgentParser {

    // Basic regex to capture browser name and version.
    // This is a simplified example and won't cover all edge cases.
    private val browserRegex = Regex("(.+?)(?:/([\\d.]+))?")

    // Basic regex to capture OS name and version.
    private val osRegex = Regex("(Windows|Mac OS X|Linux|Android|iOS)(?:.*?([\\d.]+))?")

    data class ParseResult(
        val browserName: String? = null,
        val browserVersion: String? = null,
        val osName: String? = null,
        val osVersion: String? = null
    )

    fun parse(userAgent: String): ParseResult {
        var browserName: String? = null
        var browserVersion: String? = null
        var osName: String? = null
        var osVersion: String? = null

        // Attempt to find browser information
        val browserMatch = browserRegex.find(userAgent)
        if (browserMatch != null) {
            browserName = browserMatch.groups[1]?.value?.trim()
            browserVersion = browserMatch.groups[2]?.value
        }

        // Attempt to find OS information
        val osMatch = osRegex.find(userAgent)
        if (osMatch != null) {
            osName = osMatch.groups[1]?.value?.trim()
            osVersion = osMatch.groups[2]?.value
        }

        return ParseResult(browserName, browserVersion, osName, osVersion)
    }
}

fun main() {
    val userAgentStrings = listOf(
        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36",
        "Mozilla/5.0 (iPhone; CPU iPhone OS 13_5 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.1.1 Mobile/15E148 Safari/604.1",
        "Mozilla/5.0 (Linux; Android 10; SM-G975F) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.106 Mobile Safari/537.36",
        "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36"
    )

    println("--- User Agent Parsing Demo ---")
    userAgentStrings.forEach {
        println("\nOriginal: $it")
        val result = UserAgentParser.parse(it)
        println("Parsed: Browser=${result.browserName} (${result.browserVersion}), OS=${result.osName} (${result.osVersion})")
    }
}
