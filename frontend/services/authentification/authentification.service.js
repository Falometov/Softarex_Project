export default class {

  constructor($resource, DOMAIN_URL, CLIENT_ID, CLIENT_SECRET, LocalStorageService) {
    'ngInject';

    let encoded = btoa(`${CLIENT_ID}:${CLIENT_SECRET}`);
    this.LocalStorageService = LocalStorageService;

    return $resource(DOMAIN_URL + '/oauth/token', {},
    {
      login: {
        method: 'POST',
        params: {
          username: '@username',
          password: '@password',
          grant_type: 'password'
        },
        headers: {
          Authorization: `Basic ${encoded}`,
          'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        }
      }
    });
  }

}