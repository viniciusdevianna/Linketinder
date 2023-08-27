package model

class CPF {
    String number

    CPF(String number) {
        setNumber(number)
    }

    static Boolean validateCPF(String number) {
        if (!number.isNumber() || number.length() != 11) {
            return false
        }

        List digits = []
        for (digit in number) {
            digits << (digit as Integer)
        }
        if (digits.every { it == digits[0]}) {
            return false
        }

        Integer validateRangeUpperLimit = 10

        for (i in 0..1) {
            Range validateFactors = (validateRangeUpperLimit + i)..2
            List digitsMultiplied = []
            digits[0..<(validateRangeUpperLimit + i - 1)].eachWithIndex {
                digit, index -> digitsMultiplied << digit * validateFactors[index]
            }

            Integer validationDigitSum = digitsMultiplied.sum() as Integer
            Integer checkModule = validationDigitSum % 11
            Integer validationDigit = checkModule < 2 ? 0 : 11 - checkModule

            if (validationDigit != digits[validateRangeUpperLimit + i - 1]) {
                return false
            }
        }

        return true
    }

    void setNumber(String number) {
        if (!validateCPF(number)) {
            throw new IllegalArgumentException("CPF inválido")
        }
        this.number = number
    }

    @Override
    String toString() {
        return "${number[0..2]}.${number[3..5]}.${number[6..8]} - ${number[9,10]}"
    }
}
