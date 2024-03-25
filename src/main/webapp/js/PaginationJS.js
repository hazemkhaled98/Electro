$(document).ready(function() {
    var pageSize = 5; // number of rows to show per page
    var currentPage = 0;
    var rows = $('#tbody tr');
    var numberOfPages = Math.ceil(rows.length / pageSize);

    function showPage(pageNumber) {
        var start = pageNumber * pageSize;
        var end = start + pageSize;
        rows.hide().slice(start, end).show();
    }

    function setupPagination() {
        $('#pagination').empty();
        var paginationDiv = $('<div>').addClass('pagination-row');
        for (var i = 0; i < numberOfPages; i++) {
            paginationDiv.append('<a href="#" class="page-link">' + (i+1) + '</a> ');
        }
        $('#pagination').append(paginationDiv);
        $('.page-link').click(function() {
            currentPage = $(this).text() - 1;
            showPage(currentPage);
        });
    }

    showPage(currentPage);
    setupPagination();
});