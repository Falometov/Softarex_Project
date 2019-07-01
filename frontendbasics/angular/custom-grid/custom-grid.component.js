"use strict";

angular.module("gridModule").component("customGrid", {
    templateUrl: "custom-grid/custom-grid.template.html",
    controller: ["customGridService", function (customGridService) {
        customGridService.retrieveData().then(response => {
            this.columns = response.data.columns;
            this.data = response.data.frameworks;
        });
        this.reset = () => this.filterValue = "";
    }]
});