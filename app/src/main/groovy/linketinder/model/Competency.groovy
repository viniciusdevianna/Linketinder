package linketinder.model

class Competency {
    Integer idCompetency
    String language

    @Override
    String toString() {
        return this.language
    }
}
