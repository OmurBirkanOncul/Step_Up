package com.example.stepup.helpers



import com.google.firebase.firestore.FirebaseFirestore
import com.example.stepup.models.Task

object FirestoreHelper {
    private val db = FirebaseFirestore.getInstance()

    fun addTask(task: Task, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        db.collection("tasks")
            .document(task.id)
            .set(task)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onFailure(it) }
    }

    fun getTasks(onSuccess: (List<Task>) -> Unit, onFailure: (Exception) -> Unit) {
        db.collection("tasks")
            .get()
            .addOnSuccessListener { result ->
                val tasks = result.map { it.toObject(Task::class.java) }
                onSuccess(tasks)
            }
            .addOnFailureListener { onFailure(it) }
    }
}
