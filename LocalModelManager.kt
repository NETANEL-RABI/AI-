package com.example.localai

import android.content.Context
import com.google.mediapipe.tasks.genai.llminference.LlmInference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalModelManager(private val context: Context) {

    private var llmInference: LlmInference? = null

    /**
     * אתחול המודל מקובץ המאוחסן במכשיר.
     * @param modelPath הנתיב המלא לקובץ המודל במכשיר (למשל: "/data/local/tmp/model.bin")
     */
    fun initModel(modelPath: String) {
        val options = LlmInference.LlmInferenceOptions.builder()
            .setModelPath(modelPath)
            .setMaxTokens(512) // אורך התשובה המרבי
            .build()

        llmInference = LlmInference.createFromOptions(context, options)
    }

    /**
     * הרצת המודל ברקע (כדי לא לתקוע את הממשק) וקבלת תשובה.
     */
    suspend fun generateResponse(prompt: String): String = withContext(Dispatchers.IO) {
        val model = llmInference ?: return@withContext "המודל אינו מועמס עדיין"
        
        try {
            return@withContext model.generateResponse(prompt)
        } catch (e: Exception) {
            return@withContext "שגיאה בהרצת המודל: ${e.localizedMessage}"
        }
    }
}
