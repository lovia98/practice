(function($) {
    var pluginName = 'pagination';

    // These are the plugin defaults values
    var defaults = {
        pageNumber : 1,
        pageSize : 10,
        totalRecords : 0,
        pageParam : {},
        pageUrl : '',
        pageCallback : null
    };

    var Pagination = function(element, options) {

        return true;
    };

    $.fn.pagination = function(options) {
        if(!$.data(this, pluginName)){
            $.data(this, pluginName, new Pagination(this, options));
        }
    };

})(jQuery);

