<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'book.label', default: 'Book')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>

    <g:javascript>
      $(document).ready(function() {
          $('#dt').DataTable({
              sScrollY: "75%",
              sScrollX: "100%",
              bProcessing: true,
              bServerSide: true,
              sAjaxSource: "/TekDays/tekEvent/revision/${tekEventId}",
              bJQueryUI: false,
              bAutoWidth: false,
              sPaginationType: "full_numbers",
              aLengthMenu: [[5, 10, 25, 50, 100, 200], [5, 10, 25, 50, 100, 200]],
              iDisplayLength: 5,
              order: [],
              stateSave: true,
              aoColumnDefs: [
                  { "orderable": true, "bSearchable": true, "targets": [0, 1, 2, 3, 4, 5] },
                  {
                      createdCell: function (td, cellData, rowData, row, col) {
                          $(td).attr('style', 'color:red;');
                      },
                      aTargets: [0]
                  },
                  {
                      createdCell: function (td, cellData, rowData, row, col) {
                          $(td).attr('style', 'text-align: center;');
                      },
                      aTargets: [1]
                  },
                  {
                      createdCell: function (td, cellData, rowData, row, col) {
                          $(td).attr('style', 'text-align: center;');
                      },
                      aTargets: [2]
                  },
                  {
                      createdCell: function (td, cellData, rowData, row, col) {
                          $(td).attr('style', 'text-align: center;');
                      },
                      aTargets: [3]
                  },
                  {
                      createdCell: function (td, cellData, rowData, row, col) {
                          $(td).attr('style', 'text-align: center;');
                      },
                      aTargets: [4]
                  },
                  {
                      createdCell: function (td, cellData, rowData, row, col) {
                          $(td).attr('style', 'text-align: center;');
                      },
                      aTargets: [5]
                  }
              ]
          });
      });
    </g:javascript>

    <style>
    .button-container {
        display: flex;
        justify-content: flex-start; /* Align the button to the left */
        margin-bottom: 20px; /* Adds space between button and table */
    }
    </style>

</head>

<body>

<!-- Flexbox container for left-aligned button -->
<div class="button-container">
    <g:link controller="tekEvent" action="index">
        <button type="button">TekEvent List</button>
    </g:link>
</div>

<!-- Table -->
<table class="display compact" id="dt">
    <thead>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Description</th>
        <th>City</th>
        <th>ChangedBy</th>
        <th>TimeStamp</th>
    </tr>
    </thead>
    <tbody></tbody>
</table>
</body>
</html>
