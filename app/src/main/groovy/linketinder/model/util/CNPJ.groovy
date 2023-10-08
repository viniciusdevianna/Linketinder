package linketinder.model.util

class CNPJ implements RegisterNumber{
    String number
    static final NUMBER_OF_DIGITS = 14
    static final LIST_OF_VALIDATION_FACTORS = (2..9 as List) + (2..5 as List)

    void setNumber(String number) {
        if (!validateRegisterNumber(number, NUMBER_OF_DIGITS, LIST_OF_VALIDATION_FACTORS)) {
            throw new IllegalArgumentException("CNPJ inválido")
        }
        this.number = number
    }

    @Override
    String toString() {
        return "${number[0..7]}/${number[8..11]} - ${number[12,13]}"
    }
}
