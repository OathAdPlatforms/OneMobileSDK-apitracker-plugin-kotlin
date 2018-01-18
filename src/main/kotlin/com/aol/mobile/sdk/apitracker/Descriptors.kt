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

package com.aol.mobile.sdk.apitracker

data class TypeDescriptor(val modifiers: Collection<String>,
                          val name: String,
                          val fields: Set<VariableDescriptor>,
                          val methods: Set<MethodDescriptor>)

data class VariableDescriptor(val modifiers: Collection<String>,
                              val name: String,
                              val type: String)

data class MethodDescriptor(val modifiers: Collection<String>,
                            val name: String,
                            val returnType: String,
                            val params: Set<VariableDescriptor>)

data class UniversalDescriptor(val modifiers: List<String>,
                               val returnType: String?,
                               val fullname: String,
                               val kind: Kind,
                               val params: List<String>)

enum class Kind {
    TYPE, FIELD, METHOD
}
