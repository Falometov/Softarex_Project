export default class {

  constructor($resource, DOMAIN_URL, LocalStorageService) {
    'ngInject';

    this.LocalStorageService = LocalStorageService;

    return $resource(DOMAIN_URL + '/purchases', {},
    {
      create: {
        method: 'POST',
        headers: {
          Authorization: () => `Bearer ${this.LocalStorageService.getAccessToken()}`,
          'Content-Type': 'application/json'
        }
      }
    });
  }
}