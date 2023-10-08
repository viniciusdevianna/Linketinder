package linketinder.model.util

class CPF implements RegisterNumber{
    String number
    static final NUMBER_OF_DIGITS = 11
    static final LIST_OF_VALIDATION_FACTORS = 2..10 as List

    void setNumber(String number) {
        if (!validateRegisterNumber(number, NUMBER_OF_DIGITS, LIST_OF_VALIDATION_FACTORS)) {
            throw new IllegalArgumentException("CPF inválido")
        }
        this.number = number
    }

    @Override
    String toString() {
        return "${number[0..2]}.${number[3..5]}.${number[6..8]} - ${number[9,10]}"
    }
}
