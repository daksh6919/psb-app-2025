package com.ur4nium.daksh19

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class SpamRepository {

    private val db = Firebase.firestore
    private val spamCollection = db.collection("reported_numbers")

    suspend fun checkIfSpam(phoneNumber: String): Boolean {
        // ... (this function is correct, no changes needed here)
        val docRef = spamCollection.document(phoneNumber)
        return try {
            val document = docRef.get().await()
            if (document != null && document.exists()) {
                val reportCount = document.getLong("reportcount") ?: 0
                reportCount >= 5
            } else {
                false
            }
        } catch (e: Exception) {
            false
        }
    }

    suspend fun reportNumber(phoneNumber: String) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: "anonymous_user"
        val docRef = spamCollection.document(phoneNumber)

        db.runTransaction { transaction ->
            val snapshot = transaction.get(docRef)

            if (!snapshot.exists()) {
                val data = hashMapOf(
                    "reportcount" to 1L,
                    "reporters" to listOf(userId),
                    "lastReportedAt" to FieldValue.serverTimestamp()
                )
                transaction.set(docRef, data)
            } else {
                val reporters = snapshot.get("reporters") as? List<String> ?: listOf()
                if (userId !in reporters) {
                    transaction.update(docRef, "reportcount", FieldValue.increment(1))
                    transaction.update(docRef, "reporters", FieldValue.arrayUnion(userId))
                    transaction.update(docRef, "lastReportedAt", FieldValue.serverTimestamp())
                } else {
                    // THIS IS THE FIX: An empty 'else' block to satisfy the compiler.
                    // It means "if the user already reported, do nothing."
                }
            }
        }.await()
    }
}