package tekdays

class HelloController {

    def barev() {

        def books = [
                [title: 'The Shining', author: 'Stephen King'],
                [title: 'It', author: 'Stephen King'],
                [title: 'The Hobbit', author: 'J.R.R. Tolkien'],
                [title: 'Carrie', author: 'Stephen King']
        ]
        [books: books]
    }
}
