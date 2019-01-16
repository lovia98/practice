$(document).ready(function () {

    //숫자만 입력 받기
    $(document).on('keyup', '.onlyNumber', inputUts.removeNonNumber);

    //최대 글자표시
    $(document).on('keyup', 'input:text', inputUts.checkLength);

});

var inputUts = (function () {

    let _this;
    let numberReg = /[^0-9]/g;

    _this = {};

    _this.removeNonNumber = function () {
        let text = $(this).val();
        $(this).val(text.replace(numberReg, ''));
    };

    _this.checkLength = function (event) {

        var max = $(this).attr('max');
        if (typeof max == 'undefined') {
            return false;
        }

        if (!overLimit($(this), max))
            event.preventDefault();
    };

    function overLimit(el, max) {
        if (max > getByte(el)) {
            return true;
        } else {
            return false;
        }
    }

    function getByte(el) {
        var codeByte = 0;
        for (var idx = 0; idx < el.val().length; idx++) {
            var oneChar = escape(el.val().charAt(idx));
            if (oneChar.length === 1) {
                codeByte++;
            } else if (oneChar.indexOf("%u") !== -1) {
                codeByte += 2;
            } else if (oneChar.indexOf("%") !== -1) {
                codeByte++;
            }
        }
        return codeByte;
    }

    return _this;

})();

//input enter key event callback
$.fn.enterKey = function (fn) {

    if (this[0].tagName != 'INPUT')
        return false;

    if (this[0].type != 'text')
        return false;

    $(this[0], document).on('keyup', function (event) {
        if (event.keyCode == 13) {
            fn();
        }
    });
};
