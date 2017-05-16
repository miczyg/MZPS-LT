/**
    * Created on 2017-05-14.
    */

angular.module('mzpsApp').controller('CategoryController', ['$scope', function ($scope) {
    var ctrl = this;
    ctrl.categoryFilter = '';
    ctrl.strictFilter = true;

    ctrl.selectCategory = function(category) {
        ctrl.categoryFilter = category;
        if(ctrl.categoryFilter === 'Wszystkie'){
            ctrl.strictFilter = false;
            ctrl.categoryFilter='';
        }
        else ctrl.strictFilter = true;
    };

    ctrl.isSelected = function(category) {
        return (ctrl.categoryFilter === category);
    };

    ctrl.getCategory = function(){
        return ctrl.categoryFilter;
    };

    ctrl.isStrict = function () {
        return ctrl.strictFilter;
    };

}]);