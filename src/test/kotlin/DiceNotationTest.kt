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

class DiceNotationTest : ShouldSpec({

    should("Sum two dice correctly") {
        "d1 + d1".diceNotation().roll().total shouldBe 2
        "12d1 + 4d1".diceNotation().roll().total shouldBe 16
    }

    should("Sum multiple dice correctly") {
        "d1 + d1 + d1 + d1".diceNotation().roll().total shouldBe 4
        "12d1 + 14d1 + 25d1".diceNotation().roll().total shouldBe 51
    }

    should("Sum dice with constant values") {
        "d1 + 17 + d1".diceNotation().roll().total shouldBe 19
    }

    should("Subtract dice") {
        "d1 - d1".diceNotation().roll().total shouldBe 0
    }

    should("Subtract dice with constant values") {
        "d1 - 1".diceNotation().roll().total shouldBe 0
    }

    should("Subtract multiple dice") {
        "d1 - d1 - d1 - 10".diceNotation().roll().total shouldBe -11
    }

})
