$(document).ready(function () {

    var status = $("[name='status']");
    var fields = $('.field');
    var button = $('button');
    var vehicleId = $("[name='vehicleId']");
    var workerId = $("[name='workerId']");
    var problemDescription = $("[name='problemDescription']");
    var repairDescription = $("[name='repairDescription']");
    var plannedRepairDate = $("[name='plannedRepairDate']");
    var repairDate = $("[name='repairDate']");
    var repairCost = $("[name='repairCost']");
    var partsCost = $("[name='partsCost']");
    var workHours = $("[name='workHours']");

    repairCost.attr('required', true);
    fields.attr('required', true);
    checkFields();
    button.on('click', function (ev) {
        //todo napisać event który nie pozwoli na wysłanie formularza podczas gdy wyświetlane są divy z wymaganym polem
    });

    function checkFields() {
        fields.each(function (index, element) {
            var div = $('<div>');
            div.addClass('required');
            div.text('Pole wymagane');
            var parentElem = $(element).parent();
            parentElem.after(div);
            if ($(element).is(':required')) {
                if (!$(element).val() || $(element).val() == 0) {
                    div.show();
                } else {
                    div.hide();
                }
            }
            $(element).on('change keyup paste', function (ev) {
                if ($(element).is(':required')) {
                    if (!$(element).val() || $(element).val() == 0) {
                        div.show();
                    } else {
                        div.hide();
                    }
                }
            });
        })
    }


});