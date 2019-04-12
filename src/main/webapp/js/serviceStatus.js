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
    fields.attr('required', false);
    console.log(status.attr('value'));
    switch (status.attr('value')) {
        case 'accepted':
            problemDescription.attr('required', true);
            vehicleId.attr('required', true);
            break;
        case 'repairCostApproved':
            problemDescription.attr('required', true);
            vehicleId.attr('required', true);
            plannedRepairDate.attr('required', true);
            repairCost.attr('required', true);
            break;
        case 'underRepair':
            problemDescription.attr('required', true);
            vehicleId.attr('required', true);
            plannedRepairDate.attr('required', true);
            repairCost.attr('required', true);
            workerId.attr('required', true);
            break;
        case 'readyToPickup':
            problemDescription.attr('required', true);
            vehicleId.attr('required', true);
            plannedRepairDate.attr('required', true);
            repairCost.attr('required', true);
            workerId.attr('required', true);
            repairDate.attr('required', true);
            repairDescription.attr('required', true);
            partsCost.attr('required', true);
            workHours.attr('required', true);
            break;
        case 'completed':
            problemDescription.attr('required', true);
            vehicleId.attr('required', true);
            plannedRepairDate.attr('required', true);
            repairCost.attr('required', true);
            workerId.attr('required', true);
            repairDate.attr('required', true);
            repairDescription.attr('required', true);
            partsCost.attr('required', true);
            workHours.attr('required', true);
            break;
    }

    checkFields();

    button.on('click', function (ev) {
        fields.each(function (index, element) {
            if ($(element).is(':required')) {
                if (!$(element).val() || $(element).val() == 0) {
                    ev.preventDefault();
                }
            }
        });
    });

    function checkFields() {
        fields.each(function (index, element) {
            var div = $('<div>');
            div.addClass('required');
            div.text('Pole wymagane');
            var parentElem = $(element).parent();
            parentElem.after(div);
            div.show();
            if (!$(element).is(':required')) {
                div.hide();
            } else if ($(element).val() && $(element).is(':required') && $(element).val() != 0) {
                div.hide();
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
})
;