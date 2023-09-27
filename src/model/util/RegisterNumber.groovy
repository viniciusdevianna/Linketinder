package model.util

trait RegisterNumber {
    Boolean validateRegisterNumber(String number, Integer numberLength, List<Integer> validateFactors) {
        if (!number.isNumber() || number.size() != numberLength) {
            return false
        }

        List digits = []
        for (digit in number) {
            digits << (digit as Integer)
        }
        if (digits.every { it == digits[0]}) {
            return false
        }

        def factors = new ArrayList(validateFactors)
        for (i in 0..1) {
            List digitsMultiplied = []
            digits[(factors.size() - 1)..0].eachWithIndex {
                digit, index -> digitsMultiplied << digit * factors[index]
            }

            Integer validationDigitSum = digitsMultiplied.sum() as Integer
            Integer checkModule = validationDigitSum % 11
            Integer validationDigit = checkModule < 2 ? 0 : 11 - checkModule

            if (validationDigit != digits[factors.size()]) {
                return false
            }
            def factorToAdd = factors.last() + 1
            factors << factorToAdd
        }
        return true
    }
}
