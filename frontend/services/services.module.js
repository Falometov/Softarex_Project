import CertificatesService from './certificates/certificates.service';
import AuthentificationService from './authentification/authentification.service';
import LocalStorageService from './localstorage/localstorage.service';
import UserService from './user/user.service';
import PurchaseService from './purchase/purchase.service';

const module = angular.module('frontend.services', []);

module.service('CertificatesService', CertificatesService);
module.service('AuthentificationService', AuthentificationService);
module.service('LocalStorageService', LocalStorageService);
module.service('UserService', UserService);
module.service('PurchaseService', PurchaseService);

export default module.name;
