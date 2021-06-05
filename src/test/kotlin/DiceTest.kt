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

import br.com.colman.dicehelper.Dice.DiceRollResult
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

class DiceTest : ShouldSpec({

    should("Roll all possible values") {
        List(10_000) {
            "d100".dice().single().roll()
        }.flatten().distinct().sorted() shouldBe (1..100).toList()
    }

    should("Roll the same fixed value always") {
        List(10_000) { "20".dice().single().roll() }.flatten() shouldBe List(10_000) { 20 }
    }

    should("Roll all dice from a group") {
        "25d100".dice().single().roll() shouldHaveSize 25
    }

    should("Roll all dice from a notation") {
        "25d1 + 12d1".dice().rollAll() shouldBe listOf(
            DiceRollResult(RandomDice(25, 1), List(25) { 1 }),
            DiceRollResult(RandomDice(12, 1), List(12) { 1 })
        )
    }
})
