<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main"/>
<title>TekDays - The Community is the Conference!</title>

    <style>
    .buttons {
        display: block;
        text-align: right;
        margin-right: 111px; /* Move buttons a little to the left */
    }
    p {
        padding-left: 30px; /* Move text slightly away from the right */
    }
    h3 {
        padding-left: 30px
    }
    </style>

</head>
<body>
<div id="welcome">
<br />
<h3>Welcome to TekDays.com</h3>
    <br>
<p>TekDays.com is a site dedicated to assisting individuals and
communities to organize technology conferences. To bring great
minds with common interests and passions together for the good
of greater geekdom!</p>
</div>
<br>
<div class="homeCell">
<h3>Find a Tek Event</h3>
    <br>
<p> See if there's a technical event in the works that strikes your
fancy. If there is, you can volunteer to help or just let the
organizers know that you'd be interested in attending.
Everybody has a role to play.</p>
<span class="buttons" >
<g:link controller="tekEvent" action="index">Find a Tek Event</g:link>
</span>
</div>
<br>
<div class="homeCell">
<h3>Organize a Tek Event</h3>
    <br>
<p>If you don't see anything that suits your interest and location,
then why not get the ball rolling. It's easy to get started and
there may be others out there ready to get behind you to make it
happen.</p>
<span class="buttons" >
<g:link controller="tekEvent" action="create"> Organize a Tek Event</g:link>
</span>
</div>
<br>
<div class="homeCell">
    <h3>Sponsor a Tek Event</h3>
    <br>
    <p>If you are part of a business or organization that is involved in
    technology then sponsoring a tek event would be a great way to
    let the community know that you're there and you're involved.</p>
    <span class="buttons" >
        <g:link controller="tekSponsor" action="create">Sponsor a Tek Event</g:link>
    </span>
</div>
</body>
</html>