export default class {

  constructor(LocalStorageService, $resource, DOMAIN_URL) {
    'ngInject';

    this.LocalStorageService = LocalStorageService;

    return $resource(DOMAIN_URL + '/certificates/:id', 
    {
      id: '@id'
    },
    {
      findAll: {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json'
        }
      },
      create: {
        method: 'POST',
        headers: {
          Authorization: () => `Bearer ${this.LocalStorageService.getAccessToken()}`,
          'Content-Type': 'application/json'
        }
      },
      update: {
        method: 'PUT',
        headers: {
          Authorization: () => `Bearer ${this.LocalStorageService.getAccessToken()}`,
          'Content-Type': 'application/json'
        }
      },
      remove: {
        method: 'DELETE',
        headers: {
          Authorization: () => `Bearer ${this.LocalStorageService.getAccessToken()}`,
          'Content-Type': 'application/json'
        }
      }
    });

  }

}