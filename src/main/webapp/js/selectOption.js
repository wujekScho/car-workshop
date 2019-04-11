$(document).ready(function () {
    var rows = $('tr');
    rows.each(function (trIndex, trElement) {
        if (trIndex !== 0) {
            var options = $(trElement).find('select').find('option');
            var status = $(trElement).find("[name='status']");
            var button = $(trElement).find('button');
            options.each(function (index, element) {
                if ($(element).attr('value') === $(status).attr('value')) {
                    $(element).attr('selected', 'true');
                    button.on('click', function (ev) {
                        if ($(element).is(':selected')) {
                            ev.preventDefault();
                        }
                    })
                }
            })

        }
    });
});