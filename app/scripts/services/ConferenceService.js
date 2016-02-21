(function () {
    'use strict';

    angular.module('speechRank').factory('ConferenceService', function ( $http, $q, REST_END_POINT ) {
        var conferenceService = {};

        conferenceService.getConferences = function() {
            return $http({
                method: 'GET',
                url: REST_END_POINT + '/api/conferences'
            });
        };

        conferenceService.getConference = function(conferenceId) {
            return $http({
                method: 'GET',
                url: REST_END_POINT + '/api/conference/'+conferenceId
            });
        };

	    conferenceService.addComment = function(presentationId, userId, comment, username) {
		    return $http({
			    method: 'POST',
			    data: {comment: comment,
				        userId: userId,
			            presentationId: presentationId,
			            username: username},
			    url: REST_END_POINT + '/api/comment/'
		    });
	    };

	    conferenceService.addRating = function(presentationId, userId, rating) {
		    return $http({
			    method: 'POST',
			    data: {rate: rating,
				    presentationId: presentationId,
			        userId: userId},
			    url: REST_END_POINT + '/api/rating/'
		    });
	    };

        return conferenceService;
    });
})();
