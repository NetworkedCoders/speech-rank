(function () {
    'use strict';

    angular.module('speechRank').controller('ApplicationController', function( $scope ) {
        console.log('ApplicationController loaded..');
	    $scope.user = {
		    username: 'Ola Kunysz',
		    userId: 123
	    };
    });
})();
