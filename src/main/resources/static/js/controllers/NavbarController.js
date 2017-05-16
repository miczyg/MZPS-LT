/**
    * Created on 2017-04-24.
    */
angular.module('mzpsApp').controller('NavbarController', function($scope) {
    $(".nav a").on("click", function(){
        $(".nav").find(".active").removeClass("active");
        $(this).parent().addClass("active");
    });

});