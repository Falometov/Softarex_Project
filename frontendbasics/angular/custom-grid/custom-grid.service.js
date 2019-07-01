"use strict";

angular.module("gridModule").service("customGridService", function ($http) {
    this.retrieveData = () => $http.get("custom-grid/frameworks.json");
});