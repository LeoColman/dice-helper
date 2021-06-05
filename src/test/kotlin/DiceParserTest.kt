/*
 * Copyright 2021 Leonardo Colman Lopes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.colman.dicehelper

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.positiveInts
import io.kotest.property.checkAll

class DiceParserTest : ShouldSpec({

    should("Parse simple dice notation assuming a single dice") {
        Arb.positiveInts(max = 1000).checkAll {
            "d$it".dice() shouldBe listOf(RandomDice(1, it))
        }
    }

    should("Parse notation for multiple dice") {
        Arb.positiveInts(max = 1000).checkAll {
            "${it}d${it + 1}".dice() shouldBe listOf(RandomDice(it, it + 1))
        }
    }

    should("Parse sum of multiple dice") {
        "4d5 + 2d6 + d8 + 1d9".dice() shouldBe listOf(
            RandomDice(4, 5),
            RandomDice(2, 6),
            RandomDice(1, 8),
            RandomDice(1, 9)
        )
    }

    should("Parse a fixed value") {
        "35".dice() shouldBe listOf(FixedDice(35))
    }

    should("Parse dice with fixed values inbetween") {
        "6d9 + 12 + 14d5".dice() shouldBe listOf(
            RandomDice(6, 9),
            FixedDice(12),
            RandomDice(14, 5)
        )
    }
})
