(function () {
    'use strict';

    angular.module('speechRank').controller('ConferenceController', function( $scope, $stateParams, ConferenceService ) {
        console.log('ConferenceController loaded..');

        var conferenceId = $stateParams.id;

        $scope.getConference = function(conferenceId) {
            ConferenceService.getConference(conferenceId)
                .then(function(response) {
                    console.log('response', response);
                    $scope.conference = response.data;
                }).catch(function(error) {
                    console.error('error', error);
                });
        };

        $scope.getConference(conferenceId);
    });
})();
