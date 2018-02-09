/*
 * Copyright (c) 2018. Oath.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do
 * so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN
 * AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.aol.mobile.sdk.apitracker.tasks

import com.aol.mobile.sdk.apitracker.dto.asTypeDescriptorList
import com.aol.mobile.sdk.apitracker.utils.ChangeAggregator
import com.aol.mobile.sdk.apitracker.utils.Markdown
import com.aol.mobile.sdk.apitracker.utils.ensureParentExists
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import java.io.File

open class ApiCompareTask : DefaultTask() {
    @InputFile
    lateinit var oldManifestFile: File
    @InputFile
    lateinit var newManifestFile: File
    @Input
    var implicitNamespaces = listOf("java.lang")
    @OutputFile
    lateinit var changeReportFile: File

    @Suppress("unused")
    @TaskAction
    fun comparePublicApi() {
        val newManifest = newManifestFile.readText().asTypeDescriptorList()
        val oldManifest = oldManifestFile.readText().asTypeDescriptorList()
        val changeReport = ChangeAggregator.process(oldManifest, newManifest)

        changeReportFile.ensureParentExists()
        changeReportFile.writeText(Markdown.render(implicitNamespaces, changeReport))
    }
}
