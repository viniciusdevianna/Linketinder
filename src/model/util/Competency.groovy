package model.util

class Competency {
    Integer id_competency
    String language

    @Override
    String toString() {
        return this.language
    }
}
