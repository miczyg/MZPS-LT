/**
    * Created on 2017-05-14.
    */

angular.module('mzpsApp').controller('CategoryController', ['$scope', function ($scope) {
    var ctrl = this;
    ctrl.category = '';
    ctrl.strictFilter = true;

    ctrl.selectCategory = function(category) {
        ctrl.category = category;
        if(ctrl.category === 'Wszystkie'){
            ctrl.strictFilter = false;
            ctrl.category='';
        }
        else ctrl.strictFilter = true;
    };

    ctrl.isSelected = function(category) {
        return (ctrl.category === category);
    };

    ctrl.getCategory = function(){
        return ctrl.category;
    };

    ctrl.isStrict = function () {
        return ctrl.strictFilter;
    };

}]);