
<html>
<body>


%{--<g:each in = "${[1,2,3]}" var = "num">--}%
%{--    <p> Number ${num} </p>--}%
%{--</g:each>--}%


%{--setov variable enq define anum  value-ov arjeq tali   while-ov iterate enq aneum  test ov stugvum payman@--}%

%{--<g:while test="${val < 5 }">--}%
%{--    <p>tvi arjeq@  ${val++}</p>--}%
%{--</g:while>--}%


%{--findAll-ov iterate enq anum booksi vra  expr stugvuma  expression@  voronq en hamapatasxanum  findAll  uses groovy expression to filter based on specific atribute values or conditions--}%
%{--Stephen King's Books:--}%
%{--<g:findAll in="${books}" expr="it.author == 'Stephen King'">--}%
%{--    <p>Title: ${it.title}</p>--}%
%{--</g:findAll>--}%


<g:render template="templateBooks"/>



%{--grep tagov   we do the same job  as the above filtering  usus to make type checking  to filter based on specific properties or conditions of the objects in the collection  use this to filter item based on their types--}%

%{--<g:grep in="${books}" filter="NonFictionBooks.class">--}%
%{--    <p> Title: ${it.title} </p>>--}%
%{--</g:grep>--}%



%{--LINKS--}%

%{--<g:link uri="https://www.google.com/">Google</g:link>--}%

<!--  ete viewri mi texic myusi view etam +controller -->
%{--<g:link action="index" controller="TekEvent">Google</g:link>--}%


</body>
</html>