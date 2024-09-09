package sponsor

class TekSponsor {
    String name
    String website
    String description
    byte[] logo

    String toString(){
        name
    }

    static constraints = {
        name blank: false
        website blank: false, url: true
        description nullable: true, maxSize: 5000
        logo nullable: true, maxSize: 1000000
    }
}