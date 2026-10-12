import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.net.URL

suspend fun getOrDownloadModel(context: Context, modelUrl: String): File {
    val modelFile = File(context.filesDir, "model.bin")
    
    // אם הקובץ כבר קיים במכשיר, נחזיר אותו מיד
    if (modelFile.exists() && modelFile.length() > 0) {
        return modelFile
    }

    // אם הקובץ אינו קיים, נוריד אותו מהרשת ברקע
    withContext(Dispatchers.IO) {
        URL(modelUrl).openStream().use { input ->
            modelFile.outputStream().use { output ->
                input.copyTo(output)
            }
        }
    }
    
    return modelFile
}
