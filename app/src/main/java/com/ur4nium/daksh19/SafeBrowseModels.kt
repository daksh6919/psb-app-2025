package com.ur4nium.daksh19

// --- Request Models ---

// The main request object we will send
data class SafeBrowseRequest(
    val client: ClientInfo,
    val threatInfo: ThreatInfo
)

// Describes our app
data class ClientInfo(
    val clientId: String,      // A unique name for your app, e.g., "com.ur4nium.daksh19"
    val clientVersion: String  // The version of your app, e.g., "1.0.0"
)

// Describes what we want to check
data class ThreatInfo(
    val threatTypes: List<String>,
    val platformTypes: List<String>,
    val threatEntries: List<ThreatEntry>
)

// The actual URL we are checking
data class ThreatEntry(
    val url: String
)


// --- Response Models ---

// The response object we get from Google
data class SafeBrowseResponse(
    val matches: List<ThreatMatch>? // This will be null or empty if the URL is safe
)

// Contains details if a threat is found
data class ThreatMatch(
    val threatType: String,
    val platformType: String,
    val threat: ThreatEntry
)