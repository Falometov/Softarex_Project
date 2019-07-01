export default class {

  constructor($resource, DOMAIN_URL, LocalStorageService) {
    'ngInject';

    this.LocalStorageService = LocalStorageService;

    return $resource(DOMAIN_URL + '/users/:id',
    {
      id: '@id'
    },
    {
      create: {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        }
      },
      findCertificates: {
        method: 'GET',
        url: DOMAIN_URL + '/users/:id/certificates',
        headers: {
          Authorization: () => `Bearer ${this.LocalStorageService.getAccessToken()}`,
          'Content-Type': 'application/json'
        }
      }
    });
  }
}