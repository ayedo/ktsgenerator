package ch.ayedo.ktsgenerator

import ch.ayedo.ktsgenerator.TypeScriptGeneratorTask
import org.gradle.testfixtures.ProjectBuilder
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class GradleApplyTest {

    @Test
    fun addPluginToProject() {

        val project = ProjectBuilder.builder().build()

        project.pluginManager.apply("ch.ayedo.ktsgenerator")

        val taskLookup = project.task(hashMapOf("type" to TypeScriptGeneratorTask::class.java), "generateTypescriptDefinitions")

        assertTrue(taskLookup is TypeScriptGeneratorTask)

    }

}
