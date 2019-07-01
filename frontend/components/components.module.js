import certificates from './certificates/certificates.module';
import Authentification from './authentification/authentification.module';
import Signup from './signup/signup.module';
import Addcertificate from './addcertificate/addcertificate.module';
import Editcertificate from './editcertificate/editcertificate.module';

const module = angular.module('frontend.components', [

  certificates,
  Authentification,
  Signup,
  Addcertificate,
  Editcertificate

]);

export default module.name;
