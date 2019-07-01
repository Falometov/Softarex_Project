"use strict";

angular.module("gridModule").filter("filterByName", function () {
    return (frameworksList, keyName) => {
        return !keyName ? frameworksList : frameworksList.filter(
            framework => framework.name.toUpperCase() === keyName.toUpperCase());
    }
});