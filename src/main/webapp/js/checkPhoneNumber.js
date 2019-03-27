addEventListener('DOMContentLoaded', function (evt) {
    var warning = document.querySelector('#phoneWarning');
    var input = warning.previousElementSibling.previousElementSibling.firstElementChild;
    var button = document.querySelector('#btn1');
    input.addEventListener('keyup', function (evt1) {
        if (input.value.length > 0 && numberFits(input.value) === false) {
            warning.style.display = 'block';
        } else {
            warning.style.display = 'none';
        }
    });
    button.addEventListener('click', function (evt2) {
        console.log(warning.style.display);
        if (warning.style.display === 'block') {
            evt2.preventDefault();
        } else {
            this.click();
        }
    });

    function numberFits(phoneNumber) {
        var regex = new RegExp('^[0-9+\\- ]+$');
        return regex.test(phoneNumber);
    }
});