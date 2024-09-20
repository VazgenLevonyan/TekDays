<g:each in="${books}" var="book">
    <div class="book" id="${book?.id}">
        <div>Title: ${book?.title}</div>
        <div>Author: ${book?.name}</div>
    </div>
</g:each>
