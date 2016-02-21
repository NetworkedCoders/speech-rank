(function () {
    'use strict';

    angular.module('speechRank').factory('ConferenceService', function ( $http, $q, REST_END_POINT ) {
        var conferenceService = {};

        conferenceService.getConferences = function() {
            return $http({
                method: 'GET',
                url: REST_END_POINT + '/conferences'
            });
        };

        conferenceService.getConference = function(conferenceId) {
            return $http({
                method: 'GET',
                data: conferenceId,
                url: REST_END_POINT + '/conference'
            });
        };

        return conferenceService;
    });
})();