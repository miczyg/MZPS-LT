/**
    * Created on 2017-04-24.
    */
angular.module('reportsApp').controller('NavbarController', function($scope) {
    var vm = this;
    vm.isLoggedIn = false;
    vm.username = "";

    $scope.$on('login', function () {
        vm.isLoggedIn = true;
        // vm.username = UserService.getUsername();
    });

    $scope.$on('logout', function () {
        vm.isLoggedIn = false;
        vm.username = "";
    });

    vm.logout = function () {
        // UserService.logout();
    }

});