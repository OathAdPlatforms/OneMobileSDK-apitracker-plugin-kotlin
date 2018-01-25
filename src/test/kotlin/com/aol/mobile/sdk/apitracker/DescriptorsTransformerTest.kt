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

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class DescriptorsTransformerTest {
    private lateinit var typeDescriptors: List<TypeDescriptor>

    @Before
    fun before() {
        val json = """
            [
              {
                "modifiers": [
                  "public",
                  "final"
                ],
                "name": "com.aol.mobile.sdk.player.OneSDK",
                "fields": [
                  {
                    "modifiers": [
                      "public",
                      "final"
                    ],
                    "name": "field1",
                    "type": "int"
                  }
                ],
                "methods": [
                  {
                    "modifiers": [
                      "public"
                    ],
                    "name": "method1",
                    "returnType": "void",
                    "params": [
                      {
                        "modifiers": [],
                        "type": "android.content.Context"
                      }
                    ]
                  }
                ]
              },
              {
                "modifiers": [
                  "public",
                  "final"
                ],
                "name": "com.aol.mobile.sdk.player.TwoSDK",
                "fields": [
                  {
                    "modifiers": [
                      "public",
                      "final"
                    ],
                    "name": "field2",
                    "type": "int"
                  }
                ],
                "methods": [
                  {
                    "modifiers": [
                      "public"
                    ],
                    "name": "method2",
                    "returnType": "void",
                    "params": [
                      {
                        "modifiers": [],
                        "type": "android.content.Context"
                      }
                    ]
                  }
                ]
              }
            ]
            """

        typeDescriptors = deserializeTypeDescriptors(json)
    }

    @Test
    fun testTransformationOfTypeDescriptorsToUniversalDescriptors() {
        val universalDescriptors: List<UniversalDescriptor> = typeDescriptors.map { it.toUniversalDescriptors() }.flatten()

        assertThat(universalDescriptors.size).isEqualTo(6)

        assertThat(universalDescriptors[0].fullname).isEqualTo("com.aol.mobile.sdk.player.OneSDK")
        assertThat(universalDescriptors[0].kind).isEqualTo(Kind.TYPE)

        assertThat(universalDescriptors[1].fullname).isEqualTo("com.aol.mobile.sdk.player.OneSDK.field1")
        assertThat(universalDescriptors[1].kind).isEqualTo(Kind.FIELD)
        assertThat(universalDescriptors[1].returnType).isEqualTo("int")

        assertThat(universalDescriptors[5].fullname).isEqualTo("com.aol.mobile.sdk.player.TwoSDK.method2")
        assertThat(universalDescriptors[5].kind).isEqualTo(Kind.METHOD)
        assertThat(universalDescriptors[5].returnType).isEqualTo("void")
    }
}