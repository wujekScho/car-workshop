$(document).ready(function () {
    var buttons = $(".showServices");
    var addWorkerBtn = $("#addWorkerBtn");
    buttons.on('click', function (ev) {
        var id = $(this).data("workerid");
        //todo tu skończyłęm, dodać rozwijaną tabelką ze zlecaeniami pracownika
        ev.preventDefault();
    })
});